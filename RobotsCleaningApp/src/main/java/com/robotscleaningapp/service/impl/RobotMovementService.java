package com.robotscleaningapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.robotscleaningapp.logic.IMovements;
import com.robotscleaningapp.service.IRobotMovementService;
import com.robotscleaningapp.dto.RobotDto;
import com.robotscleaningapp.dto.SquareAreaDto;
import com.robotscleaningapp.input.IInputReader;
import com.robotscleaningapp.util.Constants;
import com.robotscleaningapp.util.Utils;

/**
 * Service class for processing robot movements.
 * 
 * This class is responsible for processing the movements of robots within a defined area,
 * handling collisions, and ensuring that robots stay within bounds.
 */
public class RobotMovementService implements IRobotMovementService {
	
	private static final Logger logger = LoggerFactory.getLogger(RobotProcessingService.class);
	
	@Override
    public RobotDto processMovements(IInputReader inputReader, RobotDto robot, SquareAreaDto area, IMovements movements, 
    		List<RobotDto> robotsList) {
        
		logger.debug("RobotMovementService :: processMovements: Processing movements for robot {}", robot.getId());

		System.out.println();
		System.out.println("Enter the robot´s movements (L, R, M) on a single line (For Robot["+robot.getId()+"]):");
        String commands = inputReader.nextLine().toUpperCase();

        for (char command : commands.toCharArray()) {
            switch (command) {
                case Constants.LEFT:
                    robot.setOrientation(movements.left(robot.getOrientation()));
                    break;
                case Constants.RIGHT:
                    robot.setOrientation(movements.right(robot.getOrientation()));
                    break;
                case Constants.MOVE_FORWARD:
                	robot = handleMoveForward(robot, area, movements, robotsList);
                    break;                                    	
                default:
                    System.out.println("Invalid command. Orientation must be (N, E, S, W): " + command);
            }
        }

        return robot;
    }
    
    /**
	 * Handles the move forward command for a robot, checking for collisions and area boundaries.
	 * 
	 * @param robot The RobotDto object representing the robot to be moved.
	 * @param area The SquareAreaDto object representing the area where the robot operates.
	 * @param movements The Movements object for handling robot movements.
	 * @param robotsList The list of robots to check for collisions.
	 * @return The updated RobotDto object after processing the move forward command.
	 */
    private RobotDto handleMoveForward(RobotDto robot, SquareAreaDto area, IMovements movements, 
            List<RobotDto> robotsList) {
		
    	// Clone the robot to avoid modifying the original object
    	RobotDto clonedRobot = robot.clone();        
    	RobotDto newRobot = movements.move(clonedRobot, Constants.NUMBER_GRID_POINTS);
		
		try {
			if (Utils.willCollision(newRobot, robotsList)) {
				robot.setMessage(robot.getMessage() + "[Invalid movement: collision with another robot.] ");
				logger.warn("RobotMovementService :: handleMoveForward: Invalid movement, collision with another robot.");
			} else if (!area.isWithinBounds(newRobot.getCordX(), newRobot.getCordY())) {
				robot.setMessage(robot.getMessage() + "[Invalid movement: the robot would leave the area.] ");
				logger.warn("RobotMovementService :: handleMoveForward: Invalid movement, the robot would leave the area.");
			} else {
				robot = newRobot;			
			}
		} catch (Exception e) {
			logger.error("RobotMovementService :: handleMoveForward: Error processing movement: {}", e.getMessage());
		}

		return robot;
	}

}
