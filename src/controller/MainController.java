package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTabPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;

public class MainController implements Initializable {
	private static TablesController controller_tablesController;
	private static POSController controller_POSController;
	private static KitchenController controller_kitchenController;
	
	public static TablesController getTablesController() {
		return controller_tablesController;
	}
	
	public static POSController getPOSController() {
		return controller_POSController;
	}
	
	public static KitchenController getKitchenController() {
		return controller_kitchenController;
	}
	
	@FXML
	private JFXTabPane tabPaneMain;
	private Tab tabFrontOffice = new Tab("Front Office");
	private Tab tabBackOffice = new Tab("Back Office");
	private Tab tabKitchen = new Tab("Kitchen");
	private Tab tabReservation = new Tab("Reservation");
	private Tab tabSettings = new Tab("Settings");
	private Tab tabTablesFront = new Tab("Tables");
	private Tab tabPOS = new Tab("POS");
	private Tab tabTablesSetting = new Tab("Tables");
	private JFXTabPane tabPaneFront = new JFXTabPane();
	private JFXTabPane tabPaneSettings = new JFXTabPane();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tabPaneMain.getTabs().addAll(tabFrontOffice, tabBackOffice, tabKitchen, tabReservation, tabSettings);
		tabPaneFront.getTabs().addAll(tabTablesFront, tabPOS);
		tabPaneFront.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabFrontOffice.setContent(tabPaneFront);
		tabSettings.setContent(tabPaneSettings);
		tabPaneSettings.getTabs().add(tabTablesSetting);
		tabPaneSettings.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		try {
			FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("/fxmlDocument/TablesView.fxml"));
			Parent secondUI = secondLoader.load();
			controller_tablesController = secondLoader.getController();
			tabTablesFront.setContent(secondUI);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("/fxmlDocument/POSView.fxml"));
			Parent secondUI = secondLoader.load();
			controller_POSController = secondLoader.getController();
			tabPOS.setContent(secondUI);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("/fxmlDocument/KitchenView.fxml"));
			Parent secondUI = secondLoader.load();
			controller_kitchenController = secondLoader.getController();
			tabKitchen.setContent(secondUI);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try {
//			FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("/fxmlDocument/TablesSettingView.fxml"));
//			Parent secondUI = secondLoader.load();
//			tabTablesSetting.setContent(secondUI);
//			TablesSettingController tablesSettingController = secondLoader.getController();
//			tablesSettingController.setMainController(this);
//			tablesSettingController.setContent();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		tabPaneMain.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
//			if (!tabFrontOffice.isSelected()) {
//				tablesController.clearZoneSelection();
//			}
//		});
	}

	public boolean confirmDialog(String contentText) {
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

	public void openPOSWithTableNumber(String tableNumber) {
		controller_POSController.setTableNumber(tableNumber);
		tabPOS.getTabPane().getSelectionModel().select(tabPOS);
	}

}
