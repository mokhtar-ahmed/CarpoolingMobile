package com.iti.jets.carpoolingV1.eventshome;

import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;

import com.iti.jets.carpoolingV1.httphandler.RetriveUserEventsHandler;
import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.pojos.Circle;
import com.iti.jets.carpoolingV1.pojos.Event;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

public class EventsHomeController {

	EventsHome view;
	
	public EventsHomeController(EventsHome view){
		
		this.view = view;
		
	}
	
	public void onPostExecute(String result) {

		//Toast.makeText(view.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
		
		System.out.println(result);
	
		if(result.equals("No Connection")== false){
			
		
			ArrayList<Event> events = new ArrayList<Event>();
			JSONArray eventsJson;
			try {
				
				
				JSONObject js = new JSONObject(result);
				
				
				eventsJson =  js.getJSONArray("ResponseValue");

				for(int i =0 ; i<eventsJson.length(); i++){
					Event ev = JsonParser.parseToEventList(eventsJson.getJSONObject(i));
					System.out.println(ev.getId());
					
					if(ev != null)
						events.add(ev);
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(events.isEmpty()){
				
				UIManagerHandler.goToNoEvent(view.getActivity());
				
			}else {
				
				Collections.sort(events);
			    view.values = events;
				view.fillListViewData();
		 }
			
		}else{
			UIManagerHandler.goToConnectionFailed(view.getActivity());
		
		} 
		
		

		
	
	}
	public void retriveAllEvents(String input) {
	
		new RetriveUserEventsHandler(this).execute(new String[]{input});

		}


}
