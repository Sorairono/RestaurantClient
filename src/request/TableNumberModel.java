package request;

public class TableNumberModel {
	private String zoneLetter;
	private int tableNumber;

	public TableNumberModel() {

	}

	public TableNumberModel(String zoneLetter, int tableNumber) {
		super();
		this.zoneLetter = zoneLetter;
		this.tableNumber = tableNumber;
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

}
