package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Bill;
import model.Vehicle;

public class ControlBillView extends ControlGlobal implements Generator {

	@FXML Label entryDate;
	@FXML Label departureDate;
	@FXML Label price;
	@FXML Label parkingName;
	@FXML Label parkingAddress;
	@FXML GridPane departureField;
	@FXML GridPane priceField;
	@FXML HBox buttonsField;
	@FXML Button pay;
	@FXML Button next;
	private Vehicle vehicle;
	private Bill actual;
	
	@Override
	public void generate() {
		
		actual = vehicle.getFirstBill();
		
		if(actual != null) {
			
			
		}
	}
	
	public void loadInterface() {
		
		entryDate.setText(actual.getEntryDate().toString());
		parkingName.setText(actual.getParkingName());
		parkingAddress.setText(actual.getParkingAddress());
		
		if(actual.getNext() != null) {
			
			if(!actual.isPayed()) {
				
				if(actual.getDepartureDate() != null) {
					
					
				}
			}
		}
	}
	
	public void pay() {
		
	}	
	
	public void next() {
		
		actual = actual.getNext();
		
	}
	
	public void back() {
		
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
