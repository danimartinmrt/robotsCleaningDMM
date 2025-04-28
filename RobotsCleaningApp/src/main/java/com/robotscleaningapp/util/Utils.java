package com.robotscleaningapp.util;

import java.util.List;

import com.robotscleaningapp.dto.RobotDto;
import com.robotscleaningapp.dto.SquareAreaDto;

/**
 * Utility class for common operations in the Robot Cleaning App.
 * 
 * This class contains static methods for various utility functions used throughout the application.
 */
public class Utils {

	/**
	 * Retrieves a SquareAreaDto object by its ID from a list of areas.
	 * 
	 * @param areas The list of SquareAreaDto objects.
	 * @param id The ID of the area to retrieve.
	 * @return The SquareAreaDto object with the specified ID.
	 * @throws IllegalArgumentException if the area with the specified ID is not found.
	 */
	public static SquareAreaDto getAreaById(List<SquareAreaDto> areas, int id) {
        return areas.stream()
                    .filter(area -> area.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Area with ID " + id + " not found."));
    }
	
	/**
	 * Checks if a new robot's position will collide with any existing robots.
	 * 
	 * @param newRobot The RobotDto object representing the new robot.
	 * @param robotsList The list of existing robots.
	 * @return true if there is a collision, false otherwise.
	 */
	public static boolean willCollision(RobotDto newRobot, List<RobotDto> robotsList) {
        return robotsList.stream()
                .anyMatch(otherRobot -> otherRobot.getId() != newRobot.getId() &&
                                        otherRobot.getCordX() == newRobot.getCordX() &&
                                        otherRobot.getCordY() == newRobot.getCordY());
    }
}
