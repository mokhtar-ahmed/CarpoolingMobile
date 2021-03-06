package com.iti.jets.carpoolingV1.SearchHome;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.widget.Toast;

import com.google.android.gms.internal.ev;
import com.google.android.gms.internal.in;
import com.iti.jets.carpoolingV1.httphandler.GetAllUserNotification;
import com.iti.jets.carpoolingV1.httphandler.MarkNotificationAsReaded;
import com.iti.jets.carpoolingV1.httphandler.RetriveUserEventsHandler;
import com.iti.jets.carpoolingV1.httphandler.SearchEventByDriverServiceHandler;
import com.iti.jets.carpoolingV1.httphandler.SearchEventByLocationServiceHandler;
import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.pojos.Circle;
import com.iti.jets.carpoolingV1.pojos.Event;
import com.iti.jets.carpoolingV1.pojos.Notification;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

public class SearchHomeController {

	SearchHome view;
	
	public SearchHomeController(SearchHome view){
		
		this.view = view;
		
	}
	


	public void onDriverPostExecute(String result) {
		// TODO Auto-generated method stub
	
		view.prog.dismiss();
		System.out.println(result);
		
		if(result.equals("No Connection")== false){
			
			
			ArrayList<Event> events = new ArrayList<Event>();
			JSONArray eventsJson;
			try {
				
				
				JSONObject js = new JSONObject(result);

				if ( js.getBoolean("HasError") == true ){
					
					AlertDialog alertDialog = new AlertDialog.Builder(
		                    view.getActivity()).create();
					alertDialog.setMessage("Failed to load the result");
					alertDialog.show();
				}else {
			
				
					eventsJson =  js.getJSONArray("ResponseValue");

					for(int i =0 ; i<eventsJson.length(); i++){
						Event ev = JsonParser.parseToEventList(eventsJson.getJSONObject(i));
						System.out.println(ev.getId());
						
						if(ev != null)
							events.add(ev);
					}
					if(events.isEmpty()){
					
						AlertDialog alertDialog = new AlertDialog.Builder(
			                    view.getActivity()).create();
						alertDialog.setMessage("You didn't attand evnets with him before.");
						alertDialog.show();
					}else{
					view.values = events;
					view.fillListViewData();
					}
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}else{
			UIManagerHandler.goToConnectionFailed(view.getActivity());

			
		} 

	
	}

	public void searchByDriver(String input) {
		// TODO Auto-generated method stub
		new SearchEventByDriverServiceHandler(this).execute(new String[]{input});
	}

	public void searchByLocation(String input) {
		// TODO Auto-generated method stub
		
		new SearchEventByLocationServiceHandler(this).execute(new String[]{input});
	}

	public void onLocationPostExecute(String result) {
		// TODO Auto-generated method stub
		System.out.println(result);
		
		view.prog.dismiss();
		if(result.equals("No Connection")== false){
			
			ArrayList<Event> events = new ArrayList<Event>();
			JSONArray eventsJson;
			try {
				
				
				JSONObject js = new JSONObject(result);
				
				JSONArray eventsFrom = js.getJSONObject("ResponseValue").getJSONArray("eventsFrom");
				JSONArray eventsTo = js.getJSONObject("ResponseValue").getJSONArray("eventsTo");
				//eventsJson =  

				for(int i =0 ; i<eventsFrom.length(); i++){
					Event ev = JsonParser.parseToEventList(eventsFrom.getJSONObject(i));
					System.out.println(ev.getId());
					
					if(ev != null)
						events.add(ev);
				}
				
				for(int i =0 ; i<eventsTo.length(); i++){
					Event ev = JsonParser.parseToEventList(eventsTo.getJSONObject(i));
					System.out.println(ev.getId());
					
					if(ev != null)
						events.add(ev);
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(events.isEmpty()){
				
				AlertDialog alertDialog = new AlertDialog.Builder(
	                    view.getActivity()).create();
				alertDialog.setMessage("You didn't attand evnets from this place before.");
				alertDialog.show();
			}else{
				view.values = events;
				view.fillListViewData();
			}
			
		}else{
			
			UIManagerHandler.goToConnectionFailed(view.getActivity());
		} 

	

	}

	

}
