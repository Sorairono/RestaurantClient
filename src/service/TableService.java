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
import model.TableModel;
import model.ZoneModel;
import request.AddToTableCart;
import request.ChangeAmountInCart;
import request.DeleteProductFromCart;
import request.TableNumberModel;
import response.ResponseModel;

public class TableService {
	private static TableService instance;
	Gson gson = new Gson();

	public static TableService getInstance() {
		if (instance == null) {
			synchronized (TableService.class) {
				if (instance == null) {
					instance = new TableService();
				}
			}
		}
		return instance;
	}

	public List<TableModel> getTablesListInZone(ZoneModel zone) {
		List<TableModel> tablesListInZone = new ArrayList<TableModel>();
		try {
			URL url = new URL(ConstantURL.url_getTablesListInZone);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			String json = gson.toJson(zone);
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
				TypeToken<List<TableModel>> token = new TypeToken<List<TableModel>>() {
				};
				tablesListInZone = gson.fromJson(response.toString(), token.getType());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tablesListInZone;
	}

	public TableModel getTable(TableNumberModel model) {
		try {
			URL url = new URL(ConstantURL.url_getTable);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
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
				TableModel table = gson.fromJson(response.toString(), TableModel.class);
				return table;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public ResponseModel openTable(TableNumberModel model) {
		try {
			URL url = new URL(ConstantURL.url_openTable);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
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
				ResponseModel responseModel = gson.fromJson(response.toString(), ResponseModel.class);
				return responseModel;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public ResponseModel addToTableCart(AddToTableCart model) {
		try {
			URL url = new URL(ConstantURL.url_addToTableCart);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
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
				ResponseModel responseModel = gson.fromJson(response.toString(), ResponseModel.class);
				return responseModel;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public ResponseModel changeAmountInCart(ChangeAmountInCart model) {
		try {
			URL url = new URL(ConstantURL.url_changeAmountInCart);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
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
				ResponseModel responseModel = gson.fromJson(response.toString(), ResponseModel.class);
				return responseModel;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public ResponseModel deleteProductFromCart(DeleteProductFromCart model) {
		try {
			URL url = new URL(ConstantURL.url_deleteProductFromCart);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
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
				ResponseModel responseModel = gson.fromJson(response.toString(), ResponseModel.class);
				return responseModel;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
