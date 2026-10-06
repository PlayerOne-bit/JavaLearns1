package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

public class Controller implements Initializable {
	@FXML public WebView view;
	@FXML public TextField link;
	@FXML private Button search;
	@FXML private WebEngine engine;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		engine=view.getEngine();
		search();
	}
	public void search() {
			String input=link.getText();
			
			if(input.matches(".+[.].+[.]*.*")) {
				engine.load("https://"+input);
				
				
			}else {
				System.out.println("Should be example.com");;	
			}
	}
	
}
