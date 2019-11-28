package controller;

import exception.AlreadyExistException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import model.Owner;

public class ControlParkingCreator extends ControlGlobal{
	
	//Nodes
	@FXML private TextField name;
	@FXML private TextField country;
	@FXML private TextField department;
	@FXML private TextField city;
	@FXML private TextField address;
	@FXML private TextField email;
	@FXML private TextField information;
	@FXML private TextField priceCar;
	@FXML private TextField priceMotorcycle;
	@FXML private TextField priceBicycle;
	
	public void create() {
		if(!emptyTextFields()) {
			try{
				double priceCar=Double.parseDouble(this.priceCar.getText());
				double priceMotorcycle=Double.parseDouble(this.priceMotorcycle.getText());
				double priceBicycle=Double.parseDouble(this.priceBicycle.getText());
				
				if(possibleNumber(new double[] {priceCar, priceMotorcycle, priceBicycle})){
					try {
						((Owner) getActualUser()).addParking(name.getText(), address.getText(), city.getText(),department.getText(),country.getText(),email.getText(),information.getText(),new double[] {priceCar, priceMotorcycle, priceBicycle});
						load("ParkingMenu");
						getApp().saveUsers();
					} catch (AlreadyExistException e) {
						//AlreadyExist
						ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
						Alert alert = new Alert(AlertType.NONE, "Parking with this name all ready exist!", ok);
						alert.setHeaderText(null);
						alert.setTitle(null);
						setStyle(alert);
						alert.showAndWait();
					}
				}
				else{
					//Negatives
					ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
					Alert alert = new Alert(AlertType.NONE, "Please set positive numbers in the price and slot fields!", ok);
					alert.setHeaderText(null);
					alert.setTitle(null);
					setStyle(alert);
					alert.showAndWait();
				}
			}
			catch(NumberFormatException e){
				//NotNumbers
				ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "Please set numbers in the price and slot fields!", ok);
				alert.setHeaderText(null);
				alert.setTitle(null);
				setStyle(alert);
				alert.showAndWait();
			}
			
			
		}
		else{
			//EmptyFields
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "Please complete the fields!", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			setStyle(alert);
			alert.showAndWait();
		}
	}
	
	public void back() {
		load("ParkingMenu");
	}
	
	//Verify
	public boolean emptyTextFields(){
		boolean v1= name.getText().isEmpty() || country.getText().isEmpty() || department.getText().isEmpty();
		boolean v2= city.getText().isEmpty() || address.getText().isEmpty() || priceCar.getText().isEmpty();
		boolean v3= priceMotorcycle.getText().isEmpty() || priceBicycle.getText().isEmpty();
		return v1 || v2 || v3;
	}
	
	public boolean possibleNumber(double[] numbers){
		boolean possible=true;
		
		for(int i=0; (i<numbers.length) && possible; i++){
			if(numbers[i]<0){
				possible=false;
			}
		}
		
		return possible;
	}
	
}
