package com.iti.jets.carpoolingV1.eventRequests;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;

import com.iti.jets.carpoolingV1.httphandler.AcceptEvent;
import com.iti.jets.carpoolingV1.httphandler.RejectEvent;
import com.iti.jets.carpoolingV1.httphandler.RetriveUserEventsHandler;
import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.pojos.Circle;
import com.iti.jets.carpoolingV1.pojos.Event;

public class RequestsHomeController {

	RequestsHome view;
	
	public RequestsHomeController(RequestsHome view){
		
		this.view = view;
		
	}
	
	public void onPostExecute(String result) {

	
		

		
		
	}
	public void retriveAllEvents(String input) {
	
		//new RetriveUserEventsHandler(this).execute(new String[]{input});

		}

	public void acceptHandler(String string) {
		// TODO Auto-generated method stub
		new AcceptEvent(this).execute(new String[]{string});
	}

	public void rejectHandler(String string) {
		// TODO Auto-generated method stub
		new RejectEvent(this).execute(new String[]{string});
	}

	public void onRejectPostExecute(String result) {
		// TODO Auto-generated method stub
		Toast.makeText(view.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
		
	}

	public void onAcceptPostExecute(String result) {
		// TODO Auto-generated method stub
		Toast.makeText(view.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();	
	}


}
