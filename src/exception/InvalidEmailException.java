package exception;

public class InvalidEmailException extends Exception{

	//Constructor
	public InvalidEmailException(){
		super("The email is not valid");
	}
	
	public InvalidEmailException(String message){
		super(message);
	}
	
}
