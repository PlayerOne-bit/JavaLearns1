package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestFXML extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(Pages.FLIGHT));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("ジョヴェン");
		stage.show();
		Music.setMusic();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
