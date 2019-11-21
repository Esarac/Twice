package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Client;
import model.Vehicle;

public class ControlVehicles extends ControlGlobal implements Initializable, Generator{

	@FXML
	private VBox box;
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
	}

	@Override
	public void generate(){
		
		ArrayList<Vehicle> vehicles=((Client)app.getActualUser()).getVehicles();
		for(int i=0; i<vehicles.size(); i++){
			HBox hBox=new HBox();
			Label vehiclePlate=new Label(vehicles.get(i).getPlate());
			hBox.getChildren().add(vehiclePlate);
			
			Button deleteButton=new Button("X");
			deleteButton.setOnAction((event)->{
				((Client)app.getActualUser()).deleteVehicle(vehiclePlate.getText());
				box.getChildren().remove(hBox);
				app.saveUsers();
			});
			hBox.getChildren().add(deleteButton);
			
			box.getChildren().add(hBox);
		}
	}
	
	public void loadCreator(){
		load("VehicleCreator");
	}
	
	public void back(){
		load("Menu");
	}
	
}
