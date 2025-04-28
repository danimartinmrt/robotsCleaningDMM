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

import com.robotscleaningapp.dto.RobotDto;
import com.robotscleaningapp.dto.SquareAreaDto;
import com.robotscleaningapp.input.IInputReader;
import com.robotscleaningapp.util.Constants;

class RobotInputServiceTest {

    @InjectMocks
    private RobotInputService inputService;

    @Mock
    private IInputReader inputReader;

    private List<SquareAreaDto> areas;
    private List<RobotDto> robotsList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        areas = new ArrayList<>();
        areas.add(new SquareAreaDto(1, 0, 0, 5, 5));

        robotsList = new ArrayList<>();
    }

    @Test
    void testCaptureInitialRobotPosition_ValidInput() {
        // Arrange
        when(inputReader.nextLine()).thenReturn("1 2 N");        

        // Act
        RobotDto result = inputService.captureInitialRobotPosition(inputReader, areas, 1, robotsList);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getCordX());
        assertEquals(2, result.getCordY());
        assertEquals(Constants.NORTH, result.getOrientation());
    }
    
    @Test
    void testCaptureInitialRobotPosition_InvalidInput() {
        // Arrange
        when(inputReader.nextLine()).thenReturn("1 2");
        
        // Act
        RobotDto result = inputService.captureInitialRobotPosition(inputReader, areas, 1, robotsList);

		// Assert
        assertNotNull(result);
        assertEquals(new RobotDto(), result);

    }
    
    @Test
    void testCaptureInitialRobotPosition_OutOfBounds() {
        // Arrange
        when(inputReader.nextLine()).thenReturn("6 6 N");

        // Act
        RobotDto result = inputService.captureInitialRobotPosition(inputReader, areas, 1, robotsList);

        // Assert
        assertNotNull(result);
        assertEquals(new RobotDto(), result);
    }

}
