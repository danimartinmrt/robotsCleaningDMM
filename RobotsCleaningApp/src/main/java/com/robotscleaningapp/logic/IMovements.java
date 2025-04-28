package com.robotscleaningapp.logic;

import com.robotscleaningapp.dto.RobotDto;

/**
 * Interface for defining movement operations for robots.
 * 
 * This interface provides methods to handle robot movements, including turning left,
 * turning right, and moving forward a specified number of grid points.
 */
public interface IMovements {
	
	/**
	 * Turns the robot left based on its current orientation.
	 * 
	 * @param currentPosition The current orientation of the robot.
	 * @return The new orientation after turning left.
	 */
	String left(String currentPosition);
    
	/**
	 * Turns the robot right based on its current orientation.
	 * 
	 * @param currentPosition The current orientation of the robot.
	 * @return The new orientation after turning right.
	 */
	String right(String currentPosition);
    
	/**
	 * Moves the robot forward a specified number of grid points based on its current orientation.
	 * 
	 * @param robot The robot to be moved.
	 * @param numberGridPoints The number of grid points to move forward.
	 * @return The updated robot after the movement.
	 */
	RobotDto move(RobotDto robot, int numberGridPoints);	
}
