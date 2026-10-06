package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import system.Authentication;

public class LogInController {
	@FXML
	private Button signin;
	@FXML
	private Label error;
	@FXML
	private TextField email;
	@FXML
	private PasswordField pass;
	
	public void LogIn(ActionEvent e) throws Exception {
		error.setText("");
		String email_address = email.getText().toLowerCase(),
				password = pass.getText();
		
		if(email_address.isEmpty()||password.isEmpty()) {
			error.setText("Fill in the missing inputs");
			return;
		}else if(Authentication.logIn(email_address, password)) {
				Pages.clear();
				Pages.change(e, Pages.MAIN_PAGE);
				Music.setMusic();
		}else {
			error.setText("Invalid username/email address or password!");
		}
		email.setText("");
		pass.setText("");
	}
	
	
	public void Register(ActionEvent e) throws Exception {
		Pages.change(e,Pages.LOG_IN, Pages.REGISTER);
	}
}
