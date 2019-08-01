package singleton;

public class Singleton_Kitchen {
	private static Singleton_Kitchen instance;

	private Singleton_Kitchen() {

	}

	public static Singleton_Kitchen getInstance() {
		if (instance == null) {
			synchronized (Singleton_Kitchen.class) {
				if (instance == null) {
					instance = new Singleton_Kitchen();
				}
			}
		}
		return instance;
	}

	private boolean check_addProduct = false;
	private boolean check_productReady = false;

	public boolean isCheck_addProduct() {
		return check_addProduct;
	}

	public void setCheck_addProduct(boolean check_addProduct) {
		this.check_addProduct = check_addProduct;
	}

	public boolean isCheck_productReady() {
		return check_productReady;
	}

	public void setCheck_productReady(boolean check_productReady) {
		this.check_productReady = check_productReady;
	}

}
