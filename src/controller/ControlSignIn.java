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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.App;

public class ControlSignIn extends ControlGlobal implements Initializable{
	
	//Node
	@FXML
	private TextField name;
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField passwordTest;
	@FXML
	private Button Client;
	@FXML
	private Button Employee;
	
	//OnAction
	public void signInClient(ActionEvent e){
		if(password.getText().equals(passwordTest.getText())){
			boolean possible=app.addClient(name.getText(), email.getText(), password.getText());
			app.saveUsers();
			if(possible)load("LogIn");
		}
	}
	
	public void signInEmployee(ActionEvent e){
		if(password.getText().equals(passwordTest.getText())){
			boolean possible=app.addEmployee(name.getText(), email.getText(), password.getText());
			app.saveUsers();
			if(possible)load("LogIn");
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setApp(App app){
		this.app=app;
	}
	
}
