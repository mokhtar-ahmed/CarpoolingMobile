package com.iti.jets.carpoolingV1.jsonhandler;

import java.util.ArrayList;

import org.json.*;

import com.iti.jets.carpoolingV1.common.User;



public class JsonConverter {

	public JSONArray arrayListToJSONArray (ArrayList<String> arrayList)
	{
		JSONArray jsonArray = new JSONArray(arrayList);
		return jsonArray;
	}
	
	public JSONObject objectToJSON (User user)
	{
		JSONObject tempJasonObj = new JSONObject();
		try {			
			tempJasonObj.put("name", user.getName());
			tempJasonObj.put("phone", user.getPhone());
			tempJasonObj.put("userId", user.getUserId());
			return tempJasonObj;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return tempJasonObj;
	}
}
