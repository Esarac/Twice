package exception;

public class SlotMismatchException extends Exception{

	//Constructor
	public SlotMismatchException(){
		super("");
	}
	
	public SlotMismatchException(String message){
		super(message);
	}
	
}
