package exception;

public class InvalidEmailException extends Exception{

	//Constructor
	public InvalidEmailException(){
		super("");
	}
	
	public InvalidEmailException(String message){
		super(message);
	}
	
}
