package exception;

public class AlreadyExistException extends Exception{

	//Constructor
	public AlreadyExistException(){
		super("");
	}
	
	public AlreadyExistException(String message){
		super(message);
	}
	
}
