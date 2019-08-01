package core;

public class ConstantURL {
	public static final String DOMAIN = "http://localhost:8080/RestaurantServer/webapi/";

	public static final String url_openTable = DOMAIN + "Table/OpenTable";

	public static final String url_getTablesListInZone = DOMAIN + "Table/GetTablesListInZone";
	public static final String url_getTable = DOMAIN + "Table/GetTable";

	public static final String url_getCategoriesList = DOMAIN + "Category/GetCategoriesList";

	public static final String url_getZonesList = DOMAIN + "Zone/GetZonesList";

	public static final String url_getProductsListByType = DOMAIN + "Product/GetProductsListByType";

	public static final String url_addToTableCart = DOMAIN + "Table/AddToTableCart";
	public static final String url_changeAmountInCart = DOMAIN + "Table/ChangeAmountInCart";
	public static final String url_deleteProductFromCart = DOMAIN + "Table/DeleteProductFromCart";

	public static final String url_sendOrder = DOMAIN + "Table/SendOrder";
	public static final String url_sendOrderToKitchen = DOMAIN + "Kitchen/SendOrderToKitchen";

	public static final String url_getKitchenList = DOMAIN + "Kitchen/GetKitchenList";
}
