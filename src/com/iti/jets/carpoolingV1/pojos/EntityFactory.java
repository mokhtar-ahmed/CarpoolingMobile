package com.iti.jets.carpoolingV1.pojos;

import java.util.ArrayList;

public class EntityFactory {

	private static ArrayList<Location> locations = null;
	private static ArrayList<Circle> circles = null;
	private static ArrayList<Event> events = null;
	private static ArrayList<CustomUser> usersCustom = null;
	private static ArrayList<Notification> notifications = null;
	private static User user = null;
	private static String pushNotificationId ="";
	
	public static ArrayList<Location> getLocationsInstance(){
		return locations;		
	}
	
	public static ArrayList<CustomUser> getUsersCustom() {
		return usersCustom;
	}

	public static void setUsersCustom(ArrayList<CustomUser> usersCustom) {
		EntityFactory.usersCustom = usersCustom;
	}

	public static User getUserInstance(){
		return user;
	}

	public static void setEvents(ArrayList<Event> events) {
		EntityFactory.events = events;
	}

	public static  ArrayList<Event> getEventInstance(){
		return events;
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
	public static void setNotificationsInstance(ArrayList<Notification> notifs) {
		
		notifications = notifs;
		
	}
	public static ArrayList<Notification> getNotificationsInstance(){
		return notifications;
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
	
	public static Location getLocationByAddress(String ads){
		
		for (int i = 0; i < locations.size(); i++) {
			
			if(locations.get(i).getAddress().equals(ads) == true)
				return locations.get(i);
		}
		
		return null;
			
		}
	public static Circle getCircleByName(String name){
		
		for (int i = 0; i < circles.size(); i++) {
			
			if(circles.get(i).getCircleName().equals(name) == true)
				return circles.get(i);
		}
		
		return null;
			
		}
	public static int getLocationId(String address){
		
		for(Location l : locations){
			
			if(l.getAddress().equals(address)== true)
				return l.getId();
			
		}
		
		return -1;
	}
	public static int SearchLocationId(String address){
		
		for(Location l : locations){
			
			if(l.getAddress().contains(address)== true)
				return l.getId();
			
		}
		
		return -1;
	}

	
	
}
