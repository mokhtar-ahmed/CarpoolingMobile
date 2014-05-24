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
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.editprofileactivity.EditProfileActivity;
import com.iti.jets.carpoolingV1.editprofileactivity.GoToEditProfileActivity;
import com.iti.jets.carpoolingV1.editprofileactivity.RetrieveUserController;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;


public class RetrieveUserServiceHandler {

	JSONObject userToRetrieve;
	String  returnServiceOutput;
	RetrieveUserController retrieveUserController;
	int userId = 0;
	public RetrieveUserServiceHandler(RetrieveUserController retrieveUserController, int userId) {
		// TODO Auto-generated constructor stub
	    userToRetrieve = new JSONObject();
	    try {
			userToRetrieve.put("userId", userId);
			this.retrieveUserController = retrieveUserController;
			this.userId = userId;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebserviceAsyncTask task = new WebserviceAsyncTask();
		task.execute( HttpConstants.SERVER_URL+ HttpConstants.Edit_Profile_URL);
	
	}

	private class WebserviceAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
          
        	String url=urls[0];
		    DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);	
//            Log.d("RRRRRRRRRRRRRRRR",userToLoginJS.toString());
            int id = 13;
            JSONObject userToLoginJS ;
            try {
            	userToLoginJS = new JSONObject();
				userToLoginJS.put("userId",userId);
				nameValuePairs.add(new BasicNameValuePair("userToLoginJS",userToLoginJS.toString()));
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
			try {	 		
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse= httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            returnServiceOutput = EntityUtils.toString(httpEntity);   
           // Log.d(returnServiceOutput,"%%%%%%%%%%%%%%%returnService%%%%%%%%%%%%%%%%%%%");
			} catch (Exception e) {
				
				e.printStackTrace();
			}   
     
	  return  returnServiceOutput;
		   
        }

        @Override
        protected void onPostExecute(String result) {
              
        	retrieveUserController.getResultFromWebService(result);	
        
        }
      }	
}
