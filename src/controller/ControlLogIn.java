package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.App;

public class ControlLogIn extends ControlGlobal implements Initializable, Generator {
	
	@FXML private TextField email;
	@FXML private PasswordField password;
	@FXML private CheckBox logging;
	@FXML private Button logIn;
	@FXML private Button register;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void logIn() {
		
		if(email.getText().isEmpty() || password.getText().isEmpty()) {
			
			//Creates an alert if any field is empty.
			ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.NONE, "Please complete the fields!", ok);
			alert.setHeaderText(null);
			alert.setTitle(null);
			
			//Sets the alert style.
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("file:images/TWICE_LOGO.png"));
			setCss(alert);
			
			//Shows the alert.
			alert.showAndWait();
		}
		else {
			
			boolean keep = false;
			
			if(logging.isSelected())
				keep = true;
			
			if(app.logIn(email.getText(), password.getText(), keep) == null) {
				
				//Creates an alert if the email or password is incorrect.
				ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
				Alert alert = new Alert(AlertType.NONE, "Email or password incorrect, please try again!", ok);
				alert.setHeaderText(null);
				alert.setTitle(null);
				
				//Sets the alert style.
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("file:images/TWICE_LOGO.png"));
				setCss(alert);
				
				//Shows the alert.
				alert.showAndWait();
				
				//Clears the text fields.
				email.setText("");
				password.setText("");
			}
		}
	}
	
	public void register() {
		
		load("Register");
		stage.setWidth(800);
		stage.setHeight(600);
	}

	@Override
	public void generate() {
		
		app = new App("TWICE");
		
	}
	
}
