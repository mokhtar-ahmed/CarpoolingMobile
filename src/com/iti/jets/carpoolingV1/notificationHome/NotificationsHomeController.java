package com.iti.jets.carpoolingV1.notificationHome;

import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;

import com.iti.jets.carpoolingV1.httphandler.GetAllUserNotification;
import com.iti.jets.carpoolingV1.httphandler.MarkNotificationAsReaded;
import com.iti.jets.carpoolingV1.httphandler.RetriveUserEventsHandler;
import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.pojos.Circle;
import com.iti.jets.carpoolingV1.pojos.Event;
import com.iti.jets.carpoolingV1.pojos.Notification;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

public class NotificationsHomeController {

	NotificationHome view;
	
	public NotificationsHomeController(NotificationHome view){
		
		this.view = view;
		
	}
	
	public void onPostExecute(String result) {
		
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
					
					view.values = nfList;
					view.fillListViewData();
				}
					
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
				
			
		}else 
			Toast.makeText(view.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
	
		
	}
	public void retriveAllNotification(String input) {
	
	new GetAllUserNotification(this).execute(new String[]{input});
		
	}

	public void markAsReaded(String input) {
		// TODO Auto-generated method stub
	  new MarkNotificationAsReaded().execute(new String[]{input});	
	}
	

}
