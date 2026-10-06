package application;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import system.User;

public class SettingController implements Initializable{
	@FXML private Slider volume;
	@FXML private Label id,email;
	@FXML private Button toggleId,toggleEmail;
	@FXML private ImageView music;
	public void mainpage(ActionEvent e) throws Exception {
		Pages.change(e, Pages.MAIN_PAGE);
	}
	public void flight(ActionEvent e) throws Exception {
		Pages.change(e, Pages.FLIGHT);
	}
	public void schedule(ActionEvent e)throws Exception {
		Pages.change(e, Pages.SCHEDULE);
	}
	
	public void log_out(ActionEvent e) throws Exception {
		Pages.popup(e, Pages.LOG_OUT,"LOG OUT");
	}
	public void changePassword(ActionEvent e) {
		Pages.popup(e, Pages.CHANGE_PASSWORD, "CHANGE PASSWORD");
	}
	public void deleteAccount(ActionEvent e) {
		Pages.popup(e, Pages.DELETE_ACCOUNT, "DELETE ACCOUNT");
	}
	private boolean showId=false;
	public void showId() {
		showId=!showId;
		toggleId.setText((showId)?"SHOW":"HIDE");
		id.setText((showId)?User.getId():"#########");
	}
	private boolean showEmail=false;
	private String hiddenEmail="";
	public void showEmail() {
		showEmail=!showEmail;
		toggleEmail.setText((showEmail)?"SHOW":"HIDE");
		email.setText((showEmail)?User.getEmailAddress():hiddenEmail);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		id.setText("#########");
		for(int i=0; i<User.getEmailAddress().length();i++) {
			if(User.getEmailAddress().charAt(i)=='@') {
				hiddenEmail+="@";
				continue;
			}
			if(User.getEmailAddress().charAt(i)=='.') {
				hiddenEmail+=".";
				continue;
			}
			hiddenEmail+="#";
		}
		email.setText(hiddenEmail);
		volume.setValue(Music.getVolume());
	}
	public void setVolume() {
		music.setImage(new Image(getClass().getResource((volume.getValue()>0)?"images/musicicon.png":"images/nomusic.png").toExternalForm()));
		Music.setVolume(volume.getValue());
	}
	
	
}
