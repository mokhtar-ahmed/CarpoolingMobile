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
import org.json.JSONObject;
import android.os.AsyncTask;
import android.util.Log;

import com.iti.jets.carpoolingV1.editprofileactivity.EditProfileController;
import com.iti.jets.carpoolingV1.synccontactsactivity.SyncContactsController;



public class EditProfileServiceHandler {

	private String returnServiceOutput;
	private JSONObject userDataObj, imgJsonObject;
	public void connectToWebService(JSONObject userDataObj,
			JSONObject imgJsonObj, String uri) {
		
		this.userDataObj = userDataObj;
		this.imgJsonObject = imgJsonObj;
		EditProfileWebserviceAsyncTask task = new EditProfileWebserviceAsyncTask ();
		task.execute(uri);
		
	}

	private class EditProfileWebserviceAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
          

        	String url=urls[0];
		    DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);	
			nameValuePairs.add(new BasicNameValuePair("userDataObj",userDataObj.toString()));
			nameValuePairs.add(new BasicNameValuePair("imagJsObj",imgJsonObject.toString()));
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
              
        	EditProfileController controller = new EditProfileController();
        	
        	controller.getServiceData(result);
        	
        	
        }
      }	
}
