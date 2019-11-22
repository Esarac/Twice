package model;

import exception.AlreadyExistsException;

/**
* <b>Description:</b> The abstract class User in the package model.<br>
* @author VoodLyc & Esarac.
*/

public abstract class User implements Encryptor, Comparable<User>{

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
	
	public User(String name, String email, String password) {
		
		this.name = name;
		this.email = email;
		this.password = encrypt(password);
	}
	
	//Add
	public boolean addUser(User user) {
		boolean possible=true;
		if(user.compareTo(this)<0){
			if(left!=null){
				possible=left.addUser(user);
			}
			else{
				left=user;
			}
		}
		else if(user.compareTo(this)>0){
			if(right!=null){
				possible=right.addUser(user);
			}
			else{
				right=user;
			}
		}
		else {
			try {
				throw new AlreadyExistsException();
			} catch (AlreadyExistsException e) {
				possible=false;
			}
		}
		return possible;
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
	
	public String decrypt(){
		String decryptedText="";
		for(int i=password.length(); i>0; i-=3){
			char letter=(char)Integer.parseInt(password.substring(i-3, i));
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
