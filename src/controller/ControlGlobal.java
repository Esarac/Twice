package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.App;

public abstract class ControlGlobal {

	@FXML
	private BorderPane pane;
	
	protected App app;
	
	public void setApp(App app){
		this.app=app;
	};
	
	public void load(String dir){
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/"+dir+".fxml"));//FXML
			Parent root = (Parent) loader.load();
			root.getStylesheets().add("/view/application.css");//CSS
			
			ControlGlobal nextController=loader.getController();
			nextController.setApp(app);
			if(nextController instanceof Generator){
				Generator generatorController=(Generator)nextController;
				generatorController.generate();
			}
			Stage stage = (Stage) pane.getScene().getWindow();
			stage.setScene(new Scene(root));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
