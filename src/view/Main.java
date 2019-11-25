package view;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("TWICE");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
