package model;

import java.util.ArrayList;

import model.Vehicle.VehicleFuel;
import model.Vehicle.VehicleType;

public class Client extends User{

	private ArrayList<Vehicle> vehicles;
	
	public Client(String name, String lastName, String email, String id, String password){
		super(name, lastName, email, id, password);
		this.vehicles=new ArrayList<Vehicle>();
	}

	public void addVehicle(VehicleType type, String plate, VehicleFuel fuel){
		vehicles.add(new Vehicle(type, plate, fuel));
	}
	
}
