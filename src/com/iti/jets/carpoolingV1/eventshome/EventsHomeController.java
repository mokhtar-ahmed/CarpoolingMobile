package com.iti.jets.carpoolingV1.eventshome;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.widget.Toast;

import com.iti.jets.carpoolingV1.httphandler.RetriveUserEventsHandler;
import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.pojos.Circle;
import com.iti.jets.carpoolingV1.pojos.Event;

public class EventsHomeController {

	EventsHome view;
	
	public EventsHomeController(EventsHome view){
		
		this.view = view;
		
	}
	
	public void onPostExecute(String result) {
		
	Toast.makeText(view.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
	
		System.out.println(result);
	
		if(result.equals("No Connection")== false){
			
		
			ArrayList<Event> events = new ArrayList<Event>();
			JSONArray eventsJson;
			try {
				
				eventsJson = new JSONArray(result);

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
		
			view.fillListViewData(events);
			
		}else{
			Toast.makeText(view.getActivity().getApplicationContext(), "Connect to internet", Toast.LENGTH_LONG).show();
			
		} 
		
		
	}
	public void retriveAllEvents(String input) {
	
		new RetriveUserEventsHandler(this).execute(input);
		
	}
	

}
