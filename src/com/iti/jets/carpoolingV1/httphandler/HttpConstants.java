package com.iti.jets.carpoolingV1.httphandler;

import android.util.Log;

public final class HttpConstants {

	//Webservices URI addresses 
  //  public static final String SERVER_URL="http://192.168.200.1:9191/CarpoolingBackendFinal/service/";
    public static final String SERVER_URL="http://10.145.57.134:9191/CarpoolingBackendFinal/service/";
	public static final String LOGIN_URL="user/newLogin";
	public static final String GET_CIRCLE_USERS_URL="newCircle/retrieveCirclesUsers";
	public static final String JOIN_EVENT ="user/joinvent";
	public static final String ACCEPT_EVENT ="user/accept";
	public static final String REJECT_EVENT ="user/reject";
	public static final String ADDEVENT_SERVICE_URL="event/addEvent";
	public static final String RETRIEVE_USER_EVENT_SERVICE_URL="event/retrieveAllUserEvents";
	public static final String RETRIEVE_USER_NOTIFICATION_SERVICE_URL="event/getUserJoinEvents";
	public static final String RETRIEVE_EVENT_SERVICE_URL="event/getEvent";
	public static final String UPDATE_EVENT_SERVICE_URL="event/updateEvent";
	public static final String ADD_COMMENT_URL="comment/addComment";
	public static final String DELETE_COMMENT_URL = "comment/deleteComments";
	public static final String RETRIVE_ALL_LOCATIONS_SERVICE_URL="location/allLocation";
	public static final String RETRIVE_ALL_USER_CIRCLES_SERVICE_URL="viewcircle/view";
	public static final String RETRIVE_EVENT_SERVICE_URL="event/getEvent";
	public static final String RETRIEVE_ALL_CIRCLES_URL="viewcircle/view";
	public static final String RETRIEVE_USER_CIRCLES_URL="viewcircle/view2";
	public static final String REGISTER_SERVICE_URL="register/reg";
	public static final String Edit_Profile_URL = "editprofileservice/edit";
	public static final String ADD_CIRCLE_SERVICE_URL = "addCircle/add";
	public static final String SYNC_CONTACTS_URL="syncContacts/sync";
	public static final String ADD_USERTO_CIRCLE_SERVICE_URL="addUserToCircle/addUser";
	public static final String RETRIEVE_CIRCLE_USRES_URL="retrieveCircleUsers/retrieve";
	public static final String DELETE_CIRCLE_URL="deleteCircle/delete";
	public static final String DELETE_USER_FROM_CIRCLE_URL="deleteUserFromCircle/deleteUser";
	
	public static final String GETFRIENDS_SERVICE_URL="getFriends";
	public static final String ADDFRIEND_SERVICE_URL="addFriend";
	public static final String REMOVEFRIEND_SERVICE_URL="removeFriend";
	public static final String UPDATEPROFILE_SERVICE_URL="update";
	public static final String RETRIEVE_CIRCLE_USERS_URL="retrievecircleusers/getusers";
	//Connection Constants
	public static final int timeoutSocket = 5000;
	public static final int timeoutConnection = 5000;
		
}
