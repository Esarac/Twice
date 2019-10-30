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
import javafx.stage.Stage;
import model.App;

public class ControlStart extends ControlGlobal implements Initializable{

	//Nodes
	@FXML
	private Button logIn;
	@FXML
	private Button signIn;
	
	//OnAction
	public void loadLogIn(ActionEvent e){
		
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/logIn.fxml"));//FXML
			Parent root = (Parent) loader.load();
			root.getStylesheets().add("/view/application.css");//CSS
			
			ControlLogIn nextController=loader.getController();
			nextController.setApp(app);
			
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void loadSignIn(ActionEvent e){
		
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/signIn.fxml"));//FXML
			Parent root = (Parent) loader.load();
			root.getStylesheets().add("/view/application.css");//CSS
			
			ControlSignIn nextController=loader.getController();
			nextController.setApp(app);
			
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		app=new App();
	}
	
}
