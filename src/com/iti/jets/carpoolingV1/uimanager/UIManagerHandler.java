package com.iti.jets.carpoolingV1.uimanager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.addevent.AddEventActivity;
import com.iti.jets.carpoolingV1.eventDetails.EventDetailsActivity;
import com.iti.jets.carpoolingV1.loginactivity.LoginActivity;

import com.iti.jets.carpoolingV1.registrationactivity.RegisterActivity;

import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment;
import com.iti.jets.carpoolingV1.sharedlayout.MainActivity;

public class UIManagerHandler {

	public static void goToHome(Context context){
		
		context.startActivity(new Intent(context, MainActivity.class));
	}
	public static void goToLogin(Context context){
		
		context.startActivity(new Intent(context , LoginActivity.class));
	}
	public static void goToRegister(Context context){
		context.startActivity(new Intent(context , RegisterActivity.class));
	}
	
	public static void goToRetrieveAllCircles(Context context){
		context.startActivity(new Intent(context , AllCirclesListFragment.class));
	}
	public static void goToAddEvent(Activity ac){
		
		Fragment fragment = new AddEventActivity();
		if (fragment != null) {
			
			FragmentManager fragmentManager = ac.getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
		}
	}
	public static void goToEventDetails(Activity ac){
		
		Fragment fragment = new EventDetailsActivity();
		
		if (fragment != null) {
			
			FragmentManager fragmentManager = ac.getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
		}
	}

}
