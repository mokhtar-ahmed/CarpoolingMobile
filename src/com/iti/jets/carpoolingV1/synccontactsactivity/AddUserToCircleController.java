package com.iti.jets.carpoolingV1.synccontactsactivity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleFragment;
import com.iti.jets.carpoolingV1.common.ImageHandler;
import com.iti.jets.carpoolingV1.common.ImageLoadingUtils;
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.httphandler.AddCircleServiceHandler;
import com.iti.jets.carpoolingV1.httphandler.AddUserToCircleServiceHandler;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;

import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.CirclesCustomArrayAdapter;
import com.iti.jets.carpoolingV1.sharedlayout.MainActivity;

public class AddUserToCircleController {

	private ImageLoadingUtils utils;
	
	private SyncContactsFragment addUserToCircActObj;
		
	private AddUserToCircleServiceHandler addUserToCircleHanlerObject;
	private String uri = HttpConstants.SERVER_URL + HttpConstants.ADD_USERTO_CIRCLE_SERVICE_URL;
	
	public AddUserToCircleController() {
		// TODO Auto-generated constructor stub
		addUserToCircActObj = new SyncContactsFragment();
		
	}
	public AddUserToCircleController(SyncContactsFragment syncContactsActivity)
	{
		addUserToCircActObj = syncContactsActivity;
		
	}
	
	public void setArguments(ArrayList<User> selectedUser, int circleId) {
		// TODO Auto-generated method stub
		JSONObject circleUserObjJS = new JSONObject();
		JSONArray selectedUsersJSArray = new JSONArray();
		int userId = 0; 
		for(int i=0;i<selectedUser.size();i++)
		{
			User tempUser = selectedUser.get(i);
			userId = tempUser.getUserId();
			
			JSONObject temp = new JSONObject();
			try {
				temp.put("userId", userId);
				System.out.println("ID"+userId);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			selectedUsersJSArray.put(temp);
		}
		try {
			
			circleUserObjJS.put("circleId", circleId);
			addUserToCircleHanlerObject = new AddUserToCircleServiceHandler();
			addUserToCircleHanlerObject.connectToWebService(selectedUsersJSArray,circleUserObjJS,uri);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

		
		
	
	
	public void getServiceData(String result) {
		// TODO Auto-generated method stub
		Log.d("CONT",result);
		if((result != null))
		{
			Log.d("CONTENTERED",result);
//			JSONArray circleUsersJsArray;
//			User tempUser;
//			JSONObject userTempJS;
//			ArrayList<User> circleActualUsersList; 
//			try {
//				 circleUsersJsArray = new JSONArray(result);
//				circleActualUsersList = new ArrayList<User>();
//				userTempJS = new JSONObject();
//				tempUser = new User();
//				
//				for(int i=0;i<circleUsersJsArray.length();i++)
//				{
//					userTempJS = circleUsersJsArray.getJSONObject(i);
//					tempUser.setUserId(userTempJS.getInt("userId"));
//					tempUser.setName(userTempJS.getString("Name"));
//					tempUser.setImageURL(userTempJS.getString("Image"));
//					circleActualUsersList.add(tempUser);
//				}
			

				Fragment fragment = new AllCirclesListFragment();
				Bundle args = new Bundle();
			    args.putString("result", result);
			    args.putBoolean("flag", true);
			    fragment.setArguments(args);
			    MainActivity activ = new MainActivity();
				FragmentManager fragmentManager = activ.getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, fragment).commit();

				
			//addCircleActObj.getServiceData(result);
//			Fragment fragment = new CircleUsersFragment();
//			//fragment.se
//			Bundle args = new Bundle();
//		    args.putInt("circle_Id",circle_Id);
//		    args.putInt("user_Id",  userId);
//		    args.putString("selectedCircleName", selectedCircleName );
//		    fragment.setArguments(args);
//			FragmentManager fragmentManager = getFragmentManager();
//			fragmentManager.beginTransaction()
//					.replace(R.id.frame_container, fragment).commit();
			/*Resources res =getResources();
	        list = ( ListView )   rootView.findViewById( R.id.list );   
	        /**************** Create Custom Adapter *********/
	        /*adapter=new CirclesCustomArrayAdapter(this,userCirclesList,res);
	        list.setAdapter(adapter);*/
			
		}
		else
		{
			Log.d("CONTEXIT",result);
		}
	}
	

}
