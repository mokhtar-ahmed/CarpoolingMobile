package com.iti.jets.carpoolingV1.registrationactivity;



import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.R.id;
import com.iti.jets.carpoolingV1.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class RegisterActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registeration_main);
		
    // Create an instance of ExampleFragment
	   RegisterFragment registerFragment = new  RegisterFragment ();
	
	// In case this activity was started with special instructions from an Intent,
    // pass the Intent's extras to the fragment as arguments
	   registerFragment.setArguments(getIntent().getExtras());

	// Add the fragment to the 'fragment_container' FrameLayout
	    getSupportFragmentManager().beginTransaction()
	            .add(R.id.framee2, registerFragment).commit();
	}


}
