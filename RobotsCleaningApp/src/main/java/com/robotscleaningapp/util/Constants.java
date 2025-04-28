package com.robotscleaningapp.util;

/**
 * Constants class for the Robot Cleaning App.
 * 
 * This class contains various constants used throughout the application, including directions,
 * movement commands, and default values for areas and robots.
 */
public class Constants {
	
	// Orientation constants
	public static final String NORTH = "NORTH";
	public static final String EAST = "EAST";
	public static final String SOUTH = "SOUTH";
	public static final String WEST = "WEST";
	
	// Movement command constants
	public static final char LEFT = 'L';
	public static final char RIGHT = 'R';
	public static final char MOVE_FORWARD = 'M';
	
	// Default values for areas and robots
	public static final int ROBOTS_NUMBER = 2;	
	public static final int AREAS_NUMBER = 1;	
	public static final int DEFAULT_AREA_ID = 1;	
	public static final int NUMBER_GRID_POINTS = 1;
	
	// Origin coordinates for the area
	public static final int ORIGIN_CORD_X = 0;
	public static final int ORIGIN_CORD_Y = 0;
}
