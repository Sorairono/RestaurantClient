package request;

public class DeleteProductFromCart {
	private String zoneLetter;
	private int tableNumber;
	private int productIndex;

	public DeleteProductFromCart() {

	}

	public DeleteProductFromCart(String zoneLetter, int tableNumber, int productIndex) {
		super();
		this.zoneLetter = zoneLetter;
		this.tableNumber = tableNumber;
		this.productIndex = productIndex;
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

}
