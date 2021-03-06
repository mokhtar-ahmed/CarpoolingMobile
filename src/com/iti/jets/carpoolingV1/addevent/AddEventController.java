package com.iti.jets.carpoolingV1.addevent;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.view.View;
import android.widget.Toast;

import com.facebook.widget.FacebookDialog;
import com.facebook.widget.FacebookDialog.ShareDialogBuilder;
import com.google.android.gms.internal.al;
import com.google.android.gms.internal.fb;
import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.common.ContactObj;
import com.iti.jets.carpoolingV1.httphandler.AddEvent;
import com.iti.jets.carpoolingV1.httphandler.RetrieveCircleUsersHandler;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.pojos.User;
import com.iti.jets.carpoolingV1.registrationactivity.RegisterFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.RetrieveCircleUsersController;
import com.iti.jets.carpoolingV1.synccontactsactivity.SyncContactsController;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

public class AddEventController {

	AddEventActivity addEventActivity;
	
	
	public AddEventController(AddEventActivity addEventActivity) {
		// TODO Auto-generated constructor stub
		this.addEventActivity = addEventActivity;
		
	}

	public  void fbShare(){
		ShareDialogBuilder builder = new ShareDialogBuilder(addEventActivity.getActivity())
		   	.setName("5odny M3ak events")
		    .setLink("https://www.facebook.com/5odnyMa3ak")
			.setPicture("https://scontent-a-ams.xx.fbcdn.net/hphotos-xfa1/t1.0-9/10393575_1416420081974633_8006832328250289632_n.png")
			.setDescription(EntityFactory.getUserInstance().getName()+"is now using 5odny M3ak Mobile Application");
		if (builder.canPresent()) {
			FacebookDialog dialog = builder.build();
			dialog.present();
		}
	}
	
	public void onPostExecute(String result) {
		
	
		System.out.println(result);
		
		
		addEventActivity.prog.dismiss();
		
		if(result.equals("No Connection") == false){
		
			
			JSONObject obj;
			try {
				
				obj = new JSONObject(result);
				
				if ( obj.getBoolean("HasError") == true ){
			
					AlertDialog alertDialog = new AlertDialog.Builder(
		                    addEventActivity.getActivity()).create();
					alertDialog.setMessage(obj.getString("FaultsMsg"));
					alertDialog.show();
					//Toast.makeText(addEventActivity.getActivity().getApplicationContext(), obj.getString("FaultsMsg"), Toast.LENGTH_LONG).show();	
					
				}else{
				
					addEventActivity.callSetEventAtCalendar();
					fbShare();
					UIManagerHandler.getoEventHome(addEventActivity.getActivity());
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else{
		//	Toast.makeText(addEventActivity.getActivity().getApplicationContext(), "No connection", Toast.LENGTH_LONG).show();
			UIManagerHandler.goToConnectionFailed(addEventActivity.getActivity());
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
