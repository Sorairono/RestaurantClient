package request;

public class DeleteProductFromCart {
	private String zoneLetter;
	private int tableNumber;
	private String productName;

	public DeleteProductFromCart() {

	}

	public DeleteProductFromCart(String zoneLetter, int tableNumber, String productName) {
		super();
		this.zoneLetter = zoneLetter;
		this.tableNumber = tableNumber;
		this.productName = productName;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
