package controller;

import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import model.Owner;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class ControlMenu extends ControlGlobal implements Generator {
	
	//Nodes
	@FXML ChoiceBox<Theme> themes;
	@FXML Button vehicles;

	//Methods
	@Override
	public void generate() {
		
		themes.getItems().addAll(Theme.FEEL_SPECIAL, Theme.FANCY, Theme.SIGNAL, Theme.TT, Theme.YES_OR_YES, Theme.LIKEY, Theme.WHAT_IS_LOVE);
		themes.getSelectionModel().select(getTheme());
		themes.setOnAction(event -> pickTheme());
		
		if(getActualUser() instanceof Owner) {
			
			pane.getChildren().remove(vehicles);
			
		}
		
	}
	
		//Select Theme
	public void pickTheme() {
		
		//Sets the new theme.
		setTheme(themes.getValue());
		updateStyle();
		saveStyle();
		
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
			
			getApp().logOut();
			load("LogIn");
		}
	}
	
}
