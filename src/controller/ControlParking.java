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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.Client;
import model.Owner;
import model.Parking;

public class ControlParking extends ControlGlobal implements Initializable, Generator{

	private Parking parking;
	
	//Node
	@FXML
	private Label name;
	@FXML
	private Label address;
	@FXML
	private Label email;
	@FXML
	private Label price;
	@FXML
	private Label cupos;
	@FXML
	private Label info;
	@FXML
	private WebView map;
	@FXML
	private Button park;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setParking(Parking parking){
		this.parking=parking;
	}
	
	public void generate(){
		if( parking.myCarIsHere((Client) app.getActualUser()) )park.setText("Sacar Vehiculo");
		name.setText(parking.getName());
		address.setText(parking.getAddress());
		email.setText(parking.getEmail());
		price.setText(" Carro:"+parking.getPricePerHour()[0]+" Moto:"+parking.getPricePerHour()[1]+" Bicicleta:"+parking.getPricePerHour()[2]);
		cupos.setText(parking.emptySlots());
		info.setText(parking.getInfo());
		WebEngine engine= map.getEngine();
		engine.load("https://www.google.com/maps/place/"+parking.getWebAddress());
	}
	
	public void back(){
		load("Menu");
	}
	
	public void park(ActionEvent e){
		if( parking.myCarIsHere((Client) app.getActualUser()) ){
			
			Alert alerta=new Alert(AlertType.INFORMATION);
			alerta.setTitle("Costo:");
			alerta.setHeaderText("$"+parking.removeCar((Client) app.getActualUser()));
			alerta.show();
			app.saveUsers();
			load("Menu");
		}
		else{
			try {
				FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/Slots.fxml"));//FXML
				Parent root = (Parent) loader.load();
				root.getStylesheets().add("/view/application.css");//CSS
				
				ControlSlots nextController=loader.getController();
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
	}
}
