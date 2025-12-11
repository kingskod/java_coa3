package com.voxelengine.audio;

import com.voxelengine.utils.FileUtils;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;

public class SoundManager {

    private long device;
    private long context;
    private final Map<String, Integer> soundBuffers = new HashMap<>();
    private final Map<String, Integer> sources = new HashMap<>();

    public SoundManager() {
        init();
    }

    private void init() {
        // Open default device
        String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
        device = alcOpenDevice(defaultDeviceName);
        
        int[] attributes = {0};
        context = alcCreateContext(device, attributes);
        alcMakeContextCurrent(context);

        ALCCapabilities alcCapabilities = ALC.createCapabilities(device);
        ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);

        if (!alCapabilities.OpenAL10) {
            throw new RuntimeException("Audio library not supported.");
        }
        
        // Listener default
        alListener3f(AL_POSITION, 0, 0, 0);
        alListener3f(AL_VELOCITY, 0, 0, 0);
    }

    /**
     * Loads a WAV file from resources into an OpenAL buffer.
     */
    public void loadSound(String name, String path) {
        // Load raw bytes
        ByteBuffer data = null;
        int format = -1;
        int samplerate = -1;
        
        // Custom simple WAV loader since we aren't using external Utils for audio loading
        try (InputStream is = SoundManager.class.getResourceAsStream("/assets/sounds/" + path)) {
            if (is == null) {
                System.err.println("Sound not found: " + path);
                return;
            }
            
            // Read all bytes
            byte[] bytes = is.readAllBytes();
            ByteBuffer buffer = MemoryUtil.memAlloc(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            
            // Parse WAV Header (Simple RIFF parser)
            // Offset 22: Channels (2 bytes)
            int channels = buffer.get(22) & 0xFF;
            // Offset 24: Sample Rate (4 bytes)
            samplerate = buffer.getInt(24);
            // Offset 34: Bits Per Sample (2 bytes)
            int bitsPerSample = buffer.get(34) & 0xFF;
            
            if (bitsPerSample == 8) {
                format = (channels == 1) ? AL_FORMAT_MONO8 : AL_FORMAT_STEREO8;
            } else if (bitsPerSample == 16) {
                format = (channels == 1) ? AL_FORMAT_MONO16 : AL_FORMAT_STEREO16;
            }
            
            // Offset 44: Data starts (usually, assuming standard PCM)
            // Ideally we parse chunks, but for AssetGenerator wavs, 44 is standard.
            buffer.position(44);
            data = buffer.slice();
            
            int bufferId = alGenBuffers();
            alBufferData(bufferId, format, data, samplerate);
            soundBuffers.put(name, bufferId);
            
            MemoryUtil.memFree(buffer);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play(String name) {
        if (!soundBuffers.containsKey(name)) return;
        
        int bufferId = soundBuffers.get(name);
        
        // Generate a source
        int sourceId = alGenSources();
        alSourcei(sourceId, AL_BUFFER, bufferId);
        alSourcef(sourceId, AL_GAIN, 1.0f);
        alSourcef(sourceId, AL_PITCH, 1.0f);
        alSource3f(sourceId, AL_POSITION, 0, 0, 0);
        
        alSourcePlay(sourceId);
        
        // Clean up source later? For a simple engine, we assume fire-and-forget or reuse.
        // A robust engine pools sources. Here we track simply.
        sources.put(name + "_" + System.nanoTime(), sourceId);
    }

    public void cleanup() {
        for (int source : sources.values()) {
            alDeleteSources(source);
        }
        for (int buffer : soundBuffers.values()) {
            alDeleteBuffers(buffer);
        }
        alcDestroyContext(context);
        alcCloseDevice(device);
    }
}