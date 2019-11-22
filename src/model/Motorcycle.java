package model;

/**
* <b>Description:</b> The class Motorcycle in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class Motorcycle extends MotorVehicle implements TwoWheels{
	
	//Constants
	public enum MotorcycleType{QUAD, STANDARD}
	
	//Attributes
	private MotorcycleType type;
	private int cylindered;
}
