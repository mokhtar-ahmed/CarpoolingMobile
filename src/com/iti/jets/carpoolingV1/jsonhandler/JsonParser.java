package com.iti.jets.carpoolingV1.jsonhandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.pojos.Circle;
import com.iti.jets.carpoolingV1.pojos.Comment;
import com.iti.jets.carpoolingV1.pojos.CustomUser;
import com.iti.jets.carpoolingV1.pojos.Event;
import com.iti.jets.carpoolingV1.pojos.Location;
import com.iti.jets.carpoolingV1.pojos.Notification;
import com.iti.jets.carpoolingV1.pojos.User;

public abstract class JsonParser {


    
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
	public static Circle parseToCircleList(JSONObject jsonObject) {
	
		Circle c = null;
		try {
			
			c = new Circle();
			c.setId(jsonObject.getInt("circleId"));
			c.setCircleName((String)jsonObject.get("circleName"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	public static Event parseToEventList(JSONObject jsonObject) {
		
		Event ev =null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			ev = new Event();
			ev.setId(jsonObject.getInt("idEvent"));
			ev.setName(jsonObject.getString("eventName"));
			String dateInString = jsonObject.getString("eventDate");
			
			try {
				ev.setDate(formatter.parse(dateInString));
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
			ev.setUserStatue(jsonObject.getString("userStatue"));
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return ev;
	}
	public static Comment parseToCommentList(JSONObject jsonObject){
		Comment ev =null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			ev = new Comment();
			ev.setId(jsonObject.getInt("id"));
			ev.setUsername(jsonObject.getString("user"));
			ev.setImage(jsonObject.getString("image"));
			ev.setText(jsonObject.getString("text"));
			String dateInString = jsonObject.getString("date");
			
			try {
				ev.setDate(formatter.parse(dateInString));
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
						
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return ev;
	}
	public static CustomUser parseToCustomUsertList(JSONObject jsonObject) {
		CustomUser ev =null;
		
		try {
			ev = new CustomUser();
			ev.setId(jsonObject.getInt("id"));
			ev.setUsername(jsonObject.getString("userName"));
			ev.setImage(jsonObject.getString("userImage"));
			ev.setUserStatue(jsonObject.getString("userStatue"));
		
						
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return ev;
	}
	public static Notification parseToNotifications(JSONObject jsonObject){
		Notification ns = new Notification();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		

		try {
			ns.setId(jsonObject.getInt("id"));
			ns.setEvent(JsonParser.parseToEventList(jsonObject.getJSONObject("event")));
			ns.setMessage(jsonObject.getString("message"));
			ns.setUser(jsonObject.getInt("userId"));
			try {
				ns.setNotificationDate(formatter.parse(jsonObject.getString("date")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ns.setEventType(jsonObject.getString("eventType"));
			ns.setEventState(jsonObject.getString("eventState"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return ns;
		
	}
}
