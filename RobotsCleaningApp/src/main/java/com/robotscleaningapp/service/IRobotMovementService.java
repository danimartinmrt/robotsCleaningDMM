package com.robotscleaningapp.service;

import java.util.List;

import com.robotscleaningapp.dto.RobotDto;
import com.robotscleaningapp.dto.SquareAreaDto;
import com.robotscleaningapp.input.IInputReader;
import com.robotscleaningapp.logic.IMovements;

/**
 * Interface for processing robot movements.
 * 
 * This interface defines the contract for processing robot movements, handling collisions,
 * and ensuring that robots stay within bounds.
 */
public interface IRobotMovementService {
   
	/**
	 * Processes the movements of a robot based on user input.
	 * 
	 * @param scanner The Scanner object for user input.
	 * @param robot The RobotDto object representing the robot to be moved.
	 * @param area The SquareAreaDto object representing the area where the robot operates.
	 * @param movements The Movements object for handling robot movements.
	 * @param robotsList The list of robots to check for collisions.
	 * @return The updated RobotDto object after processing movements.
	 */
	RobotDto processMovements(IInputReader inputReader, RobotDto robot, SquareAreaDto area, IMovements movements, 
                              List<RobotDto> robotsList);

}
