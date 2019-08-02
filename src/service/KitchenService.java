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
import model.KitchenProductModel;
import response.ResponseModel;

public class KitchenService {
	private static KitchenService instance;
	Gson gson = new Gson();
	
	private KitchenService() {
		
	}
	
	public static KitchenService getInstance() {
		if (instance == null) {
			synchronized (KitchenService.class) {
				if (instance == null) {
					instance = new KitchenService();
				}
			}
		}
		return instance;
	}
	
	public ResponseModel sendOrderToKitchen(List<KitchenProductModel> model) {
		try {
			URL url = new URL(ConstantURL.url_sendOrderToKitchen);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			TypeToken <List<KitchenProductModel>> token = new TypeToken<List<KitchenProductModel>>() {};
			String json = gson.toJson(model, token.getType());
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
				ResponseModel responseModel = gson.fromJson(response.toString(), ResponseModel.class);
				return responseModel;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public List<KitchenProductModel> getKitchenList() {
		List<KitchenProductModel> kitchenList = new ArrayList<KitchenProductModel>();
		try {
			URL url = new URL(ConstantURL.url_getKitchenList);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
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
				TypeToken <List<KitchenProductModel>> token = new TypeToken<List<KitchenProductModel>>() {};
				kitchenList = gson.fromJson(response.toString(), token.getType());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return kitchenList;
	}
}
