package application;

import java.io.IOException;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Pages{
	public final static String 
			LOG_IN = "login.fxml",
			REGISTER = "register.fxml",
			MAIN_PAGE = "mainpage.fxml",
			FLIGHT = "flights.fxml",
			SCHEDULE = "schedule.fxml",
			SETTINGS = "setting.fxml",
			LOG_OUT = "logout.fxml", 
			CHANGE_PASSWORD = "changepassword.fxml",
			DELETE_ACCOUNT = "deleteaccount.fxml",
			BOOK_FLIGHT="book.fxml",
			TRAVEL = "travel.fxml",
			SEAT = "seat.fxml",
			PRICE = "price.fxml";
	
	private static Stack<String> stack= new Stack<>(); 
	private static Stage stage, stage2;
	private static Scene scene;
	private static Parent root;
	public static void clear() {
		stack.clear();
	}
	public static void popup(ActionEvent e,String pop,String title)  {
		try {
			FXMLLoader loader = new FXMLLoader(Pages.class.getResource(pop));
			root = loader.load();
			stage2 = new Stage();
			stage2.getIcons().add(new Image(Pages.class.getResourceAsStream("images/logo.png")));
			stage2.setTitle(title);
			stage2.setScene(new Scene(root));
			stage2.initModality(Modality.APPLICATION_MODAL);
			stage2.setResizable(false);
			stage2.showAndWait();
		}catch(Exception d) {
			System.out.println(d);
		}
	}
	public static void addComponent(FlowPane parent,String fxml) {
		try {
			FXMLLoader loader =new FXMLLoader(Pages.class.getResource(fxml));
			Node child=loader.load();
			parent.getChildren().add(child);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void cancel(ActionEvent e) {
		stage2.close();
	}
	public static void main_menu(ActionEvent e) {
		cancel(e);
		try {
			Parent root = FXMLLoader.load(Pages.class.getResource(LOG_IN));
			stage.setScene(new Scene(root));
			stage.centerOnScreen();
			Music.stopMusic();
		} catch (IOException e1) {
			System.out.println(e1);
		}
	}
	
	public static void change(ActionEvent e, String prev, String next){
		Pages.stack.push(prev);
		change(e,next);
	}
	public static void change(ActionEvent e, String next) {
		try {
		root=FXMLLoader.load(Pages.class.getResource(next));
		stage=(Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
		}catch(Exception error) {
			System.out.println(error);
		}
	}
	
	
	public static void back(ActionEvent e) {
		try {
			String pages = stack.pop();
			root=FXMLLoader.load(Pages.class.getResource(pages));
			stage=(Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		}catch(Exception d) {
			System.out.println(d);
		}
	}
	
}
