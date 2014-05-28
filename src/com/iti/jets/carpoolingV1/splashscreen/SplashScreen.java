package com.iti.jets.carpoolingV1.splashscreen;


import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

<<<<<<< HEAD
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesUtil;
//import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.iti.carpoolingV1.pushdemo.MainActivity;
import com.iti.carpoolingV1.pushdemo.MainActivity.NewTask;
=======
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

>>>>>>> branch 'master' of https://github.com/mokhtar-ahmed/CarpoolingMobile.git
import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.httphandler.RetriveAllLocationServiceHandler;
import com.iti.jets.carpoolingV1.loginactivity.LoginActivity;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreen extends Activity {
	    public static final String EXTRA_MESSAGE = "message";
	    public static final String PROPERTY_REG_ID = "registration_id";
	    private static final String PROPERTY_APP_VERSION = "appVersion";
	    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	    String SENDER_ID = "26322642745";
	    static final String TAG = "GCMDemo";
	    GoogleCloudMessaging gcm;
	    AtomicInteger msgId = new AtomicInteger();
	    SharedPreferences prefs;
	    Context context;
	    String regid;

	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_splash);
		 
		 
	     context = getApplicationContext();

	        
	       
	        if (checkPlayServices()) {
	            gcm = GoogleCloudMessaging.getInstance(this);
	            regid = getRegistrationId(context);

	            if (regid.equals("") == true) {
	                registerInBackground();
	            }else{
	            	//Toast.makeText(context, regid, Toast.LENGTH_LONG).show();
	            	EntityFactory.setNotificationIdInstance(regid);
	            	System.out.println(regid);
	            }
	        } else {
	            Log.i(TAG, "No valid Google Play Services APK found.");
	        }
	        
		 new RetriveAllLocationServiceHandler().execute();
			Thread th = new Thread(){

				@Override
				public void run() {
					try {
						
						sleep(3000);
						
						UIManagerHandler.goToLogin(SplashScreen.this);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			};
			
			th.start();
			
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
	
	private SharedPreferences getGCMPreferences(Context context) {

<<<<<<< HEAD
	    return getSharedPreferences(MainActivity.class.getSimpleName(),Context.MODE_PRIVATE);
=======
	    return getSharedPreferences(SplashScreen.class.getSimpleName(),Context.MODE_PRIVATE);
>>>>>>> branch 'master' of https://github.com/mokhtar-ahmed/CarpoolingMobile.git
	}
	
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

                sendRegistrationIdToBackend();

                storeRegistrationId(context, regid);
            } catch (IOException ex) {
                msg = "Error :" + ex.getMessage();
                ex.printStackTrace();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
        //    mDisplay.append(msg + "\n");
        	Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
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
    
	private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGCMPreferences(context);
        int appVersion = getAppVersion(context);
        Log.i(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}
}
