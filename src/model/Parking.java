package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.Vehicle.VehicleType;

public class Parking implements Serializable{
	
	private String name;
	private String address;//Address, City, Department, Country
	private String email;
	private String info;
	private double[] pricePerHour;
	private ArrayList<Slot> slots;

	public Parking(String name, String address, String email, String info, double priceCar, double priceMotorcycle, double priceBicycle){
		
		this.name=name;
		this.address=address;
		this.email=email;
		this.pricePerHour=new double[3];
		this.pricePerHour[0]=priceCar;
		this.pricePerHour[1]=priceMotorcycle;
		this.pricePerHour[2]=priceBicycle;
		this.slots=new ArrayList<Slot>();
		
	}
	
	public void addSlots(String id,VehicleType type, int quantity){
		for(int i=0; i<quantity; i++){
			slots.add(new Slot(id+"-"+i,type));
		}
	}
	
	public boolean myCarIsHere(Client client){
		boolean isHere=false;
		
		for(int i=0; (i<slots.size()) && !isHere; i++){
			isHere=slots.get(i).myCarIsHere(client);
		}
		
		return isHere;
	}
	
	public double removeCar(Client client){
		boolean possible=false;
		long start=0;
		double money=0;
		
		for(int i=0; (i<slots.size()) && !possible; i++){
			if(slots.get(i).myCarIsHere(client)){
				Vehicle vehicle=slots.get(i).getVehicle();
				start=slots.get(i).removeCar();
				
				long end=System.currentTimeMillis();
				long hours=(long) (((end-start)/(1000*60*60)) % 24);
				
				if(vehicle.getType()==VehicleType.Car)money=hours*pricePerHour[0];
				else if(vehicle.getType()==VehicleType.Motorcycle)money=hours*pricePerHour[1];
				else if(vehicle.getType()==VehicleType.Bicycle)money=(int)hours*pricePerHour[2];
				else money=hours*pricePerHour[0];
				
				possible=true;
			}
		}
		
		return money;
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
	
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
	
	public String getEmail() {
		return email;
	}

	public String getInfo() {
		return info;
	}
	
	public double[] getPricePerHour() {
		return pricePerHour;
	}

	public ArrayList<Slot> getSlots() {
		return slots;
	}
	
	public String emptySlots(){
		int car=0;
		int motor=0;
		int bike=0;
		for(int i=0; i<slots.size(); i++){
			if(slots.get(i).empty()){
				if(slots.get(i).getType()==VehicleType.Car){
					car++;
				}
				else if(slots.get(i).getType()==VehicleType.Motorcycle){
					motor++;
				}
				else if(slots.get(i).getType()==VehicleType.Bicycle){
					bike++;
				}
			}
		}
		String text=" Carro:"+car+" Moto:"+motor+" Bicicleta:"+bike;
		return text;
	}
	
}
