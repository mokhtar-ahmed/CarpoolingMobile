package com.iti.jets.carpoolingV1.addevent;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.view.View;
import android.widget.Toast;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.httphandler.AddEvent;
import com.iti.jets.carpoolingV1.httphandler.RetrieveCircleUsersHandler;
import com.iti.jets.carpoolingV1.pojos.User;
import com.iti.jets.carpoolingV1.retrieveallcircles.RetrieveCircleUsersController;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

public class AddEventController {

	AddEventActivity addEventActivity;
	
	
	public AddEventController(AddEventActivity addEventActivity) {
		// TODO Auto-generated constructor stub
		this.addEventActivity = addEventActivity;
	}

	public void onPostExecute(String result) {
		
	
		System.out.println(result);
		
		
		addEventActivity.prog.dismiss();
		
		if(result.equals("No Connection") == false){
		
			
			JSONObject obj;
			try {
				
				obj = new JSONObject(result);
				
				if ( obj.getBoolean("HasError") == true ){
					Toast.makeText(addEventActivity.getActivity().getApplicationContext(), obj.getString("FaultsMsg"), Toast.LENGTH_LONG).show();	
				}else{
				
					addEventActivity.callSetEventAtCalendar();
					UIManagerHandler.getoEventHome(addEventActivity.getActivity());
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else{
			Toast.makeText(addEventActivity.getActivity().getApplicationContext(), "No connection", Toast.LENGTH_LONG).show();
			
		}
	}


	public void addEventHandler(String parm) {
		new AddEvent(this).execute(new String []{parm});
	}

	public void getCirclesUsers(JSONObject circlesIds){
		
		new RetrieveCircleUsersHandler(this).execute(new String[]{circlesIds.toString()});
	}
	public void setCirclesUsers(String result) {
		
		// TODO Auto-generated method stub
		try {
			
			ArrayList<User> ul = new ArrayList<User>();
			
			JSONObject js = new JSONObject(result);
			JSONArray usrArr = js.getJSONArray("ResponseValue");
		
			for(int i=0; i<usrArr.length();i++){
				
				JSONObject jobj = usrArr.getJSONObject(i);
				
				User u = new User();
				u.setId(jobj.getInt("id"));
				u.setName(jobj.getString("username"));
				
				ul.add(u);
				
			}
			addEventActivity.Users = ul;
			addEventActivity.showBlockUserDialog();
			addEventActivity.prog.dismiss();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
