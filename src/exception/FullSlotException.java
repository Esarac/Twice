package exception;

public class FullSlotException extends Exception{
	
	//Constructor
	public FullSlotException(){
		super("The slot is full");
	}
	
	public FullSlotException(String message){
		super(message);
	}
	
}
