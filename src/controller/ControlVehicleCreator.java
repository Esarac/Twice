package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Client;
import model.Vehicle.VehicleFuel;
import model.Vehicle.VehicleType;

public class ControlVehicleCreator extends ControlGlobal implements Initializable{

	ObservableList<VehicleType> typeList=FXCollections.observableArrayList(VehicleType.Car,VehicleType.Motorcycle, VehicleType.Bicycle);
	ObservableList<VehicleFuel> fuelList=FXCollections.observableArrayList(VehicleFuel.Gasoline, VehicleFuel.Diesel, VehicleFuel.Non, VehicleFuel.Gas, VehicleFuel.Extra, VehicleFuel.Electric, VehicleFuel.Hybrid);
	
	@FXML
	private ChoiceBox type;
	@FXML
	private TextField plate;
	@FXML
	private ChoiceBox fuel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		type.setValue(VehicleType.Car);
		type.setItems(typeList);
		fuel.setValue(VehicleFuel.Non);
		fuel.setItems(fuelList);
	}
	
	public void create(ActionEvent e) {
		((Client)app.getActualUser()).addVehicle((VehicleType)type.getValue(),plate.getText(),(VehicleFuel)fuel.getValue());
		load("Vehicles");
		app.saveUsers();
	}
	
	public void back(){
		load("Vehicles");
	}
}
