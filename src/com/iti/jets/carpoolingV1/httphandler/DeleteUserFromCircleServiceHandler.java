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
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.firstrun.CircleUsersFragment2.FragmentCallback2;
import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment.FragmentCallback;

public class DeleteUserFromCircleServiceHandler {

	String returnServiceOutput;	
	String Url = null;	
	JSONArray userToDelJsArr = new JSONArray();
	int circleId;
	boolean flag2 = false;
	FragmentCallback fragmentCallback;
	private FragmentCallback2 fragmentCallback2;
	public void connectToWebService(int circleId, JSONArray userToDelJsArr,
			FragmentCallback fragmentCallback, String uri) {
		// TODO Auto-generated method stub
		this.userToDelJsArr = userToDelJsArr;
		this.fragmentCallback = fragmentCallback;
		Url = uri;
		flag2 = false;
		this.circleId = circleId;
		WebserviceAsyncTask task = new WebserviceAsyncTask();
		task.execute(Url);
	}
	
	public void connectToWebService(int circleId2, JSONArray userToDelJsArr2,
			FragmentCallback2 fragmentCallback2, String uri) {
		// TODO Auto-generated method stub
		flag2 = true;
		this.userToDelJsArr = userToDelJsArr;
		this.fragmentCallback2 = fragmentCallback2;
		Url = uri;
		this.circleId = circleId;
		WebserviceAsyncTask task = new WebserviceAsyncTask();
		task.execute(Url);
		
	}
	
	private class WebserviceAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
        	String url=urls[0];
    	    DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);	
            JSONObject existInJS = new JSONObject();
            try {
            	
            	 existInJS.put("circleId",circleId);
            	 existInJS.put("userToDelJsArr",userToDelJsArr);
    		} catch (JSONException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		nameValuePairs.add(new BasicNameValuePair("exist", existInJS.toString()));
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
              
          super.onPostExecute(result);
  		  Log.d(result,"%%%%%%%%%%%%%%%returnService%%%%%%%%%%%%%%%%%%%");
  		  if(flag2)
  		  {
  			fragmentCallback2.onTaskDone(result);
  		  }
  		  else
  		  {
  			fragmentCallback.onTaskDone(result);
  		  }
  		  
 	
        }
      }



}
