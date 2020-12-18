package io.tlf.graphics;

/**
 * A commonly-used power-of-two level.
 * Used for anistropic filtering, anti-aliasing, sample levels, etc...
 */
public enum PowerLevel {
    OFF(0),
    ONE(1),
    TWO(2),
    FOUR(4),
    EIGHT(8),
    SIXTEEN(16);

    private final int level;

    PowerLevel(int level) {
        this.level = level;
    }

    public int toInteger() {
        return level;
    }

}
