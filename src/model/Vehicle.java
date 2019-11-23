package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

/**
* <b>Description:</b> The abstract class Vehicle in the package model.<br>
* @author VoodLyc & Esarac.
*/

public abstract class Vehicle implements Comparable<Vehicle>, Comparator<Vehicle>, Serializable{
	
	//Attribute
	private String name;
	private Bill firstBill;
	
	//Constructor
	public Vehicle(String name){
		this.name=name;
	}
	
	//Add
	public void addBill(Calendar entryDate, String parkingName, String parkingAddress){
		Bill bill=new Bill(entryDate, parkingName, parkingAddress);
		bill.setNext(firstBill);
		firstBill=bill;
	}
	
	//Search
	public ArrayList<Bill> unpaidBills(){
		ArrayList<Bill> unpaid=new ArrayList<Bill>();
		
		Bill actual=firstBill;
		while(actual!=null){
			if(!actual.isPayed()){
				unpaid.add(actual);
			}
			actual=actual.getNext();
		}
		
		return unpaid;
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
	
	public Bill getFirstBill() {
		return firstBill;
	}
	
}
