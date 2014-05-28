package com.iti.jets.carpoolingV1.httphandler;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.jsonhandler.JsonConstants;
import com.iti.jets.carpoolingV1.jsonhandler.JsonConverter;
import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.loginactivity.LoginController;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.pojos.Location;

import android.os.AsyncTask;
import android.widget.Toast;

public class RetriveAllLocationServiceHandler extends AsyncTask<String, Void, String> {

	
	private String output;
	private String url = HttpConstants.SERVER_URL + HttpConstants.RETRIVE_ALL_LOCATIONS_SERVICE_URL;

	
	public RetriveAllLocationServiceHandler(){
		
		
	}
	@Override
	protected String doInBackground(String... params) {
		          
		     
		       try {
		    	
		    	   HttpPost httpPost = new HttpPost(url);
		    	   httpPost.setHeader("content-type", "application/json");
		    	   HttpParams httpParameters = new BasicHttpParams();
		    	   HttpConnectionParams.setConnectionTimeout(httpParameters, HttpConstants.timeoutConnection);
		    	   HttpConnectionParams.setSoTimeout(httpParameters,  HttpConstants.timeoutSocket);
		    	   DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
		    	   BasicHttpResponse  response = (BasicHttpResponse)httpClient.execute(httpPost);
		           output= EntityUtils.toString( response.getEntity());
		           
		       }catch(ConnectTimeoutException ex){
		    	  output = "No Connection";
		       }
		       catch(SocketTimeoutException exx ){
		    	   output = "No Connection"; 
		       }catch (ClientProtocolException e) {
		    	   output ="No Connection";
					e.printStackTrace();
			   } catch (IOException e) {
					e.printStackTrace();
			   }
		       return output;
		        }

	@Override
	protected void onPostExecute(String result) {
	
		System.out.println(result +"at location retrive");
		
		if(result != null){
		
			if(result.equals("No Connection") == false){
			
				
				try {
			
					JSONObject returnedJson = new JSONObject(result);
					
					if(returnedJson.getBoolean("HasError")){
						
						
						
					}
					else {
						
						JSONArray locationsJson = returnedJson.getJSONArray("ResponseValue");
						
						ArrayList<Location> locs = new ArrayList<Location>();
					
						for(int i =0 ; i<locationsJson.length(); i++){
							Location loc = JsonParser.parseToLocation(locationsJson.getJSONObject(i));
							System.out.println(loc.getId());
							
							if(loc != null)
								locs.add(loc);
						}
						EntityFactory.setLocationsInstance(locs);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
	}
}
