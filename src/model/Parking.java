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
	private Slot firstSlot;
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
	//Add
	public void addSlot(int amount, Class<? extends Vehicle> type) {
		
		if(amount > 0 && firstSlot == null) {
			
			firstSlot = new Slot("1", type);
			
			for(int i = 1; i < amount; i++) {
				
				firstSlot.addSlot(new Slot((i+1) + "", type));
				
			}
		}
		else {
			
			int prev = slotsSize();
			
			for(int i = (prev + 1); i < (amount + (prev + 1)); i++) {
				
				firstSlot.addSlot(new Slot(i + "", type));
			}
		}
	}
	
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
	
	//Search
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
	
	//Calculate
	public boolean isEmpty(){
		boolean empty=true;
		
		Slot actual=firstSlot;
		while((actual!=null) && empty) {
			empty=actual.isEmpty();
			actual=actual.getNext();
		}
		
		return empty;
	}
	
	public int slotsSize() {
		int size=0;
		
		Slot actual=firstSlot;
		while(actual != null) {
			
			size++;
			actual=actual.getNext();
			
		}
		
		return size;
	}
	
	public double calculateAverage() {
		
		double average=0;
		
		for(double price: pricePerHour){
			
			average+=price;
			
		}
		
		average/=pricePerHour.length;
		
		return average;
	}
	
	public int emptySlotsQuantity(Class<? extends Vehicle> type){
		int size=0;
		
		Slot actual=firstSlot;
		while(actual != null) {
			
			if((type.equals(actual.getType())) && (actual.isEmpty())){
				size++;
			}
			actual=actual.getNext();
			
		}
		
		return size;
	}
	
	public String generateMapUrl(){
		String webAddress="https://www.google.com/maps/place/";
		String address=getCompleteAddress();
		for(int i=0; i<address.length(); i++){
			if(address.charAt(i)==' '){
				webAddress+="+";
			}
			else if(address.charAt(i)=='#'){
				webAddress+="%23";
			}
			else{
				webAddress+=address.charAt(i);
			}
		}
		return webAddress;
	}
	
	//Compare
	/**
	 *<b>Description:</b> This method allows comparing a parking with other by the name.<br>
	 *@param owner The parking with which it compares.
	 *@return 0 if the names are equals, 1  if the parking which it compares is major, -1 if the parking which it compares is minor.
	 */
	
	@Override
	public int compareTo(Parking parking) {
		
		return name.compareTo(parking.getName());
		
	}
	
	/**
	 *<b>Description:</b> This method allows comparing a parking with other by the country.<br>
	 *@param owner The parking with which it compares.
	 *@return 0 if the names are equals, 1  if the parking which it compares is major, -1 if the parking which it compares is minor.
	 */
	
	@Override
	public int compare(Parking parking, Parking parking1) {
		
		return parking.getCountry().compareTo(parking1.getCountry());
		
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
	
	public String getCompleteAddress() {
		return address+", "+city+", "+department+", "+country;
	}
	
	/**
	 * <b>Description:</b> Gets the value of the attribute pricePerHour.<br>
	 * @return The attribute pricePerHour.
	 */
	
	public double[] getPricePerHour() {
		return pricePerHour;
	}
	
	public Slot getSlot(int index) {
		
		Slot slot = null;
		
		if(firstSlot != null) {
			
			slot = firstSlot.getSlot(index);
		}
		
		return slot;
		
	}
	
}