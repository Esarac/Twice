package model;

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
	
}
