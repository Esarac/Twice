package model;

import java.util.ArrayList;

import model.Vehicle.VehicleType;

public class Owner extends User{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Parking> parkings;
	
	public Owner(String name, String email, String password) {
		super(name, email, password);
		this.parkings=new ArrayList<Parking>();
	}

	public void addParking(String name,String email, String info,String address, String city, String department, String country, double priceCar, double priceMotorcycle, double priceBicycle, int slotsCar, int slotsMotorcycle, int slotsBicycle){
		String realAddress=address+", "+city+", "+department+", "+country;
		Parking parking=new Parking(name, realAddress, email, info, priceCar, priceMotorcycle, priceBicycle);
		parking.addSlots("C", VehicleType.Car, slotsCar);
		parking.addSlots("M", VehicleType.Motorcycle, slotsMotorcycle);
		parking.addSlots("B", VehicleType.Bicycle, slotsBicycle);
		parkings.add(parking);
	}
	
	public ArrayList<Parking> getParkings(){
		return parkings;
	}
	
}
