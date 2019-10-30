package model;

import java.util.ArrayList;

public class Employee extends User{

	private ArrayList<Parking> parkings;
	
	public Employee(String name, String lastName, String email, String id, String password) {
		super(name, lastName, email, id, password);
	}

	public void addParking(Parking parking){
		parkings.add(parking);
	}
	
}
