package com.robotscleaningapp.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.robotscleaningapp.dto.SquareAreaDto;
import com.robotscleaningapp.input.IInputReader;
import com.robotscleaningapp.service.IAreaInputService;
import com.robotscleaningapp.util.Constants;

/**
 * Service class for capturing area data.
 * 
 * This class is responsible for capturing the initial coordinates of square areas
 * and validating the input data.
 */
public class AreaInputService implements IAreaInputService {
  
	
    private static final Logger logger = LoggerFactory.getLogger(AreaInputService.class);
	    
    @Override
    public List<SquareAreaDto> captureAreaData(IInputReader inputReader, int numberOfAreas) {
        
    	logger.debug("AreaInputService :: captureAreaData: Capturing area data for {} areas", numberOfAreas);		
    	List<SquareAreaDto> areas = new ArrayList<>();

        for (int i = 1; i <= numberOfAreas; i++) {
            int attempts = 0;
        	while (attempts < 3) {
            	System.out.println();
            	System.out.println("Enter the initial coordinates (Y X) separated by a space for the areaId[" + i + "]:");            
            	String[] coordinates = inputReader.nextLine().split(" ");                
                
                if (coordinates.length != 2) {
                    System.out.println("Invalid entry. You must enter two numbers separated by a space.");
                    attempts++;
                    continue;
                }

                try {
                    int maxY = Integer.parseInt(coordinates[0]);
                    int maxX = Integer.parseInt(coordinates[1]);
                    
                    SquareAreaDto newArea = new SquareAreaDto(i, Constants.ORIGIN_CORD_X, Constants.ORIGIN_CORD_Y, maxX, maxY);
                    
                    if (!newArea.isMinimumArea(newArea.getMaxCordX(), newArea.getMaxCordY())) {
                    	logger.error("AreaInputService :: captureAreaData: Area coordinates are not minimal area. Data input: {}",
								Arrays.toString(coordinates));
						System.out.println("The area coordinates are not minimal area. Please try again.");
						attempts++;
						continue;
                    }else {
                    	areas.add(newArea);
                    	logger.info("AreaInputService :: captureAreaData: Área {} successfully captured: ({}, {})", i, maxX, maxY);
                    	break;
                    }                                                            
                } catch (NumberFormatException e) { 
                	logger.error("AreaInputService :: captureAreaData: Invalid input coordinates: {}", (Object) coordinates, e);
                    System.out.println("Invalid input. Coordinates must be numbers. Please try again.");
                    attempts++;
                } catch (Exception ex) { 
                	logger.error("AreaInputService :: captureAreaData: Generic invalid input coordinates: {}", (Object) coordinates, ex);
                    System.out.println("Invalid input. Generic invalid input coordinates. Please try again.");
                    attempts++;
                }
            }
        }

        return areas;
    }
}
