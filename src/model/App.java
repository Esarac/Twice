package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import exception.AlreadyExistException;
import exception.InvalidPasswordException;

public class App {

	//Constant
	public final static String FILE_PATH_USER="dat/user.al";
	public final static String FILE_PATH_PARKING="dat/parking.al";
	public final static String FILE_PATH_ACTUAL="dat/actual.txt";
	
	//Attribute
	private String name;
	private ArrayList<User> users;
	private User actualUser;
	
	//Constructor
	public App(String name){
		this.name=name;
		this.users=new ArrayList<User>();
		loadUsers();
		loadActualUser();
		
//		Parking p1=new Parking("Parqueadero Premier", "Calle 5, Autopista Sur ##68-70, Cali, Valle del Cauca, Colombia","premierlimonar@gmail.com", "7:00am-8:00pm",2500, 1000, 500);
//		p1.addSlots("C",VehicleType.Car, 15);
//		p1.addSlots("M",VehicleType.Motorcycle, 10);
//		p1.addSlots("B",VehicleType.Bicycle, 5);
//		Parking p2=new Parking("Parqueadero Javeriana", "Calle 18 #118-250, Cali, Valle del Cauca, Colombia","javerianos.cali@hotmail.com", "24/7 lunes-viernes",1000, 700, 200);
//		p2.addSlots("C",VehicleType.Car, 14);
//		p2.addSlots("M",VehicleType.Motorcycle, 11);
//		p2.addSlots("B",VehicleType.Bicycle, 9);
//		parkings.add(p1);
//		parkings.add(p2);
	}
	
	public User logIn(String email, String password){
		Collections.sort(users);
		User user=null;
		
		//Binary
		boolean found=false;
		int start=0;
		int end=users.size()-1;
		while((start<=end) && !found){
			int middle=(start+end)/2;
			if(users.get(middle).getEmail().compareTo(email)==0){
				found=true;
				if(users.get(middle).correctPassword(password)){
					user=users.get(middle);
				}
			}
			else if(users.get(middle).getEmail().compareTo(email)>0){
				end=middle-1;
			}
			else{
				start=middle+1;
			}
		}
		//...
		
		this.actualUser=user;//Correct!
		return user;
	}
	
	public void logOut(){
		this.actualUser=null;
		File actual=new File(FILE_PATH_ACTUAL);
		actual.delete();
	}
	
	//Add
	public boolean addClient(String name, String email, String password){
		boolean possible=true;
		if(!userExist(email)){
			if(validPassword(password)){
				users.add(new Client(name, email, password));
			}
			else{
				try{throw new InvalidPasswordException();}
				catch(InvalidPasswordException e){possible=false;}
			}
		}
		else{
			try{throw new AlreadyExistException();}
			catch(AlreadyExistException e){possible=false;}
		}
		return possible;
	}
	
	public boolean addOwner(String name, String email, String password){
		boolean possible=true;
		if(!userExist(email)){
			if(validPassword(password)){//Email
				users.add(new Owner(name, email, password));
			}
			else{
				try{throw new InvalidPasswordException();}
				catch(InvalidPasswordException e){possible=false;}
			}
		}
		else{
			try{throw new AlreadyExistException();}
			catch(AlreadyExistException e){possible=false;}
		}
		return possible;
	}
	
	//Supplier
	public boolean userExist(String email){
		boolean exist=false;
		for(int i=0; (i<users.size()) && !exist; i++){
			if(email.equals(users.get(i).getEmail())){
				exist=true;
			}
		}
		return exist;
	}
	
	public boolean validPassword(String password){
		boolean valid=true;
		if((8<=password.length())&&(password.length()<=20)){
			boolean number=false;
			boolean capital=false;
			
			for(int i=0; i<password.length(); i++){
				char actualChar=password.charAt(i);
				if((48<=actualChar)&&(actualChar<=57)){
					number=true;
				}
				else if((65<=actualChar)&&(actualChar<=90)){
					capital=true;
				}
			}
			
			valid= number && capital;
		}
		else{
			valid=false;
		}
		return valid;
	}
	
	//Save
	public boolean saveUsers(){//[File]
		boolean possible=true;
		try {
			File dir=new File("dat//");
			dir.mkdir();
			FileOutputStream file=new FileOutputStream(FILE_PATH_USER);
			ObjectOutputStream creator=new ObjectOutputStream(file);
			creator.writeObject(users);
			creator.close();
		}
		catch (IOException e) {possible=false;}
		return possible;
	}
	
	public boolean saveActualUser(){//[File]
		boolean possible=true;
		try {
			File dir=new File("dat//");
			dir.mkdir();
			File actual=new File(FILE_PATH_ACTUAL);
			String text=actualUser.getEmail()+"\n"+actualUser.getPassword();
			PrintWriter writer=new PrintWriter(actual);
			writer.append(text);
			writer.close();
			System.out.println(users);
		}
		catch (IOException e) {possible=false;}
		catch (NullPointerException e) {possible=false;}
		return possible;
	}
	
	//Load
	public boolean loadUsers(){//[File]
		boolean possible=true;
		try{
			FileInputStream fileU=new FileInputStream(FILE_PATH_USER);
			ObjectInputStream creatorU=new ObjectInputStream(fileU);
			this.users=(ArrayList<User>)creatorU.readObject();
			creatorU.close();
		}
		catch (IOException e) {saveUsers();} 
		catch (ClassNotFoundException e) {possible=false;}
		return possible;
	}
	
	public boolean loadActualUser(){//[File]
		boolean possible=true;
		try{
			File actual=new File(FILE_PATH_ACTUAL);
			actual.createNewFile();
			FileReader fileReader=new FileReader(actual);
			BufferedReader reader=new BufferedReader(fileReader);
			String actualLine;
			String text[]=new String[2];
			int pos=0;
			while((actualLine=reader.readLine())!=null){
				text[pos]=actualLine;
				pos++;
			}
			reader.close();
			logIn(text[0], text[1]);
		}
		catch (IOException e) {saveActualUser();}
		catch (NullPointerException e) {possible=false;} 
		return possible;
	}

	public String getName(){
		return name;
	}
	
	public ArrayList<Parking> getParkings() {
		ArrayList<Parking> parkings=new ArrayList<Parking>();
		
		for(int i=0; i<users.size(); i++){
			if(users.get(i) instanceof Owner){
				Owner owner=(Owner)users.get(i);
				parkings.addAll(owner.getParkings());
			}
		}
		
		return parkings;
	}
	
	public ArrayList<Parking> getOwnerParkings() {
		ArrayList<Parking> parkings=null;
		
		if(actualUser instanceof Owner){
			Owner owner=(Owner)actualUser;
			parkings=owner.getParkings();
		}
		
		return parkings;
	}
	
	public User getActualUser(){
		return actualUser;
	}
	
}
