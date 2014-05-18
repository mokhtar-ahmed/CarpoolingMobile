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
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;



public class CircleUsersHandler {

	private JSONObject circleNameJsObj,userIdJsObj;
	private String webserviceURI;
	private String returnServiceOutput;
	private CircleUsersFragment circleUsersActivityObj;
	private String selectedCircleName;
	
	public CircleUsersHandler() {
		// TODO Auto-generated constructor stub
	}

	public void connectToWebService(String selectedCircleName,int userId,CircleUsersFragment circleUsersActivityObj, String url) {
		// TODO Auto-generated method stub
		
		this.selectedCircleName = selectedCircleName;
		circleNameJsObj = new JSONObject();
		userIdJsObj = new JSONObject();
		
		try {
			circleNameJsObj.put("circleName", selectedCircleName);
			userIdJsObj.put("userId", userId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		webserviceURI = url;
		this.circleUsersActivityObj = circleUsersActivityObj;	

		WebserviceAsyncTask task = new WebserviceAsyncTask();
		task.execute(webserviceURI);
		
	}

	
	private class WebserviceAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
          

        	String url=urls[0];
		    DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);	
            Log.d("RRRRRRRRRRRRRRRR",circleNameJsObj.toString());
            Log.d("RRRRRRRRRR222222",userIdJsObj.toString());
			nameValuePairs.add(new BasicNameValuePair("circleName",circleNameJsObj.toString()));
			nameValuePairs.add(new BasicNameValuePair("userId",userIdJsObj.toString()));
			
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
        	try {
				JSONArray circleUsers = new JSONArray(result);
				if(circleUsers.length() == 0)
				{
					System.out.println("&&&&&&&&&&&&&&EMPTYUSERSARRAY&&&&&&&&&&&&&&&&&");
				}
				else
				{
					System.out.println("&&&&&&&&&&&&&&FILLEEEEEED&&&&&&&&&&&&&&&&&");
				}
////				Intent intent = new Intent(circleUsersActivityObj.getApplicationContext(),CircleUsersActivity.class);
//				intent.putExtra("circleUsers", result);
//				intent.putExtra("flag", "resultFlag"); 
//				intent.putExtra("circleName", selectedCircleName);
//				circleUsersActivityObj.startActivity(intent);
				//circleUsersActivityObj.getResultFromWebservice(circleUsers);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
//        	try {
//				userCirclesJs = new JSONArray(result);
//				System.out.println("JSARRAYSIZE"+"              "+userCirclesJs.length());
//				int circleId = 0;
//				String circleName = null;
//				for(int i=0;i<userCirclesJs.length();i++)
//				{		
//					System.out.println("Entered"+"  "+i);
//					circleJs = userCirclesJs.getJSONObject(i);
//					circleId = circleJs.getInt("circleId");
//					circleName = circleJs.getString("circleName");
//					System.out.println("   "+circleId+"   "+circleName);
//					if(!userCirclesMap.containsKey(circleId))
//					{
//						System.out.println("%%%%%%%%%ENTERED%%%%%%%%");
//						userCirclesMap.put(circleId, circleName);
//					
//					}
//				}
//				System.out.println("SizeofMap"+"       "+userCirclesMap.size());
//				circlesListRefrence.getUserCircles(userCirclesMap);
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
        	
        	
        	
        }
      }	
}
