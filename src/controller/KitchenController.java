package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import model.*;

import service.KitchenService;

public class KitchenController implements Initializable {
	private static KitchenController instance;
	
	public static KitchenController getInstance() {
		if (instance == null) {
			synchronized (KitchenController.class) {
				if (instance == null) {
					instance = new KitchenController();
				}
			}
		}
		return instance;
	}
	
	@FXML
	private TableView<KitchenProductModel> tableView;
	private TableColumn<KitchenProductModel, String> productNameList = new TableColumn<KitchenProductModel, String>(
			"Product Name");
	private TableColumn<KitchenProductModel, Number> quantityList = new TableColumn<KitchenProductModel, Number>("Quantity");
	private TableColumn<KitchenProductModel, String> timeList = new TableColumn<KitchenProductModel, String>("Time");

	@FXML
	private void handleReady() {
//		if (tableView.getSelectionModel().getSelectedIndex() != -1) {
//			for (int i = 0; i < main.getTablesList().size(); i++) {
//				if (main.getTablesList().get(i).toString()
//						.compareTo(tableView.getSelectionModel().getSelectedItem().getTableString()) == 0) {
//					for (int j = 0; j < main.getTablesList().get(i).getCustomerProduct().size(); j++) {
//						if (main.getTablesList().get(i).getCustomerProduct().get(j)
//								.equals(tableView.getSelectionModel().getSelectedItem())) {
//							main.getTablesList().get(i).getCustomerProduct().get(j).setProductReady(true);
//							main.getTablesList().get(i).setFoodReady(true);
//							main.getWaitList().remove(tableView.getSelectionModel().getSelectedIndex());
//							tableView.getSelectionModel().clearSelection();
//							return;
//						}
//					}
//				}
//			}
//		}
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productNameList.prefWidthProperty().bind(tableView.widthProperty().multiply(0.6));
		productNameList.setCellValueFactory(new PropertyValueFactory<KitchenProductModel, String>("productName"));
		quantityList.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
		quantityList.setCellValueFactory(new PropertyValueFactory<KitchenProductModel, Number>("amount"));
		timeList.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
		timeList.setCellValueFactory(new PropertyValueFactory<KitchenProductModel, String>("timeOrdered"));
		tableView.getColumns().addAll(productNameList, quantityList, timeList);
//		loadData();
	}
	
//	public void loadData() {
//		ObservableList<KitchenProductModel> kitchenList = FXCollections.observableArrayList(KitchenService.getInstance().getKitchenList());
//		tableView.setItems(kitchenList);
//	}
	
}
