package com.iti.jets.carpoolingV1.httphandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.addevent.AddEventController;
import com.iti.jets.carpoolingV1.eventDetails.AcceptedEventDetialsController;
import com.iti.jets.carpoolingV1.eventDetails.EventDetailsActivity;
import com.iti.jets.carpoolingV1.eventDetails.EventDetialsController;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LeaveEvent extends AsyncTask<String, Void, String> {
	
	String output;
	AcceptedEventDetialsController controller;
    
	String url = HttpConstants.SERVER_URL + HttpConstants.LEAVE_EVENT_SERVICE_URL;
	
	public LeaveEvent(AcceptedEventDetialsController controller){
	
		this.controller = controller;
	}
@Override
protected String doInBackground(String... params) {
	          
	     
	       try {
	    	   
	    	     
		    	   HttpPost httpPost = new HttpPost(url);
		    	   httpPost.setHeader("content-type", "application/json");
		    	   HttpParams httpParameters = new BasicHttpParams();
		    	   HttpConnectionParams.setConnectionTimeout(httpParameters, HttpConstants.timeoutConnection);
		    	   HttpConnectionParams.setSoTimeout(httpParameters,  HttpConstants.timeoutSocket);
				   System.out.println(params[0]);
				   StringEntity entity = new StringEntity(params[0], HTTP.UTF_8);
				   httpPost.setEntity(entity);
				   DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
				   BasicHttpResponse  response = (BasicHttpResponse)httpClient.execute(httpPost);
				   output= EntityUtils.toString( response.getEntity());
						
		    	
	        } catch (ClientProtocolException e) {
	        	output ="No Connection";
				e.printStackTrace();
			
			} catch (IOException e) {
				output ="No Connection";
				e.printStackTrace();
				
			}
	       
	       return output;
	 }

	        @Override
	        protected void onPostExecute(String result) {
	        
	        	controller.onLeavePostExecute(result);
	        }
}
	    
