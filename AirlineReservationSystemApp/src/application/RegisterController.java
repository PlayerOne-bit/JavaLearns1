package application;
import system.Authentication;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import org.mindrot.jbcrypt.BCrypt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController implements Initializable{
	@FXML public TextField username,email;
	@FXML public PasswordField password,cPassword;
	private static boolean[] error= new boolean[6];
	@FXML private Label error1,error2,error3,error4,error5;
	private static void style(TextField tf,Label lb ,String text,boolean condition) {
		String format="-fx-border-width: 2 2 2 2; -fx-border-radius:10; -fx-background-radius:10;-fx-padding:5;-fx-border-color: ",
				red=format+"rgb(255,50,50)",
				green=format+"lightgreen",
				correct="-fx-text-fill: lightgreen";
		if(condition && !tf.getText().isEmpty()) {
			tf.setStyle(green);
			lb.setStyle(correct);
		}else {
			tf.setStyle(red);
			lb.setStyle("");
		}
		lb.setText(text);
	}
	private static void style(PasswordField pf, Label lb, String text, int condition) {
		String format="-fx-border-width: 2 2 2 2; -fx-border-radius:10; -fx-background-radius:10;-fx-padding:5;-fx-border-color: ",
				correct="-fx-text-fill: ";
		if(condition>0 && !pf.getText().isEmpty()) {
			if(condition==1) {pf.setStyle(format+"orangered"); lb.setStyle(correct+"orangered");}
			else if(condition==2) {pf.setStyle(format+"orange");lb.setStyle(correct+"orange");}
			else if(condition==3) {pf.setStyle(format+"yellow");lb.setStyle(correct+"yellow");}
			else {pf.setStyle(format+"lightgreen");lb.setStyle(correct+"lightgreen");}
		}else {
			pf.setStyle(format+"rgb(255,50,50)");
			lb.setStyle("");
		}
		lb.setText(text);
	}
	private boolean isMissing() {
		TextField[] tfs= {username,email};
		PasswordField[] pfs= {password,cPassword};
		String format="-fx-border-width: 2 2 2 2; -fx-border-radius:10; -fx-background-radius:10;-fx-padding:5;-fx-border-color: ",
				red=format+"rgb(255,50,50)";
		
		boolean isEmpty=false;
		for(TextField tf: tfs) {
			if(tf.getText().isEmpty()) {
				tf.setStyle(red);
				isEmpty=true;
			}
		}
		for(PasswordField pf: pfs) {
			if(pf.getText().isEmpty()) {
				pf.setStyle(red);
				isEmpty=true;
			}
		}
		if(isEmpty) error5.setText("Fill missing information"); ;
		return isEmpty;
	}
	public String generateId() {
		String id="";
		for(int i=0; i<9; i++) {
			id+=new Random().nextInt(10);
		}
		return id;
	}
	public void register(ActionEvent e) {
		try {
			String id=generateId();
			String[] data = {
					id,
					username.getText().trim(),
					email.getText().trim().toLowerCase(), 
					BCrypt.hashpw(password.getText(),BCrypt.gensalt())
			};
			boolean hasError=false;
			for(boolean err:error) {
				if(err) {
					hasError=true;
					break;
				}
			}
			if(!isMissing() && !hasError) {
				Authentication.createAccount(data);
				Back(e);
			}
		}catch(Exception d) {
			d.printStackTrace();
		}
	}
	public void Back(ActionEvent e) throws Exception {
		Pages.back(e);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		username.textProperty().addListener((_,_,text)->{
			if(Authentication.hasUsername(text.trim())) {
				error[0]=true;
				style(username,error1,"Username already taken",false);
			}else{
				error[0]=false;
				style(username,error1,"Username is available",true);
			}
		});
		email.textProperty().addListener((_,_,text)->{
			if(!text.trim().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
				error[1]=true;
				style(email,error2,"Incorrect email format",false);
			}else if(Authentication.hasEmailAddress(text.trim())) {
				error[1]=true;
				style(email,error2,"Email address already signed in",false);
			}else{
				error[1]=false;
				style(email,error2,"Email address is valid",true);
			}
		});
		password.textProperty().addListener((_,_,text)->{
			int num=passwordStrength(text);
			if(text.trim().length()<8 || text.trim().length()>15) {
				num=0;
				error[2]=true;
				style(password,error3,"Password length must between 8 to 15 characters",num);
			}else {
				error[2]=false;
				style(password,error3,"Contains least 1 upper/lowercase, digits,& symbols",num);
			}
		});
		cPassword.textProperty().addListener((_,_,text)->{
			if(!text.trim().equals(password.getText().trim())){
				error[3]=true;
				style(cPassword,error4,"Password and Confirm Password must be the same",false);
			}else {
				error[3]=false;
				style(cPassword,error4,"Password and Confirm Password are the same",true);
			}
		});
//		contact_info.textProperty().addListener((_,_,text)->{
//			if(text.matches("0[0-9]{10}")||
//				text.matches("[0-9]{4}-[0-9]{3}-[0-9]{4}")||
//				text.matches("\\+[0-9]{12}")) {
//				error[4]=false;
//				style(contact_info,error5,"Valid contact number",true);
//			}else {
//				error[4]=true;
//				style(contact_info,error5,"Invalid contact number",false);
//			}
//		});
		
	}
	private int passwordStrength(String password) {
	    int strength = 0;
	    if (password.matches(".*[A-Z].*")) strength++;
	    if (password.matches(".*[a-z].*")) strength++;
	    if (password.matches(".*\\d.*")) strength++;
	    if (password.matches(".*[^a-zA-Z0-9].*")) strength++;
	    return strength;
	}

	
}
