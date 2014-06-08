package com.iti.jets.carpoolingV1.eventDetails;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Toast;

import com.iti.jets.carpoolingV1.httphandler.JoinEvent;
import com.iti.jets.carpoolingV1.httphandler.RetriveEvent;
import com.iti.jets.carpoolingV1.httphandler.UpdateEvent;
import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.pojos.Comment;
import com.iti.jets.carpoolingV1.pojos.CustomUser;
import com.iti.jets.carpoolingV1.pojos.Location;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

public class InvitedEventDetialsController {
	InvitedEventDetailsActivity view;
	
	public InvitedEventDetialsController(InvitedEventDetailsActivity view){
			this.view = view;
		
	}
	public void onPostExecute(String result) {
		
		//Toast.makeText(view.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
		System.out.println(result);
		
		if(result.equals("No Connection") == false){
			
			try {
				
				JSONObject Obj = new JSONObject(result);
				JSONObject eventObj	= Obj.getJSONObject("ResponseValue");
				
				
					JSONArray toList = eventObj.getJSONArray("eventToLocation");
					JSONArray members = eventObj.getJSONArray("joinEvent");
					JSONArray comments = eventObj.getJSONArray("comments");
					JSONObject loc = eventObj.getJSONObject("location");
					
				view.toTxt.setText("To :\t ");
				for(int i=0;i<toList.length();i++){
					
					JSONObject jj = toList.getJSONObject(i);
					view.toTxt.append(jj.getString("address")+",");
				}
			
				view.eventNameTxt.setText("Event Name :\t"+ eventObj.getString("eventName"));
				
				view.fromTxt.setText("From :\t"+ loc.getString("address"));
				view.no_of_slots.setText("slots : \n \t"+ eventObj.getInt("noOfSlots"));
				view.dp.setText("Date :\n\t"+eventObj.getString("eventDate"));	
				
		
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			
			Toast.makeText(view.getActivity().getApplicationContext(), "Connect to internet", Toast.LENGTH_LONG).show();
			
		}
		
	}

	public void retrieveEventHandler(String parm) {
		
		new RetriveEvent(this).execute(new String[]{parm});
	
	}
	public void joinEventHandler(String string) {
		
		// TODO Auto-generated method stub
		new JoinEvent(this).execute(new String[]{string});
	}
	public void onJoinPostExecute(String result) {
		// TODO Auto-generated method stub
		view.prog.dismiss();
		Toast.makeText(view.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
		UIManagerHandler.getoEventHome(view.getActivity());
		
	}
	
	
}
