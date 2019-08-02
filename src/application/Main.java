package application;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	private static Main instance;
	private static Stage primaryStage = null;
	private static MainController controller_mainController;
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage stage) {
		primaryStage = stage;
	}
	
	public static Main getInstance() {
		if (instance == null) {
			synchronized (Main.class) {
				if (instance == null) {
					instance = new Main();
				}
			}
		}
		return instance;
	}
	
	public static MainController getMainController() {
		return controller_mainController;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setMaximized(true);
			primaryStage.setResizable(false);
			setPrimaryStage(primaryStage);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlDocument/MainView.fxml"));
			Parent root = loader.load();
			controller_mainController = loader.getController();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
