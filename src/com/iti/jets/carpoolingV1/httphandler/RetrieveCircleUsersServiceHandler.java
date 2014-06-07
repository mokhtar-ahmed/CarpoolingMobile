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

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment.FragmentCallback;


public class RetrieveCircleUsersServiceHandler {

	JSONObject circleIdJs;
	FragmentCallback fragment_CallBack;
	AllCirclesListFragment allCirclesListFragment;
	String Url, returnServiceOutput;
	public void connectToWebService(int circleId,
			FragmentCallback fragmentCallback2, String uri, AllCirclesListFragment allCirclesListFragment) {
		// TODO Auto-generated method stub
		circleIdJs = new JSONObject();
		try {
			circleIdJs.put("circleId", circleId);
			this.allCirclesListFragment = allCirclesListFragment;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fragment_CallBack= fragmentCallback2;
		Url = uri;
		WebserviceAsyncTask task = new WebserviceAsyncTask();
		task.execute(Url);
//		allCirclesListFragment.dialog2 = ProgressDialog.show(allCirclesListFragment.getActivity(), "", "Loading...Please wait...", true);
//		allCirclesListFragment.dialog2.show();
	}

	private class WebserviceAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
        	String url=urls[0];
    	    DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);	
            
    		nameValuePairs.add(new BasicNameValuePair("circleId", circleIdJs.toString()));
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
  		  allCirclesListFragment.dialog2.dismiss();
  		  fragment_CallBack.onTaskDone(result);
 	
        }
      }	
}
