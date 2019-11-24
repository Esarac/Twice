package model;

import java.io.Serializable;

/**
* <b>Description:</b> The class Slot in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class Slot implements Serializable {
	
	//Attributes
	private String id;
	private long initTime;
	private Vehicle actualVehicle;
	private Class<? extends Vehicle> type;//[BAD]
	//Suppliers
	private Slot prev;
	private Slot next;
	//Constructor
	
	/**
	 * <b>Description:</b> Creates a new instance of Slot.<br>
	 * @param id The slot id.
	 * @param initTime The time (In milliseconds) when the vehicle enters the parking.
	 * @param actualVehicle The vehicle in the parking.
	 */
	public Slot(String id, Class<? extends Vehicle> type) {
		
		this.id = id;
		this.type = type;
		
	}
	
	//Methods
	
	//Add
	public void addSlot(Slot slot) {
		
		if(next == null) {
			
			next = slot;
			slot.setPrev(this);
		}
		else {
			
			next.addSlot(slot);
		}
	}
	

	public <T extends Vehicle> boolean addVehicle(T vehicle) {//[BAD]
		
		boolean added = true;
		
		try {
			
			actualVehicle =  type.cast(vehicle);
		}
		catch(ClassCastException e) {
			
			added = false;
		}
		
		return added;

	}
	
	public Slot getSlot(int index) {
		
		Slot slot;
		
		if(index == 0) {
			slot = this;
		}
		else {
			slot = next.getSlot(index - 1);
		}
		
		return slot;
	}
	
	//Getters
	
	/**
	 * <b>Description:</b> Gets the value of the attribute actualVehicle.<br>
	 * @return The attribute actualVehicle.
	 */
	
	public Vehicle getActualVehicle() {
		return actualVehicle;
	}
	
	public boolean isEmpty(){
		return actualVehicle==null;
	}
	
	/**
	 * <b>Description:</b> Gets the value of the attribute id.<br>
	 * @return The attribute id.
	 */
	
	public String getId() {
		return id;
	}
	
	public Class<? extends Vehicle> getType(){
		return type;
	}
	
	public Slot getNext(){
		return next;
	}
	
	//Setters
	
	/**
	 * <b>Description:</b> Sets the value of prev.<br>
	 * @param slot the new prev. 
	 */
	
	public void setPrev(Slot slot) {
		
		this.prev = slot;
	}
}