package application;


import org.mindrot.jbcrypt.BCrypt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import system.Authentication;
import system.User;

public class ChangePassword {

	@FXML private PasswordField pass,cpass;
	@FXML private Label error;
	public void changePassword(ActionEvent e) throws Exception {
		String password = pass.getText(),
				confirmPassword = cpass.getText();
		if(password.length()<8 || password.length()>15) {
			error.setText("Password length must between 8 to 15 characters");
			return;
		}else if(!password.equals(confirmPassword)) {
			error.setText("New Password and Confirm Password must be the same!");
			return;
		}else if(BCrypt.checkpw(password, User.getPassword())) {
			error.setText("New Password must not be the same as Old Password!");
			return;
		}
		Authentication.changePassword(password);
		cancel(e);
	}
	
	public void cancel(ActionEvent e) {
		Pages.cancel(e);
	}
}