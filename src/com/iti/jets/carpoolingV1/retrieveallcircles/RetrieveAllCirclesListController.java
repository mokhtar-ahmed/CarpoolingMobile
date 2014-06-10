package com.iti.jets.carpoolingV1.retrieveallcircles;

import android.util.Log;

import com.iti.jets.carpoolingV1.firstrun.AllCirclesListFragment2;
import com.iti.jets.carpoolingV1.httphandler.*;



public class RetrieveAllCirclesListController {
	
	private RetrieveAllCirclesServiceHandler serviceHandler;
	private int userId;

public RetrieveAllCirclesListController(int userId,AllCirclesListFragment2 circleListReference)
{
		this.userId = userId;
		
		serviceHandler = new RetrieveAllCirclesServiceHandler();
		String url =  HttpConstants.SERVER_URL+ HttpConstants.RETRIEVE_ALL_CIRCLES_URL;
     	serviceHandler.connectToWebService(userId,circleListReference,url);	
     	Log.d("ENTERED","ENTERED");
     	Log.d("URL", url);
		
}	
public RetrieveAllCirclesListController(int userId,AllCirclesListFragment circleListReference)
	{
		this.userId = userId;
		
		serviceHandler = new RetrieveAllCirclesServiceHandler();
		String url =  HttpConstants.SERVER_URL+ HttpConstants.RETRIEVE_ALL_CIRCLES_URL;
     	serviceHandler.connectToWebService(userId,circleListReference,url);	
     	Log.d("ENTERED","ENTERED");
     	Log.d("URL", url);
		
	}

}
