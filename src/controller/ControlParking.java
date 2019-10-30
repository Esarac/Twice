package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Employee;
import model.Parking;

public class ControlParking extends ControlGlobal implements Initializable{

	//Node
	@FXML
	private WebView map;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Parking parking=new Parking(new Employee("Name", "LastName", "Email", "Id", "Password"), "Cl. 15 #121-25, Cali, Valle del Cauca, Colombia", 1000);
		WebEngine engine= map.getEngine();
		engine.load("https://www.google.com/maps/place/"+parking.getWebAddress());
		
	}

	
	
}
