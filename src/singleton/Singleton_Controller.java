package singleton;

import controller.*;

public class Singleton_Controller {
	private static Singleton_Controller instance;

	private Singleton_Controller() {

	}

	public static Singleton_Controller getInstance() {
		if (instance == null) {
			synchronized (Singleton_Controller.class) {
				if (instance == null) {
					instance = new Singleton_Controller();
				}
			}
		}
		return instance;
	}

	private BillController Bill_Controller;
	private MainController Main_Controller;
	private POSController POS_Controller;
	private TablesController Tables_Controller;
	private KitchenController Kitchen_Controller;

	public BillController getBill_Controller() {
		return Bill_Controller;
	}

	public void setBill_Controller(BillController bill_Controller) {
		Bill_Controller = bill_Controller;
	}

	public MainController getMain_Controller() {
		return Main_Controller;
	}

	public void setMain_Controller(MainController main_Controller) {
		Main_Controller = main_Controller;
	}

	public POSController getPOS_Controller() {
		return POS_Controller;
	}

	public void setPOS_Controller(POSController pOS_Controller) {
		POS_Controller = pOS_Controller;
	}

	public TablesController getTables_Controller() {
		return Tables_Controller;
	}

	public void setTables_Controller(TablesController tables_Controller) {
		Tables_Controller = tables_Controller;
	}

	public KitchenController getKitchen_Controller() {
		return Kitchen_Controller;
	}

	public void setKitchen_Controller(KitchenController kitchen_Controller) {
		Kitchen_Controller = kitchen_Controller;
	}

}
