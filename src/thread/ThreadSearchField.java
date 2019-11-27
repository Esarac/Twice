package thread;

import controller.ControlParkingView;
import controller.Searchable;
import model.Parking;
import model.User;

public class ThreadSearchField extends Thread{

	//Attributes
	private Searchable controller;
	private User user;
	
	//Constructor
	public ThreadSearchField(Searchable controller, User user) {
		this.controller=controller;
		this.user=user;
		start();
	}
	
	//Run
	public void run() {
		
	}
}
