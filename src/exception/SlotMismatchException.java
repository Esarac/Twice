package exception;

public class SlotMismatchException extends Exception{

	//Constructor
	public SlotMismatchException(){
		super("The vehicle is not the slot type");
	}
	
	public SlotMismatchException(String message){
		super(message);
	}
	
}
