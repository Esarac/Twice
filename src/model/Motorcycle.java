package model;

import exception.InvalidPlateException;
import model.MotorVehicle.VehicleFuel;

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
	
	//Constructor
	public Motorcycle(String name, String plate, VehicleFuel fuel, MotorcycleType type, int cylindered) throws InvalidPlateException{
		super(name, plate, fuel);
		this.type=type;
		this.cylindered=cylindered;
	}
	
}
