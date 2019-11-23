package model;

import java.io.Serializable;
import java.util.Calendar;

/**
* <b>Description:</b> The abstract class Record in the package model.<br>
* @author VoodLyc & Esarac.
*/

public abstract class Record implements Serializable{

	//Attributes
	private Calendar entryDate;
	private Calendar departureDate;
	private double price;
	
	//Constructor
	public Record(Calendar entryDate) {
		this.entryDate=entryDate;
	}
	
	//Get
	public double getPrice(){
		return price;
	}
	
	public Calendar getEntryDate() {
		return entryDate;
	}
	
}
