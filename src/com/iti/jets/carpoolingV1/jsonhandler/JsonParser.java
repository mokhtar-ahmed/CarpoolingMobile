package com.iti.jets.carpoolingV1.jsonhandler;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.pojos.Location;
import com.iti.jets.carpoolingV1.pojos.User;

public class JsonParser {


    
	public static Location parseToLocation(JSONObject locationData){
	
		Location loc = new Location();
		try {
		
			loc.setId(Integer.parseInt((String)locationData.get(JsonConstants.LOCATION_ID)));
			loc.setAltitude((String)locationData.get(JsonConstants.LOCATION_ALTITUDE));
			loc.setAddress((String)locationData.get(JsonConstants.LOCATION_ADDRESS));
			loc.setLongitude((String)locationData.get(JsonConstants.LOCATION_LONGITUDE));
			loc.setLattitude((String)locationData.get(JsonConstants.LOCATION_LATTITUDE));
	
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return loc;
	}
	public static User parseToUser(JSONObject userData){
		
		User user = new User();
		int id;
		try {
			
			id = Integer.parseInt((String)userData.get(""));
			user.setId( id);
			user.setEmail((String)userData.get(""));
			user.setFacebookKey((String)userData.get(""));
		//	user.setDateOfBirth((String)userData.get(""));
			user.setGender((String)userData.get(""));
			user.setName((String)userData.get(""));
			user.setPassword((String)userData.get(""));
			user.setPhone((String)userData.get(""));
			user.setPushNotificationId((String)userData.get(""));
			user.setRank((String)userData.get(""));
			user.setUsername((String)userData.get(""));
			user.setUserImage((String)userData.get(""));
			
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return user;
		
	}
}
