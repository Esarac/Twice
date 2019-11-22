package model;

import java.util.Calendar;

/**
* <b>Description:</b> The class Report in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class Report extends Record{

	//Attributes
	private String clientName;
	private String carPlate;
	//Suppliers
	private Report left;
	private Report right;
	
	/**
	 * <b>Description:</b> Creates a new instance of Report.<br>
	 * @param entryDate The entry date on the parking.
	 * @param clientName The name of the client that uses the parking.
	 * @param carPlate The car license plate.
	 */
	
	public Report(Calendar entryDate, String clientName, String carPlate) {
		
		super(entryDate);
		this.clientName = clientName;
		this.carPlate = carPlate;
	}
}
