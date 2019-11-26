package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

import exception.FullSlotException;
import exception.SlotMismatchException;

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
	
	/**
	 * <b>Description:</b> This method allows adding slots.<br>
	 * @param amount The number of slots to be added.
	 * @param type The slots type.
	 */
	
	public void addSlot(int amount, Class<? extends Vehicle> type) {
		Slot first=null;
		int slotNumber=0;
		if(firstSlot!=null){
			slotNumber=firstSlot.getId();
		}
		
		
		for(int i=0; i<amount; i++){
			slotNumber++;
			
			if(first!=null){
				
				Slot actual=new Slot(slotNumber, type);
				actual.setNext(first);//A->F
				first.setPrev(actual);//A<-F
				first=actual;
				
			}
			else {
				
				first=new Slot(slotNumber, type);
				
				if(firstSlot!=null){
					first.setNext(firstSlot);//F->FS
					firstSlot.setPrev(first);//F<-FS
				}
				
			}
			
		}
		
		this.firstSlot=first;
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
	
	/**
	 * <b>Description:</b> This method allows inserting a vehicle in the slot given.<br>
	 * @param vehicle The vehicle who will be add.
	 * @param client The client that try to add the vehicle.
	 * @param slotId The slot id.
	 * @throws SlotMismatchException If the vehicle does not match with the slot type.
	 * @throws FullSlotException If the slot already have a vehicle.
	 */
	
	public void insertVehicle(Vehicle vehicle, Client client, int slotId) throws SlotMismatchException, FullSlotException{
		
		if(searchSlotOwnerVehicle(client)==null){
			boolean found=false;
			Slot actual=firstSlot;
			while(actual!=null && !found){
				if(actual.getId()==slotId){
					//Insertion
					found=true;
					actual.insertVehicle(vehicle, name, getCompleteAddress());
					if(vehicle instanceof MotorVehicle){
						addReport(client.getEmail(), ((MotorVehicle)vehicle).getPlate() );
					}
					else{
						addReport(client.getEmail(), null);
					}
					//...
				}
				else{
					actual=actual.getNext();
				}
			}
		}
		
	}
	
	//Delete
	
	/**
	 * <b>Description:</b> This method allows deleting a vehicle.<br>
	 * @param client The client who want to deleted a vehicle.
	 * @return True if the vehicle could be deleted, false in otherwise.
	 */
	
	public boolean removeVehicle(Client client) {
		boolean possible=false;
		
		Slot actual=searchSlotOwnerVehicle(client);
		if(actual!=null){
			//Removed
			possible=true;
			
			double price=actual.removeVehicle(pricePerHour);
			Report report=searchLastReport(client.getEmail());
			report.setPrice(price);
			report.setDepartureDate(new GregorianCalendar());
			//...
		}
		
		return possible;
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
	
	/**
	 * <b>Description:</b> This method allows searching the last report.<br>
	 * @param email The client email
	 * @return The report if could be found, false in otherwise.
	 */
	
	public Report searchLastReport(String email){
		Report prev=null;
		Report actual = searchReport(email);
		while(actual!=null){
			prev=actual;
			actual=prev.getNext();
		}
		return prev;
	}
	
	/**
	 * <b>Description:</b> This method allows searching the slot where the owner has a vehicle.<br>
	 * @param client The client that has the vehicle.
	 * @return The slot if could be found, null in otherwise.
	 */
	
	public Slot searchSlotOwnerVehicle(Client client){
		boolean found=false;
		Slot slot=null;
		
		Slot actual=firstSlot;
		while((actual!=null) && !found){
			
			if(!actual.isEmpty()){
				
				for(Vehicle vehicle: client.getVehicles()){
					if(vehicle.equals(actual.getActualVehicle())){
						found=true;
						slot=actual;
					}
				}
				
			}
			
			actual=actual.getNext();
			
		}
		
		return slot;
	}
	
	//Calculate
	
	/**
	 * <b>Description:</b> This method allows checking if the parking is empty.<br>
	 * @return True if the parking is empty, false in otherwise.
	 */
	
	public boolean isEmpty(){
		boolean empty=true;
		
		Slot actual=firstSlot;
		while((actual!=null) && empty) {
			empty=actual.isEmpty();
			actual=actual.getNext();
		}
		
		return empty;
	}
	
	/**
	 * <b>Description:</b> This method allows calculating the price average.<br>
	 * @return the price average.
	 */
	
	public double calculateAverage() {
		
		double average=0;
		
		for(double price: pricePerHour){
			
			average+=price;
			
		}
		
		average/=pricePerHour.length;
		
		return average;
	}
	
	/**
	 * <b>Description:</b> This method allows counting the number of empty slots of specific type.<br>
	 * @param type The slot type.
	 * @return The number of empty slots of that type.
	 */
	
	public int emptySlotsQuantity(Class<? extends Vehicle> type){
		int size=0;
		
		Slot actual=firstSlot;
		while(actual != null) {
			
			if((actual.getType().isAssignableFrom(type)) && (actual.isEmpty())){
				size++;
			}
			actual=actual.getNext();
			
		}
		
		return size;
	}
	
	/**
	 * <b>Description:</b> This method allows generation a google maps url with the parking address.<br>
	 * @return the google maps url.
	 */
	
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
		
		return name.compareToIgnoreCase(parking.getName());
		
	}
	
	/**
	 *<b>Description:</b> This method allows comparing a parking with other by the country.<br>
	 *@param owner The parking with which it compares.
	 *@return 0 if the names are equals, 1  if the parking which it compares is major, -1 if the parking which it compares is minor.
	 */
	
	@Override
	public int compare(Parking parking1, Parking parking2) {
		int delta=parking1.country.compareToIgnoreCase(parking2.country);
		if(delta==0) {
			delta=parking1.department.compareToIgnoreCase(parking2.department);
			if(delta==0){
				delta=parking1.city.compareToIgnoreCase(parking2.city);
				if(delta==0) {
					delta=parking1.address.compareToIgnoreCase(parking2.address);
				}
			}
		}
		
		return delta;
	}
	
	/**
	 *<b>Description:</b> This method allows comparing a parking with other by the pricePerHour.<br>
	 *@param owner The parking with which it compares.
	 *@return 0 if the names are equals, 1  if the parking which it compares is major, -1 if the parking which it compares is minor.
	 */
	
	public int compareByAverage(Parking parking) {
		double delta=(calculateAverage()-parking.calculateAverage())*1000;
		return (int)delta;
	}
	
	//Getter
	
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
	
	public String getEmail() {
		return email;
	}
	
	/**
	 * <b>Description:</b> Gets the value of the attribute pricePerHour.<br>
	 * @return The attribute pricePerHour.
	 */
	
	public double[] getPricePerHour() {
		return pricePerHour;
	}
	
	public Slot getFirstSlot() {
		return firstSlot;
	}
	
	public Report getRootReport() {
		return rootReport;
	}
	
	public String toString() {
		return name;
	}
	
}