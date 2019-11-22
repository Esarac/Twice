package exception;

public class InvalidPasswordException extends Exception{

	//Constructor
	public InvalidPasswordException(){
		super("");
	}
	
	public InvalidPasswordException(String message){
		super(message);
	}
	
}
