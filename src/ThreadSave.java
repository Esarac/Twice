import javafx.application.Platform;
import model.App;

public class ThreadSave extends Thread{

	//Attributes
	private App app;
	
	//Constructor
	public ThreadSave(App app) {
		this.app=app;
		setDaemon(true);
		start();
	}
	
	//Start
	public void run() {
		Runnable save=new Runnable(){
			public void run() {
				app.saveUsers();
			}
		};
		
		while(true) {
			Platform.runLater(save);
			try {sleep(60000);}
			catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
}
