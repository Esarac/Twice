  
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import exception.AlreadyExistException;
import exception.InvalidEmailException;
import exception.InvalidPasswordException;
import exception.InvalidPlateException;
import model.Car.CarType;
import model.MotorVehicle.VehicleFuel;
import model.Motorcycle.MotorcycleType;

/**
* <b>Description:</b> The class Client in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class Client extends User implements FileLoader<Vehicle>{//[TEST]

	//Attributes
	private ArrayList<Vehicle> vehicles;
	
	//Constructor
	
	/**
	 * <b>Description:</b> Creates a new instance of Client.<br>
	 * @param name The client name.
	 * @param email The client email.
	 * @param password The client password - password must be a minimum of eight (8) characters in length and contain at least one (1) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9).
	 */
	
	public Client(String name, String email, String password)throws InvalidEmailException, InvalidPasswordException{
		super(name, email, password);
		this.vehicles=new ArrayList<Vehicle>();
	}

	//Add
	
	/**
	 * <b>Description:</b> This method allows adding a vehicle from a file.<br>
	 * @param path The file path.
	 * @return True if the car was added, false in otherwise.
	 * @throws AlreadyExistException If already exists a vehicle with that name or license plate.
	 */
	
	public boolean addVehicle(String path) throws AlreadyExistException{
		boolean possible=true;
		Vehicle vehicle=load(path);
		if(vehicle!=null){
			
			if((searchVehicle(vehicle.getName())!=null)){//Name
				throw new AlreadyExistException();
			}
			if(vehicle instanceof MotorVehicle){//Plate
				if(searchMotorVehicle(((MotorVehicle)vehicle).getPlate())!=null){
					throw new AlreadyExistException();
				}
			}
			
			if(possible){
				vehicles.add(vehicle);
			}
		}
		else{
			possible=false;
		}
		
		return possible;
	}
	
	/**
	 * <b>Description:</b> This method allows adding a car.<br>
	 * @param name The car name.
	 * @param plate The car license plate - plate must be of 6 or 7 characters in length and contain minimum at least three (3) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9).
	 * @param fuel The car type of fuel.
	 * @param type The car type.
	 * @param polarized The level of polarized.
	 * @throws InvalidPlateException If the plate do not is 6 or 7 characters in length and contain minimum at least three (3) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9).
	 * @throws AlreadyExistException If already exists a car with that name or license plate.
	 */
	
	public void addCar(String name, String plate, VehicleFuel fuel, CarType type, int polarized) throws InvalidPlateException, AlreadyExistException{
		if((searchVehicle(name)==null) && (searchMotorVehicle(plate)==null)){
			vehicles.add(new Car(name, plate, fuel, type, polarized));
		}
		else{
			throw new AlreadyExistException();
		}
	}
	
	/**
	 * <b>Description:</b> This method allows adding a motorcycle.<br>
	 * @param name The motorcycle name
	 * @param plate The motorcycle license plate - plate must be of 6 or 7 characters in length and contain minimum at least three (3) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9).
	 * @param fuel The motorcycle type of fuel.
	 * @param type The motorcycle type.
	 * @param cylindered The motorcycle cylindered.
	 * @throws InvalidPlateException If the plate do not is 6 or 7 characters in length and contain minimum at least three (3) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9).
	 * @throws AlreadyExistException If already exists a motorcycle with that name or license plate.
	 */
	
	public void addMotorcycle(String name, String plate, VehicleFuel fuel, MotorcycleType type, int cylindered) throws InvalidPlateException, AlreadyExistException{
		if((searchVehicle(name)==null) && (searchMotorVehicle(plate)==null)){
			vehicles.add(new Motorcycle(name, plate, fuel, type, cylindered));
		}
		else{
			throw new AlreadyExistException();
		}
	}
	
	/**
	 * <b>Description:</b> This method allows adding a bicycle.<br>
	 * @param name The bicycle name.
	 * @param color The bicycle color.
	 * @throws AlreadyExistException If already exists a bicycle with that name.
	 */
	
	public void addBicycle(String name, String color) throws AlreadyExistException{
		if((searchVehicle(name)==null)){
			vehicles.add(new Bicycle(name, color));
		}
		else{
			throw new AlreadyExistException();
		}
	}
	
	//Delete
	
	/**
	 * <b>Description:</b> This method allows deleting a vehicle by the name.<br>
	 * @param name The vehicle name.
	 * @return True if the vehicle was deleted, false in otherwise.
	 */
	
	public boolean deleteVehicle(String name){
		sortVehiclesByName();
		boolean possible=false;
		
		boolean found=false;
		int start=0;
		int end=vehicles.size()-1;
		while((start<=end)&&!found) {
			int middle=(start+end)/2;
			if(vehicles.get(middle).getName().compareToIgnoreCase(name)==0){
				found=true;
				if(vehicles.get(middle).unpaidBills().size()==0){
					vehicles.remove(middle);
					possible=true;
				}
			}
			else if(vehicles.get(middle).getName().compareToIgnoreCase(name)<0){
				start=middle+1;
			}
			else{
				end=middle-1;
			}
		}
		
		return possible;
	}
	
	//Search
	
	/**
	 * <b>Description:</b> This method allows searching for a vehicle that exactly or partially matches the name.<br>
	 * @param name The vehicle name.
	 * @return The vehicles that exactly or partially matches the name.
	 */
	
	public ArrayList<Vehicle> searchVehicles(String name){
		sortVehiclesByName();
		ArrayList<Vehicle> vehicleList=new ArrayList<Vehicle>();
		
		boolean found=false;
		int start=0;
		int end=vehicles.size()-1;
		while((start<=end)&&!found) {
			int middle=(start+end)/2;
			
			String shortedName;
			try{shortedName=vehicles.get(middle).getName().substring(0,name.length());}
			catch(IndexOutOfBoundsException e){shortedName=vehicles.get(middle).getName();}
			if(shortedName.compareToIgnoreCase(name)==0){//Found
				//Left
				boolean runLeft=true;
				for(int i=middle; (i>=0) && runLeft; i--){
					try{
						if(name.equalsIgnoreCase(vehicles.get(i).getName().substring(0,name.length()))){
							vehicleList.add(vehicles.get(i));
						}
						else {
							runLeft=false;
						}
					}
					catch(IndexOutOfBoundsException e){runLeft=false;}
				}
				//...
				//Right
				boolean runRight=true;
				for(int i=middle+1; (i<vehicles.size()) && runRight; i++){
					try{
						if(name.equalsIgnoreCase(vehicles.get(i).getName().substring(0,name.length()))){
							vehicleList.add(vehicles.get(i));
						}
						else {
							runRight=false;
						}
					}
					catch(IndexOutOfBoundsException e){runRight=false;}
				}
				//...
				found=true;
			}//...
			else if(shortedName.compareToIgnoreCase(name)<0){
				start=middle+1;
			}
			else{
				end=middle-1;
			}
		}
		
		return vehicleList;
	}
	
	/**
	 * <b>Description:</b> This method allows searching a vehicle by the name.<br>
	 * @param name The vehicle name;
	 * @return The vehicle if could be found, null in otherwise.
	 */
	
	public Vehicle searchVehicle(String name){//Binary Search
		sortVehiclesByName();
		Vehicle vehicle=null;
		
		boolean found=false;
		int start=0;
		int end=vehicles.size()-1;
		while((start<=end)&&!found) {
			int middle=(start+end)/2;
			if(vehicles.get(middle).getName().compareToIgnoreCase(name)==0){
				vehicle=vehicles.get(middle);
				found=true;
			}
			else if(vehicles.get(middle).getName().compareToIgnoreCase(name)<0){
				start=middle+1;
			}
			else{
				end=middle-1;
			}
		}
		
		return vehicle;
	}
	
	/**
	 * <b>Description:</b> This method allows searching a motor vehicle by the name.<br>
	 * @param name The motor vehicle name;
	 * @return The motor vehicle if could be found, null in otherwise.
	 */
	
	public MotorVehicle searchMotorVehicle(String plate){
		sortVehiclesByPlate();
		MotorVehicle motorVehicle=null;
		boolean found=false;
		for(int i=0; (i<vehicles.size()) && !found; i++){
			if(vehicles.get(i) instanceof MotorVehicle){
				MotorVehicle vehicle=(MotorVehicle)vehicles.get(i);
				if(plate.equals(vehicle.getPlate())) {
					motorVehicle=vehicle;
				}
			}
		}
		return motorVehicle;
	}
	
	//Sort
	
	/**
	 * <b>Description:</b> This method allows sorting the vehicles from minor to major by the name.<br>
	 * <b>Post:</b> The vehicles are sorted by name from minor to major.<br>
	 */
	
	public void sortVehiclesByName(){//Selection
		for(int i=0; i<(vehicles.size()-1); i++){
			Vehicle min=vehicles.get(i);
			int minPos=i;
			for(int j=(i+1); j<vehicles.size(); j++){
				if(vehicles.get(j).compareTo(min)<0){
					min=vehicles.get(j);
					minPos=j;
				}
			}
			Vehicle actual=vehicles.get(i);
			vehicles.set(i, min);
			vehicles.set(minPos, actual);
		}
	}
	
	/**
	 * <b>Description:</b> This method allows sorting the vehicles from minor to major by the frequency of use.<br>
	 * <b>Post:</b> The vehicles are sorted by frequency of use from minor to major.<br>
	 */
	
	public void sortVehiclesByUses(){//Insertion
		for(int i=1; i<vehicles.size(); i++){
			for(int j=i; (j>0)&&(vehicles.get(j-1).compare(vehicles.get(j-1), vehicles.get(j))<0); j--){
				Vehicle actual=vehicles.get(j);
				vehicles.set(j, vehicles.get(j-1));
				vehicles.set(j-1, actual);
			}
		}
	}
	
	/**
	 * <b>Description:</b> This method allows sorting the vehicles from minor to major by the plate.<br>
	 * <b>Post:</b> The vehicles are sorted by plate from minor to major.<br>
	 */
	
	public void sortVehiclesByPlate(){//Bubble
		for(int i=0; i<vehicles.size(); i++){
			for(int j=0; j<(vehicles.size()-(i+1)); j++){
				if(vehicles.get(j+1) instanceof MotorVehicle){
					MotorVehicle vehicle=(MotorVehicle)vehicles.get(j+1);
					if(vehicle.comparePlate(vehicles.get(j))>0){
						Vehicle actual=vehicles.get(j);
						vehicles.set(j,vehicles.get(j+1));
						vehicles.set(j+1,actual);
					}
				}
				else{
					Vehicle actual=vehicles.get(j);
					vehicles.set(j,vehicles.get(j+1));
					vehicles.set(j+1,actual);
				}
			}
		}
	}
	
	//Reader
	
	/**
	 * <b>Description:</b> This method allows reading a file.<br>
	 * @param path The file path.
	 * @return The file content if the file exists, null in otherwise.
	 * @throws IOException If an I/O error occurs.
	 */
	
	public String read(String path) throws IOException{
		String text="";
		File file=new File(path);
		if(file.exists()){
			file.createNewFile();
			FileReader fileReader=new FileReader(file);
			BufferedReader reader=new BufferedReader(fileReader);
			String actualLine;
			while((actualLine=reader.readLine())!=null){
				if(!(actualLine.charAt(0)=='*') && !(actualLine.isEmpty())){
					text+=actualLine+"\n";
				}
			}
			reader.close();
		}
		else{
			text=null;
		}
		
		return text;
	}

	//Loader
	
	/**
	 * <b>Description:</b> This method allows creating a Vehicle form a file.<br>
	 * @param path The file path.
	 * @return A vehicle with the attributes given if the file exists and is a valid file, null in otherwise.
	 */
	
	public Vehicle load(String path){
		Vehicle vehicle=null;
		try{
			String[] data=read(path).split("\n");
			if(data[0].equals("CAR")){
				vehicle=new Car(data[1], data[2], VehicleFuel.valueOf(data[3]), CarType.valueOf(data[4]), Integer.parseInt(data[5]));
			}
			else if(data[0].equals("MOTORCYCLE")){
				vehicle=new Motorcycle(data[1], data[2], VehicleFuel.valueOf(data[3]), MotorcycleType.valueOf(data[4]), Integer.parseInt(data[5]));
			}
			else if(data[0].equals("BICYCLE")){
				vehicle=new Bicycle(data[1], data[2]);
			}
		}
		catch (IOException | IndexOutOfBoundsException | IllegalArgumentException | NullPointerException | InvalidPlateException e){
			vehicle=null;
		}
		return vehicle;
	}
	
	//Get
	
	/**
	 * <b>Description:</b> Gets the value of the attribute vehicles.<br>
	 * @return The attribute vehicles.
	 */
	
	public ArrayList<Vehicle> getVehicles(){
		return vehicles;
	}
	
}