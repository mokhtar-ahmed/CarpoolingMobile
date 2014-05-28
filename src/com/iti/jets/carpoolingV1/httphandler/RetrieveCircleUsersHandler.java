package com.iti.jets.carpoolingV1.httphandler;

import java.io.IOException;
import java.net.SocketTimeoutException;
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

import com.iti.jets.carpoolingV1.addevent.AddEventController;

import android.os.AsyncTask;

public class RetrieveCircleUsersHandler extends AsyncTask<String, Void, String> {

	
	private String output;
	private String url = HttpConstants.SERVER_URL + HttpConstants.GET_CIRCLE_USERS_URL;
	
	AddEventController controller ;
	
	public RetrieveCircleUsersHandler(AddEventController controller){
		
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
	
		if(result != null){
			controller.setCirclesUsers(result);
		}
	}
}
