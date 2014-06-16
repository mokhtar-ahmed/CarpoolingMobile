package com.iti.jets.carpoolingV1.eventDetails;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.widget.Toast;

import com.iti.jets.carpoolingV1.httphandler.LeaveEvent;
import com.iti.jets.carpoolingV1.httphandler.RetriveEvent;
import com.iti.jets.carpoolingV1.httphandler.UpdateEvent;
import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.pojos.Comment;
import com.iti.jets.carpoolingV1.pojos.CustomUser;
import com.iti.jets.carpoolingV1.pojos.Location;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

public class AcceptedEventDetialsController {
	AcceptedEventDetailsActivity view;
	
	public AcceptedEventDetialsController(AcceptedEventDetailsActivity view){
			this.view = view;
		
	}
	public void onPostExecute(String result) {
		
		//Toast.makeText(view.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
		System.out.println(result);
		
		if(result.equals("No Connection") == false){
			
			try {
				
				JSONObject Obj = new JSONObject(result);
				if ( Obj.getBoolean("HasError") == true ){
					
					AlertDialog alertDialog = new AlertDialog.Builder(view.getActivity()).create();
					alertDialog.setMessage(Obj.getString("FaultsMsg"));
					alertDialog.show();
					UIManagerHandler.goToNoEvent(view.getActivity());
				}else {
			
					JSONObject eventObj	= Obj.getJSONObject("ResponseValue");
					JSONArray toList = eventObj.getJSONArray("eventToLocation");
					JSONArray members = eventObj.getJSONArray("joinEvent");
					JSONArray comments = eventObj.getJSONArray("comments");
					JSONObject loc = eventObj.getJSONObject("location");
					
				view.toTxt.setText("");
				for(int i=0;i<toList.length();i++){
					
					JSONObject jj = toList.getJSONObject(i);
					view.toTxt.append(jj.getString("address")+",");
				}
			
				view.eventNameTxt.setText(""+ eventObj.getString("eventName"));
				
				view.fromTxt.setText(""+ loc.getString("address"));
				view.no_of_slots.setText(""+ eventObj.getInt("noOfSlots"));
				view.dp.setText(""+eventObj.getString("eventDate"));	
				view.getActivity().getActionBar().setTitle(eventObj.getString("eventName"));
				
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
				view.fillUsersList();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			
			//Toast.makeText(view.getActivity().getApplicationContext(), "Connect to internet", Toast.LENGTH_LONG).show();
			UIManagerHandler.goToConnectionFailed(view.getActivity());
		}
		
	}

	public void retrieveEventHandler(String parm) {
		
		new RetriveEvent(this).execute(new String[]{parm});
	
	}
	
	public void onUpdatePostExecute(String result) {
		// TODO Auto-generated method stub
		
		
		try {
			
			if(result.equals("No Connection") == false){
				JSONObject ob = new JSONObject(result);
				
				if(ob.getBoolean("HasError") == true){
					AlertDialog alertDialog = new AlertDialog.Builder(view.getActivity()).create();
					alertDialog.setMessage(ob.getString("FaultsMsg"));
					alertDialog.show();
					//UIManagerHandler.goToFeedsHome(view.getActivity());
				}else{
					
					AlertDialog alertDialog = new AlertDialog.Builder( view.getActivity()).create();
					alertDialog.setMessage("Event updated successfully.....");
					alertDialog.show();
					UIManagerHandler.goToFeedsHome(view.getActivity());
					//Toast.makeText(view.getActivity().getApplicationContext(),ob.getString("ResponseValue"), Toast.LENGTH_LONG).show();
				}
			}		
			else 
				UIManagerHandler.goToConnectionFailed(view.getActivity());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void onLeavePostExecute(String result) {
		// TODO Auto-generated method stub
		
		
		view.prog.dismiss();
		try {
			
			if(result.equals("No Connection") == false){
				JSONObject ob = new JSONObject(result);
				
				if(ob.getBoolean("HasError") == true){
					AlertDialog alertDialog = new AlertDialog.Builder(view.getActivity()).create();
					alertDialog.setMessage(ob.getString("FaultsMsg"));
					alertDialog.show();
					//UIManagerHandler.goToNotificationHome(view.getActivity());
				}else{
					
					AlertDialog alertDialog = new AlertDialog.Builder( view.getActivity()).create();
					alertDialog.setMessage("Leaved Successfully..... ");
					alertDialog.show();
					//Toast.makeText(view.getActivity().getApplicationContext(),ob.getString("ResponseValue"), Toast.LENGTH_LONG).show();
					UIManagerHandler.goToFeedsHome(view.getActivity());
				}
			}		
			else 
				UIManagerHandler.goToConnectionFailed(view.getActivity());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void leaveEventHandler(String string) {
		new LeaveEvent(this).execute(new String[]{string});
		
	}

}
