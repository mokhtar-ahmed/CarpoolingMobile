package com.iti.jets.carpoolingV1.retrievealleventcomments;

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

import com.iti.jets.carpoolingV1.addcomment.AddCommentController;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;


public class RetrieveEventCommentsServiceHandler {

	String url = HttpConstants.SERVER_URL + HttpConstants.RETRIEVE_ALL_EVENT_COMMENTS_URL;
	JSONObject eventIdJson;
	AddCommentController addCommentController;
	String  returnServiceOutput;
	public void connectToWebservice(AddCommentController addCommentController,
			int eventId) {
		this.addCommentController = addCommentController;
		eventIdJson = new JSONObject();
		try {
			eventIdJson.put("idEvent", eventId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebserviceAsyncTask task = new WebserviceAsyncTask ();
		task.execute(url);
	}

	private class WebserviceAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
          

        	String url=urls[0];
		    DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);	
			nameValuePairs.add(new BasicNameValuePair("input",eventIdJson.toString()));
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
              
        	System.out.println("Result"+"   "+result);
        	addCommentController.getAllEventComments(result);
 	
        }
      }		
}