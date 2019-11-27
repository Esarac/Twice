package thread;

import controller.ControlParkingView;
import javafx.application.Platform;
import model.Bicycle;
import model.Car;
import model.Motorcycle;
import model.Parking;

public class ThreadEmptySlotsUpdater extends Thread{

	//Attributes
	private ControlParkingView controller;
	private Parking parking;
	
	//Constructor
	public ThreadEmptySlotsUpdater(ControlParkingView controller,Parking parking) {
		this.controller=controller;
		this.parking=parking;
		start();
	}
	
	//Start
	public void run() {
		Runnable set=new Runnable(){
			public void run() {
				String emptySlotsQuantity="Empty Slots: [Car: "+parking.emptySlotsQuantity(Car.class)+"] "+"[Motorcycle: "+parking.emptySlotsQuantity(Motorcycle.class)+"] "+"[Bicycle: "+parking.emptySlotsQuantity(Bicycle.class)+"]";
    			controller.setSlotsQuantity(emptySlotsQuantity);
			}
		};
		
		while(controller.isActive()) {
			Platform.runLater(set);
			try {sleep(1000);}
			catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
}
