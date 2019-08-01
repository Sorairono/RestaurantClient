package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import core.ConstantURL;
import model.ZoneModel;

public class ZoneService {
	private static ZoneService instance;
	
	public static ZoneService getInstance() {
		if (instance == null) {
			synchronized (ZoneService.class) {
				if (instance == null) {
					instance = new ZoneService();
				}
			}
		}
		return instance;
	}
	
	public List<ZoneModel> getZonesList() {
		List<ZoneModel> zonesList = new ArrayList<ZoneModel>();
		try {
			URL url = new URL(ConstantURL.url_getZonesList);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			Gson gson = new Gson();
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
				TypeToken<List<ZoneModel>> token = new TypeToken<List<ZoneModel>>() {

				};
				zonesList = gson.fromJson(response.toString(), token.getType());
			}
		} catch (Exception e) {
			
		}
		return zonesList;
	}
}
