package com.robotscleaningapp.service;

import java.util.List;

import com.robotscleaningapp.dto.RobotDto;
import com.robotscleaningapp.dto.SquareAreaDto;
import com.robotscleaningapp.input.IInputReader;
import com.robotscleaningapp.logic.IMovements;

public interface IRobotProcessingService {

	/**
	 * Processes the robots by capturing their initial positions and movements.
	 * 
	 * @param scanner The Scanner object for user input.
	 * @param area The list of square areas where robots can operate.
	 * @param movements The Movements object for handling robot movements.
	 * @param robotCount The number of robots to process.
	 * @return A list of RobotDto objects representing the processed robots.
	 */
    List<RobotDto> processRobots(IInputReader inputReader, List<SquareAreaDto> area, IMovements movements, int robotCount);
}
