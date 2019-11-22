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
	private Slot prev;
	private Slot next;
	
}