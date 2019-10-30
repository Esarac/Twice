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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.App;

public class ControlSignIn extends ControlGlobal implements Initializable{
	
	//Node
	@FXML
	private TextField name;
	@FXML
	private TextField lastName;
	@FXML
	private TextField email;
	@FXML
	private TextField id;
	@FXML
	private TextField password;
	@FXML
	private Button Client;
	@FXML
	private Button Employee;
	
	//OnAction
	public void signInClient(ActionEvent e){
		
		boolean possible=app.addClient(name.getText(), lastName.getText(), email.getText(), id.getText(), password.getText());
		if(possible)loadStart(e);
		
	}
	
	public void signInEmployee(ActionEvent e){
		
		boolean possible=app.addEmployee(name.getText(), lastName.getText(), email.getText(), id.getText(), password.getText());
		if(possible)loadStart(e);
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setApp(App app){
		this.app=app;
	}
	
	public void loadStart(ActionEvent e){
		
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/start.fxml"));//FXML
			Parent root = (Parent) loader.load();
			root.getStylesheets().add("/view/application.css");//CSS
			
			ControlStart nextController=loader.getController();
			nextController.setApp(app);
			
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
}
