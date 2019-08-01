package request;

public class ChangeAmountInCart {
	private String zoneLetter;
	private int tableNumber;
	private int productIndex;
	private int newAmount;
	private double newTotalPrice;

	public ChangeAmountInCart() {

	}

	public ChangeAmountInCart(String zoneLetter, int tableNumber, int productIndex, int newAmount,
			double newTotalPrice) {
		super();
		this.zoneLetter = zoneLetter;
		this.tableNumber = tableNumber;
		this.productIndex = productIndex;
		this.newAmount = newAmount;
		this.newTotalPrice = newTotalPrice;
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

	public int getProductIndex() {
		return productIndex;
	}

	public void setProductIndex(int productIndex) {
		this.productIndex = productIndex;
	}

	public int getNewAmount() {
		return newAmount;
	}

	public void setNewAmount(int newAmount) {
		this.newAmount = newAmount;
	}

	public double getNewTotalPrice() {
		return newTotalPrice;
	}

	public void setNewTotalPrice(double newTotalPrice) {
		this.newTotalPrice = newTotalPrice;
	}

}
