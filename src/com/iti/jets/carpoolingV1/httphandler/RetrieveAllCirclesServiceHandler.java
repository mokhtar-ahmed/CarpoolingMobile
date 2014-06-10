package com.iti.jets.carpoolingV1.httphandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.iti.jets.carpoolingV1.firstrun.AllCirclesListFragment2;
import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment;
import com.iti.jets.carpoolingV1.synccontactsactivity.SyncContactsController;

public class RetrieveAllCirclesServiceHandler {

	private HttpConstants httpContsantshandler;
	private String webserviceURI,returnServiceOutput ;
	private JSONObject jsObj,circleJs;
	private int userId;
	private JSONArray userCirclesJs ;
	private AllCirclesListFragment circlesListRefrence;
	private AllCirclesListFragment2 circlesListRefrence2;
	boolean flag2 = true;
	
	public RetrieveAllCirclesServiceHandler ()
	{
		
		
		
	}
	
	public void connectToWebService(int userId,AllCirclesListFragment circlesListRefrence, String url)
	{
		flag2=false;
		this.userId = userId;
		this.circlesListRefrence = circlesListRefrence;
		webserviceURI = url;
		circleJs = new JSONObject();
		Log.d("URL",webserviceURI);
		jsObj = new JSONObject();
		try {
			jsObj.put("userId", userId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebserviceAsyncTask task = new WebserviceAsyncTask();
		task.execute(webserviceURI);
		circlesListRefrence.dialog = ProgressDialog.show(circlesListRefrence.getActivity(), "", "Loading...Please wait...", true);
		circlesListRefrence.dialog.show();
		
	}
	public void connectToWebService(int userId,AllCirclesListFragment2 circlesListRefrence, String url)
	{
		
		flag2=true;
		this.userId = userId;
		this.circlesListRefrence2 = circlesListRefrence;
		webserviceURI = url;
		circleJs = new JSONObject();
		Log.d("URL",webserviceURI);
		jsObj = new JSONObject();
		try {
			jsObj.put("userId", userId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebserviceAsyncTask task = new WebserviceAsyncTask();
		task.execute(webserviceURI);
		circlesListRefrence2.dialog = ProgressDialog.show(circlesListRefrence2.getActivity(), "", "Loading...Please wait...", true);
		circlesListRefrence2.dialog.show();
		
	}
	private class WebserviceAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
          

        	String url=urls[0];
		    DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);	
            Log.d("RRRRRRRRRRRRRRRR",jsObj.toString());
			nameValuePairs.add(new BasicNameValuePair("userId",jsObj.toString()));
			
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
        	if(flag2)
        	{
        		circlesListRefrence2.dialog.dismiss();
    		    circlesListRefrence2.getUserCircles(result);
        	}
        	else
        	{
        		circlesListRefrence.dialog.dismiss();
    		    circlesListRefrence.getUserCircles(result);
        	}
        	
			
        	
        	
        	
        }
      }	
}
