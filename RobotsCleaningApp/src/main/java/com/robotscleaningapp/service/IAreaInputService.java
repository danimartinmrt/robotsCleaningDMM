package com.robotscleaningapp.service;

import java.util.List;
import com.robotscleaningapp.dto.SquareAreaDto;
import com.robotscleaningapp.input.IInputReader;

/**
 * Interface for capturing area data.
 * 
 * This interface defines the contract for capturing the initial coordinates of square areas
 * and validating the input data.
 */
public interface IAreaInputService {
    
	/**
	 * Captures area data from the user.
	 * 
	 * @param scanner Scanner object for reading user input.
	 * @param numberOfAreas Number of areas to capture.
	 * @return List of SquareAreaDto objects representing the captured areas.
	 */
	List<SquareAreaDto> captureAreaData(IInputReader inputReader, int numberOfAreas);
}
