package model;

import java.io.Serializable;
import java.util.ArrayList;

import exception.AlreadyExistException;
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
	
	/**
	 * <b>Description:</b> This method allows adding a user.<br>
	 * @param user The user who will be added.
	 * @throws AlreadyExistException If already exist a user with that email.
	 */
	
	public void addUser(User user) throws AlreadyExistException {
		
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
				throw new AlreadyExistException();
		}
	}
	
	//Search
	
	/**
	 * <b>Description:</b> This method allows searching a user by the email.<br>
	 * @param email The user email.
	 * @return The user if could be found, null in otherwise.
	 */
	
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
	
	/**
	 * <b>Description:</b> This method allows verifying if the password has a minimum of eight (8) characters in length and contain at least one (1) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9). .<br>
	 * @param password The password
	 * @return True if the password has a minimum of eight (8) characters in length and contain at least one (1) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9), false in otherwise.
	 */
	
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
	
	/**
	 * <b>Description:</b> This method allows verifying the email.<br>
	 * @param email The email.
	 * @return True if the string has a @ character, false in otherwise.
	 */
	
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
	
	/**
	 * <b>Description:</b> This method allows encrypting a password.<br>
	 */
	
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
	
	/**
	 * <b>Description:</b> This method allows decrypting a password.<br>
	 */
	
	public String decrypt(String text){
		String decryptedText="";
		for(int i=text.length(); i>0; i-=3){
			char letter=(char)Integer.parseInt(text.substring(i-3, i));
			decryptedText+=letter;
		}
		return decryptedText;
	}
	
	//Compare
	
	/**
	 * <b>Description:</b> This method allows comparing a user with other by the email.<br>
	 * @return The difference.
	 */
	
	public int compareTo(User user) {
		return email.compareTo(user.email);
	}

	//Get
	
	/**
	 * <b>Description:</b> This method allows getting all the parkings in the app.<br>
	 * @param parkings All the parkings.
	 */
	
	public void getAllParkings(ArrayList<Parking> parkings) {
		if(this instanceof Owner) {
			parkings.addAll(((Owner)this).getParkings());
		}
		
		if(left!=null){
			left.getAllParkings(parkings);
		}
		if(right!=null) {
			right.getAllParkings(parkings);
		}
	}
	
	/**
	 * <b>Description:</b> Gets the value of the attribute email.<br>
	 * @return The attribute email.
	 */
	
	public String getEmail() {
		return email;
	}
	
	/**
	 * <b>Description:</b> Gets the value of the attribute password.<br>
	 * @return The attribute password.
	 */

	public String getPassword() {
		return password;
	}
	
	/**
	 * <b>Description:</b> Gets the value of left.<br>
	 * @return The left.
	 */

	public User getLeft() {
		return left;
	}
	
	/**
	 * <b>Description:</b> Gets the value of right.<br>
	 * @return The right.
	 */

	public User getRight() {
		return right;
	}
	
}
