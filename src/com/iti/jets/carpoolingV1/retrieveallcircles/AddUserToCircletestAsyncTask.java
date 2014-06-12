package com.iti.jets.carpoolingV1.retrieveallcircles;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.firstrun.CircleUsersFragment2;
import com.iti.jets.carpoolingV1.firstrun.CircleUsersFragment2.FragmentCallback2;
import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment.FragmentCallback;

import android.os.AsyncTask;
import android.util.Log;

public class AddUserToCircletestAsyncTask extends AsyncTask<String, Void, String>{
	
	 private FragmentCallback mFragmentCallback;
	 private int circleId;
	 ArrayList<User> usersList;
	 private String returnServiceOutput;
	 JSONObject tempObj = null;
     JSONArray usersListJSArray;
     boolean flag2 = false;
     CircleUsersFragment circleUsersFragment;
	private FragmentCallback2 mFragmentCallback2;
	private CircleUsersFragment2 circleUsersFragment2;
	    public AddUserToCircletestAsyncTask(CircleUsersFragment circleUsersFragment, FragmentCallback fragmentCallback, int circleId, ArrayList<User> circleActualUsersList) {
	    	flag2 = false;
	    	mFragmentCallback = fragmentCallback;
	        this.circleId = circleId;
	        usersList = circleActualUsersList;
	        usersListJSArray = new JSONArray();
	        User tempUser;
	        this.circleUsersFragment = circleUsersFragment;
	        for(int i=0;i<usersList.size();i++)
				try {
					{
						tempUser =  new User();
						try {
							tempUser = usersList.get(i);
							tempObj = new JSONObject();
							tempObj.put("userId",tempUser.getUserId());
							
							 usersListJSArray.put(tempObj);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }

	public AddUserToCircletestAsyncTask(
				CircleUsersFragment2 circleUsersFragment2,
				FragmentCallback2 fragmentCallback2, int circle_Id,
				ArrayList<User> circleActualUsersList) {
			// TODO Auto-generated constructor stub
		flag2 = true;
		mFragmentCallback2 = fragmentCallback2;
        this.circleId = circle_Id;
        usersList = circleActualUsersList;
        usersListJSArray = new JSONArray();
        User tempUser;
        this.circleUsersFragment2 = circleUsersFragment2;
        for(int i=0;i<usersList.size();i++)
			try {
				{
					tempUser =  new User();
					try {
						tempUser = usersList.get(i);
						tempObj = new JSONObject();
						tempObj.put("userId",tempUser.getUserId());
						
						 usersListJSArray.put(tempObj);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		String url=arg0[0];
	    DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);	
        JSONObject circleIdJS = new JSONObject();
        try {
			circleIdJS.put("circleId",circleId);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		nameValuePairs.add(new BasicNameValuePair("circleId", circleIdJS.toString()));
		nameValuePairs.add(new BasicNameValuePair("userIds", usersListJSArray.toString()));
		//Toast.makeText(controller.getRef(), "ASYNCTASK", Toast.LENGTH_LONG).show();
		try {	 		
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse httpResponse= httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        returnServiceOutput = EntityUtils.toString(httpEntity);   
        Log.d(returnServiceOutput,"%%%%%%%%%%%%%%%returnService%%%%%%%%%%%%%%%%%%%");
		} catch (Exception e) {
			
			e.printStackTrace();
		}   
		return returnServiceOutput;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		  Log.d(result,"%%%%%%%%%%%%%%%returnService%%%%%%%%%%%%%%%%%%%");
		  if(flag2)
		  {
			  circleUsersFragment2.dialog.dismiss();
				 mFragmentCallback2.onTaskDone(result);
		  }
		  else
		  {
			  circleUsersFragment.dialog.dismiss();
				 mFragmentCallback.onTaskDone(result);
		  }
		  
	}
}
