package model;

import java.util.ArrayList;

import model.Vehicle.VehicleFuel;
import model.Vehicle.VehicleType;

public class Client extends User{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Vehicle> vehicles;
	
	public Client(String name, String email, String password){
		super(name, email, password);
		this.vehicles=new ArrayList<Vehicle>();
	}

	public void addVehicle(VehicleType type, String plate, VehicleFuel fuel){
		vehicles.add(new Vehicle(type, plate, fuel));
	}
	
	public boolean deleteVehicle(String plate){
		boolean found=false;
		for(int i=0; (i<vehicles.size()) && !found; i++){
			if(vehicles.get(i).getPlate().equals(plate)){
				vehicles.remove(i);
				found=true;
			}
		}
		return found;
	}
	
	public ArrayList<Vehicle> getVehicles(){
		return vehicles;
	}
	
}
