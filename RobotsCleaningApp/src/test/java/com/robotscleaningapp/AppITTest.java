package com.robotscleaningapp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import com.robotscleaningapp.dto.RobotDto;
import com.robotscleaningapp.util.Constants;

class AppITTest {
    
    @Test
    void testRunApp() {
    	// Arrange
        String simulatedInput = """
        5 5
        1 2 N
        LMLMLMLMM
        3 3 E
        MMRMMRMRRM
        """;
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        // Act
        List<RobotDto> robots = App.runApp(scanner);

        // Assertions
        assertEquals(2, robots.size());

        RobotDto robot1 = robots.get(0);
        assertEquals(1, robot1. getCordX());
        assertEquals(3, robot1.getCordY());
        assertEquals(Constants.NORTH, robot1.getOrientation());

        RobotDto robot2 = robots.get(1);
        assertEquals(5, robot2.getCordX());
        assertEquals(1, robot2.getCordY());
        assertEquals(Constants.EAST, robot2.getOrientation());
    }
}
