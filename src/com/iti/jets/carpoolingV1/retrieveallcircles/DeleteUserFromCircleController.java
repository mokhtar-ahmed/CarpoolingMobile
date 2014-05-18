package com.iti.jets.carpoolingV1.retrieveallcircles;

import com.iti.jets.carpoolingV1.common.Circle;
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.httphandler.DeleteCircleServiceHandler;
import com.iti.jets.carpoolingV1.httphandler.DeleteUserFromCircleServiceHandler;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;
import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment.FragmentCallback;

public class DeleteUserFromCircleController {

	private String returnServiceOutput;
	private User userObj;
	private FragmentCallback fragmentCallback;
	private DeleteUserFromCircleServiceHandler delUserFromCircleHanler;
	private String uri;
	private int circleId;
	public void setArguments(User userValues, int circle_Id, FragmentCallback fragmentCallback) {
		// TODO Auto-generated method stub
		userObj = userValues; 
		circleId = circle_Id;
		this.fragmentCallback = fragmentCallback;
		delUserFromCircleHanler = new DeleteUserFromCircleServiceHandler();
		uri = HttpConstants.SERVER_URL + HttpConstants.DELETE_USER_FROM_CIRCLE_URL;
		delUserFromCircleHanler.connectToWebService(circleId,userObj,fragmentCallback ,uri);
		
		
	}

}
