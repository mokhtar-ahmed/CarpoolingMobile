package com.iti.jets.carpoolingV1.addevent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.FragmentManager;
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
		
		
		
		if(result.equals("No Connection") == false){
		
			JSONObject obj;
			try {
				obj = new JSONObject(result);
				if ( obj.getBoolean("HasError") == true ){
					Toast.makeText(addEventActivity.getActivity().getApplicationContext(), obj.getString("FaultsMsg"), Toast.LENGTH_LONG).show();	
				}else{
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
			
			JSONObject js = new JSONObject(result);
			JSONArray usrArr = js.getJSONArray("ResponseValue");
		
			for(int i=0; i<usrArr.length();i++){
				
				JSONObject jobj = usrArr.getJSONObject(i);
				
				User u = new User();
				u.setId(jobj.getInt("id"));
				u.setName(jobj.getString("username"));
				
				addEventActivity.Users.add(u);
				
			}
			addEventActivity.showBlockUserDialog();
			addEventActivity.prog.dismiss();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
