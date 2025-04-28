package com.robotscleaningapp.logic.impl;

import com.robotscleaningapp.dto.RobotDto;
import com.robotscleaningapp.util.Constants;
import com.robotscleaningapp.logic.IMovements;

/**
 * Implementation of Movements interface for 90-degree movements.
 * This class provides methods to turn left, turn right, and move forward.
 */
public class Movements90Degrees implements IMovements {

    @Override
    public String left(String currentPosition) {
    	switch (currentPosition) {
	        case Constants.NORTH: return Constants.WEST;
	        case Constants.EAST: return Constants.NORTH;
	        case Constants.SOUTH: return Constants.EAST;
	        case Constants.WEST: return Constants.SOUTH;                        
	        default: return currentPosition;
	    }
    }

    @Override
    public String right(String currentPosition) {
        switch (currentPosition) {
	        case Constants.NORTH: return Constants.EAST;
	        case Constants.EAST: return Constants.SOUTH;
	        case Constants.SOUTH: return Constants.WEST;
	        case Constants.WEST: return Constants.NORTH;
	        default: return currentPosition;
	    }
    }

    @Override
    public RobotDto move(RobotDto robot, int numberGridPoints) {
    	int cordX = robot.getCordX();
        int cordY = robot.getCordY();
        String position = robot.getOrientation();

        switch (position) {
            case Constants.NORTH:
                robot.setCordY(cordY + numberGridPoints);
                break;
            case Constants.EAST:
                robot.setCordX(cordX + numberGridPoints);
                break;
            case Constants.SOUTH:
                robot.setCordY(cordY - numberGridPoints);
                break;            
            case Constants.WEST:
                robot.setCordX(cordX - numberGridPoints);
                break;
        }
        return robot;
    }
}
