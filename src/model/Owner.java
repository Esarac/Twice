package model;

import java.util.ArrayList;

public class Owner extends User{

	private ArrayList<Parking> parkings;
	
	public Owner(String name, String email, String password) {
		super(name, email, password);
	}

	public void addParking(Parking parking){
		parkings.add(parking);
	}
	
}
