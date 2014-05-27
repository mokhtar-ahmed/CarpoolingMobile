package com.iti.jets.carpoolingV1.pojos;

import java.util.ArrayList;

public class EntityFactory {

	private static ArrayList<Location> locations = null;
	private static ArrayList<Circle> circles = null;
	private static User user = null;
	private static String pushNotificationId ="";
	
	public static ArrayList<Location> getLocationsInstance(){
		return locations;		
	}
	
	public static User getUserInstance(){
		return user;
	}

	public static ArrayList<Circle> getCirclesInstance(){
		return circles;
	}
	public static void setLocationsInstance(ArrayList<Location> locs){
		locations = locs;	
	}
	
	public static void setUserInstance(User us){
		user = us;
	}

	public static void setCirclesInstance(ArrayList<Circle> cir){
		circles = cir;	
	}
	public static void setNotificationIdInstance(String BNI){
		pushNotificationId = BNI;	
	}
	public static String getNotificationIdInstance(){
		return pushNotificationId;	
	}
}
