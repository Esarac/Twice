package controller;

import exception.AlreadyExistException;
import exception.InvalidEmailException;
import exception.InvalidPasswordException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class ControlRegister extends ControlGlobal implements Generator {
	
	@FXML TextField name;
	@FXML TextField email;
	@FXML PasswordField password;
	@FXML PasswordField confirmPassword;
	@FXML ChoiceBox<String> choice;
	
	public void register() {
		
		if(name.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty() || confirmPassword.getText().isEmpty()) {
			
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
			
			if(!password.getText().equals(confirmPassword.getText())) {
				
				//Creates an alert if the passwords are not equals.
				ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "The passwords are not equals, please try again!", ok);
				alert.setHeaderText(null);
				alert.setTitle(null);
				
				//Sets the alert style.
				setStyle(alert);
				
				//Shows the alert.
				alert.showAndWait();
				
				//Clear the password fields.
				password.setText("");
				confirmPassword.setText("");
			}
			else {
				
				addUser();
			}
		}
	}
	
	public void addUser() {
		
		try {
			
			if(choice.getValue().equals("Client")) {
				
				getApp().addClient(name.getText(), email.getText(), password.getText());
			}
			else {
				
				getApp().addOwner(name.getText(), email.getText(), password.getText());
			}
		}
		catch(InvalidEmailException e) {
			
			//Creates an alert if the email is invalid.
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "Email must include \"@\" character", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			
			//Sets the alert style.
			setStyle(alert);
			
			//Shows the alert.
			alert.showAndWait();
			
			//Clears email field.
			email.setText("");
			
		}
		catch(InvalidPasswordException e) {
			
			//Creates an alert if the password is invalid.
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "Password must be between eight (8) and twenty (20) characters in length and contain at least one (1) character from two (2) of the following categories: uppercase letter (A-Z) and digit (0-9)", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			
			//Sets the alert style.
			setStyle(alert);
			
			//Shows the alert.
			alert.showAndWait();
			
			//Clear the password fields.
			password.setText("");
			confirmPassword.setText("");
		}
		catch(AlreadyExistException e) {
			
			//Creates an alert if already exists an user with that email.
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "An account is already registered with that email, please try again!", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			
			//Sets the alert style.
			setStyle(alert);
			
			//Shows the alert.
			alert.showAndWait();
			
			//Clear the fields.
			name.setText("");
			email.setText("");
			password.setText("");
			confirmPassword.setText("");
		}
		
		getApp().saveUsers();
	}
	
	public void cancel() {
		
		load("LogIn");
	}

	@Override
	public void generate() {
		
		choice.getItems().addAll("Client", "Manager");
		choice.getSelectionModel().select(0);
		
	}
}
