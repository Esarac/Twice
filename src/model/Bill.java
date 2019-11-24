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
	
	/**
	 * <b>Description:</b> Creates a new instance of Bill.<br>
	 * @param entryDate The entry date of the vehicle in the parking.
	 * @param parkingName The parking name.
	 * @param parkingAddress The parking address.
	 */
	
	public Bill(Calendar entryDate, String parkingName, String parkingAddress) {
		super(entryDate);
		this.parkingName=parkingName;
		this.parkingAddress=parkingAddress;
		this.payed=false;
	}

	//Setters
	
	/**
	 * <b>Description:</b> This method allows paying the bill.<br>
	 * <b>Post:</b> Sets the value of payed in true.<br>
	 */
	
	public void pay(){
		this.payed=true;
	}
	
	/**
	 * <b>Description:</b> Sets the value of the attribute next.<br>
	 * @param next the new value of the attribute next. 
	 */
	
	public void setNext(Bill next) {
		this.next=next;
	}
	
	//Getters
	
	/**
	 * <b>Description:</b> Gets the value of the attribute parkingName.<br>
	 * @return The attribute parkingName.
	 */
	
	public String getParkingName(){
		return parkingName;
	}
	
	/**
	 * <b>Description:</b> Gets the value of the attribute payed.<br>
	 * @return The attribute payed.
	 */
	
	public boolean isPayed() {
		return payed;
	}
	
	/**
	 * <b>Description:</b> Gets the value of the attribute next.<br>
	 * @return The attribute next.
	 */

	public Bill getNext() {
		return next;
	}
	
}
