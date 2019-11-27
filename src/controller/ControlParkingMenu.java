package controller;

import java.util.ArrayList;
import java.util.Optional;

import controller.ControlGlobal.Theme;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
import model.Client;
import model.Owner;
import model.Parking;

public class ControlParkingMenu extends ControlGlobal implements Generator, Searchable{

	//Nodes
	@FXML HBox box1;
	@FXML HBox box2;
	@FXML TextField search;
	@FXML ChoiceBox<String> sort;
	@FXML ListView<HBox> list;
	@FXML Button add;
	
	//Methods
	public void generate() {
		sort.getItems().addAll("Name", "Address", "Price");
		sort.getSelectionModel().select("Name");
		sort.setOnAction(event -> generateParkingList());
		
		if(getActualUser() instanceof Client){
			box1.getChildren().remove(search);
			box2.getChildren().remove(add);
		}
		
		generateParkingList();
	}
	
	public void generateParkingList() {
		list.getItems().clear();
		if(getActualUser() instanceof Client){
			generateParkingListClient();
		}
		else if(getActualUser() instanceof Owner) {
			generateParkingListOwner();
		} 
	}
	
	public void generateParkingListClient(){
		ArrayList<Parking> parkings=null;
		
		if(sort.getValue().equals("Name")) {
			parkings=getApp().sortParkingsByName();
		}
		else if(sort.getValue().equals("Address")) {
			parkings=getApp().sortParkingsByAddress();
		}
		else if(sort.getValue().equals("Price")) {
			parkings=getApp().sortParkingsByPrice();
		}
		
		for(int i=0; i<parkings.size(); i++){
			HBox box=new HBox();
			
			Button parking=new Button(parkings.get(i).toString());
			Parking park=parkings.get(i);
			parking.setOnAction((event)->{
				load(park);
			});
			
			box.getChildren().add(parking);
			
			list.getItems().add(box);
		}
	}
	
	public void generateParkingListOwner() {
		
		if(sort.getValue().equals("Name")) {
			((Owner)getActualUser()).sortParkingsByName();
		}
		else if(sort.getValue().equals("Address")) {
			((Owner)getActualUser()).sortParkingsByAddress();
		}
		else if(sort.getValue().equals("Price")) {
			((Owner)getActualUser()).sortParkingsByPrice();
		}
		
		ArrayList<Parking> parkings;
		if(!search.getText().isEmpty()){
			parkings=((Owner)getActualUser()).searchParkings(search.getText());
		}
		else{
			parkings=((Owner)getActualUser()).getParkings();
		}
		
		
		for(int i=0; i<parkings.size(); i++){
			HBox box=new HBox();
			
			Button parking=new Button(parkings.get(i).toString());
			Parking park=parkings.get(i);
			parking.setOnAction((event)->{
				load(park);
			});
			box.getChildren().add(parking);
			
			Button delete=new Button("X");
			int index=i;
			delete.setOnAction((event)->{
				ButtonType accept = new ButtonType("Accept", ButtonBar.ButtonData.OK_DONE);
				ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "Are you sure?", accept, cancel);
				alert.setHeaderText(null);
				alert.setTitle(null);
				setStyle(alert);
				Optional <ButtonType> action = alert.showAndWait();
				
				if(action.get() == accept) {
					((Owner)getActualUser()).deleteParking(parkings.get(index).getName());
					generateParkingList();
					getApp().saveUsers();
				}
			});
			delete.getStyleClass().add("delete");
			box.getChildren().add(delete);
			
			list.getItems().add(box);
		}
	}
	
	public void createParking() {
		load("ParkingCreator");
	}
	
	public void back(){
		load("Menu");
	}
	
	//Load
	public ControlGlobal load(Parking parking) {
		ControlParkingView nextController=null;
		try {
			//Load
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/ParkingView.fxml"));//FXML
			Parent root = (Parent) loader.load();
			//Style
			root.getStylesheets().clear();
			root.getStylesheets().add("/view/" + getThemeName() + ".css");//CSS
			root.getStyleClass().add("pane");
			//Next Controller
			nextController=loader.getController();
			nextController.setActualUser(getActualUser());nextController.setApp(getApp()); nextController.setStage(getStage()); nextController.setTheme(getTheme());//Set All Attributes
			nextController.setParking(parking);
			nextController.generate();
			nextController.updateStyle();
			//Stage Root
			getStage().getScene().setRoot(root);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return nextController;
	}

	@Override
	public void actualizeSearch() {
		// TODO Auto-generated method stub
		
	}
	
}
