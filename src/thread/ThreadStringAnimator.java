package thread;

import controller.ControlLogIn;
import javafx.application.Platform;

public class ThreadStringAnimator extends Thread{

	//Attributes
	private String text;
	private ControlLogIn controller;
	
	//Contructor
	public ThreadStringAnimator(ControlLogIn controller, String text) {
		this.text=text;
		this.controller=controller;
	}
	
	//Run
	public void run() {
		
		for(int i=0; i<text.length(); i++){
			
			int index=i+1;
			String letter=text.charAt(i)+"";
			
			Runnable credits=new Runnable() {
				public void run() {controller.showCredits(index, letter);}
			};
			
			Platform.runLater(credits);
			try {sleep(100);} catch (InterruptedException e)
			{e.printStackTrace();}
			
		}
		
	}
	
}
