package model;

import exception.InvalidPlateException;

/**
* <b>Description:</b> The abstract class MotorVehicle in the package model.<br>
* @author VoodLyc & Esarac.
*/

public abstract class MotorVehicle extends Vehicle{//[TEST]

	//Constants
	public enum VehicleFuel{GASOLINE, DIESEL, GAS, EXTRA, ELECTRIC, HYBRID}
	
	//Attribute
	private String plate;
	private VehicleFuel fuel;
	
	//Constructor
	
	/**
	 * <b>Description:</b> Creates a new instance of MotorVehicle.<br>
	 * @param name The motor vehicle name.
	 * @param plate The motor vehicle license plate.
	 * @param fuel The motor vehicle type of fuel.
	 * @throws InvalidPlateException If the plate do not is 6 or 7 characters in length and contain minimum at least three (3) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9).
	 */
	
	public MotorVehicle(String name, String plate, VehicleFuel fuel) throws InvalidPlateException{
		super(name);
		if(validatePlate(plate)){
			this.plate=plate;
		}
		else{
			throw new InvalidPlateException();
		}
		this.fuel=fuel;
	}
	
	//Calculate
	
	/**
	 * <b>Description:</b> This method allows validating a plate, check if the plate have 6 or 7 characters in length and contain minimum at least three (3) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9). .<br>
	 * @param plate The license plate.
	 * @return True if the plate is valid, false in otherwise.
	 */
	
	public boolean validatePlate(String plate) {
		boolean valid=true;
		if((plate.length()!=6) && (plate.length()!=7)){
			valid=false;
		}
		else{
			for(int i=0; i<plate.length(); i++){
				char actualChar=plate.charAt(i);
				if(!(((65<=actualChar)&&(actualChar<=90)) || ((48<=actualChar)&&(actualChar<=57)))){
					valid=false;
				}
			}
		}
		return valid;
	}
	
	//Compare
	
	/**
	 * <b>Description:</b> This method allows comparing two motor vehicle by the license plate.<br>
	 * @param vehicle The motor vehicle with which it compares.
	 * @return The difference if the vehicle is a motor vehicle, -1 in otherwise.
	 */
	
	public int comparePlate(Vehicle vehicle){
		int delta=0;
		if(vehicle instanceof MotorVehicle){
			MotorVehicle mVehicle=(MotorVehicle)vehicle;
			delta=plate.compareTo(mVehicle.plate);
		}
		else{
			delta=-1;
		}
		return delta;
	}
	
	//Get
	
	/**
	 * <b>Description:</b> Gets the value of the attribute plate.<br>
	 * @return The attribute plate.
	 */
	
	public String getPlate() {
		return plate;
	}
	
}
