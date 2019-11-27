package controller;

import java.util.ArrayList;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import model.Client;
import model.Vehicle;

public class ControlVehicleMenu extends ControlGlobal implements Generator{

	
	@FXML ListView<GridPane> list;
	@FXML ChoiceBox<String> sort;
	@FXML TextField search;
	private Client client;
	
	@Override
	public void generate() {
		
		sort.getItems().addAll("Name", "Plate", "Uses");
		sort.getSelectionModel().selectFirst();
		sort.setOnAction(event -> sort(sort.getValue()));
		client = (Client) getActualUser();
		client.sortVehiclesByName();
		search.setOnKeyTyped(event -> search(search.getText()));
		
		ArrayList<Vehicle> vehicles = client.getVehicles();
		generateList(vehicles);
		
	}
	
	public void generateList(ArrayList<Vehicle> vehicles) {
		
		list.getItems().clear();
		
		for(int i = 0; i < vehicles.size(); i++) {
			
			GridPane grid = new GridPane();
			grid.getColumnConstraints().add(new ColumnConstraints());
			grid.getColumnConstraints().add(new ColumnConstraints());
			grid.getRowConstraints().add(new RowConstraints());
			
			Button vehicle = new Button(vehicles.get(i) + " Bills: " + vehicles.get(i).unpaidBills().size());
			vehicle.getStyleClass().add("vehicle");
			int index = i;
			vehicle.setOnAction(event -> enterVehicle(vehicle.getText(), vehicles.get(index)));
			grid.add(vehicle, 0, 0);
			
			if(vehicles.get(i).unpaidBills().size() == 0) {
				
				Button delete = new Button("X");
				delete.getStyleClass().add("delete");
				delete.setOnAction(event -> deleteVehicle(vehicle.getText(), grid));
				grid.add(delete, 1, 0);
			}
			
			list.getItems().add(grid);
		}
	}
	
	public void back() {
		
		load("Menu");
	}
	
	public void addVehicle() {
		
		load("VehicleCreator");
	}
	
	public void enterVehicle(String name, Vehicle vehicle) {
		
		ControlBillView controller = (ControlBillView) load("BillView");
		controller.setVehicle(vehicle);
	}
	
	public void search(String search) {
		
		ArrayList<Vehicle> vehicles = client.searchVehicles(search);
		
		generateList(vehicles);
	}
	
	public void sort(String choice) {
		
		ArrayList<Vehicle> vehicles;
		
		if(choice.equals("Name")) {
			
			client.sortVehiclesByName();
			
		}
		else if(choice.equals("Plate")) {
			
			client.sortVehiclesByPlate();
		}
		else {
			
			client.sortVehiclesByUses();
		}
		
		vehicles = client.getVehicles();
		generateList(vehicles);
		
	}
	
	public void deleteVehicle(String name, GridPane grid) {
		
		//Creates an alert to ask for confirmation.
		ButtonType accept = new ButtonType("Accept", ButtonBar.ButtonData.OK_DONE);
		ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.OK_DONE);
		Alert alert = new Alert(AlertType.NONE, "Are you sure?", accept, cancel);
		alert.setHeaderText(null);
		alert.setTitle(null);
		
		//Sets the alert style.
		setStyle(alert);
		
		//Shows the alert.
		Optional <ButtonType> action = alert.showAndWait();
		
		if(action.get() == accept) {
			
			client.deleteVehicle(name);
			list.getItems().remove(grid);
			getApp().saveUsers();
		}	
	}

}
