package com.voxelengine.audio;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.*;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;

public class SoundManager {

    private long device;
    private long context;
    private final Map<String, Integer> soundBuffers = new HashMap<>();
    
    // FIXED: Use a pool instead of infinite map
    private final List<Integer> sourcePool = new ArrayList<>();
    private static final int MAX_SOURCES = 32;

    public SoundManager() {
        init();
    }

    private void init() {
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
        
        // Create fixed pool
        for (int i=0; i<MAX_SOURCES; i++) {
            int source = alGenSources();
            if (alGetError() == AL_NO_ERROR) {
                sourcePool.add(source);
            }
        }
        
        alListener3f(AL_POSITION, 0, 0, 0);
        alListener3f(AL_VELOCITY, 0, 0, 0);
    }

    public void loadSound(String name, String path) {
        if (soundBuffers.containsKey(name)) return;

        try (InputStream is = SoundManager.class.getResourceAsStream("/assets/sounds/" + path)) {
            if (is == null) return;
            
            byte[] bytes = is.readAllBytes();
            ByteBuffer buffer = MemoryUtil.memAlloc(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            
            int channels = buffer.get(22) & 0xFF;
            int samplerate = buffer.getInt(24);
            int bitsPerSample = buffer.get(34) & 0xFF;
            
            int format = -1;
            if (bitsPerSample == 8) format = (channels == 1) ? AL_FORMAT_MONO8 : AL_FORMAT_STEREO8;
            else if (bitsPerSample == 16) format = (channels == 1) ? AL_FORMAT_MONO16 : AL_FORMAT_STEREO16;
            
            buffer.position(44); 
            ByteBuffer data = buffer.slice();
            
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
        
        // Find free source
        int sourceId = -1;
        for (int source : sourcePool) {
            if (alGetSourcei(source, AL_SOURCE_STATE) != AL_PLAYING) {
                sourceId = source;
                break;
            }
        }
        
        if (sourceId != -1) {
            alSourcei(sourceId, AL_BUFFER, bufferId);
            alSourcef(sourceId, AL_GAIN, 1.0f);
            alSourcef(sourceId, AL_PITCH, 1.0f);
            alSource3f(sourceId, AL_POSITION, 0, 0, 0);
            alSourcePlay(sourceId);
        }
    }

    public void cleanup() {
        for (int source : sourcePool) alDeleteSources(source);
        for (int buffer : soundBuffers.values()) alDeleteBuffers(buffer);
        alcDestroyContext(context);
        alcCloseDevice(device);
    }
}