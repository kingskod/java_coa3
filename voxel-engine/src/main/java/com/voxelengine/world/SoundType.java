package com.voxelengine.world;

/**
 * Defines the sound set for a block type.
 * Specifies the audio files to play for stepping, breaking, and placing actions.
 */
public enum SoundType {
    STONE("step.wav", "break.wav", "place.wav"),
    GRASS("step.wav", "break.wav", "place.wav"), // Placeholder: Reuse sounds for now
    WOOD("step.wav", "break.wav", "place.wav"),
    SAND("step.wav", "break.wav", "place.wav"),
    LIQUID("splash.wav", "splash.wav", "splash.wav");

    public final String stepSound;
    public final String breakSound;
    public final String placeSound;

    SoundType(String step, String breakS, String place) {
        this.stepSound = step;
        this.breakSound = breakS;
        this.placeSound = place;
    }
}