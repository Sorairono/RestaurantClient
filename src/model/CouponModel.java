package model;

public class CouponModel {
	private String couponCode;
	private boolean percent;
	private double discountValue;

	public CouponModel() {

	}

	public CouponModel(String couponCode, boolean percent, double discountValue) {
		super();
		this.couponCode = couponCode;
		this.percent = percent;
		this.discountValue = discountValue;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public boolean isPercent() {
		return percent;
	}

	public void setPercent(boolean percent) {
		this.percent = percent;
	}

	public double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}
	
	@Override
	public String toString() {
		return couponCode;
	}

}
