package exception;

public class InvalidPasswordException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidPasswordException(){
		super();
	}
	
	public InvalidPasswordException(String message){
		super(message);
	}
	
}
