package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.App;
import model.Client;
import model.User;
import thread.ThreadSave;

public class ControlLogIn extends ControlGlobal implements Generator {
	
	//Nodes
	@FXML private TextField email;
	@FXML private PasswordField password;
	@FXML private CheckBox logging;
	
	//Methods
	@Override
	public void generate() {
		
		//Init
		setApp(new App("TWICE"));
		loadStyle();
		new ThreadSave(getApp());
		
		//LogIn
		User user=getApp().automaticLogIn();
		if(user != null) {
			setActualUser(user);
			load("Menu");
		}
		
	}
	
		//LogIn
	public void logIn() {
		if(email.getText().isEmpty() || password.getText().isEmpty()) {
			
			//Creates an alert if any field is empty.
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "Please complete the fields!", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			
			//Sets the alert style.
			setStyle(alert);
			
			//Shows the alert.
			alert.showAndWait();
		}
		else {
			User user=getApp().logIn(email.getText(), password.getText(), logging.isSelected());
			if( user == null) {
				
				//Creates an alert if the email or password is incorrect.
				ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "Email or password incorrect, please try again!", ok);
				alert.setHeaderText(null);
				alert.setTitle(null);
				
				//Sets the alert style.
				setStyle(alert);
				
				//Shows the alert.
				alert.showAndWait();
				
				//Clears the text fields.
				email.setText("");
				password.setText("");
			}
			else {
				//Loads the Menu.fxml.
				setActualUser(user);
				load("Menu");
			}
		}
	}
	
		//Register
	public void register() {
		//Loads the Register.fxml.
		load("Register");
	}
	
}
