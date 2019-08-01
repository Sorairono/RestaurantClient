package model;

public class KitchenProductModel {
	private String productName;
	private String timeOrdered;
	private String timeCompleted;
	private int amount;
	private String tableString;

	public KitchenProductModel() {

	}

	public KitchenProductModel(String productName, String timeOrdered, String timeCompleted, int amount,
			String tableString) {
		super();
		this.productName = productName;
		this.timeOrdered = timeOrdered;
		this.timeCompleted = timeCompleted;
		this.amount = amount;
		this.tableString = tableString;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getTableString() {
		return tableString;
	}

	public void setTableString(String tableString) {
		this.tableString = tableString;
	}

}
