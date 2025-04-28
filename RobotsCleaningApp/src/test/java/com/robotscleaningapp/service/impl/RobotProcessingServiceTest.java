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
import com.robotscleaningapp.logic.IMovements;
import com.robotscleaningapp.service.IRobotInputService;
import com.robotscleaningapp.service.IRobotMovementService;


class RobotProcessingServiceTest {

	@InjectMocks
	private RobotProcessingService processingService;
    
	@Mock
    private IRobotInputService inputService;

    @Mock
    private IRobotMovementService movementService;
   
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   @Test
   void testProcessRobots_Success() {
        // Arrange
        IInputReader inputReader = mock(IInputReader.class);
        IMovements movements = mock(IMovements.class);

        List<SquareAreaDto> areas = new ArrayList<>();
        areas.add(new SquareAreaDto(1, 0, 0, 5, 5));

        RobotDto mockRobot = new RobotDto(1, 1, 0, "N", 1, "");
        when(inputService.captureInitialRobotPosition(any(), any(), anyInt(), anyList()))
        .thenReturn(mockRobot);        
        when(movementService.processMovements(any(), any(), any(), any(), anyList()))
                .thenReturn(mockRobot);

        // Act
        List<RobotDto> result = processingService.processRobots(inputReader, areas, movements, 1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockRobot, result.get(0));

        verify(inputService, times(1)).captureInitialRobotPosition(any(), any(), anyInt(), anyList());
        verify(movementService, times(1)).processMovements(any(), any(), any(), any(), anyList()); 
    }
    
}
