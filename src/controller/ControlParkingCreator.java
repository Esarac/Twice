package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Owner;

public class ControlParkingCreator extends ControlGlobal implements Initializable{

	@FXML
	private TextField name;
	@FXML
	private TextField country;
	@FXML
	private TextField department;
	@FXML
	private TextField city;
	@FXML
	private TextField address;
	@FXML
	private TextField email;
	@FXML
	private TextField carPrice;
	@FXML
	private TextField motorcyclePrice;
	@FXML
	private TextField bicyclePrice;
	@FXML
	private TextField carSlots;
	@FXML
	private TextField motorcycleSlots;
	@FXML
	private TextField bicycleSlots;
	@FXML
	private TextField info;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void create(ActionEvent e) {
		try{
			((Owner)app.getActualUser()).addParking(name.getText(), email.getText(), info.getText(),address.getText(),city.getText(),department.getText(),country.getText(),Double.parseDouble(carPrice.getText()),Double.parseDouble(motorcyclePrice.getText()),Double.parseDouble(bicyclePrice.getText()),Integer.parseInt(carSlots.getText()),Integer.parseInt(motorcycleSlots.getText()),Integer.parseInt(bicycleSlots.getText()));
			load("Menu");
			app.saveUsers();
		}
		catch(NumberFormatException ex){}
	}
	
	public void back(){
		load("Menu");
	}
	
}
