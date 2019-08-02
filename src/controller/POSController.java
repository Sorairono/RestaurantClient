package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.text.TextAlignment;

import core.*;
import model.*;
import request.*;
import response.*;
import service.*;

public class POSController implements Initializable {
	private static POSController instance;

	public static POSController getInstance() {
		if (instance == null) {
			synchronized (POSController.class) {
				if (instance == null) {
					instance = new POSController();
				}
			}
		}
		return instance;
	}

	@FXML
	private Label tableMessage;
	@FXML
	private HBox mainPane;
	@FXML
	private BorderPane cartPane;
	@FXML
	private VBox choicePane;
	@FXML
	private AnchorPane cartBottom;
	@FXML
	private ScrollPane categoriesPane;
	@FXML
	private ScrollPane typesPane;
	@FXML
	private ScrollPane menuPane;
	@FXML
	private TableView<CartProductModel> cartView;
	@FXML
	private HBox changeTableBox;
	@FXML
	private JFXComboBox<String> zoneLetterBox;
	@FXML
	private JFXComboBox<Integer> tableNumberBox;
	@FXML
	private Label tableNumber;
	@FXML
	private ToggleButton changeTableButton;
	@FXML
	private JFXButton enterButton;
	@FXML
	private Label message;
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
	@FXML
	private ToggleButton discountButton;
	@FXML
	private HBox discountInput;
	@FXML
	private JFXTextField discountField;
	@FXML
	private JFXTextField filterField;
	private TableColumn<CartProductModel, String> productNameList = new TableColumn<CartProductModel, String>("Name");
	private TableColumn<CartProductModel, CartProductModel> quantityList = new TableColumn<CartProductModel, CartProductModel>(
			"Quantity");
	private TableColumn<CartProductModel, Number> eachList = new TableColumn<CartProductModel, Number>("Each");
	private TableColumn<CartProductModel, Number> totalList = new TableColumn<CartProductModel, Number>("Total");
	private TableColumn<CartProductModel, String> timeOrderedList = new TableColumn<CartProductModel, String>(
			"Time Ordered");
	private TableColumn<CartProductModel, String> timeCompletedList = new TableColumn<CartProductModel, String>(
			"Time Completed");
	private TableColumn<CartProductModel, String> statusList = new TableColumn<CartProductModel, String>("Status");
	private IntegerProperty columnCount = new SimpleIntegerProperty(0);
	private GridPane gridPane1 = new GridPane();
	private GridPane gridPane2 = new GridPane();
	private GridPane gridPane3 = new GridPane();
	private List<CategoryModel> categoriesList = new ArrayList<CategoryModel>();
	private List<ProductModel> displayMenu = FXCollections.observableArrayList();
	private ObjectProperty<TableRow<CartProductModel>> lastSelectedRow = new SimpleObjectProperty<>();
	private List<ZoneModel> zoneList = new ArrayList<ZoneModel>();
	private SplittedTableString tableString;

	@FXML
	private void handleEnter() {
		if (zoneLetterBox.getSelectionModel().getSelectedIndex() == -1
				|| tableNumberBox.getSelectionModel().getSelectedIndex() == -1) {
			StaticMethods.setMessage("Please fill in the field with table number", message);
			return;
		}
		TableNumberModel tableNumberModel = new TableNumberModel(zoneLetterBox.getSelectionModel().getSelectedItem(),
				tableNumberBox.getSelectionModel().getSelectedItem());
		TableModel newTable = TableService.getInstance().getTable(tableNumberModel);
		if (newTable != null) {
			if (!newTable.isOccupied()) {
				if (!newTable.isMerge()) {
					if (StaticMethods
							.confirmDialog("Do you want to open a bill at table " + newTable.toString() + "?")) {
						ResponseModel openTable = TableService.getInstance().openTable(tableNumberModel);
						if (openTable.isSuccess()) {
							tableNumber.setText(newTable.toString());
							StaticMethods.setMessage("Opened Table " + newTable.toString(), message);
						}
					}
				} else {
					tableNumber.setText(newTable.getMergeTable());
					StaticMethods.setMessage("Changed to merge Table " + newTable.getMergeTable(), message);
				}
			} else {
				tableNumber.setText(newTable.toString());
				StaticMethods.setMessage("Changed to Table " + newTable.toString(), message);
			}
		}
	}

	@FXML
	private void handleEnterCoupon() {
//		for (int i = 0; i < couponList.size(); i++) {
//			if (discountField.getText().compareTo(couponList.get(i).getCouponCode()) == 0) {
//				currentTable.setCoupon(couponList.get(i));
//				setPriceLabel();
//				discountField.setText("");
//				discountInput.setVisible(false);
//				main.setMessage("Discount successfully applied", message);
//				return;
//			}
//		}
//		main.setMessage("Invalid coupon code", message);
	}

	@FXML
	private void handleCancel() {
//		if (currentTable != null) {
//			if (!tableProductList.removeIf(c -> !c.isOrderSent())) {
//				main.setMessage("You didn't choose any product", message);
//			}
//		} else {
//			main.setMessage("Please choose a table first", message);
//		}
	}

	@FXML
	private void handleSend() {
		if (tableString != null) {
			if (cartView.getItems().size() > 0) {
				for (int i = 0; i < cartView.getItems().size(); i++) {
					if (!cartView.getItems().get(i).isOrderSent()) {
						ResponseModel sendOrder = TableService.getInstance().sendOrder(new SendOrderTableModel(
								tableString.zoneLetter, tableString.tableNumber, i, cartView.getItems().size() - 1));
						if (!sendOrder.isSuccess() || sendOrder == null) {
							return;
						}
						List<KitchenProductModel> model = new ArrayList<KitchenProductModel>();
						for (int j = i; j < cartView.getItems().size(); j++) {
							KitchenProductModel newKitchenProduct = new KitchenProductModel(
									cartView.getItems().get(j).getProductName(), "", "",
									cartView.getItems().get(j).getAmount(), tableString.zoneLetter,
									tableString.tableNumber);
							model.add(newKitchenProduct);
						}
						ResponseModel sendOrderToKitchen = KitchenService.getInstance().sendOrderToKitchen(model);
						if (sendOrderToKitchen.isSuccess()) {
							refreshView();
							MainController.getKitchenController().loadData();
							StaticMethods.setMessage("Order sent to the kitchen", message);
						}
						return;
					}
				}
				StaticMethods.setMessage("You didn't choose any product", message);
			} else {
				StaticMethods.setMessage("Your cart is empty at the moment", message);
			}
		} else {
			StaticMethods.setMessage("Please choose a table first", message);
		}
	}

	@FXML
	private void handleDiscount() {
//		if (currentTable != null) {
//			if (discountButton.isSelected()) {
//				discountInput.setVisible(true);
//			} else {
//				currentTable.setCoupon(null);
//				setPriceLabel();
//				discountInput.setVisible(false);
//			}
//		} else {
//			main.setMessage("Please choose a table first", message);
//		}
	}

	@FXML
	private void handlePrint() {
//		if (currentTable != null) {
//			if (tableProductList.size() > 0) {
//				try {
//					FXMLLoader thirdLoader = new FXMLLoader(getClass().getResource("/fxmlDocument/BillView.fxml"));
//					Parent thirdUI = thirdLoader.load();
//					BillController billController = thirdLoader.getController();
//					billController.setContent(currentTable);
//					Scene scene = new Scene(thirdUI);
//					Stage dialogStage = new Stage();
//					main.lockedSizeStage(dialogStage);
//					dialogStage.setScene(scene);
//					dialogStage.showAndWait();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			} else {
//				main.setMessage("You haven't bought anything", message);
//			}
//		} else {
//			main.setMessage("Please choose a table first", message);
//		}
	}

	@FXML
	private void handlePay() {
//		if (currentTable != null) {
//			if (tableProductList.size() > 0) {
//				if (currentTable.getBalance() > 0) {
//					for (int i = 0; i < tableProductList.size(); i++) {
//						if (!tableProductList.get(i).isProductDelivered()) {
//							main.setMessage("You still have unfinished product in your cart", message);
//							return;
//						}
//					}
//					boolean isPaymentComplete = false;
//					try {
//						FXMLLoader thirdLoader = new FXMLLoader(
//								getClass().getResource("/fxmlDocument/GuestPaymentView.fxml"));
//						Parent thirdUI = thirdLoader.load();
//						GuestPaymentController guestPaymentController = thirdLoader.getController();
//						guestPaymentController.setMainController(main);
//						guestPaymentController.setTable(currentTable);
//						Scene scene = new Scene(thirdUI);
//						Stage dialogStage = new Stage();
//						main.lockedSizeStage(dialogStage);
//						dialogStage.setScene(scene);
//						dialogStage.showAndWait();
//						isPaymentComplete = guestPaymentController.isCompleteClicked();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					if (isPaymentComplete) {
//						tableNumber.setText("");
//						main.setMessage("Payment complete", message);
//					}
//				} else {
//					main.setMessage(
//							"You are having a negative balance. Please disable your discount or buy more product",
//							message);
//				}
//			} else {
//				main.setMessage("You haven't bought anything", message);
//			}
//		} else {
//			main.setMessage("Please choose a table first", message);
//		}
	}

	private void setPriceLabel(TableModel table) {
		subTotalLabel.setText(String.format("%.2f", table.getSubTotal()));
		taxLabel.setText(String.format("%.2f", table.getTax()));
		totalLabel.setText(String.format("%.2f", table.getTotal()));
		discountLabel.setText(String.format("%.2f", table.getDiscount()));
		balanceLabel.setText(String.format("%.2f", table.getBalance()));
	}

	private void setCartDisplay(TableModel table) {
		ObservableList<CartProductModel> displayCart = FXCollections.observableArrayList(table.getCustomerProduct());
		cartView.setItems(displayCart);
		cartView.getScene().addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (lastSelectedRow.get() != null) {
					Bounds boundsOfSelectedRow = lastSelectedRow.get()
							.localToScene(lastSelectedRow.get().getLayoutBounds());
					if (boundsOfSelectedRow.contains(event.getSceneX(), event.getSceneY()) == false) {
						cartView.getSelectionModel().clearSelection();
					}
				}
			}
		});
	}

	private void addToCart(ProductModel product) {
		for (int i = 0; i < cartView.getItems().size(); i++) {
			if (cartView.getItems().get(i).getProductName().compareTo(product.getProductName()) == 0
					&& !cartView.getItems().get(i).isOrderSent()) {
				return;
			}
		}
		ResponseModel addToCart = TableService.getInstance()
				.addToTableCart(new AddToTableCart(tableString.zoneLetter, tableString.tableNumber, product));
		if (!addToCart.isSuccess() || addToCart == null) {
			return;
		}
		refreshView();
	}

	private void changeAmount(CartProductModel cartProduct, int productIndex, int newAmount) {
		double newTotalPrice = newAmount * cartProduct.getPrice();
		ResponseModel changeAmount = TableService.getInstance().changeAmountInCart(new ChangeAmountInCart(
				tableString.zoneLetter, tableString.tableNumber, productIndex, newAmount, newTotalPrice));
		if (!changeAmount.isSuccess() || changeAmount == null) {
			return;
		}
		refreshView();
	}

	private void deleteProduct(String productName) {
		ResponseModel deleteProduct = TableService.getInstance().deleteProductFromCart(
				new DeleteProductFromCart(tableString.zoneLetter, tableString.tableNumber, productName));
		if (!deleteProduct.isSuccess() || deleteProduct == null) {
			return;
		}
		refreshView();
	}

	private void setZoneLetterBox() {
		ObservableList<String> zoneListRepresentatives = FXCollections.observableArrayList();
		for (int i = 0; i < zoneList.size(); i++) {
			zoneListRepresentatives.add(zoneList.get(i).getZoneLetter());
		}
		zoneLetterBox.setItems(zoneListRepresentatives);
	}

	private void setTable() {
		gridPane2.getChildren().clear();
		gridPane3.getChildren().clear();
		refreshView();
		for (int i = 0; i <= categoriesList.size() / columnCount.get(); i++) {
			for (int j = 0; j < columnCount.get(); j++) {
				int categoryIndex = i * columnCount.get() + j;
				if (categoryIndex < categoriesList.size()) {
					Label newCategory = new Label(categoriesList.get(categoryIndex).toString());
					newCategory.setPrefSize(150, 50);
					newCategory.setWrapText(true);
					newCategory.setAlignment(Pos.CENTER);
					StackPane stack1 = new StackPane();
					Rectangle newRec1 = new Rectangle(150, 50, Paint.valueOf("cyan"));
					stack1.getChildren().addAll(newRec1, newCategory);
					gridPane1.add(stack1, j, i);
					stack1.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							gridPane2.getChildren().clear();
							gridPane3.getChildren().clear();
							for (int i = 0; i <= categoriesList.get(categoryIndex).getTypes().size()
									/ columnCount.get(); i++) {
								for (int j = 0; j < columnCount.get(); j++) {
									int typeIndex = i * columnCount.get() + j;
									if (typeIndex < categoriesList.get(categoryIndex).getTypes().size()) {
										Label newType = new Label(
												categoriesList.get(categoryIndex).getTypes().get(typeIndex));
										newType.setPrefSize(150, 50);
										newType.setWrapText(true);
										newType.setAlignment(Pos.CENTER);
										StackPane stack2 = new StackPane();
										Rectangle newRec2 = new Rectangle(150, 50, Paint.valueOf("cyan"));
										stack2.getChildren().addAll(newRec2, newType);
										gridPane2.add(stack2, j, i);
										stack2.setOnMouseClicked(new EventHandler<MouseEvent>() {
											@Override
											public void handle(MouseEvent event) {
												gridPane3.getChildren().clear();
												displayMenu = ProductService.getInstance()
														.getProductsListByType(new FindProductByType(
																categoriesList.get(categoryIndex).toString(),
																categoriesList.get(categoryIndex).getTypes()
																		.get(typeIndex)));
												for (int i = 0; i <= displayMenu.size() / columnCount.get(); i++) {
													for (int j = 0; j < columnCount.get(); j++) {
														int menuIndex = i * columnCount.get() + j;
														if (menuIndex < displayMenu.size()) {
															Label menuProduct = new Label(
																	displayMenu.get(menuIndex).toString());
															menuProduct.setPrefSize(150, 50);
															menuProduct.setWrapText(true);
															menuProduct.setTextAlignment(TextAlignment.CENTER);
															menuProduct.setAlignment(Pos.CENTER);
															StackPane stack3 = new StackPane();
															Rectangle newRec3 = new Rectangle(150, 50,
																	Paint.valueOf("cyan"));
															stack3.getChildren().addAll(newRec3, menuProduct);
															gridPane3.add(stack3, j, i);
															stack3.setOnMouseClicked(new EventHandler<MouseEvent>() {
																@Override
																public void handle(MouseEvent event) {
																	if (event.getClickCount() == 2) {
																		addToCart(displayMenu.get(menuIndex));
																	}
																}
															});
														}
													}
												}
											}
										});
									}
								}
							}
						}
					});
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		categoriesList = CategoryService.getInstance().getCategoriesList();
		zoneList = ZoneService.getInstance().getZonesList();
		setZoneLetterBox();
		zoneLetterBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				for (int i = 0; i < zoneList.size(); i++) {
					if (zoneLetterBox.getSelectionModel().getSelectedItem()
							.compareTo(zoneList.get(i).getZoneLetter()) == 0) {
						ObservableList<Integer> tableNumberRepresentatives = FXCollections.observableArrayList();
						for (int j = 1; j <= zoneList.get(i).getTableCount(); j++) {
							tableNumberRepresentatives.add(j);
						}
						tableNumberBox.setItems(tableNumberRepresentatives);
						break;
					}
				}
			}
		});
		tableNumber.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				changeTableButton.setSelected(false);
				zoneLetterBox.getSelectionModel().clearSelection();
				tableNumberBox.getSelectionModel().clearSelection();
				changeTableBox.setVisible(false);
				tableMessage.setVisible(false);
				tableString = StaticMethods.splitTableString(newValue);
				setTable();
			}
		});
		cartPane.prefWidthProperty().bind((mainPane.widthProperty().subtract(50)).divide(2));
		choicePane.prefWidthProperty().bind((mainPane.widthProperty().subtract(50)).divide(2));
		menuPane.prefHeightProperty().bind(choicePane.heightProperty().subtract(325));
		columnCount.bind(choicePane.widthProperty().divide(180));
		gridPane1.setPadding(new Insets(5, 50, 5, 50));
		gridPane1.setVgap(15);
		gridPane1.setHgap(15);
		gridPane2.setPadding(new Insets(5, 50, 5, 50));
		gridPane2.setVgap(15);
		gridPane2.setHgap(15);
		gridPane3.setPadding(new Insets(25, 50, 25, 50));
		gridPane3.setVgap(25);
		gridPane3.setHgap(15);
		categoriesPane.setContent(gridPane1);
		categoriesPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		typesPane.setContent(gridPane2);
		typesPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		menuPane.setContent(gridPane3);
		menuPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		productNameList.prefWidthProperty().bind(cartView.widthProperty().multiply(0.25));
		productNameList.setCellValueFactory(new PropertyValueFactory<CartProductModel, String>("productName"));
		eachList.prefWidthProperty().bind(cartView.widthProperty().multiply(0.11));
		eachList.setCellValueFactory(new PropertyValueFactory<CartProductModel, Number>("price"));
		totalList.prefWidthProperty().bind(cartView.widthProperty().multiply(0.11));
		totalList.setCellValueFactory(new PropertyValueFactory<CartProductModel, Number>("totalPrice"));
		quantityList.prefWidthProperty().bind(cartView.widthProperty().multiply(0.2));
		quantityList.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue()));
		quantityList.setCellFactory(c -> new TableCell<CartProductModel, CartProductModel>() {
			@Override
			protected void updateItem(CartProductModel cartProduct, boolean empty) {
				super.updateItem(cartProduct, empty);
				if (cartProduct == null || empty) {
					setGraphic(null);
					return;
				} else if (cartProduct.isOrderSent()) {
					setGraphic(new Label(String.valueOf(cartProduct.getAmount())));
					return;
				}
				Label quantity = new Label();
				quantity.setPrefSize(50, 50);
				quantity.setAlignment(Pos.CENTER);
				quantity.setText(String.valueOf(cartProduct.getAmount()));
				StackPane button1 = new StackPane(new Circle(15, Paint.valueOf("cyan")), new Label("-"));
				StackPane button2 = new StackPane(new Circle(15, Paint.valueOf("cyan")), new Label("+"));
				button1.setOnMouseClicked(e -> {
					if (cartProduct.getAmount() == 1) {
						deleteProduct(cartView.getSelectionModel().getSelectedItem().getProductName());
					} else {
						changeAmount(cartProduct, cartView.getSelectionModel().getSelectedIndex(),
								cartProduct.getAmount() - 1);
					}
				});
				button2.setOnMouseClicked(e -> {
					changeAmount(cartProduct, cartView.getSelectionModel().getSelectedIndex(),
							cartProduct.getAmount() + 1);
				});
				HBox pane = new HBox(button1, quantity, button2);
				pane.setSpacing(15);
				pane.setAlignment(Pos.CENTER);
				setGraphic(pane);
			}
		});
		timeOrderedList.prefWidthProperty().bind(cartView.widthProperty().multiply(0.11));
		timeOrderedList.setCellValueFactory(new PropertyValueFactory<CartProductModel, String>("timeOrdered"));
		timeCompletedList.prefWidthProperty().bind(cartView.widthProperty().multiply(0.11));
		timeCompletedList.setCellValueFactory(new PropertyValueFactory<CartProductModel, String>("timeCompleted"));
		statusList.prefWidthProperty().bind(cartView.widthProperty().multiply(0.125));
		statusList.setCellValueFactory(new PropertyValueFactory<CartProductModel, String>("status"));
		cartView.getColumns().addAll(productNameList, quantityList, eachList, totalList, timeOrderedList,
				timeCompletedList, statusList);
		changeTableButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				changeTableBox.setVisible(changeTableButton.isSelected());
			}
		});
		discountField.setTextFormatter(new TextFormatter<>((change) -> {
			change.setText(change.getText().toUpperCase());
			return change;
		}));
		cartView.setRowFactory(tableView -> {
			TableRow<CartProductModel> row = new TableRow<CartProductModel>();
			row.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
				if (isNowSelected) {
					lastSelectedRow.set(row);
				}
			});
			return row;
		});
//		cartView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				if (event.getClickCount() == 2 && cartView.getSelectionModel().getSelectedIndex() != -1
//						&& cartView.getSelectionModel().getSelectedItem().isProductReady()) {
//					cartView.getSelectionModel().getSelectedItem().setProductDelivered(true);
//					;
//					for (int i = 0; i < tableProductList.size(); i++) {
//						if (tableProductList.get(i).isProductReady() && !tableProductList.get(i).isProductDelivered()) {
//							return;
//						}
//					}
//					currentTable.setFoodReady(false);
//				}
//			}
//		});
		filterField.setTextFormatter(new TextFormatter<>((change) -> {
			change.setText(change.getText().toLowerCase());
			return change;
		}));
//		filterField.textProperty().addListener(new ChangeListener<String>() {
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//				if (newValue != null && tableNumber.getText() != "") {
//					displayMenu.clear();
//					gridPane2.getChildren().clear();
//					gridPane3.getChildren().clear();
//					for (int i = 0; i < main.getProductData().size(); i++) {
//						if (main.getProductData().get(i).toString().toLowerCase().contains(newValue)) {
//							displayMenu.add(main.getProductData().get(i));
//						}
//					}
//					for (int i = 0; i <= displayMenu.size() / columnCount.get(); i++) {
//						for (int j = 0; j < columnCount.get(); j++) {
//							int menuIndex = i * columnCount.get() + j;
//							if (menuIndex < displayMenu.size()) {
//								Label menuProduct = new Label(displayMenu.get(menuIndex).toString());
//								menuProduct.setAlignment(Pos.CENTER);
//								StackPane stack3 = new StackPane();
//								Rectangle newRec3 = new Rectangle(150.0, 50.0, Paint.valueOf("cyan"));
//								stack3.getChildren().addAll(newRec3, menuProduct);
//								gridPane3.add(stack3, j, i);
//								stack3.setOnMouseClicked(new EventHandler<MouseEvent>() {
//									@Override
//									public void handle(MouseEvent event) {
//										if (event.getClickCount() == 2) {
//											for (int i = 0; i < tableProductList.size(); i++) {
//												if (displayMenu.get(menuIndex).toString()
//														.compareTo(tableProductList.get(i).toString()) == 0
//														&& !tableProductList.get(i).getOrderSent()) {
//													main.setMessage("This product is already in your cart", message);
//													return;
//												}
//											}
//											tableProductList.add(
//													new CartProductModel(displayMenu.get(menuIndex).getProductName(),
//															displayMenu.get(menuIndex).getPrice(),
//															displayMenu.get(menuIndex).getCategory(),
//															displayMenu.get(menuIndex).getType(), "", "", 1,
//															displayMenu.get(menuIndex).getPrice(),
//															tableNumber.getText(), false, false, false, ""));
//											setPriceLabel();
//										}
//									}
//								});
//							}
//						}
//					}
//				}
//			}
//		});
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber.setText(tableNumber);
	}

	public void refreshView() {
		TableModel table = TableService.getInstance()
				.getTable(new TableNumberModel(tableString.zoneLetter, tableString.tableNumber));
		setCartDisplay(table);
		setPriceLabel(table);
	}
}
