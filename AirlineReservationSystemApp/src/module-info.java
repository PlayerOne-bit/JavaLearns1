module AirlineReservationSystemApp {
	requires java.desktop;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	requires jbcrypt;
	requires javafx.media;
	
	opens application to javafx.graphics, javafx.fxml;
}