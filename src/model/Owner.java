package model;

import java.util.ArrayList;

public class Owner extends User{

	private ArrayList<Parking> parkings;
	
	public Owner(String name, String email, String password) {
		super(name, email, password);
		this.parkings=new ArrayList<Parking>();
	}

	public void addParking(Owner owner,String name,String email, String info,String address, String city, String department, String country, double priceCar, double priceMotorcycle, double priceBicycle){
		String realAddress=address+", "+city+", "+department+", "+country;
		Parking parking=new Parking(name, realAddress, email, info, priceCar, priceMotorcycle, priceBicycle);
		parkings.add(parking);
	}
	
	public ArrayList<Parking> getParkings(){
		return parkings;
	}
	
}
