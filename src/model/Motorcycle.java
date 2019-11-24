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
	
	/**
	 * <b>Description:</b> Creates a new instance of Motorcycle.<br>
	 * @param name The motorcycle name
	 * @param plate The motorcycle license plate - plate must be of 6 or 7 characters in length and contain minimum at least three (3) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9).
	 * @param fuel The motorcycle type of fuel.
	 * @param type The motorcycle type.
	 * @param cylindered The motorcycle cylindered.
	 * @throws InvalidPlateException If the plate do not is 6 or 7 characters in length and contain minimum at least three (3) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9).
	 */
	
	public Motorcycle(String name, String plate, VehicleFuel fuel, MotorcycleType type, int cylindered) throws InvalidPlateException{
		super(name, plate, fuel);
		this.type=type;
		this.cylindered=cylindered;
	}
	
}
