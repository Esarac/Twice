package model;

import java.io.Serializable;

public class Vehicle implements Serializable{
	
	public enum VehicleType{Car, Motorcycle, Bicycle}
	public enum VehicleFuel{Gasoline, Diesel, Non, Gas, Extra, Electric, Hybrid}
	
	private VehicleType type;
	private String plate;
	private String fuel;

	public Vehicle(VehicleType type, String plate, VehicleFuel fuel){
		
		this.type=type;
		this.plate=plate;
		this.fuel=fuel.name();
		
	}
	
	public VehicleType getType(){
		return type;
	}
	
	public String getPlate(){
		return plate;
	}
	
	public String toString(){
		return plate;
	}
	
}
