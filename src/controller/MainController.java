package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTabPane;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;

import model.*;

public class MainController implements Initializable {
	private static MainController instance;
	
	public static MainController getInstance() {
		if (instance == null) {
			synchronized (MainController.class) {
				if (instance == null) {
					instance = new MainController();
				}
			}
		}
		return instance;
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

	private List<ZoneModel> zoneList = new ArrayList<ZoneModel>();
	private List<TableModel> tablesList = new ArrayList<TableModel>();
	private List<CategoryModel> categories = new ArrayList<CategoryModel>();
	private List<ProductModel> productData = new ArrayList<ProductModel>();
	private List<CartProductModel> waitList = new ArrayList<CartProductModel>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tabPaneMain.getTabs().addAll(tabFrontOffice, tabBackOffice, tabKitchen, tabReservation, tabSettings);
		tabPaneFront.getTabs().addAll(tabTablesFront, tabPOS);
		tabPaneFront.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabFrontOffice.setContent(tabPaneFront);
		tabSettings.setContent(tabPaneSettings);
		tabPaneSettings.getTabs().add(tabTablesSetting);
		tabPaneSettings.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		setTablesList();
		setZonesList();
		setCategoriesList();
		setProductsList();
		try {
			FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("/fxmlDocument/TablesView.fxml"));
			Parent secondUI = secondLoader.load();
			tabTablesFront.setContent(secondUI);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("/fxmlDocument/POSView.fxml"));
			Parent secondUI = secondLoader.load();
			tabPOS.setContent(secondUI);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("/fxmlDocument/KitchenView.fxml"));
			Parent secondUI = secondLoader.load();
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

	public List<ZoneModel> getZoneList() {
		return zoneList;
	}

	public List<TableModel> getTablesList() {
		return tablesList;
	}

	public List<CategoryModel> getCategories() {
		return categories;
	}

	public List<ProductModel> getProductData() {
		return productData;
	}

	public List<CartProductModel> getWaitList() {
		return waitList;
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
		POSController.getInstance().setTableNumber(tableNumber);
		tabPOS.getTabPane().getSelectionModel().select(tabPOS);
	}

	private void setTablesList() {
		try {
			URL url = new URL("http://localhost:8080/RestaurantServer/webapi/Table/GetTablesList");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			Gson gson = new Gson();
			// String json = gson.toJson(model);
			// OutputStream os = conn.getOutputStream();
			// os.write(json.getBytes());
			// os.flush();
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					byte[] utf8Bytes = inputLine.getBytes("UTF-8");
					String book = new String(utf8Bytes, "UTF-8");
					response.append(book);
				}
				in.close();
				TypeToken<List<TableModel>> token = new TypeToken<List<TableModel>>() {
				};
				tablesList = gson.fromJson(response.toString(), token.getType());
			}
		} catch (Exception e) {

		}
	}

	private void setZonesList() {
		try {
			URL url = new URL("http://localhost:8080/RestaurantServer/webapi/Table/GetZonesList");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			Gson gson = new Gson();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					byte[] utf8Bytes = inputLine.getBytes("UTF-8");
					String book = new String(utf8Bytes, "UTF-8");
					response.append(book);
				}
				in.close();
				TypeToken<List<ZoneModel>> token = new TypeToken<List<ZoneModel>>() {

				};
				zoneList = gson.fromJson(response.toString(), token.getType());
			}
		} catch (Exception e) {
			
		}
	}

	private void setProductsList() {
		try {
			URL url = new URL("http://localhost:8080/RestaurantServer/webapi/Table/GetProductsList");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			Gson gson = new Gson();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					byte[] utf8Bytes = inputLine.getBytes("UTF-8");
					String book = new String(utf8Bytes, "UTF-8");
					response.append(book);
				}
				in.close();
				TypeToken<List<ProductModel>> token = new TypeToken<List<ProductModel>>() {

				};
				productData = gson.fromJson(response.toString(), token.getType());
			}
		} catch (Exception e) {
			
		}
	}
	
	private void setCategoriesList() {
		try {
			URL url = new URL("http://localhost:8080/RestaurantServer/webapi/Table/GetCategoriesList");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			Gson gson = new Gson();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					byte[] utf8Bytes = inputLine.getBytes("UTF-8");
					String book = new String(utf8Bytes, "UTF-8");
					response.append(book);
				}
				in.close();
				TypeToken<List<CategoryModel>> token = new TypeToken<List<CategoryModel>>() {

				};
				categories = gson.fromJson(response.toString(), token.getType());
			}
		} catch (Exception e) {
			
		}
	}

}
