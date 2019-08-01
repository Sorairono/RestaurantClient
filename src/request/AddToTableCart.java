package request;

import model.ProductModel;

public class AddToTableCart {
	private String zoneLetter;
	private int tableNumber;
	private ProductModel product;
	
	public AddToTableCart() {
		
	}

	public AddToTableCart(String zoneLetter, int tableNumber, ProductModel product) {
		super();
		this.zoneLetter = zoneLetter;
		this.tableNumber = tableNumber;
		this.product = product;
	}

	public String getZoneLetter() {
		return zoneLetter;
	}

	public void setZoneLetter(String zoneLetter) {
		this.zoneLetter = zoneLetter;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public String getTableString() {
		return zoneLetter + "-" + tableNumber;
	}
	
}
