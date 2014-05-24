package com.iti.carpoolingV1.pushdemo;


import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.android.gms.common.*;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	 public static final String EXTRA_MESSAGE = "message";
	    public static final String PROPERTY_REG_ID = "registration_id";
	    private static final String PROPERTY_APP_VERSION = "appVersion";
	    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	    String SENDER_ID = "26322642745";
	    static final String TAG = "GCMDemo";
	    TextView mDisplay;
	    GoogleCloudMessaging gcm;
	    AtomicInteger msgId = new AtomicInteger();
	    SharedPreferences prefs;
	    Context context;
	    String regid;

	@Override
	protected void onResume() {
		super.onResume();
	
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		//mDisplay = (TextView) findViewById(R.id.text);

        context = getApplicationContext();

        
       
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(context);

            if (regid.equals("") == true) {
                registerInBackground();
            }else{
            	
            	mDisplay.setText(regid);
            	System.out.println(regid);
            }
        } else {
            Log.i(TAG, "No valid Google Play Services APK found.");
        }
        
       
        //send = (Button) findViewById(R.id.send);
        //clear = (Button) findViewById(R.id.clear);
        
       
        
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	private boolean checkPlayServices() {
	 
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
	    
		if (resultCode != ConnectionResult.SUCCESS) {
	    
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	            GooglePlayServicesUtil.getErrorDialog(resultCode, this,
	                    PLAY_SERVICES_RESOLUTION_REQUEST).show();
	        } else {
	        	Log.i("msg", "This device is not supported.");
	            finish();
	        }
	        return false;
	    }
	    return true;
	}
	
	/**
	 * Gets the current registration ID for application on GCM service.
	 * <p>
	 * If result is empty, the app needs to register.
	 *
	 * @return registration ID, or empty string if there is no existing
	 *         registration ID.
	 */
	private String getRegistrationId(Context context) {
	   
		final SharedPreferences prefs = getGCMPreferences(context);
	    
	    String registrationId = prefs.getString(PROPERTY_REG_ID, "");
	    
	    if (registrationId.equals("") == true) {
	        Log.i(TAG, "Registration not found.");
	        return "";
	    }
	    
	    // Check if app was updated; if so, it must clear the registration ID
	    // since the existing regID is not guaranteed to work with the new
	    // app version.
	    
	    int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
	    int currentVersion = getAppVersion(context);
	    
	    if (registeredVersion != currentVersion) {
	        Log.i(TAG, "App version changed.");
	        return "";
	    }
	    
	    return registrationId;
	}
	
	/**
	 * @return Application's {@code SharedPreferences}.
	 */
	private SharedPreferences getGCMPreferences(Context context) {
	    // This sample app persists the registration ID in shared preferences, but
	    // how you store the regID in your app is up to you.
	    return getSharedPreferences(MainActivity.class.getSimpleName(),
	            Context.MODE_PRIVATE);
	}
	
	/**
	 * @return Application's version code from the {@code PackageManager}.
	 */
	private static int getAppVersion(Context context) {
	    try {
	        PackageInfo packageInfo = context.getPackageManager()
	                .getPackageInfo(context.getPackageName(), 0);
	        return packageInfo.versionCode;
	    } catch (NameNotFoundException e) {
	        // should never happen
	        throw new RuntimeException("Could not get package name: " + e);
	    }
	}
	
	public class SendTask extends AsyncTask<Void,Void,String>{

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
	        String msg = "";
            try {
                Bundle data = new Bundle();
                    data.putString("my_message", "Hello World");
                    data.putString("my_action","com.google.android.gcm.demo.app.ECHO_NOW");
                    
                    String id = Integer.toString(msgId.incrementAndGet());
                    
                    gcm.send(SENDER_ID + "@gcm.googleapis.com", id, data);
            
                    msg = "Sent message";
            } catch (IOException ex) {
                msg = "Error :" + ex.getMessage();
            }
            return msg;
			
		}
		  @Override
          protected void onPostExecute(String msg){
              mDisplay.append(msg + "\n");
          }	
	}
	public class NewTask extends AsyncTask<Void, Void, String>{
		@Override
        protected String doInBackground(Void... params) {
            String msg = "";
            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(context);
                }
                regid = gcm.register(SENDER_ID);
                msg = "Device registered, registration ID=" + regid;

                // You should send the registration ID to your server over HTTP,
                // so it can use GCM/HTTP or CCS to send messages to your app.
                // The request to your server should be authenticated if your app
                // is using accounts.
                sendRegistrationIdToBackend();

                // For this demo: we don't need to send it because the device
                // will send upstream messages to a server that echo back the
                // message using the 'from' address in the message.

                // Persist the regID - no need to register again.
                storeRegistrationId(context, regid);
            } catch (IOException ex) {
                msg = "Error :" + ex.getMessage();
                ex.printStackTrace();
                // If there is an error, don't just keep trying to register.
                // Require the user to click a button again, or perform
                // exponential back-off.
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
            mDisplay.append(msg + "\n");
        }
        
        private void storeRegistrationId(Context context, String regId) {
            final SharedPreferences prefs = getGCMPreferences(context);
            int appVersion = getAppVersion(context);
            Log.i(TAG, "Saving regId on app version " + appVersion);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(PROPERTY_REG_ID, regId);
            editor.putInt(PROPERTY_APP_VERSION, appVersion);
            editor.commit();
        }
        
        private void sendRegistrationIdToBackend() {
            // Your implementation here.
        	
        	
        	
        }
        
	}
	private void registerInBackground() {
		
		new NewTask().execute();
		
	}

}
