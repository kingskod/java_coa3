package com.voxelengine.world;

public enum SoundType {
    STONE("step.wav", "break.wav", "place.wav"),
    GRASS("step.wav", "break.wav", "place.wav"), // In real engine, use different files
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