package com.iti.jets.carpoolingV1.eventRequests;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.widget.Toast;

import com.iti.jets.carpoolingV1.httphandler.AcceptEvent;
import com.iti.jets.carpoolingV1.httphandler.RejectEvent;
import com.iti.jets.carpoolingV1.httphandler.RetriveUserEventsHandler;
import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.pojos.Circle;
import com.iti.jets.carpoolingV1.pojos.Event;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

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
		
		JSONObject Obj;
		try {
			
			Obj = new JSONObject(result);
			
			if(result.equalsIgnoreCase("No Connection") == false){
			
				if ( Obj.getBoolean("HasError") == true ){
					
					AlertDialog alertDialog = new AlertDialog.Builder(
		                    view.getActivity()).create();
					alertDialog.setMessage(Obj.getString("FaultsMsg"));
				}else {
			
					JSONObject eventObj	= Obj.getJSONObject("ResponseValue");
					AlertDialog alertDialog = new AlertDialog.Builder(view.getActivity()).create();
					alertDialog.setMessage(eventObj.toString());
					alertDialog.show();
					
				}
					UIManagerHandler.goToConnectionFailed(view.getActivity());
				}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	public void onAcceptPostExecute(String result) {
		JSONObject Obj;
		try {
			
			Obj = new JSONObject(result);
			
			if(result.equalsIgnoreCase("No Connection") == false){
			
				if ( Obj.getBoolean("HasError") == true ){
					
					AlertDialog alertDialog = new AlertDialog.Builder(
		                    view.getActivity()).create();
					alertDialog.setMessage(Obj.getString("FaultsMsg"));
				}else {
			
					JSONObject eventObj	= Obj.getJSONObject("ResponseValue");
					AlertDialog alertDialog = new AlertDialog.Builder(
		                    view.getActivity()).create();
					alertDialog.setMessage(eventObj.toString());
					
				}
					UIManagerHandler.goToConnectionFailed(view.getActivity());
				}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}


}
