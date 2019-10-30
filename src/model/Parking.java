package model;

import java.util.ArrayList;

import model.Vehicle.VehicleType;

public class Parking {

	private Employee owner;
	private String address;//Address, City, Department, Country
	private double pricePerHour;
	private ArrayList<Slot> slots;
	
	public Parking(Employee owner, String address, double pricePerHour){
		
		this.owner=owner;
		this.address=address;
		this.pricePerHour=pricePerHour;
		this.slots=new ArrayList<Slot>();
		
	}
	
	public void addSlots(VehicleType type, int quantity){
		for(int i=0; i<quantity; i++){
			slots.add(new Slot(type));
		}
	}
	
	public String getWebAddress(){
		String webAddress="";
		for(int i=0; i<address.length(); i++){
			if(address.charAt(i)==' '){
				webAddress+="+";
			}
			else if(address.charAt(i)=='#'){
				webAddress+="%23";
			}
			else{
				webAddress+=address.charAt(i);
			}
		}
		return webAddress;
	}
	
}
