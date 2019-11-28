package controller;

import java.text.SimpleDateFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.Bill;

public class ControlBillView extends ControlGlobal implements Generator {
	
	@FXML private Label entryDate;
	@FXML private Label departureDate;
	@FXML private Label price;
	@FXML private Label parkingName;
	@FXML private Label parkingAddress;
	@FXML private HBox buttonsField;
	@FXML private Button pay;
	@FXML private Button next;
	private Bill actual;
	
	@Override
	public void generate() {
		
		loadInterface();
	}
	
	public void loadInterface() {
		
		pane.getChildren().remove(buttonsField);
		
		if(!pane.getChildren().contains(departureDate))
			pane.getChildren().add(departureDate);
		
		if(!pane.getChildren().contains(price))
			pane.getChildren().add(price);
		
		if(!buttonsField.getChildren().contains(pay)) {
			buttonsField.getChildren().add(pay);
		}
		
		if(!buttonsField.getChildren().contains(next)) {
			buttonsField.getChildren().add(next);
		}
		//EntryDate
		SimpleDateFormat f = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		String startDate=f.format(actual.getEntryDate().getTime());
		entryDate.setText("Entry date: " + startDate);
		//...
		parkingName.setText("Parking name: " + actual.getParkingName());
		parkingAddress.setText("Parking address: " + actual.getParkingAddress());
		
		if(actual.getDepartureDate() != null) {
			//DepartureDate
			String endDate=f.format(actual.getDepartureDate().getTime());
			departureDate.setText("Departure date: " + endDate);
			//...
			price.setText("Price: $" + (int) actual.getPrice());
		}
		else {
			
			pane.getChildren().remove(departureDate);
			pane.getChildren().remove(price);
			buttonsField.getChildren().remove(pay);
		}
		if(actual.getNext() == null) {
			
			buttonsField.getChildren().remove(next);
		}
		if(actual.isPayed()) {
			
			buttonsField.getChildren().remove(pay);
		}
		
		pane.getChildren().add(buttonsField);
	}
	
	public void pay() {
		
		actual.pay();
		buttonsField.getChildren().remove(pay);
		getApp().saveUsers();
	}	
	
	public void next() {
		
		actual = actual.getNext();
		loadInterface();
		
	}
	
	public void back() {
		
		load("VehicleMenu");
	}
	
	public void setBill(Bill bill) {
		actual = bill;
	}
}
