package com.iti.jets.carpoolingV1.retrieveallcircles;

import com.iti.jets.carpoolingV1.httphandler.CircleUsersHandler;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;
import com.iti.jets.carpoolingV1.httphandler.RetrieveAllCirclesServiceHandler;

public class CircleUsersController {


	private CircleUsersHandler handler;
	public CircleUsersController(String selectedCircleName, int userId,CircleUsersFragment circleUsersActivityObj) {
		
		handler = new CircleUsersHandler();
		String url = HttpConstants.SERVER_URL + HttpConstants.RETRIEVE_CIRCLE_USERS_URL;
		handler.connectToWebService(selectedCircleName,userId,circleUsersActivityObj,url);	
	}

	
}
