package com.iti.jets.carpoolingV1.splashscreen;


import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.httphandler.RetriveAllLocationServiceHandler;
import com.iti.jets.carpoolingV1.loginactivity.LoginActivity;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_splash);
		 
		 

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
