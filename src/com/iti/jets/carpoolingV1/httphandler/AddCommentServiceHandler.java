package com.iti.jets.carpoolingV1.httphandler;


import java.text.DateFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;
import com.iti.jets.carpoolingV1.pojos.Comment;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
public class AddCommentServiceHandler {

	String url = null;
	String returnServiceOutput = null;
	String comment;
	
	public  AddCommentServiceHandler(String comment) {
	
		this.comment = comment;

		url = HttpConstants.SERVER_URL + HttpConstants.ADD_COMMENT_URL;
		
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
			nameValuePairs.add(new BasicNameValuePair("input",comment));
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
              
        	JSONObject resultJson ;
        	try {
				resultJson = new JSONObject(result);
				if(!resultJson.getBoolean("HasError"))
	        	{     		
	        		System.out.println("RESUUUUUUUUUUUUUUUUUULT"+ result);
//	        		RetrieveEventCommentsController controller = new RetrieveEventCommentsController();
//	        		controller.setArguments( addCommentController,eventId);
	        	}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
      }	


}
