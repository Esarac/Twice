package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.App;

public abstract class ControlGlobal {

	
	public enum Theme {
		
		FEEL_SPECIAL, FANCY, SIGNAL, TT, YES_OR_YES, LIKEY, WHAT_IS_LOVE;
	}
	
	@FXML private Pane pane;
	
	protected App app;
	protected Stage stage;
	protected Theme theme = Theme.FANCY;
	
	public void setApp(App app) {
		this.app=app;
	}
	
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public String getTheme() {
		return theme.toString();
	}
	
	public <T extends Dialog<?>> void setStyle(T dialog) {
		
		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/view/" + getTheme() + ".css").toExternalForm());
		dialogPane.getStyleClass().add("dialog");
		Stage stage = (Stage) dialogPane.getScene().getWindow();
		stage.getIcons().add(new Image("file:themes/" + getTheme() + "/images/TWICE_LOGO.png"));
	}
	
	public void load(String dir) {
		try {
			
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/" + dir + ".fxml"));//FXML
			Parent root = (Parent) loader.load();
			root.getStylesheets().add("/view/" + getTheme() + ".css");//CSS
			root.getStyleClass().add("pane");
			ControlGlobal nextController=loader.getController();
			nextController.setApp(app);
			stage.getScene().setRoot(root);
			
			if(nextController instanceof Generator){
				Generator generatorController=(Generator)nextController;
				generatorController.generate();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}