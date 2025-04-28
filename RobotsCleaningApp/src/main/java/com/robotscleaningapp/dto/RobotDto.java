package com.robotscleaningapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RobotDto {

	private int id;
	private int cordX;
    private int cordY;
    private String orientation;
    private int assignedAreaId;    
	private String message;

	/** 
	 * Clones the RobotDto object.
	 * 
	 * @param id The ID of the robot.
	 * @param cordX The X coordinate of the robot.
	 * @param cordY The Y coordinate of the robot.
	 * @param orientation The orientation of the robot.
	 * @param assignedAreaId The ID of the area assigned to the robot.
	 */
	public RobotDto clone() {
	    return new RobotDto(this.id, this.cordX, this.cordY, this.orientation, this.assignedAreaId, this.message);
	}
	
    @Override
    public String toString() {
        return "Robot[" + id + "] --> Possition X=(" + cordX + "), Possition Y=(" + cordY + "), Orientation=(" + orientation + ")"
        		+ ", Assigned Area Id= " + assignedAreaId + ", Message=" + message;
    }
	
}
