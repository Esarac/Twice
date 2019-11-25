package model;

import java.io.Serializable;
import java.util.GregorianCalendar;

import exception.SlotMismatchException;

/**
* <b>Description:</b> The class Slot in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class Slot implements Serializable {
	
	//Attributes
	private int id;
	private long initTime;
	private Vehicle actualVehicle;
	private Class<? extends Vehicle> type;
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
	public Slot(int id, Class<? extends Vehicle> type) {
		
		this.id = id;
		this.type = type;
		
	}
	
	//Methods
	
	//Add
	public boolean insertVehicle(Vehicle vehicle, String parkingName, String parkingAddress) {
		
		boolean added = true;
		
		if(actualVehicle == null) {
			if(type.isAssignableFrom(vehicle.getClass())) {
				actualVehicle =  vehicle;
				actualVehicle.addBill(new GregorianCalendar(), parkingName, parkingAddress);
				initTime = System.nanoTime();
			}
			else {
				try {
					
					throw new SlotMismatchException();
					
				} catch (SlotMismatchException e) {
					
					added = false;
				}
			}
		}
		
		return added;
	}
	
	//Delete
	public double removeVehicle(double[] pricePerHour) {
		
		long end = System.nanoTime();
		double delta = (end - initTime) * 2.7778e-13;
		double price = 0;
		
		if(actualVehicle instanceof Car) {
			
			price = pricePerHour[0] * delta;
		}
		else if(actualVehicle instanceof Motorcycle) {
			
			price = pricePerHour[1] * delta;
		}
		else if(actualVehicle instanceof Bicycle ) {
			
			price = pricePerHour[2] * delta;
		}
		
		actualVehicle.getFirstBill().setDepartureDate(new GregorianCalendar());
		actualVehicle.getFirstBill().setPrice(price);
		actualVehicle = null;
		
		return price;
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
	
	public int getId() {
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
	
	public void setNext(Slot next){
		this.next=next;
	}
	
}