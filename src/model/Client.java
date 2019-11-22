package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import exception.AlreadyExistsException;
import exception.InvalidPlateException;
import model.Car.CarType;
import model.MotorVehicle.VehicleFuel;
import model.Motorcycle.MotorcycleType;

/**
* <b>Description:</b> The class Client in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class Client extends User implements FileLoader<Vehicle>{

	//Attributes
	private ArrayList<Vehicle> vehicles; 
	
	//Constructor
	public Client(String name, String email, String password){
		super(name, email, password);
		this.vehicles=new ArrayList<Vehicle>();
	}

	//Add
	public boolean addCar(String name, String plate, VehicleFuel fuel, CarType type, int polarized){
		boolean possible=true;
		if((searchVehicle(name)==null) && (searchMotorVehicle(plate)==null)){
			try {
				vehicles.add(new Car(name, plate, fuel, type, polarized));
			} catch (InvalidPlateException e) {
				possible=false;
			}
		}
		else{
			try {
				throw new AlreadyExistsException();
			} catch (AlreadyExistsException e) {
				possible=false;
			}
		}
		return possible;
	}
	
	public boolean addMotorcycle(String name, String plate, VehicleFuel fuel, MotorcycleType type, int cylindered){
		boolean possible=true;
		if((searchVehicle(name)==null) && (searchMotorVehicle(plate)==null)){
			try {
				vehicles.add(new Motorcycle(name, plate, fuel, type, cylindered));
			} catch (InvalidPlateException e) {
				possible=false;
			}
		}
		else{
			try {
				throw new AlreadyExistsException();
			} catch (AlreadyExistsException e) {
				possible=false;
			}
		}
		return possible;
	}
	
	public boolean addBicycle(String name, String color){
		boolean possible=true;
		if((searchVehicle(name)==null)){
			vehicles.add(new Bicycle(name, color));
		}
		else{
			try {
				throw new AlreadyExistsException();
			} catch (AlreadyExistsException e) {
				possible=false;
			}
		}
		return possible;
	}
	
	//Search
	public ArrayList<Vehicle> searchVehicles(String name){
		sortVehiclesByName();
		ArrayList<Vehicle> vehicleList=new ArrayList<Vehicle>();
		
		boolean found=false;
		int start=0;
		int end=vehicles.size()-1;
		while((start<=end)&&!found) {
			int middle=(start+end)/2;
			
			String shortedName;
			try{shortedName=vehicles.get(middle).getName().substring(0,name.length()-1);}
			catch(IndexOutOfBoundsException e){shortedName=vehicles.get(middle).getName();}
			
			if(shortedName.compareTo(name)==0){//Found
				//Left
				boolean runLeft=true;
				for(int i=middle; (i>=0) && runLeft; i++){
					try{
						if(name.equals(vehicles.get(i).getName().substring(0,name.length()-1))){
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
						if(name.equals(vehicles.get(i).getName().substring(0,name.length()-1))){
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
			else if(shortedName.compareTo(name)<0){
				start=middle+1;
			}
			else{
				end=middle-1;
			}
		}
		
		return vehicleList;
	}
	
	public Vehicle searchVehicle(String name){//Binary Search
		sortVehiclesByName();
		Vehicle vehicle=null;
		
		boolean found=false;
		int start=0;
		int end=vehicles.size()-1;
		while((start<=end)&&!found) {
			int middle=(start+end)/2;
			if(vehicles.get(middle).getName().compareTo(name)==0){
				vehicle=vehicles.get(middle);
				found=true;
			}
			else if(vehicles.get(middle).getName().compareTo(name)<0){
				start=middle+1;
			}
			else{
				end=middle-1;
			}
		}
		
		return vehicle;
	}
	
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
	
	public void sortVehiclesByUses(){//Insertion
		for(int i=1; i<vehicles.size(); i++){
			for(int j=i; (j>0)&&(vehicles.get(j-1).compare(vehicles.get(j-1), vehicles.get(j))>0); j--){
				Vehicle actual=vehicles.get(j);
				vehicles.set(j, vehicles.get(j+1));
				vehicles.set(j-1, actual);
			}
		}
	}
	
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
	public Vehicle load(String path){
		Vehicle vehicle=null;
		try{
			String[] data=read(path).split("\n");
			if(data[0].equals("Car")){
				addCar(data[1], data[2], VehicleFuel.valueOf(data[3]), CarType.valueOf(data[4]), Integer.parseInt(data[5]));
			}
			else if(data[0].equals("Motorcycle")){
				addMotorcycle(data[1], data[2], VehicleFuel.valueOf(data[3]), MotorcycleType.valueOf(data[4]), Integer.parseInt(data[5]));
			}
			else if(data[0].equals("Bicycle")){
				addBicycle(data[1], data[2]);
			}
		}
		catch (IOException | IndexOutOfBoundsException | IllegalArgumentException e){}
		return vehicle;
	}
	
}
