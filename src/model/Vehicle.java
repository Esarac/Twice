package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

/**
* <b>Description:</b> The abstract class Vehicle in the package model.<br>
* @author VoodLyc & Esarac.
*/

public abstract class Vehicle implements Comparable<Vehicle>, Comparator<Vehicle>, Serializable {//[TEST]
	
	//Attributes
	private String name;
	private Bill firstBill;
	
	//Constructor
	
	/**
	 * <b>Description:</b> Creates a new instance of Vehicle.<br>
	 * @param name The vehicle name
	 */
	
	public Vehicle(String name){
		this.name=name;
	}
	
	//Add
	
	/**
	 * <b>Description:</b> This method allows adding a bill.<br>
	 * @param entryDate The entry date of the vehicle in the parking.
	 * @param parkingName The parking name.
	 * @param parkingAddress The parking address.
	 */
	
	public void addBill(Calendar entryDate, String parkingName, String parkingAddress){
		Bill bill=new Bill(entryDate, parkingName, parkingAddress);
		bill.setNext(firstBill);
		firstBill=bill;
	}
	
	//Search
	
	/**
	 * <b>Description:</b> This method allows searching the unpaid bills.<br>
	 * @return The unpaid bills.
	 */
	
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
	
	/**
	 * <b>Description:</b> This method allows counting the bills.<br>
	 * @return The number of bills.
	 */
	
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
	
	/**
	 * <b>Description:</b> This method allows comparing a vehicle with other by the name.<br>
	 * @return the difference.
	 */
	
	public int compareTo(Vehicle vehicle){
		return name.compareToIgnoreCase(vehicle.name);
	}
	
	/**
	 * <b>Description:</b> This method allows comparing a vehicle with other by the bills size.<br>
	 * @return the difference.
	 */
	
	public int compare(Vehicle vehicle1, Vehicle vehicle2){
		return vehicle1.billsSize()-vehicle2.billsSize();
	}
	
	//Getters
	
	/**
	 * <b>Description:</b> Gets the value of the attribute name.<br>
	 * @return The attribute name.
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * <b>Description:</b> Gets the value of the first bill.<br>
	 * @return The first bil.
	 */
	
	public Bill getFirstBill() {
		return firstBill;
	}
	
	/**
	 * <b>Description:</b> This method allows converting the object in String.<br>
	 * @return The object as String.
	 */
	
	public String toString() {
		return name;
	}
	
}