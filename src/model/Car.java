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
	
	/**
	 * <b>Description:</b> Creates a new instance of Car.<br>
	 * @param name The car name.
	 * @param plate The car license plate - plate must be of 6 or 7 characters in length and contain minimum at least three (3) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9).
	 * @param fuel The car type of fuel.
	 * @param type The car type.
	 * @param polarized The level of polarized.
	 * @throws InvalidPlateException If the plate do not is 6 or 7 characters in length and contain minimum at least three (3) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9).
	 */
	
	public Car(String name, String plate, VehicleFuel fuel, CarType type, int polarized) throws InvalidPlateException{
		super(name, plate, fuel);
		this.type=type;
		this.polarized=polarized;
	}
	
}
