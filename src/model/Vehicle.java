package model;

import java.io.Serializable;

public class Vehicle implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public enum VehicleType{Car, Motorcycle, Bicycle}
	public enum VehicleFuel{Gasoline, Diesel, Non, Gas, Extra, Electric, Hybrid}
	
	private VehicleType type;
	private String plate;
	private VehicleFuel fuel;

	public Vehicle(VehicleType type, String plate, VehicleFuel fuel){
		
		this.type=type;
		this.plate=plate;
		this.fuel=fuel;
		
	}
	
	public VehicleType getType(){
		return type;
	}
	
	public String getPlate(){
		return plate;
	}
	
	public VehicleFuel getFuel(){
		return fuel;
	}
	
	public String toString(){
		return plate;
	}
	
}
