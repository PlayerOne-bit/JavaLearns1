package application;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;



public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("nigga.fxml"));
			Parent root = loader.load();
			Controller controller = loader.getController();
			Scene scene = new Scene(root);
			scene.getAccelerators().put(
				    new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN),
				    () -> controller.search()
				);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("FUCKINGGG WEBB APPLICATION");
			
			stage.setScene(scene);
			stage.getIcons().add(new Image("airplane.png"));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
