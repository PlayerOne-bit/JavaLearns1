package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import system.Authentication;
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
	    try {
	        Parent root = FXMLLoader.load(getClass().getResource(Pages.LOG_IN));
	        Scene scene= new Scene(root);
	        String css=this.getClass().getResource("application.css").toExternalForm();
	        scene.getStylesheets().add(css);
	        stage.setTitle("Wakai Airlines");
	        stage.setResizable(false);
	        stage.setScene(scene);
	        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/logo.png")));
	        stage.show();
	        Authentication.authenticate();
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}

    public static void main(String[] args) {
        launch(args);
    }
}
