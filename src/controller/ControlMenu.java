package controller;

import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import model.Owner;
import thread.ThreadStringAnimator;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

public class ControlMenu extends ControlGlobal implements Generator {
	
	//Nodes
	@FXML private ChoiceBox<Theme> themes;
	@FXML private Button vehicles;

	//Methods
	@Override
	public void generate() {
		
		themes.getItems().addAll(Theme.FEEL_SPECIAL, Theme.FANCY, Theme.SIGNAL, Theme.TT, Theme.YES_OR_YES, Theme.KNOCK_KNOCK, Theme.CHEER_UP);
		themes.getSelectionModel().select(getTheme());
		themes.setOnAction(event -> pickTheme());
		
		if(getActualUser() instanceof Owner) {
			
			pane.getChildren().remove(vehicles);
			
		}
		
	}
	
	public void showParkings() {
		load("ParkingMenu");
	}
	
	public void showVehicles() {
		load("VehicleMenu");
	}
	
		//Select Theme
	public void pickTheme() {
		
		//Sets the new theme.
		setTheme(themes.getValue());
		updateStyle();
		saveStyle();
		AudioClip audio = new AudioClip("file:themes/" + getThemeName() + "/sounds/" + getThemeName() + ".wav" );
		audio.play();
	}
	
		//LogOut
	public void logOut() {
		
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
			setActualUser(null);
			getApp().logOut();
			load("LogIn");
		}
	}
	
}
