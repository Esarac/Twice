package model;

public class Vehicle {
	
	public enum VehicleType{Car, Motorcycle, Bicycle}
	public enum VehicleFuel{Gasoline, Diesel, Non, Gas, Extra, Electric, Hybrid}
	
	private String type;
	private String plate;
	private String fuel;

	public Vehicle(VehicleType type, String plate, VehicleFuel fuel){
		
		this.type=type.name();
		this.plate=plate;
		this.fuel=fuel.name();
		
	}
	
	public String getType(){
		return type;
	}
	
}
