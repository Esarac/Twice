package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import model.App;
import model.Client;
import model.User;
import thread.ThreadSave;
import thread.ThreadStringAnimator;

public class ControlLogIn extends ControlGlobal implements Generator {
	
	//Nodes
	@FXML private Canvas canvas;
	@FXML private TextField email;
	@FXML private PasswordField password;
	@FXML private CheckBox logging;
	@FXML private Button register;
	
	//Methods
	@Override
	public void generate() {
		
		//Credits
		canvas=new Canvas(800, 50);
		pane.getChildren().add(canvas);
		ThreadStringAnimator animator=new ThreadStringAnimator(this, "Created By: Voodlyc & Esarac          ");
		animator.start();
		
		//Init
		setApp(new App("TWICE"));
		loadStyle();
		register.getStyleClass().add("register");
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
	
	public void showCredits(int pos, String letter) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFont(Font.font("Arial", FontWeight.BLACK, FontPosture.REGULAR, 14));
		gc.setFill(Color.WHITE);
		gc.fillText(letter, pos*10, 10);
		
		gc.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.REGULAR, 13));
		gc.setFill(Color.BLACK);
		gc.fillText(letter, pos*10, 10);
	}
	
}
