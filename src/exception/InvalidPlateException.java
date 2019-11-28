package exception;

public class InvalidPlateException extends Exception{

	//Constructor
	public InvalidPlateException(){
		super("The plate is not valid");
	}
	
	public InvalidPlateException(String message){
		super(message);
	}
	
}
