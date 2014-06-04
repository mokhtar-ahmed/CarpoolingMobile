package com.iti.jets.carpoolingV1.eventDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.widget.Toast;

import com.iti.jets.carpoolingV1.httphandler.CancelEvent;
import com.iti.jets.carpoolingV1.httphandler.RetriveEvent;
import com.iti.jets.carpoolingV1.httphandler.UpdateEvent;
import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.pojos.Comment;
import com.iti.jets.carpoolingV1.pojos.CustomUser;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.pojos.Location;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

public class EventDetialsController {
	EventDetailsActivity view;
	
	public EventDetialsController(EventDetailsActivity view){
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
					String add= jj.getString("address");
					view.selectedToLocs.add(add);
					view.toTxt.append(add+",");
				}
			
				view.eventNameTxt.setText("");
				view.eventNameTxt.setText("Event Name :\t"+ eventObj.getString("eventName"));
				
				String from  = loc.getString("address");
				view.selectedFromLoc = from ;
				view.fromTxt.setText("From :\t"+ from );
				
				int no = eventObj.getInt("noOfSlots");
				
				view.selectedNoOfSlots = no;
				view.no_of_slots.setText("slots : \n \t"+ no);
				
				String dateStr = eventObj.getString("eventDate");
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					view.d = formatter.parse(dateStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				view.dateBtn.setText("Date :\n\t"+view.d.toString());	
				 
				ArrayList<Comment> commentslist = new ArrayList<Comment>(); 
				for (int i = 0; i < comments.length(); i++) {
					
					Comment comment = JsonParser.parseToCommentList(comments.getJSONObject(i));
					System.out.println(comment.getId() +"coment id ");
					
					if(comment != null)
						commentslist.add(comment);
					
				}
				
				view.commentsList = commentslist;
				
				view.fillCommentList();
				ArrayList<CustomUser> userList = new ArrayList<CustomUser>(); 
				for (int i = 0; i < members.length(); i++) {
					
					CustomUser us = JsonParser.parseToCustomUsertList(members.getJSONObject(i));
					System.out.println(us.getId() +"custom user id ");
					
					if(us != null)
						userList.add(us);
					
				}
				view.usersList = userList;
				EntityFactory.setUsersCustom(userList);
				
				view.fillUsersList();
				
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
	public void updateEventHandler(String string) {
		// TODO Auto-generated method stub
		new UpdateEvent(this).execute(new String[]{string});
		
	}
	public void onUpdatePostExecute(String result) {
		// TODO Auto-generated method stub
		
		
		try {
			
			if(result.equals("No Connection") == false){
				JSONObject ob = new JSONObject(result);
				
				if(ob.getBoolean("HasError") == true){
					Toast.makeText(view.getActivity().getApplicationContext(),ob.getString("FaultsMsg"), Toast.LENGTH_LONG).show();			
					
				}else{
					
					Toast.makeText(view.getActivity().getApplicationContext(),ob.getString("ResponseValue"), Toast.LENGTH_LONG).show();
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void onCancelPostExecute(String result) {
		
	try {
		
		
			if(result.equals("No Connection") == false){
				JSONObject ob = new JSONObject(result);
				
				view.prog.dismiss();
				if(ob.getBoolean("HasError") == true){
					Toast.makeText(view.getActivity().getApplicationContext(),ob.getString("FaultsMsg"), Toast.LENGTH_LONG).show();			
					
				}else{
		
					
					Toast.makeText(view.getActivity().getApplicationContext(),ob.getString("ResponseValue"), Toast.LENGTH_LONG).show();
					UIManagerHandler.getoEventHome(view.getActivity());
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void cancelEventHandler(String string) {
		new CancelEvent(this).execute(new String[]{string});
		
	}

}
