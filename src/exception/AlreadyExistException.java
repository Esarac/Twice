package exception;

public class AlreadyExistException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public AlreadyExistException(){
		super();
	}
	
	public AlreadyExistException(String message){
		super(message);
	}
	
}
