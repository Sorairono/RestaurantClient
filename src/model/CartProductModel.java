package model;

public class CartProductModel {
	private String productName;
	private double price;
	private String timeOrdered;
	private String timeCompleted;
	private int amount;
	private double totalPrice;
	private String tableString;
	private boolean orderSent;
	private boolean productReady;
	private boolean productDelivered;
	private String status;

	public CartProductModel() {

	}

	public CartProductModel(String productName, double price, String timeOrdered, String timeCompleted, int amount,
			double totalPrice, String tableString, boolean orderSent, boolean productReady, boolean productDelivered,
			String status) {
		super();
		this.productName = productName;
		this.price = price;
		this.timeOrdered = timeOrdered;
		this.timeCompleted = timeCompleted;
		this.amount = amount;
		this.totalPrice = totalPrice;
		this.tableString = tableString;
		this.orderSent = orderSent;
		this.productReady = productReady;
		this.productDelivered = productDelivered;
		this.status = status;
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

	public String getTimeOrdered() {
		return timeOrdered;
	}

	public void setTimeOrdered(String timeOrdered) {
		this.timeOrdered = timeOrdered;
	}

	public String getTimeCompleted() {
		return timeCompleted;
	}

	public void setTimeCompleted(String timeCompleted) {
		this.timeCompleted = timeCompleted;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getTableString() {
		return tableString;
	}

	public void setTableString(String tableString) {
		this.tableString = tableString;
	}

	public boolean isOrderSent() {
		return orderSent;
	}

	public void setOrderSent(boolean orderSent) {
		this.orderSent = orderSent;
	}

	public boolean isProductReady() {
		return productReady;
	}

	public void setProductReady(boolean productReady) {
		this.productReady = productReady;
	}

	public boolean isProductDelivered() {
		return productDelivered;
	}

	public void setProductDelivered(boolean productDelivered) {
		this.productDelivered = productDelivered;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return productName;
	}

}
