package com.iti.jets.carpoolingV1.eventDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Toast;
import com.iti.jets.carpoolingV1.httphandler.RetriveEvent;

public class EventDetialsController {
	EventDetailsActivity view;
	
	public EventDetialsController(EventDetailsActivity view){
			this.view = view;
		
	}
	public void onPostExecute(String result) {
		
		Toast.makeText(view.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
		System.out.println(result);
		
		if(result.equals("No Connection") == false){
			
			try {
				
				JSONObject eventObj = new JSONObject(result);
				
					JSONArray toList = eventObj.getJSONArray("eventToLocation");
					JSONArray members = eventObj.getJSONArray("joinEvent");
					JSONArray comments = eventObj.getJSONArray("comments");
					JSONObject loc = eventObj.getJSONObject("location");
					
				view.eventTo.setText("To :\n\t");
				for(int i=0;i<toList.length();i++){
					
					JSONObject jj = toList.getJSONObject(i);
					view.eventTo.append(jj.getString("address")+",");
				}
			
				view.eventName.setText("Event Name : \n \t"+ eventObj.getString("eventName"));
				view.eventFrom.setText("From : \n \t"+ loc.getString("address"));
				view.noOfSlots.setText("Number of avaliable slots : \n \t"+ eventObj.getInt("noOfSlots"));
				view.eventDate.setText("Date :\n\t"+eventObj.getString("eventDate"));
				
				view.members.setText("Members :\n\t");
				for(int i=0;i<members.length();i++){
					
					JSONObject jj = members.getJSONObject(i);
					view.members.append(jj.getString("userName")+"\n\t");
				}
				
				view.comments.setText("Comments :\n\t");
				for(int i=0;i<comments.length();i++){
					
					JSONObject jj = comments.getJSONObject(i);
					view.comments.append(jj.getString("user")+":\t"+jj.getString("text"));
				}
				
				
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

}
