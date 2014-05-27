package com.iti.jets.carpoolingV1.httphandler;

import java.text.SimpleDateFormat;
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
import android.widget.Toast;

import com.iti.jets.carpoolingV1.pojos.User;
import com.iti.jets.carpoolingV1.registrationactivity.RegisterFragment;
import com.iti.jets.carpoolingV1.registrationactivity.RegisterationController;


public class RegisterationServiceHandler {

	RegisterationController regControlller;
	JSONObject user ;
	String returnServiceOutput;
	RegisterationController controller;
	public RegisterationServiceHandler(RegisterationController controller)
	{
		regControlller = controller;
		Toast.makeText(controller.getRef(), "EnteredSERVICEHANDLER", Toast.LENGTH_LONG).show();
		this.controller = controller;
	}

	public void connectToWebService(User newUser, String url, String imageString) {
		// TODO Auto-generated method stub
		
		try {
			user= new JSONObject();
			user.put("name",newUser.getName());
			user.put("username", newUser.getName());
			user.put("phone",newUser.getPhone());
			user.put("gender", newUser.getGender());
			user.put("password",newUser.getPassword()); 
			user.put("mail", newUser.getEmail());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = formatter.format(newUser.getDateOfBirth());
			System.out.println("selected date is  " + dateStr);
			user.put("date", dateStr);
			user.put("image", imageString);
			user.put("pushnotificationId", newUser.getPushNotificationId());
			//userToRegisterJS.put("image", "photo");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String webserviceURI = url;
		Toast.makeText(controller.getRef(), url, Toast.LENGTH_LONG).show();
		WebserviceAsyncTask task = new WebserviceAsyncTask();
		task.execute(webserviceURI);
		controller.registerActivity.dialog = ProgressDialog.show(controller.registerActivity.getActivity(), "", "Loading...Please wait...", true);
		controller.registerActivity.dialog.show();
		
	}
	
	private class WebserviceAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
          

        	String url=urls[0];
		    DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);	
            Log.d("RRRRRRRRRRRRRRRR",user.toString());
			nameValuePairs.add(new BasicNameValuePair("user",user.toString()));
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
     
	  return  returnServiceOutput;
		   
        }

        @Override
        protected void onPostExecute(String result) {
              
        	System.out.println("%%%%%%%%%%%%%RESULT"+"  "+result+"%%%%%%%%%%%%%");
        	controller.registerActivity.dialog.dismiss();
        	regControlller.getResultFromWebservice(result);
		    
			
        	
        	
        	
        }
      }

		
}
