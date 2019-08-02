package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import core.ConstantURL;
import model.ProductModel;
import request.FindProductByType;

public class ProductService {
	private static ProductService instance;
	
	public static ProductService getInstance() {
		if (instance == null) {
			synchronized (ProductService.class) {
				if (instance == null) {
					instance = new ProductService();
				}
			}
		}
		return instance;
	}
	
	public List<ProductModel> getProductsListByType(FindProductByType model) {
		List<ProductModel> productsListByType = new ArrayList<ProductModel>();
		try {
			URL url = new URL(ConstantURL.url_getProductsListByType);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			Gson gson = new Gson();
			String json = gson.toJson(model);
			OutputStream os = connection.getOutputStream();
			os.write(json.getBytes());
			os.flush();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					byte[] utf8Bytes = inputLine.getBytes("UTF-8");
					String book = new String(utf8Bytes, "UTF-8");
					response.append(book);
				}
				in.close();
				TypeToken<List<ProductModel>> token = new TypeToken<List<ProductModel>>() {
				};
				productsListByType = gson.fromJson(response.toString(), token.getType());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return productsListByType;
	}
}
