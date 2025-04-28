package com.robotscleaningapp.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.robotscleaningapp.dto.RobotDto;
import com.robotscleaningapp.dto.SquareAreaDto;
import com.robotscleaningapp.input.IInputReader;
import com.robotscleaningapp.service.IRobotInputService;
import com.robotscleaningapp.util.Constants;
import com.robotscleaningapp.util.Orientation;
import com.robotscleaningapp.util.Utils;

/**
 * Service class for capturing the initial position of robots.
 * 
 * This class is responsible for capturing the initial position of robots, including their coordinates and orientation.
 */
public class RobotInputService implements IRobotInputService {

	private static final Logger logger = LoggerFactory.getLogger(RobotInputService.class);
	
	@Override
    public RobotDto captureInitialRobotPosition(IInputReader inputReader, List<SquareAreaDto> area, int robotNumber, 
    		List<RobotDto> robotsList) {
        int cordX, cordY;
        
        logger.debug("RobotInputService :: captureInitialRobotPosition: Capturing initial position for robot {}", robotNumber);
        String orientation;
        RobotDto robot = new RobotDto();
        SquareAreaDto assignedArea = Utils.getAreaById(area, Constants.DEFAULT_AREA_ID);
                
        int attempts = 0;
        while (attempts < 3) {
        	System.out.println();
        	System.out.println("Enter the initial coordinates (X Y) and orientation (N, S, E, W) of the robot[" + robotNumber + "] "
            		+ "separated by a space:");
            String[] robotPosition = inputReader.nextLine().toUpperCase().split(StringUtils.SPACE);
            if (robotPosition.length != 3) {
            	logger.error("RobotInputService :: captureInitialRobotPosition: Invalid input. Expected 3 values. Data input: {}",
            			Arrays.toString(robotPosition));
                System.out.println("Invalid input. You must enter X, Y and bearing (N, S, E, W) separated by a space.");
                attempts++;
                continue;
            }
            
            try {
                cordX = Integer.parseInt(robotPosition[0]);
                cordY = Integer.parseInt(robotPosition[1]);
                orientation = Orientation.fromShorthand(robotPosition[2]);
                
                RobotDto newRobot = new RobotDto(robotNumber, cordX, cordY, orientation, assignedArea.getId(),StringUtils.EMPTY);
                
                if (Utils.willCollision(newRobot, robotsList)) {
                	newRobot.setMessage(newRobot.getMessage() + "[Invalid movement: collision with another robot.] ");
    				logger.warn("RobotInputService :: captureInitialRobotPosition: Invalid movement, collision with another robot.");
    				System.out.println("The starting position collides with another robot. Please try again.");
    				attempts++;
    				continue;
                }else if (!assignedArea.isWithinBounds(newRobot.getCordX(), newRobot.getCordY())) {
                	newRobot.setMessage(newRobot.getMessage() + "[Invalid movement: The starting position is out of bounds.] ");
                	logger.error("RobotInputService :: captureInitialRobotPosition: Starting position out of bounds. Data input: {}",
                			Arrays.toString(robotPosition));
                    System.out.println("The starting position is out of bounds. Please try again.");
                    attempts++;
                    continue;
                }else {
                	robot = newRobot;
                	break;
                }
            } catch (NumberFormatException e) {
            	logger.error("RobotInputService :: captureInitialRobotPosition: Invalid input. Coordinates must be integers. ",e);
                System.out.println("Invalid input. Coordinates must be integers. Please try again.");
                attempts++;
            } catch (IllegalArgumentException il) {
        		logger.error("RobotInputService :: captureInitialRobotPosition: Invalid input. Orientation must be (N, E, S, W)",il);
        		System.out.println("Generic error. Orientation must be (N, E, S, W). Please try again.");
        		attempts++;
            } catch (Exception ex) {
        		logger.error("RobotInputService :: captureInitialRobotPosition: Generic error.",ex);
        		System.out.println("Generic error. Please try again.");
        		attempts++;
        	}
        }

        return robot;
    }
    
    
}
