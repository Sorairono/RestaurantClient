package model;

import java.util.List;

public class TableModel {
	private String zoneLetter;
	private int tableNumber;
	private int capacity;
	private boolean circle;
	private List<CartProductModel> customerProduct;
	private CouponModel coupon;
	private double subTotal;
	private double tax;
	private double total;
	private double discount;
	private double balance;
	private boolean occupied;
	private boolean foodReady;
	private boolean merge;
	private String mergeTable;

	public TableModel() {

	}

	public TableModel(String zoneLetter, int tableNumber, int capacity, boolean circle,
			List<CartProductModel> customerProduct, CouponModel coupon, double subTotal, double tax, double total,
			double discount, double balance, boolean occupied, boolean foodReady, boolean merge, String mergeTable) {
		super();
		this.zoneLetter = zoneLetter;
		this.tableNumber = tableNumber;
		this.capacity = capacity;
		this.circle = circle;
		this.customerProduct = customerProduct;
		this.coupon = coupon;
		this.subTotal = subTotal;
		this.tax = tax;
		this.total = total;
		this.discount = discount;
		this.balance = balance;
		this.occupied = occupied;
		this.foodReady = foodReady;
		this.merge = merge;
		this.mergeTable = mergeTable;
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isCircle() {
		return circle;
	}

	public void setCircle(boolean circle) {
		this.circle = circle;
	}

	public List<CartProductModel> getCustomerProduct() {
		return customerProduct;
	}

	public void setCustomerProduct(List<CartProductModel> customerProduct) {
		this.customerProduct = customerProduct;
	}

	public CouponModel getCoupon() {
		return coupon;
	}

	public void setCoupon(CouponModel coupon) {
		this.coupon = coupon;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public boolean isFoodReady() {
		return foodReady;
	}

	public void setFoodReady(boolean foodReady) {
		this.foodReady = foodReady;
	}

	public boolean isMerge() {
		return merge;
	}

	public void setMerge(boolean merge) {
		this.merge = merge;
	}

	public String getMergeTable() {
		return mergeTable;
	}

	public void setMergeTable(String mergeTable) {
		this.mergeTable = mergeTable;
	}
	
	@Override
	public String toString() {
		return zoneLetter + "-" + tableNumber;
	}

}
