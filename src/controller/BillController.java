package controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CartProductModel;
import model.TableModel;

public class BillController implements Initializable {
	@FXML
	private Label customerName;
	@FXML
	private Label tableNumber;
	@FXML
	private Label time;
	@FXML
	private TableView<CartProductModel> tableView;
	@FXML
	private TableColumn<CartProductModel, String> productName;
	@FXML
	private TableColumn<CartProductModel, Number> quantity;
	@FXML
	private TableColumn<CartProductModel, Number> eachPrice;
	@FXML
	private TableColumn<CartProductModel, Number> totalPrice;
	@FXML
	private Label subTotalLabel;
	@FXML
	private Label taxLabel;
	@FXML
	private Label totalLabel;
	@FXML
	private Label discountLabel;
	@FXML
	private Label balanceLabel;
	
	private ObservableList<CartProductModel> bought = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productName.setCellValueFactory(new PropertyValueFactory<CartProductModel, String>("productName"));
		quantity.setCellValueFactory(new PropertyValueFactory<CartProductModel, Number>("amount"));
		eachPrice.setCellValueFactory(new PropertyValueFactory<CartProductModel, Number>("price"));
		totalPrice.setCellValueFactory(new PropertyValueFactory<CartProductModel, Number>("totalPrice"));
		tableView.setItems(bought);
		tableView.getStylesheets().add("/css/noScrollBarTableView.css");
		tableView.setFixedCellSize(30.0);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		time.setText(dtf.format(now));
	}

	public void setContent(TableModel table) {
		for (int i = 0; i < table.getCustomerProduct().size(); i++) {
			if (table.getCustomerProduct().get(i).getStatus().equals("Delivered")) {
				bought.add(table.getCustomerProduct().get(i));
			}
		}
		tableNumber.setText(String.valueOf(table.getTableNumber()));
		tableView.setPrefHeight(30.0 * (bought.size() + 1));
		subTotalLabel.setText(String.valueOf(table.getSubTotal()));
		taxLabel.setText(String.valueOf(table.getTax()));
		totalLabel.setText(String.valueOf(table.getTotal()));
		discountLabel.setText(String.valueOf(table.getDiscount()));
		balanceLabel.setText(String.valueOf(table.getBalance()));
	}
	
	public void setCustomerName(String s) {
		
	}
}
