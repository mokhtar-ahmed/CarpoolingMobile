package com.iti.jets.carpoolingV1.addcomment;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.registrationactivity.RegisterFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class AddCommentActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_comment_activity);
		
    // Create an instance of ExampleFragment
		AddCommentFragment addCommentFragment = new  AddCommentFragment();
	
	// In case this activity was started with special instructions from an Intent,
    // pass the Intent's extras to the fragment as arguments
		addCommentFragment.setArguments(getIntent().getExtras());

	// Add the fragment to the 'fragment_container' FrameLayout
	    getSupportFragmentManager().beginTransaction()
	            .add(R.id.framee3, addCommentFragment).commit();
	}
	
}
