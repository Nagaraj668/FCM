package com.cognizant.appdapele.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class Main {

	public static void main(String[] args) {
		FcmServer fcmServer = new FcmServer();
		fcmServer.setTo("device id comes here");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", "Image processed");
		jsonObject.put("body", "Hi, Your image has been processed");

		fcmServer.setNotification(jsonObject);

		JSONObject additionalData = new JSONObject();
		additionalData.put("key", "value");
		fcmServer.setData(additionalData);

		String responseMessage = fcmServer.sendNotification();
		if (responseMessage != null) {
			// success goes here
			System.out.println(responseMessage);
		}
	}

}

class Constants {

	public static final String SERVER_KEY = "";
	public static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";

}

class FcmServer {

	private String to;
	private JSONObject jsonObject;
	private JSONObject data;

	public void setTo(String string) {
		to = string;
	}

	public void setTitle(String string) {
		jsonObject.put("title", string);
	}

	public void setBody(String string) {
		jsonObject.put("body", string);
	}

	public String sendNotification() {
		try {
			URL url = new URL(Constants.FCM_URL);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "key=" + Constants.SERVER_KEY);
			conn.setRequestProperty("Content-Type", "application/json");

			JSONObject json = new JSONObject();
			json.put("to", to);
			json.put("data", data);
			json.put("notification", jsonObject);

			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(json.toString());
			wr.flush();
			InputStream in = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder result = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			if (result.toString() != null)
				System.out.println("Notification sent successfully");
			return result.toString();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public void setData(JSONObject jsonObject2) {
		data = jsonObject2;
	}

	public void setNotification(JSONObject jsonObject2) {
		this.jsonObject = jsonObject2;
	}

}
