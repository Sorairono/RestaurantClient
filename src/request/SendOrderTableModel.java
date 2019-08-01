package request;

public class SendOrderTableModel {
	private String zoneLetter;
	private int tableNumber;
	private int lowerBound;
	private int upperBound;

	public SendOrderTableModel() {

	}

	public SendOrderTableModel(String zoneLetter, int tableNumber, int lowerBound, int upperBound) {
		super();
		this.zoneLetter = zoneLetter;
		this.tableNumber = tableNumber;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
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

	public int getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

}