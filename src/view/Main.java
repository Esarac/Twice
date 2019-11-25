package view;
	
import controller.ControlLogIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
* <b>Description:</b> The class Main in the package view.<br>
* @author VoodLyc & Esarac.
*/

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LogIn.fxml"));
			Parent root = (Parent) loader.load();
			ControlLogIn nextController = loader.getController();
			nextController.generate();
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("TWICE");
			primaryStage.getIcons().add(new Image("file:images/TWICE_LOGO.png"));
			primaryStage.setMinHeight(600);
			primaryStage.setMinWidth(800);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
