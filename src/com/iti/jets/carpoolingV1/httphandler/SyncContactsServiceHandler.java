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
import org.json.JSONArray;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.firstrun.SyncContactsFragment2;
import com.iti.jets.carpoolingV1.synccontactsactivity.SyncContactsFragment;
import com.iti.jets.carpoolingV1.synccontactsactivity.SyncContactsController;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;




public class SyncContactsServiceHandler {
	
	
	private String webserviceURI;
	private JSONArray jsArray;
	private String returnService;
	private SyncContactsFragment syncContactsActivity;
	private SyncContactsFragment2 syncContactsActivity22;
	boolean flag2 = false;
	public SyncContactsServiceHandler()
	{
		
		
			
	}
	
	public void connectToWebService(JSONArray contactListJSArray,
			SyncContactsFragment2 syncContactsActivity2, String url) {
		// TODO Auto-generated method stub
		webserviceURI = url;
		flag2 = true;
		this.jsArray = contactListJSArray;
		SyncContactsWebserviceAsyncTask task = new SyncContactsWebserviceAsyncTask();
		task.execute(webserviceURI);
		this.syncContactsActivity22  = syncContactsActivity2;
		syncContactsActivity22.dialog = ProgressDialog.show(syncContactsActivity22.getActivity(), "", "Loading...Please wait...", true);
		syncContactsActivity22.dialog.show();
		
		
	}
	
	public void connectToWebService(JSONArray array,SyncContactsFragment syncContactsActivity, String url)
	{
		flag2 = false;
		webserviceURI = url;
		this.jsArray = array;
		SyncContactsWebserviceAsyncTask task = new SyncContactsWebserviceAsyncTask();
		task.execute(webserviceURI);
		
		syncContactsActivity.dialog = ProgressDialog.show(syncContactsActivity.getActivity(), "", "Loading...Please wait...", true);
		syncContactsActivity.dialog.show();
		this.syncContactsActivity  = syncContactsActivity;
		
		
	}
	
	private class SyncContactsWebserviceAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
          

        	String url=urls[0];
		    DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);	
			nameValuePairs.add(new BasicNameValuePair("contactList",jsArray.toString()));
			
			try {	 		
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse= httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            returnService = EntityUtils.toString(httpEntity);   
            Log.d(returnService,"%%%%%%%%%%%%%%%returnService%%%%%%%%%%%%%%%%%%%");
			} catch (Exception e) {
				
				e.printStackTrace();
			}   
     
	  return   returnService;
		   
        }

        @Override
        protected void onPostExecute(String result) {
              
        	if(flag2)
        	{
            	syncContactsActivity22.dialog.dismiss();
            	SyncContactsController controller = new SyncContactsController();
//            	Toast.makeText(syncContactsActivity.getActivity().getApplicationContext(), "FROM SERVICE HANDLER", Toast.LENGTH_LONG).show();
//            	Toast.makeText(syncContactsActivity.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
            	syncContactsActivity22.getResultFromService(result);
        	}
        	else
        	{
            	syncContactsActivity.dialog.dismiss();
            	SyncContactsController controller = new SyncContactsController();
//            	Toast.makeText(syncContactsActivity.getActivity().getApplicationContext(), "FROM SERVICE HANDLER", Toast.LENGTH_LONG).show();
//            	Toast.makeText(syncContactsActivity.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
            	syncContactsActivity.getResultFromService(result);
        	}

        	
        	
        	
        }
      }

	
	
}
