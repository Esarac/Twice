package exception;

public class AlreadyExistException extends Exception{

	//Constructor
	public AlreadyExistException(){
		super("An object with the same identifier all ready exist");
	}
	
	public AlreadyExistException(String message){
		super(message);
	}
	
}
