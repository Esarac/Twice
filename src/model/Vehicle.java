package model;

import java.util.Comparator;

/**
* <b>Description:</b> The abstract class Vehicle in the package model.<br>
* @author VoodLyc & Esarac.
*/

public abstract class Vehicle implements Comparable<Vehicle>, Comparator<Vehicle>{
	
	//Attribute
	private String name;
	private Bill firstBill;
	
	//Constructor
	public Vehicle(String name){
		this.name=name;
	}
	
	//Calculate
	public int billsSize(){
		int size=0;
		Bill actual=firstBill;
		while(actual!=null) {
			size++;
			actual=actual.getNext();
		}
		return size;
	}
	
	//Compare
	public int compareTo(Vehicle vehicle){
		return name.compareTo(vehicle.name);
	}
	
	public int compare(Vehicle vehicle1, Vehicle vehicle2){
		return vehicle1.billsSize()-vehicle1.billsSize();
	}
	
	//Get
	public String getName() {
		return name;
	}
	
}
