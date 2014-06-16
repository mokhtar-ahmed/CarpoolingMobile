package com.iti.jets.carpoolingV1.feedshome;

import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;

import com.iti.jets.carpoolingV1.httphandler.GetAllUserNotification;
import com.iti.jets.carpoolingV1.httphandler.RetriveUserEventsHandler;
import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.pojos.Circle;
import com.iti.jets.carpoolingV1.pojos.Event;
import com.iti.jets.carpoolingV1.pojos.Notification;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

public class FeedsHomeController {

	FeedsHome view;
	
	public FeedsHomeController(FeedsHome view){
		
		this.view = view;
		
	}
	
	public void onPostExecute(String result) {
		
	
		
	}
	public void retriveAllEvents(String input) {
	
		new RetriveUserEventsHandler(this).execute(new String[]{input});
		
	}
	public void retriveAllNotifications(String input) {
		
		new GetAllUserNotification(this).execute(new String[]{input});
			
   }

	public void onPostEventsExecute(String result) {
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
				
				//UIManagerHandler.goToNoEvent(view.getActivity());
				
			}else {
				
				Collections.sort(events);
			    view.eventValues = events;
				view.fillEventListViewData();
		 }
			
		}else{
			UIManagerHandler.goToConnectionFailed(view.getActivity());
		
		} 
		
	}
	
	public void onPostNotificationExecute(String result) {
		
		//Toast.makeText(view.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
			System.out.println(result);
			
			if(result.equals("No Connection")== false){
				
				try {
					
					JSONArray input = new JSONObject(result).getJSONArray("ResponseValue");
					ArrayList<Notification> nfList = new ArrayList<Notification>();
					
					for (int i = 0; i < input.length(); i++) {
						
						Notification nf = JsonParser.parseToNotifications(input.getJSONObject(i));
						nfList.add(nf);
						
					}
					
					if(nfList.size() == 0){
						UIManagerHandler.goToNoNotificationHome(view.getActivity());
					}
					else {
						Collections.sort(nfList);
						
						view.notificationValues = nfList;
						view.fillNotificationtListViewData();
					}
						
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
					
				
			}else 
				UIManagerHandler.goToConnectionFailed(view.getActivity());

		
		
	}
	
}
