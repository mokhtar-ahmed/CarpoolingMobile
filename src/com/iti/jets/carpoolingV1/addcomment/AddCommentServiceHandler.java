package com.iti.jets.carpoolingV1.addcomment;

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
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.retrievealleventcomments.RetrieveEventCommentsController;
public class AddCommentServiceHandler {

	String url = null;
	String returnServiceOutput = null;
	JSONObject commentJson,owner,event;
	AddCommentController addCommentController;
	int eventId = 1;
	public void connectToWebService(AddCommentController addCommentController, String comment) {
		// TODO Auto-generated method stub
	
		
		this.addCommentController = addCommentController;
		url = HttpConstants.SERVER_URL + HttpConstants.ADD_COMMENT_URL;
		commentJson = new JSONObject();
		owner = new JSONObject();
		event = new JSONObject();
		int id = EntityFactory.getUserInstance().getId();
		try {
			owner.put("id", id);
			event.put("id", eventId);
			commentJson.put("owner",owner);
			commentJson.put("event",event);
			commentJson.put("text", comment);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    //get current date time with Date()
			Date date = new Date();
			System.out.println(dateFormat.format(date));
		 
			//get current date time with Calendar()
			Calendar cal = Calendar.getInstance();
			System.out.println(dateFormat.format(cal.getTime()));
			commentJson.put("date",dateFormat.format(date));
			   
			WebserviceAsyncTask task = new WebserviceAsyncTask ();
			task.execute(url);
			   
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	private class WebserviceAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
          

        	String url=urls[0];
		    DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);	
			nameValuePairs.add(new BasicNameValuePair("input",commentJson.toString()));
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
	        		RetrieveEventCommentsController controller = new RetrieveEventCommentsController();
	        		controller.setArguments( addCommentController,eventId);
	        	}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
 	
        }
      }	

}
