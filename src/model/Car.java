package model;

import exception.InvalidPlateException;
import model.MotorVehicle.VehicleFuel;

/**
* <b>Description:</b> The class Car in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class Car extends MotorVehicle{

	//Constants
	public enum CarType{MICRO, STANDARD, SUV, VAN, TRUCK;}
	
	//Attributes
	private CarType type;
	private int polarized;
	
	//Constructor
	public Car(String name, String plate, VehicleFuel fuel, CarType type, int polarized) throws InvalidPlateException{
		super(name, plate, fuel);
		this.type=type;
		this.polarized=polarized;
	}
	
}
