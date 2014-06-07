package com.iti.jets.carpoolingV1.retrieveallcircles;

import org.json.JSONArray;

import com.iti.jets.carpoolingV1.common.Circle2;
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.httphandler.DeleteCircleServiceHandler;
import com.iti.jets.carpoolingV1.httphandler.DeleteUserFromCircleServiceHandler;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;
import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment.FragmentCallback;

public class DeleteUserFromCircleController {

	private String returnServiceOutput;
	private JSONArray userToDelJsArr = new JSONArray();
	private FragmentCallback fragmentCallback;
	private DeleteUserFromCircleServiceHandler delUserFromCircleHanler;
	private String uri;
	private int circleId;
	public void setArguments(JSONArray usersToRemoveJsArr, int circle_Id, FragmentCallback fragmentCallback) {
		// TODO Auto-generated method stub
		userToDelJsArr = usersToRemoveJsArr; 
		circleId = circle_Id;
		this.fragmentCallback = fragmentCallback;
		delUserFromCircleHanler = new DeleteUserFromCircleServiceHandler();
		uri = HttpConstants.SERVER_URL + HttpConstants.DELETE_USER_FROM_CIRCLE_URL;
		delUserFromCircleHanler.connectToWebService(circleId,userToDelJsArr,fragmentCallback ,uri);
		
		
	}

}
