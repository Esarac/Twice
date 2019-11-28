package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import exception.AlreadyExistException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Bill;
import model.Client;
import model.Owner;
import model.Vehicle;

public class ControlVehicleMenu extends ControlGlobal implements Generator{

	
	@FXML private ListView<GridPane> list;
	@FXML private ChoiceBox<String> sort;
	@FXML private TextField search;
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
			
			Button vehicle = new Button(vehicles.get(i) + " - Bills: " + vehicles.get(i).unpaidBills().size());
			vehicle.getStyleClass().add("vehicle");
			int index = i;
			vehicle.setOnAction(event -> enterVehicle(vehicles.get(index)));
			grid.add(vehicle, 0, 0);
			
			if(vehicles.get(i).unpaidBills().size() == 0) {
				
				Button delete = new Button("X");
				delete.getStyleClass().add("delete");
				delete.setOnAction(event -> deleteVehicle(vehicles.get(index).getName(), grid));
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
	
	public void createVehicleFile() {
		try{
			//ChooseFile
			Stage stage = (Stage) pane.getScene().getWindow();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Game Selector");
			fileChooser.getExtensionFilters().add(new ExtensionFilter("TWC", "*.twc"));
			fileChooser.getExtensionFilters().add(new ExtensionFilter("TXT", "*.txt"));
			File file=fileChooser.showOpenDialog(stage);
			//...
			try {
				if(((Client)getActualUser()).addVehicle(file.toString())) {
					ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
					Alert alert = new Alert(AlertType.NONE, "The vehicle was added!", ok);
					alert.setHeaderText(null);
					alert.setTitle(null);
					setStyle(alert);
					alert.showAndWait();
					
					getApp().saveUsers();
					ArrayList<Vehicle> vehicles = client.getVehicles();
					generateList(vehicles);
				}
				else {
					//Not Created
					ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
					Alert alert = new Alert(AlertType.NONE, "Impossible to create this vehicle!", ok);
					alert.setHeaderText(null);
					alert.setTitle(null);
					setStyle(alert);
					alert.showAndWait();
				}
			} catch (AlreadyExistException e) {
				//Already Exist
				ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "This vehicle already exist!", ok);
				alert.setHeaderText(null);
				alert.setTitle(null);
				setStyle(alert);
				alert.showAndWait();
			}
		}
		catch(NullPointerException e){
			//File Not Selected
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "You did not select a file!", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			setStyle(alert);
			alert.showAndWait();
		}
	}
	
	public void enterVehicle(Vehicle vehicle) {
		
		if(vehicle.getFirstBill() != null) {
			
			load(vehicle.getFirstBill());
		}
		else {
			
			//Creates an alert to ask for confirmation.
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "This vehicle do not have bills", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			
			//Sets the alert style.
			setStyle(alert);
			
			alert.showAndWait();
		}
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
	
	public ControlGlobal load(Bill bill) {
		ControlGlobal nextController=null;
		try {
			//Load
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/BillView.fxml"));//FXML
			Parent root = (Parent) loader.load();
			//Style
			root.getStylesheets().clear();
			root.getStylesheets().add("/view/" + getThemeName() + ".css");//CSS
			root.getStyleClass().add("pane");
			//Next Controller
			nextController=loader.getController();
			nextController.setActualUser(getActualUser());nextController.setApp(getApp()); nextController.setStage(getStage()); nextController.setTheme(getTheme());//Set All Attributes
			ControlBillView billController = (ControlBillView) nextController;
			billController.setBill(bill);
			if(nextController instanceof Generator){//Pos
				Generator generatorController=(Generator)nextController;
				generatorController.generate();
			}
			nextController.updateStyle();
			//Stage Root
			getStage().getScene().setRoot(root);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return nextController;
	}
}
