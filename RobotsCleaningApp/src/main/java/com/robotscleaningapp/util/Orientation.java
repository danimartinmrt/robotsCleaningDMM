package com.robotscleaningapp.util;

/**
 * Enum representing the four cardinal orientations (North, East, South, West).
 * Each orientation has a shorthand representation and a full name.
 */
public enum Orientation {

	N(Constants.NORTH),
	E(Constants.EAST),
	S(Constants.SOUTH),
    W(Constants.WEST);

    private final String fullName;

    Orientation(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    /**
	 * Converts a shorthand representation of an orientation to its full name.
	 *
	 * @param shorthand The shorthand representation (e.g., "N", "E", "S", "W").
	 * @return The full name of the orientation.
	 * @throws IllegalArgumentException if the shorthand is invalid.
	 */
    public static String fromShorthand(String shorthand) {
        for (Orientation direction : values()) {
            if (direction.name().equalsIgnoreCase(shorthand)) {
                return direction.getFullName();
            }
        }
        throw new IllegalArgumentException("Invalid direction shorthand: " + shorthand);
    }
}
