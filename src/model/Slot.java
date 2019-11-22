package model;

/**
* <b>Description:</b> The class Slot in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class Slot<T extends Vehicle> {
	
	//Attributes
	private String id;
	private long initTime;
	private T actualVehicle;
	//Suppliers
	private Slot<T> prev;
	private Slot<T> next;
	
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
}