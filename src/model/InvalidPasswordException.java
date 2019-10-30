package model;

public class InvalidPasswordException extends Exception{

	public InvalidPasswordException(){
		super();
	}
	
	public InvalidPasswordException(String message){
		super(message);
	}
	
}
