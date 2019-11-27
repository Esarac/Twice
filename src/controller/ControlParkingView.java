package controller;

import controller.ControlGlobal.Theme;
import exception.FullSlotException;
import exception.SlotMismatchException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Parking;
import model.Slot;
import model.Vehicle;
import thread.ThreadEmptySlotsUpdater;
import model.Car;
import model.Client;
import model.MotorVehicle;
import model.Motorcycle;
import model.Owner;
import model.Bicycle;

public class ControlParkingView extends ControlGlobal implements Generator{

	//Attributes
	private Parking parking;
	private boolean active;
	
	//Nodes
	@FXML private Label name;
	@FXML private Label address;
	@FXML private Label email;
	@FXML private Label information;
	@FXML private Label price;
	@FXML private Label emptySlots;
	@FXML private WebView map;
	
	@FXML private HBox clientBox;
	@FXML private ChoiceBox<Vehicle> vehicle;
	@FXML private ListView<Button> slots;
	
	@FXML private HBox ownerBox;
	@FXML private TextField slotQuantity;
	@FXML private ChoiceBox<String> type;
	
	@FXML private HBox box;
	@FXML private Button remove;
	
	//Methods
	public void generate() {
		//Info
		name.setText(name.getText()+parking.getName());
		address.setText(address.getText()+parking.getCompleteAddress());
		email.setText(email.getText()+parking.getEmail());
		information.setText(information.getText()+parking.getInformation());
		
		String pricePerHour="[Car: $"+parking.getPricePerHour()[0]+"] "+"[Motorcycle: $"+parking.getPricePerHour()[1]+"] "+"[Bicycle: $"+parking.getPricePerHour()[2]+"]";
		price.setText(price.getText()+pricePerHour);
		
		active=true;
		new ThreadEmptySlotsUpdater(this, parking);
		
		WebEngine engine= map.getEngine();
		engine.load(parking.generateMapUrl());
		//...
		
		//Specials
		if(getActualUser() instanceof Client){
			pane.getChildren().remove(ownerBox);
			
			if(parking.searchSlotOwnerVehicle((Client)getActualUser())==null) {
				box.getChildren().remove(remove);
				
				ObservableList<Vehicle> vehicles=FXCollections.observableArrayList(((Client)getActualUser()).getVehicles());
				vehicle.setItems(vehicles);
				
				Slot actual=parking.getFirstSlot();
				while(actual!=null){
					Button slot=new Button(actual.toString());
					int id=actual.getId();
					slot.setOnAction((event)->{
						insertVehicle(id);
					});
					slots.getItems().add(slot);
					
					actual=actual.getNext();
				}
			}
			else{
				pane.getChildren().remove(clientBox);
			}
			
		}
		else if(getActualUser() instanceof Owner) {
			pane.getChildren().remove(clientBox);
			pane.getChildren().remove(remove);
			
			type.getItems().addAll("Vehicle", "Car", "Motorcycle", "Bicycle", "MotorVehicle");
			type.getSelectionModel().select("Vehicle");
		}
		//...
	}
	
	public void insertVehicle(int slotId) {
		if(vehicle.getValue()!=null){
			try {
				parking.insertVehicle(vehicle.getValue(), (Client)getActualUser(), slotId);
				ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "Your vehicle is now in the parking", ok);
				alert.setHeaderText(null);
				alert.setTitle(null);
				setStyle(alert);
				alert.showAndWait();
				load("ParkingMenu");active=false;
				getApp().saveUsers();
			}
			catch (SlotMismatchException e) {
				//VehicleType
				ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "Your vehicle is not the type of the slot!", ok);
				alert.setHeaderText(null);
				alert.setTitle(null);
				setStyle(alert);
				alert.showAndWait();
			}
			catch(FullSlotException e) {
				//Full
				ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "Slot already occupied!", ok);
				alert.setHeaderText(null);
				alert.setTitle(null);
				setStyle(alert);
				alert.showAndWait();
			}
		}
		else{
			//AlreadyExist
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "Please select a vehicle!", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			setStyle(alert);
			alert.showAndWait();
		}
	}
	
	public void removeVehicle() {
		if(parking.removeVehicle((Client)getActualUser())){
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "The bill was added to your bills", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			setStyle(alert);
			alert.showAndWait();
			load("ParkingMenu");active=false;
			getApp().saveUsers();
		}
		else {
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "You dont have a car in this parking!", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			setStyle(alert);
			alert.showAndWait();
		}
		
	}
	
	public void addSlot() {
		if(!slotQuantity.getText().isEmpty()){
			try {
				int amount=Integer.parseInt(slotQuantity.getText());
				
				if(amount>=0){
					if(type.getValue().equals("Car")){
						parking.addSlot(amount, Car.class);
					}
					else if(type.getValue().equals("Motorcycle")) {
						parking.addSlot(amount, Motorcycle.class);
					}
					else if(type.getValue().equals("Bicycle")) {
						parking.addSlot(amount, Bicycle.class);
					}
					else if(type.getValue().equals("Vehicle")) {
						parking.addSlot(amount, Vehicle.class);
					}
					else if(type.getValue().equals("MotorVehicle")) {
						parking.addSlot(amount, MotorVehicle.class);
					}
					ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
					Alert alert = new Alert(AlertType.NONE, "You add "+amount+" new slots!", ok);
					alert.setHeaderText(null);
					alert.setTitle(null);
					setStyle(alert);
					alert.showAndWait();
					getApp().saveUsers();
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
			catch(NumberFormatException e) {
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
		load("ParkingMenu");active=false;
	}
	
	//Set
	public void setSlotsQuantity(String text){
		emptySlots.setText(text);
	}
	
	public void setParking(Parking parking){
		this.parking=parking;
	}
	
	public boolean isActive() {
		return active;
	}
	
}
