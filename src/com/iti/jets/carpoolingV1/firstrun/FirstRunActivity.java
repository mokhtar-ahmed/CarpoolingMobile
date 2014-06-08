package com.iti.jets.carpoolingV1.firstrun;

import com.google.android.gms.internal.in;
import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.R.id;
import com.iti.jets.carpoolingV1.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class FirstRunActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_run);
		

   Fragment firstFragment = new FirstRunFragment ();

   FragmentManager fragmentManager = getFragmentManager();
   
	fragmentManager.beginTransaction()
			.replace(R.id.framee, firstFragment).commit();
          
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		
		
		super.onBackPressed();
		getFragmentManager().popBackStack();
//		if(inFlag)
//		{
//			Intent intent = new Intent(getApplicationContext(),FirstRunActivity.class);
//			 startActivity(intent);
//		}
//		else
//		{
//			finish();
//		}
//		 
		

	   
		
	}

}
