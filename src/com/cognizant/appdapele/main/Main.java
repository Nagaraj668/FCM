package com.cognizant.appdapele.main;

import org.json.JSONObject;

public class Main {

	public static void main(String[] args) {
		FcmServer fcmServer = new FcmServer();
		fcmServer.setTo("e_YsOQgKQxg:APA91bFBol6dMB9dyVeHnx_PRZ_m21GquOHQOIbrWpqkde-l_AhWX3XiyojDmTTK64QQ-UWs4DpEt5cUVdcuT5FASXkVSqdvf061KSuUXZYUaJ4HXpU9AWRt8Savub8kc3VSWHldGMVV");

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
