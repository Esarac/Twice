package model;

import java.io.Serializable;

import exception.AlreadyExistsException;
import exception.InvalidEmailException;
import exception.InvalidPasswordException;

/**
* <b>Description:</b> The abstract class User in the package model.<br>
* @author VoodLyc & Esarac.
*/

public abstract class User implements Encryptor, Comparable<User>, Serializable{//[TEST]

	//Attributes
	private String name;
	private String email;
	private String password;
	//Suppliers
	private User left;
	private User right;
	
	//Constructor
	
	/**
	 * <b>Description:</b> Creates a new instance of User.<br>
	 * @param name The user name.
	 * @param email The user email.
	 * @param password The user password - password must be a minimum of eight (8) characters in length and contain at least one (1) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9).
	 */
	
	public User(String name, String email, String password) throws InvalidEmailException, InvalidPasswordException{
		
		this.name = name;
		
		if(verifyEmail(email)){this.email = email;}
		else{throw new InvalidEmailException();}
		
		if(verifyPassword(password)){this.password = encrypt(password);}
		else{throw new InvalidPasswordException();}
		
	}
	
	//Add
	public void addUser(User user) throws AlreadyExistsException {
		
		if(user.compareTo(this) < 0) {
			
			if(left != null){
				
				left.addUser(user);
			}
			else {
				
				left = user;
			}
		}
		else if(user.compareTo(this) > 0) {
			
			if(right != null) {
				
				right.addUser(user);
			}
			else {
				
				right = user;
			}
		}
		else {
				throw new AlreadyExistsException();
		}
	}
	
	//Search
	public User searchUser(String email){
		User user=null;
		if(email.compareTo(this.email)<0){
			if(left!=null){
				user=left.searchUser(email);
			}
		}
		else if(email.compareTo(this.email)>0){
			if(right!=null){
				user=right.searchUser(email);
			}
		}
		else {
			user=this;
		}
		return user;
	}
	
	//Calculate
	public boolean verifyPassword(String password){
		boolean possible=true;
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
			
			possible= number && capital;
		}
		else{
			possible=false;
		}	
		return possible;
	}
	
	public boolean verifyEmail(String email){
		boolean found=false;
		
		for(int i=0; (i<email.length()) && !found; i++){
			if(email.charAt(i)=='@'){
				found=true;
			}
		}
		
		return found;
	}
	
	//Encrypt
	public String encrypt(String text){
		String encryptedText="";
		for(int i=(text.length()-1); i>=0; i--){
			int letterValue=text.charAt(i);
			
			if(letterValue>=100){encryptedText+=letterValue;}
			else if((letterValue>=10) && (letterValue<100)){encryptedText+="0"+letterValue;}
			else{encryptedText+="00"+letterValue;}
		}
		
		return encryptedText;
	}
	
	public String decrypt(String text){
		String decryptedText="";
		for(int i=text.length(); i>0; i-=3){
			char letter=(char)Integer.parseInt(text.substring(i-3, i));
			decryptedText+=letter;
		}
		return decryptedText;
	}
	
	//Compare
	public int compareTo(User user) {
		return email.compareTo(user.email);
	}

	//Get
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public User getLeft() {
		return left;
	}

	public User getRight() {
		return right;
	}
	
}
