package model;

import exception.InvalidPlateException;

/**
* <b>Description:</b> The abstract class MotorVehicle in the package model.<br>
* @author VoodLyc & Esarac.
*/

public abstract class MotorVehicle extends Vehicle{

	//Constants
	public enum VehicleFuel{GASOLINE, DIESEL, GAS, EXTRA, ELECTRIC, HYBRID}
	
	//Attribute
	private String plate;
	private VehicleFuel fuel;
	
	//Constructor
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
	public String getPlate() {
		return plate;
	}
	
}
