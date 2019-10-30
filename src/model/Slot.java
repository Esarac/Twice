package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import model.Vehicle.VehicleType;

public class Slot {

	private Vehicle vehicle;
	private String type;
	
	private Calendar initialTime;
	
	public Slot(){
		this.type="Non";
	}
	
	public Slot(VehicleType type){
		this.type=type.name();
	}
	
	public boolean addVehicle(Vehicle vehicle){
		boolean possible=true;
		
		if(type.equals(vehicle.getType()) || type.equals("Non")){
			this.vehicle=vehicle;
			this.initialTime=new GregorianCalendar();
		}
		else{//Exception?
			possible=false;
		}
		
		return possible;
	}
	
}
