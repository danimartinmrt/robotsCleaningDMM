package com.robotscleaningapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SquareAreaDto {

	private int id;	
	private int originCordX;
	private int originCordY;
	private int maxCordX;
    private int maxCordY;
    
    /**
	 * Checks if the given coordinates are within the bounds of the square area.
	 *
	 * @param x The x-coordinate to check.
	 * @param y The y-coordinate to check.
	 * @return true if the coordinates are within bounds, false otherwise.
	 */
    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x <= maxCordX && y >= 0 && y <= maxCordY;
    }
    

	/**	
	 * Checks if the area is a minimal area.
	 * 
	 * @param x 
	 * @param y
	 * @return
	 */
    public boolean isMinimumArea(int x, int y) {
        return x > originCordX && y > originCordX;
    }

    @Override
    public String toString() {
        return "SquareAreaDto [id=" + id + ", originCordX=" + originCordX + ", originCordY=" + originCordY
				+ ", maxCordX=" + maxCordX + ", maxCordY=" + maxCordY + "]";
    }
}
