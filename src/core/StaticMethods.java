package core;

import java.util.Optional;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StaticMethods {
	public static SplittedTableString splitTableString(String tableString) {
		String[] split = tableString.split("-");
		return new SplittedTableString(split[0], Integer.parseInt(split[1]));
	}
	
	public static void setMessage(String s, Label message) {
		message.setText(s);
		FadeTransition fader = createFader(message);
		SequentialTransition fade = new SequentialTransition(message, fader);
		fade.play();
	}

	private static FadeTransition createFader(Node node) {
		FadeTransition fade = new FadeTransition(Duration.seconds(5), node);
		fade.setFromValue(1);
		fade.setToValue(0);
		return fade;
	}

	public static void lockedSizeStage(Stage stage) {
		stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				stage.setMaximized(false);
			}
		});
		stage.setResizable(false);
	}
	
	public static boolean confirmDialog(String contentText) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setContentText(contentText);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}
}
