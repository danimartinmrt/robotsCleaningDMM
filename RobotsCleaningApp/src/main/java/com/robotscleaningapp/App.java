package com.robotscleaningapp;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.robotscleaningapp.logic.IMovements;
import com.robotscleaningapp.logic.impl.Movements90Degrees;
import com.robotscleaningapp.dto.RobotDto;
import com.robotscleaningapp.dto.SquareAreaDto;
import com.robotscleaningapp.service.impl.AreaInputService;
import com.robotscleaningapp.service.impl.RobotInputService;
import com.robotscleaningapp.service.impl.RobotMovementService;
import com.robotscleaningapp.service.impl.RobotProcessingService;
import com.robotscleaningapp.util.Constants;
import com.robotscleaningapp.input.IInputReader;
import com.robotscleaningapp.input.impl.ScannerInputReader;

/**
 * Main class for the Robot Cleaning App.
 * 
 * This class is responsible for capturing area and robot data positions, processing robot movements, 
 * and displaying final results.
 */
public class App {

	private static final Logger logger = LoggerFactory.getLogger(App.class);
	

	/**
	 * Main method to run the Robot Cleaning App.
	 * 
	 * @param args Command line arguments (not used).
	 */
    public static void main(String[] args) {
        
    	long startTime = System.currentTimeMillis();
		logger.info("App :: main: Starting Robot Cleaning App");
		
    	Scanner scanner = new Scanner(System.in);
        List<RobotDto> robots = runApp(scanner);

        // Show final results
        System.out.println();
        System.out.println("Final position:");
        for (RobotDto robot : robots) {
            System.out.println(robot);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        logger.info("App :: main: Robot Cleaning App finished in {} ms", duration);
    }
    
    /**
	 * Runs the application logic to capture area and robot data, process movements, and return the final robot positions.
	 * 
	 * @param scanner Scanner object for user input.
	 * @return List of RobotDto objects representing the final positions of the robots.
	 */
    public static List<RobotDto> runApp(Scanner scanner) {
        IInputReader inputReader = new ScannerInputReader(scanner);

        // Capture area data
        AreaInputService areaInputService = new AreaInputService();
        List<SquareAreaDto> area = areaInputService.captureAreaData(inputReader, Constants.AREAS_NUMBER);

        // Process robots
        RobotInputService inputService = new RobotInputService();
        RobotMovementService movementService = new RobotMovementService();
        RobotProcessingService robotProcessServ = new RobotProcessingService(inputService, movementService);

        IMovements movements = new Movements90Degrees();
        List<RobotDto> robots = robotProcessServ.processRobots(inputReader, area, movements, Constants.ROBOTS_NUMBER);

        inputReader.close();
        return robots;
    }
			
}
