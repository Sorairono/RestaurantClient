package model;

public class ProductModel {
	private String productName;
	private double price;
	private String category;
	private String type;

	public ProductModel() {

	}

	public ProductModel(String productName, double price, String category, String type) {
		super();
		this.productName = productName;
		this.price = price;
		this.category = category;
		this.type = type;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return productName;
	}

}
