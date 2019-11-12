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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.App;

public class ControlLogIn extends ControlGlobal implements Initializable{
	
	//Node
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;
	@FXML
	private CheckBox keep;
	@FXML
	private Button logIn;
	@FXML
	private Button signIn;
	
	public void logIn(ActionEvent e){
		if( app.logIn(email.getText(), password.getText()) != null){
			if(keep.isSelected()) app.saveActualUser();
			load("Menu");
		}
	}
	
	public void loadSignIn(ActionEvent e){
		load("SignIn");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
}
