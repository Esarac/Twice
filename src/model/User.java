package model;

import java.io.Serializable;

public abstract class User implements Comparable<User>, Serializable{

	private String name;
	private String email;
	private String password;
	
	public User(String name, String email, String password){
		this.name=name;
		this.email=email;
		this.password=password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean correctPassword(String password){
		return this.password.equals(password);
	}
	
	public int compareTo(User user){
		return email.compareTo(user.email);
	}
	
	
}
