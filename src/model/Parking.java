package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;

/**
* <b>Description:</b> The class Parking in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class Parking implements Comparable<Parking>, Comparator<Parking>, Serializable {

	//Attributes
	private String name;
	private String address;
	private String city;
	private String department;
	private String country;
	private String email;
	private String information;
	private double[] pricePerHour;//[ASK]
	private Slot<?> firstSlot;
	private Report rootReport;
	
	//Constructor
	
	/**
	 * <b>Description:</b> Creates a new instance of Parking.<br>
	 * @param name The parking name.
	 * @param address The parking address.
	 * @param city The parking city.
	 * @param department The parking department.
	 * @param country The parking country.
	 * @param email The parking contact email.
	 * @param information The parking description.
	 * @param pricePerHour The parking price per hour.
	 */
	
	public Parking(String name, String address, String city, String department, String country, String email, String information, double[] pricePerHour) {
		
		this.name = name;
		this.address = address;
		this.city = city;
		this.department = department;
		this.country = country;
		this.email = email;
		this.information = information;
		this.pricePerHour = pricePerHour;
	}
	
	//Methods
	
	/**
	 *<b>Description:</b> This method allows comparing a parking with other by the name.<br>
	 *@param owner The parking with which it compares.
	 *@return 0 if the names are equals, 1  if the parking which it compares is major, -1 if the parking which it compares is minor.
	 */
	
	@Override
	public int compareTo(Parking parking) {
		
		int result = 0;
		
		if(name.compareTo(parking.getName()) > 0){
			
			result = 1;
		}
		else if(name.compareTo(parking.getName()) < 0){
			
			result = -1;
		}
		
		return result;
	}
	
	/**
	 *<b>Description:</b> This method allows comparing a parking with other by the country.<br>
	 *@param owner The parking with which it compares.
	 *@return 0 if the names are equals, 1  if the parking which it compares is major, -1 if the parking which it compares is minor.
	 */
	
	@Override
	public int compare(Parking parking, Parking parking1) {
		
		int result = 0;
		
		if(parking.getCountry().compareTo(parking1.getCountry()) > 0) {
			
			result = 1;
		}
		else if(parking.getCountry().compareTo(parking1.getCountry()) < 0) {
			
			result = -1;
		}
		
		return result;
	}
	
	/**
	 *<b>Description:</b> This method allows comparing a parking with other by the pricePerHour.<br>
	 *@param owner The parking with which it compares.
	 *@return 0 if the names are equals, 1  if the parking which it compares is major, -1 if the parking which it compares is minor.
	 */
	
	public int compareByAverage(Parking parking) {
		
		int result = 0;
		
		double average = ((pricePerHour[0] + pricePerHour[1] + pricePerHour[2]) / 3);
		double[] price = parking.getPricePerHour();
		double average2 = ((price[0] + price[1] + price[2]) / 3);
		
		if(average > average2) {
			
			result = 1;
		}
		else if(average < average2) {
			
			result = -1;
		}
		
		return result;
	}
	
	//Add
	
	/**
	 * <b>Description:</b> This method allows adding a report.<br>
	 * <b>Post:</b> The report was added.<br>
	 * @param clientEmail The client email.
	 * @param carPlate The car license plate.
	 */
	
	public void addReport(String clientEmail, String carPlate) {
		
		if(rootReport != null) {
			
			rootReport.addReport(new Report(Calendar.getInstance(), clientEmail, carPlate));
		}
		else {
			
			rootReport = new Report(Calendar.getInstance(), clientEmail, carPlate);
		}
	}
	
	/**
	 * <b>Description:</b> This method allows searching the reports that match the email.<br>
	 * @param email The report email.
	 * @return The reports if could be found, null in otherwise.
	 */
	
	public Report searchReport(String email) {
		
		Report report = null;
		
		if(rootReport != null) {
			
			report = rootReport.searchReports(email);
		}
		
		return report;
	}
	
	//Getters
	
	/**
	 * <b>Description:</b> Gets the value of the attribute name.<br>
	 * @return The attribute name.
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * <b>Description:</b> Gets the value of the attribute country.<br>
	 * @return The attribute country.
	 */
	
	public String getCountry() {
		return country;
	}
	
	/**
	 * <b>Description:</b> Gets the value of the attribute pricePerHour.<br>
	 * @return The attribute pricePerHour.
	 */
	
	public double[] getPricePerHour() {
		return pricePerHour;
	}
	
	public double calculateAverage() {
		return ((pricePerHour[0] + pricePerHour[1] + pricePerHour[2]) / 3);
	}
}
