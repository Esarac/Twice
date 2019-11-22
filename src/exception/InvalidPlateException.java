package exception;

public class InvalidPlateException extends Exception{

	//Constructor
	public InvalidPlateException(){
		super("");
	}
	
	public InvalidPlateException(String message){
		super(message);
	}
	
}
