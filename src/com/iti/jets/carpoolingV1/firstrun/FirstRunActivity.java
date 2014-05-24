package com.iti.jets.carpoolingV1.firstrun;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.R.id;
import com.iti.jets.carpoolingV1.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class FirstRunActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_run);
		
		
//    // Create an instance of ExampleFragment
    FirstRunFragment firstFragment = new  FirstRunFragment ();
//
//    // In case this activity was started with special instructions from an Intent,
//    // pass the Intent's extras to the fragment as arguments
    firstFragment.setArguments(getIntent().getExtras());
//
//    // Add the fragment to the 'fragment_container' FrameLayout
    getSupportFragmentManager().beginTransaction()
            .add(R.id.framee, firstFragment).commit();


	}

}
