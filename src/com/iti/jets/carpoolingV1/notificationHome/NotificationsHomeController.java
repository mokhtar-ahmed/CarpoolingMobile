package com.iti.jets.carpoolingV1.notificationHome;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.widget.Toast;

import com.iti.jets.carpoolingV1.httphandler.RetriveUserEventsHandler;
import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.pojos.Circle;
import com.iti.jets.carpoolingV1.pojos.Event;

public class NotificationsHomeController {

	NotificationHome view;
	
	public NotificationsHomeController(NotificationHome view){
		
		this.view = view;
		
	}
	
	public void onPostExecute(String result) {
		
	//Toast.makeText(view.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
	
		System.out.println(result);
	
		
	}
	public void retriveAllEvents(String input) {
	
	
		
	}
	

}
