package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControlGlobal.Theme;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.App;
import model.User;

public abstract class ControlGlobal implements Initializable{

	//Constants
	public static final String THEME_PATH="dat/Theme.twc";
	
	public enum Theme {
		FEEL_SPECIAL, FANCY, SIGNAL, TT, YES_OR_YES, KNOCK_KNOCK, CHEER_UP;
		
		public String toString() {
			String text="";
			
			//Transformer
			for(int i=0; i<name().length(); i++) {
				
				if(name().charAt(i)=='_'){
					text+=" ";
				}
				else if((i==0) || (name().charAt(i-1)=='_') || (!Character.isLetter(name().charAt(i)) )){
					text+=name().charAt(i);
				}
				else{
					text+=Character.toLowerCase(name().charAt(i));
				}
				
			}
			//...
			
			//Special Cases
			if(this==TT){
				text=name();
			}
			//...
			
			return text;
		}
		
	}
	
	//Attributes
	private Stage stage;
	private Theme theme;
	private App app;
	private User actualUser;
	
	//Node
	@FXML protected Pane pane;
	
	//Methods
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(getTheme()==null){
			setTheme(Theme.FANCY);
		}
	}
	
	//Load
	public ControlGlobal load(String dir) {
		ControlGlobal nextController=null;
		try {
			//Load
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/" + dir + ".fxml"));//FXML
			Parent root = (Parent) loader.load();
			//Style
			root.getStylesheets().clear();
			root.getStylesheets().add("/view/" + getThemeName() + ".css");//CSS
			root.getStyleClass().add("pane");
			//Next Controller
			nextController=loader.getController();
			nextController.setActualUser(actualUser);nextController.setApp(app); nextController.setStage(stage); nextController.setTheme(theme);//Set All Attributes
			if(nextController instanceof Generator){//Pos
				Generator generatorController=(Generator)nextController;
				generatorController.generate();
			}
			nextController.updateStyle();
			//Stage Root
			stage.getScene().setRoot(root);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return nextController;
	}
	
	//Style
	public void updateStyle() {
		//Clears the pane CSS and sets the new.
		pane.getStylesheets().clear();
		pane.getStylesheets().add("/view/" + getThemeName() + ".css");
		pane.getStyleClass().add("pane");
		//Updates the stage icon.
		stage.getIcons().clear();
		stage.getIcons().add(new Image("file:themes/" + getThemeName() + "/images/TWICE_LOGO.png"));
	}
	
	public <T extends Dialog<?>> void setStyle(T dialog) {
		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/view/" + getThemeName() + ".css").toExternalForm());
		dialogPane.getStyleClass().add("dialog");
		Stage stage = (Stage) dialogPane.getScene().getWindow();
		stage.getIcons().add(new Image("file:themes/" + getThemeName() + "/images/TWICE_LOGO.png"));
	}
	
	public void saveStyle(){
		try {
			File dir=new File("dat//");
			dir.mkdir();
			File actual=new File(THEME_PATH);
			String text=theme.name();
			PrintWriter writer=new PrintWriter(actual);
			writer.append(text);
			writer.close();
		}
		catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void loadStyle() {
		try {
			String text="";
			File file=new File(THEME_PATH);
			if(file.exists()){
				file.createNewFile();
				FileReader fileReader=new FileReader(file);
				BufferedReader reader=new BufferedReader(fileReader);
				String actualLine;
				while((actualLine=reader.readLine())!=null){
					text+=actualLine+"";
				}
				reader.close();
			}
			
			this.theme=Theme.valueOf(text);
			updateStyle();
		}
		catch(IOException | IllegalArgumentException e) {
			updateStyle();
			saveStyle();
		}
	}
	
	//Set
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
	public void setApp(App app) {
		this.app=app;
	}
	
	public void setActualUser(User user) {
		this.actualUser=user;
	}
	
	//Get
	public Stage getStage() {
		return stage;
	}
	
	public String getThemeName() {
		return theme.name();
	}
	
	public Theme getTheme() {
		return theme;
	}
	
	public App getApp() {
		return app;
	}
	
	public User getActualUser() {
		return actualUser;
	}
	
}