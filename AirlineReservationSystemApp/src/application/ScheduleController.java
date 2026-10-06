package application;

import javafx.event.ActionEvent;

public class ScheduleController {
	public void mainpage(ActionEvent e) throws Exception {
		Pages.change(e, Pages.MAIN_PAGE);
	}
	public void flight(ActionEvent e) throws Exception {
		Pages.change(e, Pages.FLIGHT);
	}
	public void settings(ActionEvent e) throws Exception {
		Pages.change(e, Pages.SETTINGS);
	}
}
