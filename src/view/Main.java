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
			//Load FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LogIn.fxml"));
			Parent root = (Parent) loader.load();
			//Next Controller
			ControlLogIn nextController = loader.getController();
			//Window Pref
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("TWICE");
			primaryStage.setMinHeight(600);
			primaryStage.setMinWidth(800);
			//Show
			primaryStage.show();
			//Controller Init
			nextController.setStage(primaryStage);
			nextController.generate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
