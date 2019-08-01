package model;

public class ZoneModel {
	private String zoneName;
	private String zoneLetter;
	private int tableCount;

	public ZoneModel() {

	}

	public ZoneModel(String zoneName, String zoneLetter, int tableCount) {
		super();
		this.zoneName = zoneName;
		this.zoneLetter = zoneLetter;
		this.tableCount = tableCount;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getZoneLetter() {
		return zoneLetter;
	}

	public void setZoneLetter(String zoneLetter) {
		this.zoneLetter = zoneLetter;
	}

	public int getTableCount() {
		return tableCount;
	}

	public void setTableCount(int tableCount) {
		this.tableCount = tableCount;
	}

	@Override
	public String toString() {
		return zoneName;
	}
}
