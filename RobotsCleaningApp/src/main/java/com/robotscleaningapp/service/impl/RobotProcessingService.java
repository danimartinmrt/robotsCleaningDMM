package com.robotscleaningapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.robotscleaningapp.logic.IMovements;
import com.robotscleaningapp.service.IRobotInputService;
import com.robotscleaningapp.service.IRobotMovementService;
import com.robotscleaningapp.service.IRobotProcessingService;
import com.robotscleaningapp.dto.RobotDto;
import com.robotscleaningapp.dto.SquareAreaDto;
import com.robotscleaningapp.input.IInputReader;
import com.robotscleaningapp.util.Utils;

/**
 * Service class for processing robot movements and capturing their initial positions.
 * 
 * This class is responsible for capturing the initial position of robots, processing their movements,
 * and handling any collisions or area boundaries.
 */
public class RobotProcessingService implements IRobotProcessingService {

	private static final Logger logger = LoggerFactory.getLogger(RobotProcessingService.class);
	
    private final IRobotInputService inputService;
    private final IRobotMovementService movementService;
    
    public RobotProcessingService(final IRobotInputService inputService,
			final IRobotMovementService movementService) {    	
    	this.inputService = inputService;
    	this.movementService = movementService;    							    	
	}
    
    @Override
    public List<RobotDto> processRobots(IInputReader inputReader, List<SquareAreaDto> area, IMovements movements, 
    		int robotCount) {
      
    	logger.debug("RobotProcessingService :: processRobots: Processing {} robots", robotCount);
						
    	List<RobotDto> robotsList = new ArrayList<>();
        for (int i = 1; i <= robotCount; i++) {
            RobotDto robot = inputService.captureInitialRobotPosition(inputReader, area, i, robotsList);
            
            SquareAreaDto assignedArea = Utils.getAreaById(area, robot.getAssignedAreaId());;
            robot = movementService.processMovements(inputReader, robot, assignedArea, movements, robotsList);
            robotsList.add(robot);
            logger.debug("RobotProcessingService :: processRobots: Robot {} processed correctly "
            		+ "in final position: ({}, {}) facing {}", robot.getId(), robot.getCordX(), 
            		robot.getCordY(), robot.getOrientation());
        }
        return robotsList;
    }
}
