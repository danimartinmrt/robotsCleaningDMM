package com.robotscleaningapp.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.robotscleaningapp.logic.impl.Movements90Degrees;
import com.robotscleaningapp.dto.RobotDto;
import com.robotscleaningapp.dto.SquareAreaDto;
import com.robotscleaningapp.input.IInputReader;
import com.robotscleaningapp.util.Constants;

class RobotMovementServiceTest {

    @InjectMocks
    private RobotMovementService movementService;

    @Mock
    private IInputReader inputReader;

    private Movements90Degrees movements90Degress = new Movements90Degrees();
    private List<RobotDto> robotsList;
    private SquareAreaDto area;
    private RobotDto robot;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        robotsList = new ArrayList<>();
        area = new SquareAreaDto(1, 0, 0, 5, 5);
        robot = new RobotDto(1, 1, 1, Constants.NORTH, 1, "");
    }

    @Test
    void testProcessMovements_ValidCommands() {
        // Arrange
        when(inputReader.nextLine()).thenReturn("LMLMR");

        // Act
        RobotDto result = movementService.processMovements(inputReader, robot, area, movements90Degress, robotsList);

        // Assert
        assertNotNull(result);
        assertEquals(Constants.WEST, result.getOrientation());
        assertEquals(0, result.getCordX());
        assertEquals(0, result.getCordY());
    }

    @Test
    void testProcessMovements_InvalidCommand() {
        // Arrange
        when(inputReader.nextLine()).thenReturn("X");

        // Act
        RobotDto result = movementService.processMovements(inputReader, robot, area, movements90Degress, robotsList);

        // Assert
        assertNotNull(result);
        assertEquals(Constants.NORTH, result.getOrientation()); // No cambia la orientación
    }
    
    

    
    @Test
    void testHandleMoveForward_Collision() {
        // Arrange
    	when(inputReader.nextLine()).thenReturn("MRM");
    	RobotDto robot2 = new RobotDto(2, 0, 0, Constants.NORTH, 1, "");
    	robotsList.add(robot);

        // Act
        RobotDto result = movementService.processMovements(inputReader, robot2, area, movements90Degress, robotsList);

        // Assert
        assertNotNull(result);
        assertTrue(result.getMessage().contains("collision with another robot"));
        assertEquals(Constants.EAST, result.getOrientation());
        assertEquals(0, result.getCordX());
        assertEquals(1, result.getCordY());
    }

    
    @Test
    void testHandleMoveForward_OutOfBounds() {
        // Arrange
    	when(inputReader.nextLine()).thenReturn("LLMM");

        // Act
        RobotDto result = movementService.processMovements(inputReader, robot, area, movements90Degress, robotsList);

        // Assert
        assertNotNull(result);
        assertTrue(result.getMessage().contains("the robot would leave the area"));
        assertEquals(Constants.SOUTH, result.getOrientation());
        assertEquals(1, result.getCordX());
        assertEquals(0, result.getCordY());
    }
    
}
