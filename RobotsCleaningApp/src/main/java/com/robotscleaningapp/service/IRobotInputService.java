package com.robotscleaningapp.service;

import java.util.List;

import com.robotscleaningapp.dto.RobotDto;
import com.robotscleaningapp.dto.SquareAreaDto;
import com.robotscleaningapp.input.IInputReader;

/**
 * Interface for capturing the initial position of robots.
 * 
 * This interface defines the contract for capturing the initial coordinates and orientation of robots.
 */
public interface IRobotInputService {
    
	/**
	 * Captures the initial position of a robot.
	 * 
	 * @param inputReader The InputReader object for user input.
	 * @param area The list of square areas where robots can operate.
	 * @param robotNumber The number of the robot being processed.
	 * @return A RobotDto object representing the captured robot position.
	 */
	RobotDto captureInitialRobotPosition(IInputReader inputReader, List<SquareAreaDto> area, int robotNumber, 
                                         List<RobotDto> robotsList);
}
