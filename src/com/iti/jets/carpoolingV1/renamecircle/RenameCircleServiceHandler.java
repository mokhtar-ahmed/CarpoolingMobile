package com.iti.jets.carpoolingV1.renamecircle;

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

import com.iti.jets.carpoolingV1.common.Circle2;
import com.iti.jets.carpoolingV1.firstrun.AllCirclesListFragment2;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.RetrieveAllCirclesListController;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

public class RenameCircleServiceHandler {
	
	AllCirclesListFragment allCirclesListFragment;
	String url;
	String returnServiceOutput,newCircleName;
	Circle2 circleValues;
	boolean flag2 = false;
	private AllCirclesListFragment2 allCirclesListFragment2;
	
	public void connectToWebService(String newCircleName,
			Circle2 circleValues, AllCirclesListFragment allCirclesListFragment) {
		// TODO Auto-generated method stub
		url = HttpConstants.SERVER_URL + HttpConstants.UPDATE_CIRCLE_URL ;
		this.circleValues = circleValues;
		this.newCircleName = newCircleName;
		flag2 = false;
		this.allCirclesListFragment = allCirclesListFragment;
		WebserviceAsyncTask task = new WebserviceAsyncTask();
		task.execute(url);
		
	}
	public void connectToWebService(String newCircleName,
			Circle2 circleValues, AllCirclesListFragment2 allCirclesListFragment) {
		// TODO Auto-generated method stub
		url = HttpConstants.SERVER_URL + HttpConstants.UPDATE_CIRCLE_URL ;
		this.circleValues = circleValues;
		flag2 = true;
		this.newCircleName = newCircleName;
		this.allCirclesListFragment2 = allCirclesListFragment;
		WebserviceAsyncTask task = new WebserviceAsyncTask();
		task.execute(url);
		
	}
	private class WebserviceAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
        	String url=urls[0];
    	    DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);	
            JSONObject circleIdJS = new JSONObject();
            try {
            	
    			circleIdJS.put("circleId",circleValues.getCircleId());
    			circleIdJS.put("userId",EntityFactory.getUserInstance().getId());
    			circleIdJS.put("circleName", newCircleName);
    		} catch (JSONException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		nameValuePairs.add(new BasicNameValuePair("circle", circleIdJS.toString()));
    		try {	 		
    		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse= httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            returnServiceOutput = EntityUtils.toString(httpEntity);   
            Log.d(returnServiceOutput,"%%%%%%%%%%%%%%%returnService%%%%%%%%%%%%%%%%%%%");
    		} catch (Exception e) {
    			
    			e.printStackTrace();
    		}   
    		return returnServiceOutput;  

        }

        @Override
        protected void onPostExecute(String result) {
              
          super.onPostExecute(result);
  		  Log.d(result,"%%%%%%%%%%%%%%%returnService%%%%%%%%%%%%%%%%%%%");
  		  if(flag2)
  		  {
  			 RetrieveAllCirclesListController controller = new RetrieveAllCirclesListController(EntityFactory.getUserInstance().getId(),allCirclesListFragment2);
  			allCirclesListFragment2.adapter.notifyDataSetChanged();
  		  }
  		  else
  		  {
  			 RetrieveAllCirclesListController controller = new RetrieveAllCirclesListController(EntityFactory.getUserInstance().getId(),allCirclesListFragment);
  	  		allCirclesListFragment.adapter.notifyDataSetChanged();
  		  }
  		
  		 //UIManagerHandler.goToAllCirclesList(allCirclesListFragment.getActivity());
  		 
 	
        }
      }
	
}
