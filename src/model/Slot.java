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
	private Slot<?> prev;
	private Slot<?> next;
	
	//Constructor
	
	/**
	 * <b>Description:</b> Creates a new instance of Slot.<br>
	 * @param id The slot id.
	 * @param initTime The time (In milliseconds) when the vehicle enters the parking.
	 * @param actualVehicle The vehicle in the parking.
	 */
	public Slot(String id, long initTime) {
		
		this.id = id;
		this.initTime = initTime;
	}
	
	//Methods
	
	//Add
	
	public void addSlot(Slot<?> slot) {
		
		if(next == null) {
			
			next = slot;
			slot.setPrev(this);
		}
		else {
			
			next.addSlot(slot);
		}
	}
	
	public int size() {
		
		int size;
		
		if(next == null) {
			
			size = 1;
		}
		else {
			
			size = 1 + next.size();
		}
		
		return size;
	}
	
	public Slot<?> getSlot(int index) {
		
		Slot<?> slot;
		
		if(index == 0) {
			
			slot = this;
		}
		else {
			
			slot = next.getSlot(index - 1);
		}
		
		return slot;
	}
	
	
	public boolean addVehicle(Vehicle vehicle) {
		
		boolean added = true;
		
		try {
			actualVehicle = (T) vehicle;
		}
		catch(ClassCastException e) {
			
			added = false;
		}
		
		return added;
	}
	
	//Getters
	
	/**
	 * <b>Description:</b> Gets the value of the attribute actualVehicle.<br>
	 * @return The attribute actualVehicle.
	 */
	
	public T getActualVehicle() {
		return actualVehicle;
	}
	
	/**
	 * <b>Description:</b> Gets the value of the attribute id.<br>
	 * @return The attribute id.
	 */
	
	public String getId() {
		return id;
	}
	
	//Setters
	
	/**
	 * <b>Description:</b> Sets the value of prev.<br>
	 * @param slot the new prev. 
	 */

	public void setPrev(Slot<?> slot) {
		
		this.prev = slot;
	}
}