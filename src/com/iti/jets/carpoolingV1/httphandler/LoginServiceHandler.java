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

import com.iti.jets.carpoolingV1.firstrun.AllCirclesListFragment2;
import com.iti.jets.carpoolingV1.loginactivity.LoginController;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

import android.app.Activity;
import android.os.AsyncTask;

public class LoginServiceHandler extends AsyncTask<String, Void, String> {

	
	private String output;
	private String url = HttpConstants.SERVER_URL + HttpConstants.LOGIN_URL;
	LoginController controller = null;
	Activity ac1;
	
	public LoginServiceHandler(LoginController controller){
		
		this.controller = controller;
		
	}
	public LoginServiceHandler(Activity ac){
		
		ac1=ac;
		
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
			if(controller == null)
			{
				//controller = new LoginController();
				UIManagerHandler.goToHome(ac1);
			}
			else
			{
				controller.onPostExcuteResult(result);
			}
			
		}
	}
}
