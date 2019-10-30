package model;

import java.util.ArrayList;
import java.util.Collections;

public class App {

	//Attribute
	private String name;
	private ArrayList<User> users;
	private ArrayList<Parking> parkings;
	private User actualUser;
	
	//Constructor
	public App(){
		this.name="ParqueApp";
		this.users=new ArrayList<User>();
		this.parkings=new ArrayList<Parking>();
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
	
	//Add
	public boolean addClient(String name, String lastName, String email, String id, String password){
		boolean possible=true;
		if(!userExist(email)){
			if(validPassword(password)){
				users.add(new Client(name, lastName, email, id, password));
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
	
	public boolean addEmployee(String name, String lastName, String email, String id, String password){
		boolean possible=true;
		if(!userExist(email)){
			if(validPassword(password)){//Email
				users.add(new Employee(name, lastName, email, id, password));
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
	
	public boolean addParking(Employee owner, String address, String city, String department, String country, double pricePerHour){
		boolean exist=userExist(owner.getEmail());
		if(!exist){
			String realAddress=address+", "+city+", "+department+", "+country;
			Parking parking=new Parking(owner, realAddress, pricePerHour);
			owner.addParking(parking);
			parkings.add(parking);
		}//Exception?
		return !exist;
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
		if((8<password.length())&&(password.length()<20)){
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
	
}
