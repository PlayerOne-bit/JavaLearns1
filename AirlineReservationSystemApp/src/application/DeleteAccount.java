package application;

import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import system.Authentication;
import system.User;

public class DeleteAccount implements Initializable{
	@FXML private TextField confirm;
	@FXML private Label error,label;
	public void cancel(ActionEvent e){
		Pages.cancel(e);
	}
	public void confirm(ActionEvent e) {
		if(!confirm.getText().equals(User.getUsername())) {
			error.setText("Invalid input");
			return;
		}
		Authentication.deleteAccount();
		Authentication.authenticate();
		Pages.main_menu(e);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		label.setText("Type \""+User.getUsername()+"\" to CONFIRM:");
	}
}
