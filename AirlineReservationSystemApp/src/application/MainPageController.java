package application;
import java.net.URL;
import javafx.util.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;
import system.User;

public class MainPageController  implements Initializable  {
	@FXML private Label username,dateNtime,emailAddress;
	@FXML private Circle profilePic;
	@FXML private FlowPane container;
	
	public static String date;
	
	public void flight(ActionEvent e) throws Exception {
		Pages.change(e, Pages.FLIGHT);
	}
	public void schedule(ActionEvent e) throws Exception{
		Pages.change(e, Pages.SCHEDULE);
	}
	public void settings(ActionEvent e) throws Exception {
		Pages.change(e, Pages.SETTINGS);
	}
	
	private void setDate() {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy - hh:mm:ss a");
	        Timeline clock = new Timeline(
	            new KeyFrame(Duration.ZERO, _ -> {
	            	date=LocalDateTime.now().format(formatter);
	                dateNtime.setText(date);
	            }),
	            new KeyFrame(Duration.seconds(1))
	        );
	        clock.setCycleCount(Timeline.INDEFINITE);
	        clock.play();
	}
	private void setInfo() {
		username.setText("Welcome, "+User.getUsername()+"!");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			for(int i=0; i<5; i++) {
			Pages.addComponent(container,Pages.TRAVEL);
			}
			setInfo();
			setDate();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
