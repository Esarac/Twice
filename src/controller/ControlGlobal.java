package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.App;

public abstract class ControlGlobal {

	@FXML private Pane pane;
	
	protected App app;
	protected Stage stage;
	
	public void setApp(App app){
		this.app=app;
	}
	
	public <T extends Dialog<?>> void setCss(T dialog) {
		
		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/view/View.css").toExternalForm());
		dialogPane.getStyleClass().add("dialog");
	}
	
	public void load(String dir) {
		try {
			
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/"+dir+".fxml"));//FXML
			Parent root = (Parent) loader.load();
			root.getStylesheets().add("/view/application.css");//CSS
			
			ControlGlobal nextController=loader.getController();
			nextController.setApp(app);
			stage = (Stage) pane.getScene().getWindow();
			stage.setScene(new Scene(root));
			
			stage.centerOnScreen();
			
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