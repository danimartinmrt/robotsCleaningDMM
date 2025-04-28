package com.robotscleaningapp.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.robotscleaningapp.dto.SquareAreaDto;
import com.robotscleaningapp.input.IInputReader;

class AreaInputServiceTest {

	@InjectMocks
    private AreaInputService areaInputService;
	
	@Mock
    private IInputReader inputReader;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

    @Test
    void testCaptureAreaData_ValidInput() {    	
    	// Arrange
        when(inputReader.nextLine()).thenReturn("5 5");

        // Act
        List<SquareAreaDto> areas = areaInputService.captureAreaData(inputReader, 1);

        // Assert
        assertEquals(1, areas.size());
        assertEquals(5, areas.get(0).getMaxCordX());
        assertEquals(5, areas.get(0).getMaxCordY());
        
    }

    @Test
    void testCaptureAreaData_InvalidInput_Retry() {
    	// Arrange
    	when(inputReader.nextLine()).thenReturn("invalid-input");    	

    	// Act
        List<SquareAreaDto> areas = areaInputService.captureAreaData(inputReader, 1);

        // Assert
        assertEquals(0, areas.size());        
    }
    
    @Test
    void testCaptureAreaData_NumberFormatException() {
    	// Arrange
    	when(inputReader.nextLine()).thenReturn("A B");    	

    	// Act
        List<SquareAreaDto> areas = areaInputService.captureAreaData(inputReader, 1);

        // Assert
        assertEquals(0, areas.size());        
    }
    
    @Test
    void testCaptureAreaData_MinimumAreaValidation() {
    	// Arrange
    	when(inputReader.nextLine()).thenReturn("0 0");     	

    	// Act
        List<SquareAreaDto> areas = areaInputService.captureAreaData(inputReader, 1);

        // Assert
        assertEquals(0, areas.size());        
    }        
}