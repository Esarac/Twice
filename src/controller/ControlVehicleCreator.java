package controller;

import exception.AlreadyExistException;
import exception.InvalidPlateException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import model.Car.CarType;
import model.Client;
import model.MotorVehicle.VehicleFuel;
import model.Motorcycle.MotorcycleType;

public class ControlVehicleCreator extends ControlGlobal implements Generator {

	
	@FXML ChoiceBox<String> vehicleType;
	@FXML TextField name;
	@FXML TextField plate;
	@FXML ChoiceBox<VehicleFuel> fuel;
	@FXML ChoiceBox<String> type;
	@FXML TextField polarized;
	@FXML GridPane fuelField;
	@FXML GridPane typeField;
	@FXML GridPane buttonsField;
	private Client client;
	
	@Override
	public void generate() {
		
		client = (Client) getActualUser();
		vehicleType.getItems().addAll("Car", "Motorcycle", "Bicycle");
		vehicleType.getSelectionModel().selectFirst();
		vehicleType.setOnAction(event -> type(vehicleType.getValue()));
		
		type.getItems().addAll(CarType.STANDARD.name(), CarType.MICRO.name(), CarType.SUV.name(), CarType.TRUCK.name(), CarType.VAN.name());
		type.getSelectionModel().selectFirst();
		
		fuel.getItems().addAll(VehicleFuel.DIESEL, VehicleFuel.ELECTRIC, VehicleFuel.EXTRA, VehicleFuel.GAS, VehicleFuel.GASOLINE, VehicleFuel.HYBRID);
		fuel.getSelectionModel().selectFirst();
	}
	
	public void type(String choice) {
		
		if(choice.equals("Car")) {
			
			pane.getChildren().remove(buttonsField);
			
			if(!pane.getChildren().contains(fuelField))
				pane.getChildren().add(fuelField);
			
			if(!pane.getChildren().contains(typeField))
				pane.getChildren().add(typeField);
			
			if(!pane.getChildren().contains(polarized))
				pane.getChildren().add(polarized);
			
			pane.getChildren().add(buttonsField);
			
			type.getItems().clear();
			type.getItems().addAll(CarType.STANDARD.name(), CarType.MICRO.name(), CarType.SUV.name(), CarType.TRUCK.name(), CarType.VAN.name());
			type.getSelectionModel().selectFirst();;
			plate.setPromptText("Plate");
			polarized.setPromptText("Polarized");
		}
		else if(choice.equals("Motorcycle")) {
			
			pane.getChildren().remove(buttonsField);
			
			if(!pane.getChildren().contains(fuelField))
				pane.getChildren().add(fuelField);
			
			if(!pane.getChildren().contains(typeField))
				pane.getChildren().add(typeField);
			
			if(!pane.getChildren().contains(polarized))
				pane.getChildren().add(polarized);
			
			pane.getChildren().add(buttonsField);
			
			type.getItems().clear();
			type.getItems().addAll(MotorcycleType.STANDARD.name(), MotorcycleType.QUAD.name());
			type.getSelectionModel().selectFirst();
			polarized.setPromptText("Cylindered");
		}
		else {
			
			plate.setPromptText("Color");
			pane.getChildren().remove(fuelField);
			pane.getChildren().remove(typeField);
			pane.getChildren().remove(polarized);
		}
		
	}
	
	public void addVehicle() {
		
		
		if(vehicleType.getValue().equals("Bicycle")) {
			
			addBicycle();
		}
		else if(vehicleType.getValue().equals("Car")) {
			
			addCar();
		}
		else {
			
			addMotorcycle();
		}
	}
	
	public void addBicycle() {
		
		if(!name.getText().isEmpty() || !plate.getText().isEmpty()) {
			
			try {
				
				client.addBicycle(name.getText(), plate.getText());
				getApp().saveUsers();
				load("VehicleMenu");
				
			} catch (AlreadyExistException e) {
				
				//Shows an alert if already exists a bicycle with that name.
				ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "Already exists a bicycle with that name", ok);
				alert.setHeaderText(null);
				alert.setTitle(null);
				
				//Sets the alert style.
				setStyle(alert);
				
				//Shows the alert.
				alert.showAndWait();
				
				name.setText("");
			}
			
		}
		else {
			
			//Shows an alert if any field is empty.
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "Please complete the fields", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			
			//Sets the alert style.
			setStyle(alert);
			
			//Shows the alert.
			alert.showAndWait();
		}
		
	}
	
	public void addCar() {
		
		if(!name.getText().isEmpty() || !plate.getText().isEmpty() || !polarized.getText().isEmpty()) {
						
			try {
				
				CarType carType = CarType.valueOf(type.getValue());
				int polarizedLevel = Integer.parseInt(polarized.getText());
				
				if(polarizedLevel > 0) {
					
					client.addCar(name.getText(), plate.getText(), fuel.getValue(), carType, polarizedLevel);
					getApp().saveUsers();
					load("VehicleMenu");
					
				}
				else {
					
					//Shows an alert if is a negative number.
					ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
					Alert alert = new Alert(AlertType.NONE, "Please enter positive numbers!", ok);
					alert.setHeaderText(null);
					alert.setTitle(null);
					
					//Sets the alert style.
					setStyle(alert);
					
					//Shows the alert.
					alert.showAndWait();
					
					polarized.setText("");
				}
				
			}
			catch(NumberFormatException e) {
				
				ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "Please enter numbers!", ok);
				alert.setHeaderText(null);
				alert.setTitle(null);
				
				//Sets the alert style.
				setStyle(alert);
				
				//Shows the alert.
				alert.showAndWait();
				
				polarized.setText("");
			}
			catch (InvalidPlateException e) {
				
				ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "Please enter a valid license plate!", ok);
				alert.setHeaderText(null);
				alert.setTitle(null);
				
				//Sets the alert style.
				setStyle(alert);
				
				//Shows the alert.
				alert.showAndWait();
				
				plate.setText("");
			} 
			catch (AlreadyExistException e) {
				
				ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "Already exists a car with that name!", ok);
				alert.setHeaderText(null);
				alert.setTitle(null);
				
				//Sets the alert style.
				setStyle(alert);
				
				//Shows the alert.
				alert.showAndWait();
				
				name.setText("");
			}
		}
		else {
			
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "Please complete the fields!", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			
			//Sets the alert style.
			setStyle(alert);
			
			//Shows the alert.
			alert.showAndWait();	
		}
	}
	
	public void addMotorcycle() {
		
		if(!name.getText().isEmpty() || !plate.getText().isEmpty() || !polarized.getText().isEmpty()) {
			
			MotorcycleType motorType = MotorcycleType.valueOf(type.getValue());
			
			try {
				
				int cylindered = Integer.parseInt(polarized.getText());
				
				if(!(cylindered < 0)) {
					
					client.addMotorcycle(name.getText(), plate.getText(), fuel.getValue(), motorType, cylindered);
					getApp().saveUsers();
					load("VehicleMenu");

				}
				else {
					
					ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
					Alert alert = new Alert(AlertType.NONE, "Please enter a positive numbers!", ok);
					alert.setHeaderText(null);
					alert.setTitle(null);
					
					//Sets the alert style.
					setStyle(alert);
					
					//Shows the alert.
					alert.showAndWait();
					
					polarized.setText("");
				}
				
			}
			catch(NumberFormatException e) {
				
				ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "Please enter a numbers!", ok);
				alert.setHeaderText(null);
				alert.setTitle(null);
				
				//Sets the alert style.
				setStyle(alert);
				
				//Shows the alert.
				alert.showAndWait();
				
				polarized.setText("");
			} 
			catch (InvalidPlateException e) {
				
				ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "Please enter a valid plate!", ok);
				alert.setHeaderText(null);
				alert.setTitle(null);
				
				//Sets the alert style.
				setStyle(alert);
				
				//Shows the alert.
				alert.showAndWait();
				
				plate.setText("");
			} 
			catch (AlreadyExistException e) {
				
				ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "Already exists a motorcycle with that name!", ok);
				alert.setHeaderText(null);
				alert.setTitle(null);
				
				//Sets the alert style.
				setStyle(alert);
				
				//Shows the alert.
				alert.showAndWait();
				
				name.setText("");
			}
		}
		else {
			
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "Please complete the fields!", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			
			//Sets the alert style.
			setStyle(alert);
			
			//Shows the alert.
			alert.showAndWait();
		}
		
	}
	
	public void back() {
		
		load("VehicleMenu");
	}

}
