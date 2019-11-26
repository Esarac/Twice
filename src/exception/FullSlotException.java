package exception;

public class FullSlotException extends Exception{
	
	//Constructor
	public FullSlotException(){
		super("");
	}
	
	public FullSlotException(String message){
		super(message);
	}
	
}
