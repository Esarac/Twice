package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Client;
import model.Owner;
import model.Parking;

public class ControlMenu extends ControlGlobal implements Initializable, Generator{

	@FXML
	private VBox menu;
	@FXML
	private Button adding;
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
	}
	
	public void loadParking(ActionEvent e, Parking parking){//Correct
		
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/Parking.fxml"));//FXML
			Parent root = (Parent) loader.load();
			root.getStylesheets().add("/view/application.css");//CSS
			
			ControlParking nextController=loader.getController();
			nextController.setApp(app);
			nextController.setParking(parking);
			nextController.generate();
			
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void loadAdding(ActionEvent e){//Correct
		if(app.getActualUser() instanceof Client){
			load("Vehicles");
		}
		else if(app.getActualUser() instanceof Owner){
			load("ParkingCreator");
		}
	}
	
	public void logOut(ActionEvent e){
		
		app.logOut();
		load("LogIn");
		
	}
	
	public void generate(){
		if(app.getActualUser() instanceof Client){
			for(int i=0; i<app.getParkings().size(); i++){
				Parking parking=app.getParkings().get(i);
				Button park=new Button(parking.getName()+"-"+parking.getAddress());
				park.setOnAction((event)->{
					loadParking(event, parking);
				});
				menu.getChildren().add(park);
			}
			adding.setText("Vehiculos");
		}
		else if(app.getActualUser() instanceof Owner){
			for(int i=0; i<app.getOwnerParkings().size(); i++){
				Parking parking=app.getOwnerParkings().get(i);
				Button park=new Button(parking.getName()+"-"+parking.getAddress());
				park.setOnAction((event)->{
					loadParking(event, parking);
				});
				menu.getChildren().add(park);
			}
			adding.setText("Añadir");
		}
	}
	
}
