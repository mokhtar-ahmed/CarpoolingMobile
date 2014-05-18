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

import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment.FragmentCallback;

import android.os.AsyncTask;
import android.util.Log;

public class RetrieveCirclesAsyncTask extends AsyncTask<String, Void, String>{
	
	 private FragmentCallback mFragmentCallback;
	 private int circleId;
	 private String returnServiceOutput;
	    public RetrieveCirclesAsyncTask(FragmentCallback fragmentCallback, int circleId) {
	        mFragmentCallback = fragmentCallback;
	        this.circleId = circleId;
	        
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		nameValuePairs.add(new BasicNameValuePair("circleIdJS", circleIdJS.toString()));
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
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		 mFragmentCallback.onTaskDone(result);
	}
}
