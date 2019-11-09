package view;
	
import controller.ControlGlobal;
import controller.ControlMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.App;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			App app=new App();
			if(app.getActualUser()!=null){
				FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/menu.fxml"));//FXML
				Parent root=(Parent)loader.load();//FXML
				root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());//CSS
				primaryStage.setTitle("ParqueApp");
				primaryStage.getIcons().add(new Image("file:../../med/images/logo.png"));
				primaryStage.setScene(new Scene(root));
				primaryStage.show();
				ControlMenu nextController=loader.getController();
				nextController.setApp(app);
				nextController.generate();
			}
			else{
				FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/logIn.fxml"));//FXML
				Parent root=(Parent)loader.load();//FXML
				root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());//CSS
				primaryStage.setTitle("ParqueApp");
				primaryStage.getIcons().add(new Image("file:../../med/images/logo.png"));
				primaryStage.setScene(new Scene(root));
				primaryStage.show();
				ControlGlobal nextController=loader.getController();
				nextController.setApp(app);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
