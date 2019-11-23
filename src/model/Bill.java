package model;

import java.util.Calendar;

/**
* <b>Description:</b> The class Bill in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class Bill extends Record{

	//Attributes
	private String parkingName;
	private String parkingAddress;
	private boolean payed;
	//Supplier
	private Bill next;
	
	//Constructor
	public Bill(Calendar entryDate, String parkingName, String parkingAddress) {
		super(entryDate);
		this.parkingName=parkingName;
		this.parkingAddress=parkingAddress;
		this.payed=false;
	}

	//Set
	public void setNext(Bill next) {
		this.next=next;
	}
	
	//Get
	public String getParkingName(){
		return parkingName;
	}
	
	public boolean isPayed() {
		return payed;
	}

	public Bill getNext() {
		return next;
	}
	
}
