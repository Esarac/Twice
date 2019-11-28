package exception;

public class InvalidPasswordException extends Exception{

	//Constructor
	public InvalidPasswordException(){
		super("The password is not valid");
	}
	
	public InvalidPasswordException(String message){
		super(message);
	}
	
}
