package model;

public abstract class User implements Comparable<User>{

	private String name;
	private String lastName;
	private String email;
	private String id;
	private String password;
	
	public User(String name, String lastName, String email, String id, String password){
		this.name=name;
		this.lastName=lastName;
		this.email=email;
		this.id=id;
		this.password=password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean correctPassword(String password){
		return this.password.equals(password);
	}
	
	public int compareTo(User user){
		return id.compareTo(user.id);
	}
	
	
}
