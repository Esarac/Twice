package model;

import java.io.Serializable;

/**
* <b>Description:</b> The class Slot in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class Slot<T extends Vehicle> implements Serializable {
	
	//Attributes
	private String id;
	private long initTime;
	private T actualVehicle;
	//Suppliers
	private Slot<T> prev;
	private Slot<T> next;
	
	//Constructor
	
	/**
	 * <b>Description:</b> Creates a new instance of Slot.<br>
	 * @param id The slot id.
	 * @param initTime The time (In milliseconds) when the vehicle enters the parking.
	 * @param actualVehicle The vehicle in the parking.
	 */
	public Slot(String id, long initTime, T actualVehicle) {
		
		this.id = id;
		this.initTime = initTime;
		this.actualVehicle = actualVehicle;
	}
	
	//Methods
	
	//Add
	
	public void addSlot(Slot<?> slot) {
		
		
	}
}