package application;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Music {
	@FXML private MediaView mv;
	
	static Media media = new Media(Music.class.getResource("music/Astronaut Airlines _ Over the Horizon.mp3").toExternalForm());
	public static MediaPlayer mp = new MediaPlayer(media);
	public static void setMusic() {
        mp.setCycleCount(MediaPlayer.INDEFINITE);
        mp.setVolume(0.5);
        mp.play();
    }
	public static void stopMusic() {
		mp.stop();
	}
	static boolean isPlayingMusic=false;
	public static void setVolume(double v) {
		mp.setVolume(v);
	}
	public static double getVolume() {
		return mp.getVolume();
	}
}
