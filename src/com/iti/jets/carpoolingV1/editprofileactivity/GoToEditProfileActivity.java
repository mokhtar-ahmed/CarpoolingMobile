package com.iti.jets.carpoolingV1.editprofileactivity;

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

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.R.layout;
import com.iti.jets.carpoolingV1.R.menu;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GoToEditProfileActivity extends Activity {

	Button go;
	String returnServiceOutput;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_go_to_edit_profile);
		go = (Button) findViewById(R.id.go);
		go.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				WebserviceAsyncTask task = new WebserviceAsyncTask();
				task.execute( HttpConstants.SERVER_URL+ HttpConstants.Edit_Profile_URL);
				Toast.makeText(getApplicationContext(),HttpConstants.SERVER_URL+ HttpConstants.Edit_Profile_URL,Toast.LENGTH_SHORT).show();		
			}
		});
	}

	private class WebserviceAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
          
        	String url=urls[0];
		    DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);	
//            Log.d("RRRRRRRRRRRRRRRR",userToLoginJS.toString());
            int id = 13;
            JSONObject userToLoginJS ;
            try {
            	userToLoginJS = new JSONObject();
				userToLoginJS.put("userId",id);
				nameValuePairs.add(new BasicNameValuePair("userToLoginJS",userToLoginJS.toString()));
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
			try {	 		
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse= httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            returnServiceOutput = EntityUtils.toString(httpEntity);   
           // Log.d(returnServiceOutput,"%%%%%%%%%%%%%%%returnService%%%%%%%%%%%%%%%%%%%");
			} catch (Exception e) {
				
				e.printStackTrace();
			}   
     
	  return  returnServiceOutput;
		   
        }

        @Override
        protected void onPostExecute(String result) {
              
        	//System.out.println("%%%%%%%%%%%%%RESULT"+"  "+result+"%%%%%%%%%%%%%");
        	//Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        	Intent intent = null;
        	JSONObject userRetrievedData;
        	try {
        		intent = new Intent	(GoToEditProfileActivity.this,EditProfileActivity.class);
				userRetrievedData = new JSONObject(result);
				//Toast.makeText(getApplicationContext(),userRetrievedData.toString(),Toast.LENGTH_LONG).show();
				//Log.d("TESTTESTTEST",userRetrievedData.toString());
				System.out.println("here");
				//9intent.putExtra("json", userRetrievedData.toString());
				intent.putExtra("Name", userRetrievedData.getString("name"));
				//intent.putExtra("ImageString",userRetrievedData.getString("imageString"));
			//	Toast.makeText(getApplicationContext(),userRetrievedData.getString("name"),Toast.LENGTH_LONG).show();
        	} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	startActivity(intent);	
        }
      }	

}
