package thread;

import controller.ControlParkingView;
import javafx.application.Platform;
import model.App;
import model.Bicycle;
import model.Car;
import model.Motorcycle;
import model.Parking;

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
