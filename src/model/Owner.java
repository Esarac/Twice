package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
* <b>Description:</b> The class Owner in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class Owner extends User implements FileLoader<Parking>{

	//Attributes
	private ArrayList<Parking> parkings;
	
	//Constructor
	
	/**
	 * <b>Description:</b> Creates a new instance of Owner.<br>
	 * @param name The owner name.
	 * @param email The owner email.
	 * @param password The owner password - password must be a minimum of eight (8) characters in length and contain at least one (1) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9).
	 */
	
	public Owner(String name, String email, String password) {
		
		super(name, email, password);
		this.parkings = new ArrayList<Parking>();
	}
	
	//Methods
	
	
	//Add
	
	/**
	 * <b>Description:</b> This method allows adding a parking.<br>
	 * <b>Pre:</b> No one parameter can be null.<br>
	 * <b>Post:</b> The parking was added.<br>
	 * @param name The parking name.
	 * @param address The parking address.
	 * @param city The parking city.
	 * @param department The parking department.
	 * @param country The parking country.
	 * @param email The parking contact email.
	 * @param information The parking description.
	 * @param pricePerHour The parking price per hour.
	 */
	
	public boolean addParking(String name, String address, String city, String department, String country, String email, String information, double[] pricePerHour) {
		
		boolean added = true;
		boolean running = true;
		
		for(int i = 0; i < parkings.size() && running; i++) {
			
			if(parkings.get(i).getName().equals(name)) {
				
				added = false;
				running = false;
				
			}
		}
		
		if(added) {
			
			parkings.add(new Parking(name, address, city, department, country, email, information, pricePerHour));
		}
		
		return added;
	}
	
	/**
	 * <b>Description:</b> This method allows adding a parking.<br>
	 * @param path The file path.
	 * @return True if the parking could be added, false otherwise.
	 */
	
	public boolean addParking(String path) {
		
		boolean added = true;
		Parking parking = load(path);
		
		if(parking != null) {
			
			boolean running = true;
			
			for(int i = 0; i < parkings.size() && running; i++) {
				
				if(parkings.get(i).getName().equals(parking.getName())) {
					
					added = false;
				}
			}
		}
		else {
			
			added = false;
		}
		
		if(added) {
			
			parkings.add(parking);
		}
		
		return added;
	}
	
	//Sort
	
	/**
	 *<b>Description:</b> This method allows sorting the parkings from minor to major by the name.<br>
	 *<b>Post:</b> The parkings are sorted by name from minor to major.<br>
	 */
	
	//Insertion sort
	public void sortParkingsByName() {
		
		for(int i = 1; i < parkings.size(); i++){
			for(int j = i - 1; j >= 0 && parkings.get(j).compareTo(parkings.get(j+1)) > 0; j--){
				
				Parking one = parkings.get(j);
				Parking two = parkings.get(j+1);
				
				parkings.set(j, two);
				parkings.set(j+1, one);
			}
		}
	}
	
	/**
	 *<b>Description:</b> This method allows sorting the parkings from minor to major by the country.<br>
	 *<b>Post:</b> The parkings are sorted by country from minor to major.<br>
	 */
	
	//Selection sort
	public void sortParkingsByCountry() {
		
		for(int i = 0; i < parkings.size() -1; i++){
			
			Parking minor = parkings.get(i);
			int minorPos = i;
			
			for(int j = i + 1; j < parkings.size(); j++){
				
				Parking actual = parkings.get(j);
				
				if(actual.compare(actual, minor) < 0){
					
					minor = actual;					
					minorPos = j;
				}
			}
			
			Parking tmp = parkings.get(i);
			parkings.set(i, minor);
			parkings.set(minorPos, tmp);
		}
	}
	
	/**
	 *<b>Description:</b> This method allows sorting the parkings from minor to major by the price.<br>
	 *<b>Post:</b> The parkings are sorted by price from minor to major.<br>
	 */
	
	//Bubble sort
	public void sortParkingsByPrice() {
		
		for(int i = parkings.size(); i > 0; i--){	
			for(int j = 0; j < i - 1; j++){
				
				if(parkings.get(j).compareByAverage(parkings.get(j+1)) > 0){
					
					Parking tmp = parkings.get(j);
					parkings.set(j, parkings.get(j+1));
					parkings.set(j+1, tmp);
				}
			}
		}
	}
	
	//Search
	
	/**
	 * <b>Description:</b> This method allows searching for parking that exactly or partially match the name.<br>
	 * @param name The parking name.
	 * @return The parkings that exactly or partially matches the name.
	 */
	
	public ArrayList<Parking> searchParkings(String name) {
		
		sortParkingsByName();
		boolean found = false;
		int start = 0;
		int end = parkings.size() - 1;
		ArrayList<Parking> parkingsSearched = new ArrayList<Parking>();
		String shortName;
		String value;
		
		while((start <= end) && !found) {
			
			int middle = ((start + end) / 2);
			
			value = parkings.get(middle).getName();
			shortName = value.length() > name.length() ? value.substring(0, name.length()) : value;
			
			if(shortName.compareToIgnoreCase(name) == 0) {
				
				parkingsSearched.add(parkings.get(middle));
				searchRight(middle, name, parkingsSearched);
				searchLeft(middle, name, parkingsSearched);
				found = true;
			}
			else if(value.compareToIgnoreCase(name) > 0) {
				
				end = middle - 1;
			}
			else {
				
				start = middle + 1;
			}
		}
		
		return parkingsSearched;
	}
	
	/**
	 *<b>Description:</b> This method allows searching in the right from the index indicated.<br>
	 *<b>Post:</b> The parking that exactly or partially matches the name was added.<br>
	 * @param index The actual position.
	 * @param name The parking name.
	 * @param parkingsSearched The current list of parkings.
	 */
	
	public void searchRight(int index, String name, ArrayList<Parking> parkingsSearched) {
		
		boolean is = true;
		String value;
		String shortName;
		
		for(int i = index + 1; i < parkings.size() && is; i++) {
			
			value = parkings.get(i).getName();
			shortName = (value.length() > name.length()) ? value.substring(0, name.length()): value;
			
			if(!shortName.equalsIgnoreCase(name)) {
				
				is = false;
			}
			else{
				
				parkingsSearched.add(parkings.get(i));
			}
		}
	}
	
	/**
	 *<b>Description:</b> This method allows searching in the left from the index indicated.<br>
	 *<b>Post:</b> The parking that exactly or partially matches the name was added.<br>
	 * @param index The actual position.
	 * @param name The parking name.
	 * @param parkingsSearched The current list of parkings.
	 */
	
	public void searchLeft(int index, String name, ArrayList<Parking> parkingsSearched) {
		
		boolean is = true;
		String value;
		String shortName;
		
		for(int i = index - 1; i >= 0 && is; i--) {
			
			value = parkings.get(i).getName();
			shortName = (value.length() > name.length()) ? value.substring(0, name.length()): value;
			
			if(!shortName.equalsIgnoreCase(name)) {
				
				is = false;
			}
			else{
				
				parkingsSearched.add(parkings.get(i));
			}
		}
	}
	
	/**
	 * <b>Description:</b> This method allows reading a file.<br>
	 * @param path The file path.
	 * @return The file content if the file exists, null in otherwise.
	 * @throws IOException If an I/O error occurs.
	 */

	@Override
	public String read(String path) throws IOException {//[FILE]
		
		String data = null;
		
		File test = new File(path);
		
		if(test.isFile()) {
			
			FileReader file = new FileReader(test);
			BufferedReader reader = new BufferedReader(file);
			String line;
			
			while((line = reader.readLine()) != null) {
				
				if(!line.isEmpty() && line.charAt(0) != '*') {
					
					data += line + "\n";
				}
			}
			
			reader.close();
		}
		
		return data;
	}
	
	/**
	 * <b>Description:</b> This method allows creating a Parking form a file.<br>
	 * @param path The file path.
	 * @return A parking with the attributes given if the file exists and is a valid file, null in otherwise.
	 * @throws IOException If an I/O error occurs.
	 */
	
	@Override
	public Parking load(String path) {//[FILE]
		
		Parking parking;
		
		try {
			
			String [] attributes = read(path).split("\n");
			
			double[] pricePerHour = new double[3];
			pricePerHour[0] = Double.parseDouble(attributes[7]);
			pricePerHour[1] = Double.parseDouble(attributes[8]);
			pricePerHour[2] = Double.parseDouble(attributes[9]);
			
			parking = new Parking(attributes[0], attributes[1], attributes[2], attributes[3], attributes[4], attributes[5], attributes[6], pricePerHour);
		}
		catch(IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
			
			parking = null;
		}
		
		return parking;
	}
	
	//Getters
	
	/**
	 * <b>Description:</b> Gets the value of the attribute parkings.<br>
	 * @return The attribute parkings.
	 */
	
	public ArrayList<Parking> getParkings() {
		return parkings;
	}
	
	//Setters
	
	/**
	 * <b>Description:</b> Sets the value of the attribute parkings.<br>
	 * @param parking the owner parkings. 
	 */
	
	public void setParkings(ArrayList<Parking> parkings) {
		this.parkings = parkings;
	}
}
