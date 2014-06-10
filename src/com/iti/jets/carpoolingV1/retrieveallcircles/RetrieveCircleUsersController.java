package com.iti.jets.carpoolingV1.retrieveallcircles;


import com.iti.jets.carpoolingV1.common.*;
import com.iti.jets.carpoolingV1.firstrun.AllCirclesListFragment2;
import com.iti.jets.carpoolingV1.httphandler.DeleteCircleServiceHandler;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;
import com.iti.jets.carpoolingV1.httphandler.RetrieveCircleUsersServiceHandler;
import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment.FragmentCallback;
import com.iti.jets.carpoolingV1.firstrun.AllCirclesListFragment2.FragmentCallback2;


public class RetrieveCircleUsersController {

	
	
	int circleId = 0;
	FragmentCallback fragmentCallback;
	private RetrieveCircleUsersServiceHandler ruServiceHandler;
	private String uri;
	private FragmentCallback2 fragmentCallback2;
	public void setArguments(AllCirclesListFragment allCirclesListFragment, int circle_Id, FragmentCallback fragmentCallback2) {
		// TODO Auto-generated method stub
		circleId =  circle_Id; 
		this.fragmentCallback = fragmentCallback2;
		ruServiceHandler = new RetrieveCircleUsersServiceHandler();
		uri = HttpConstants.SERVER_URL + HttpConstants.RETRIEVE_CIRCLE_USRES_URL;
		ruServiceHandler.connectToWebService(circleId,fragmentCallback2 ,uri,allCirclesListFragment);
	}

	public void setArguments(AllCirclesListFragment2 allCirclesListFragment, int circle_Id, FragmentCallback2 fragmentCallback22) {
		// TODO Auto-generated method stub
		circleId =  circle_Id; 
		this.fragmentCallback2 = fragmentCallback22;
		ruServiceHandler = new RetrieveCircleUsersServiceHandler();
		uri = HttpConstants.SERVER_URL + HttpConstants.RETRIEVE_CIRCLE_USRES_URL;
		ruServiceHandler.connectToWebService(circleId,fragmentCallback2 ,uri,allCirclesListFragment);
	}
}
