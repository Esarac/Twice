package exception;

public class AlreadyExistsException extends Exception{

	//Constructor
	public AlreadyExistsException(){
		super("");
	}
	
	public AlreadyExistsException(String message){
		super(message);
	}
	
}
