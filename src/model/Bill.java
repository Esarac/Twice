package model;

import java.util.Calendar;

/**
* <b>Description:</b> The class Bill in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class Bill extends Record{

	//Attributes
	private String parkingAddress;
	private boolean payed;
	//Supplier
	private Bill next;
	
	//Constructor
	public Bill(Calendar entryDate, String parkingAddress) {
		super(entryDate);
		this.parkingAddress=parkingAddress;
		this.payed=false;
	}

	//Get
	public Bill getNext() {
		return next;
	}
	
}
