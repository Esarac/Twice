package model;

import java.io.Serializable;

import model.Vehicle.VehicleType;

public class Slot implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private Vehicle vehicle;
	private VehicleType type;
	
	private long initialTime;
	
	public Slot(String id){
		this.id=id;
		this.type=null;
	}
	
	public Slot(String id, VehicleType type){
		this.id=id;
		this.type=type;
	}
	
	public boolean addVehicle(Vehicle vehicle){
		boolean possible=true;
		
		if(type.equals(vehicle.getType()) || type.equals(null)){
			this.vehicle=vehicle;
			this.initialTime=System.currentTimeMillis();
		}
		else{//Exception?
			possible=false;
		}
		
		return possible;
	}
	
	public long removeCar(){
		this.vehicle=null;
		long time=initialTime;
		this.initialTime=0;
		return time;
	}
	
	public boolean myCarIsHere(Client client){
		boolean isHere=false;
		
		if(!empty()){
			for(int i=0; (i<client.getVehicles().size()) && !isHere; i++){
				if(client.getVehicles().get(i).getPlate().equals(vehicle.getPlate())){
					isHere=true;
				}
			}
		}
		
		return isHere;
	}
	
	public boolean empty(){
		boolean empty=false;
		if(vehicle==null){
			empty=true;
		}
		return empty;
	}

	public String getId(){
		return id;
	}
	
	public Vehicle getVehicle(){
		return vehicle;
	}
	
	public VehicleType getType() {
		return type;
	}
	
}
