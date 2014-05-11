package com.iti.jets.carpoolingV1.httphandler;

public class HttpConstants {
	
	//Webservices URI addresses 
	//public static final String SERVER_URL="http://192.168.200.1:8080/5odnyM3kBackEnd/Home/";
	public static final String SERVER_URL="http://10.145.47.14:8080/5odnyM3kBackEnd/Home/";
	public static final String LOGIN_URL="user/login";
	public static final String ADDEVENT_SERVICE_URL="home/addEvent";
	public static final String RETRIEVE_USER_EVENT_SERVICE_URL="event/retrieveAllUserEvents";
	public static final String RETRIEVE_EVENT_SERVICE_URL="event/getEvent";
	public static final String UPDATE_EVENT_SERVICE_URL="event/updateEvent";
	public static final String RETRIVE_ALL_LOCATIONS_SERVICE_URL="location/allLocations";
	public static final String RETRIVE_ALL_USER_CIRCLES_SERVICE_URL="viewcircle/view";
	
	
	//Connection Constants
	public static final int timeoutSocket = 5000;
	public static final int timeoutConnection = 5000;
	

}
