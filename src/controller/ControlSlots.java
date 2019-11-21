package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import model.Client;
import model.Parking;
import model.Slot;
import model.Vehicle;

public class ControlSlots extends ControlGlobal implements Initializable, Generator{

	ObservableList<Vehicle> vehicles;
	
	private Parking parking;
	
	@FXML
	private HBox box;
	@FXML
	private ChoiceBox<Vehicle> cars;
	
	@Override
	public void generate() {
		vehicles=FXCollections.observableArrayList(((Client)app.getActualUser()).getVehicles());
		cars.setItems(vehicles);
		
		ArrayList<Slot> slots=parking.getSlots();
		for(int i=0; i<slots.size(); i++){
			Slot slot=slots.get(i);
			Button slotButton=new Button(slot.getId());
			
			if(!slot.empty())slotButton.setDisable(true);
			
			slotButton.setOnAction((event)->{
				if(cars.getValue()!=null){
					boolean possible=slot.addVehicle(cars.getValue());
					if(possible)back(); app.saveUsers();
				}
			});
			box.getChildren().add(slotButton);
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public void setParking(Parking parking){
		this.parking=parking;
	}
	
	public void back(){
		load("Menu");
	}
	
}
