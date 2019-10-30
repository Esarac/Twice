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

public class ControlLogIn extends ControlGlobal implements Initializable{

	//Node
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;
	@FXML
	private Button logIn;
	
	public void logIn(ActionEvent e){
		if( app.logIn(email.getText(), password.getText()) != null){
			System.out.println("Loged In succesfully");
			load(e);
		}
		else{
			System.out.println("Incorrect email or password");
		}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void load(ActionEvent e){//Correct
		
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/parking.fxml"));//FXML
			Parent root = (Parent) loader.load();
			root.getStylesheets().add("/view/application.css");//CSS
			
			ControlParking nextController=loader.getController();
			nextController.setApp(app);
			
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	

}
