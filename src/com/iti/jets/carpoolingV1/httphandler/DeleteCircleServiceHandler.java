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

import com.iti.jets.carpoolingV1.pojos.Circle;
import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment.FragmentCallback;
import android.os.AsyncTask;
import android.util.Log;
import com.iti.jets.carpoolingV1.common.Circle2;

public class DeleteCircleServiceHandler {

   String returnServiceOutput;	
   String Url = null;	
   Circle2 circleObj;
   FragmentCallback fragmentCallback;
	public void connectToWebService(Circle2 circleObj2,FragmentCallback fragmentCallback2, String uri) {
		// TODO Auto-generated method stub
		this.circleObj = circleObj2;
		this.fragmentCallback = fragmentCallback2;
		Url = uri;
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
            JSONObject circleIdJS = new JSONObject();
            try {
            	int circleId = circleObj.getCircleId();
    			circleIdJS.put("circleId",circleId);
    		} catch (JSONException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		nameValuePairs.add(new BasicNameValuePair("circleId", circleIdJS.toString()));
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
  
  		  fragmentCallback.onTaskDone(result);
 	
        }
      }
	
}
