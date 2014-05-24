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

import android.os.AsyncTask;
import android.util.Log;

import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleController;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;



public class AddCircleServiceHandler {

	AddCircleController addCircleController;
	public AddCircleServiceHandler(AddCircleController addCircleController)
	{
		 this.addCircleController =  addCircleController;
	}
	
	private String returnServiceOutput;
	private JSONObject circleDataObj, imgJsonObject;
	public void connectToWebService(JSONObject circleDataObj,
			JSONObject imgJsonObj, String uri) {
		// TODO Auto-generated method stub
		this.circleDataObj = circleDataObj;
		try {
			int userId = EntityFactory.getUserInstance().getId();
			circleDataObj.put("userId", userId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.imgJsonObject = imgJsonObj;
		AddCircleWebserviceAsyncTask task = new AddCircleWebserviceAsyncTask ();
		task.execute(uri);
		
		
	}

	private class AddCircleWebserviceAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
          

        	String url=urls[0];
		    DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);	
			nameValuePairs.add(new BasicNameValuePair("circleDataObj",circleDataObj.toString()));
			//nameValuePairs.add(new BasicNameValuePair("imagJsObj",imgJsonObject.toString()));
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
        		//AddCircleController controller = new AddCircleController();
            	      		
        		addCircleController.getServiceData(result);
        		
        	}
        	
        	
        	
        }
      }	
}

