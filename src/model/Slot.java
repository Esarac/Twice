package model;

import java.io.Serializable;
import java.util.GregorianCalendar;

import exception.FullSlotException;
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
		this.initTime=0;
		this.actualVehicle=null;
		this.type = type;
		
	}
	
	//Methods
	
	//Add
	
	/**
	 * <b>Description:</b> This method allows inserting a vehicle in the slot.<br>
	 * @param vehicle The vehicle who will be add.
	 * @param client The client that try to add the vehicle.
	 * @param slotId The slot id.
	 * @throws SlotMismatchException If the vehicle does not match with the slot type.
	 * @throws FullSlotException If the slot already have a vehicle.
	 */
	
	public void insertVehicle(Vehicle vehicle, String parkingName, String parkingAddress) throws SlotMismatchException, FullSlotException{
		if(actualVehicle == null) {
			if(type.isAssignableFrom(vehicle.getClass())) {
				actualVehicle =  vehicle;
				actualVehicle.addBill(new GregorianCalendar(), parkingName, parkingAddress);
				initTime = System.nanoTime();
			}
			else {
				throw new SlotMismatchException();
			}
		}
		else{
			throw new FullSlotException();
		}
	}
	
	//Delete
	
	/**
	 * <b>Description:</b> This method allows deleting a vehicle.<br>
	 * @param client The client who want to deleted a vehicle.
	 * @return True if the vehicle could be deleted, false in otherwise.
	 */
	
	public double removeVehicle(double[] pricePerHour) {
		double price=0;
		if((actualVehicle!=null) && (initTime!=0)){
			price=calculatePrice(pricePerHour);
			
			actualVehicle.getFirstBill().setDepartureDate(new GregorianCalendar());
			actualVehicle.getFirstBill().setPrice(price);
			
			this.initTime=0;
			this.actualVehicle = null;
		}
		return price;
	}
	
	//Calculate
	
	/**
	 * <b>Description:</b> This method allows calculating the price.<br>
	 * @param pricePerHour The parking price per hour. 
	 * @return the price.
	 */
	
	public double calculatePrice(double[] pricePerHour){
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
		
		return price;
	}
	
	//Setters
	
	/**
	 * <b>Description:</b> Sets the value of the attribute initTime.<br>
	 * @param slot the attribute initTime. 
	 */
	
	public void setInitTime(long initTime) {
		this.initTime=initTime;
	}
	
	/**
	 * <b>Description:</b> Sets the value of prev.<br>
	 * @param slot the new prev. 
	 */
	
	public void setPrev(Slot slot) {
		
		this.prev = slot;
	}
	
	/**
	 * <b>Description:</b> Sets the value of next.<br>
	 * @param slot the new next. 
	 */
	
	public void setNext(Slot next){
		this.next=next;
	}
	
	//Getters
	
	/**
	 * <b>Description:</b> Gets the value of the attribute initTime.<br>
	 * @return The attribute initTime.
	 */
	
	public long getInitTime(){
		return initTime;
	}
	
	/**
	 * <b>Description:</b> Gets the value of the attribute actualVehicle.<br>
	 * @return The attribute actualVehicle.
	 */
	
	public Vehicle getActualVehicle() {
		return actualVehicle;
	}
	
	/**
	 * <b>Description:</b> This method allows checking if the actual user is null.<br>
	 * @return True if is empty, false in otherwise.
	 */
	
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
	
	/**
	 * <b>Description:</b> Gets the value of the attribute type.<br>
	 * @return The attribute type.
	 */
	
	public Class<? extends Vehicle> getType(){
		return type;
	}
	
	/**
	 * <b>Description:</b> Gets the value of net.<br>
	 * @return next.
	 */
	
	public Slot getNext(){
		return next;
	}
	
	/**
	 * <b>Description:</b> This method allows converting a object in string.<br>
	 * @return The object as String.
	 */
	
	public String toString() {
		String letter="V";
		if(type.equals(MotorVehicle.class))letter="MV";
		if(type.equals(Car.class))letter="C";
		if(type.equals(Motorcycle.class))letter="M";
		if(type.equals(Bicycle.class))letter="B";
		return letter+id;
	}
	
}