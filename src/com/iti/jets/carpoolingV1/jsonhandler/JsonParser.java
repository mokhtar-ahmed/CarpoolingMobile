package com.iti.jets.carpoolingV1.jsonhandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.pojos.Location;
import com.iti.jets.carpoolingV1.pojos.User;

public class JsonParser {


    
	public static Location parseToLocation(JSONObject locationData){
	
		Location loc = new Location();
		try {
		
			int idStr =locationData.getInt(JsonConstants.LOCATION_ID);
			loc.setId(idStr);
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
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
	
		try {
			String dateInString = (String)userData.get("dateOfBirth");
			user.setId( userData.getInt("id"));
			user.setEmail((String)userData.get("email"));
			user.setFacebookKey((String)userData.get("facebookKey"));
			try {
				user.setDateOfBirth( formatter.parse(dateInString));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setGender((String)userData.get("gender"));
			user.setName((String)userData.get("name"));
			user.setPassword((String)userData.get("password"));
			user.setPhone((String)userData.get("phone"));
			user.setPushNotificationId((String)userData.get("pushNotificationID"));
			user.setRank((String)userData.get("rank"));
			user.setUsername((String)userData.get("username"));
			user.setUserImage((String)userData.get("image"));
			
			
			
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
