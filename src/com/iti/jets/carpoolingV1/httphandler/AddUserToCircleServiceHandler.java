package com.iti.jets.carpoolingV1.httphandler;

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

import android.os.AsyncTask;
import android.util.Log;

import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleController;
import com.iti.jets.carpoolingV1.synccontactsactivity.AddUserToCircleController;


public class AddUserToCircleServiceHandler {

	private String returnServiceOutput;
	private JSONObject circleUserObj;
	private JSONArray selectedUsersJS;
	public void connectToWebService(JSONArray selectedUsersJSArray, JSONObject circleUserObj ,String url) {
		// TODO Auto-generated method stub
		this.circleUserObj =  circleUserObj;
		this.selectedUsersJS = selectedUsersJSArray;
		AddUserToCircleWebserviceAsyncTask task = new AddUserToCircleWebserviceAsyncTask ();
		task.execute(url);
		
		
	}

	private class AddUserToCircleWebserviceAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
          

        	String url=urls[0];
		    DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);	
			nameValuePairs.add(new BasicNameValuePair("circleId",circleUserObj.toString()));
			nameValuePairs.add(new BasicNameValuePair("userIds",selectedUsersJS.toString()));
			try {	 		
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse= httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            returnServiceOutput = EntityUtils.toString(httpEntity);   
            Log.d(returnServiceOutput,"%%%%%%%%%%%%%%%returnService%%%%%%%%%%%%%%%%%%%");
			} catch (Exception e) {
				
				e.printStackTrace();
			}   
     
	  return   returnServiceOutput;
		   
        }

        @Override
        protected void onPostExecute(String result) {
              
        	Log.d("NULLLL",result);
        	
        	if(result != null)
        	{
        		AddUserToCircleController controller = new AddUserToCircleController();
            	      		
            	controller.getServiceData(result);
        		
        	}
        	
        	
        	
        }
      }	
}
