package com.iti.jets.carpoolingV1.uimanager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/mokhtar-ahmed/CarpoolingMobile.git
import android.os.Bundle;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.addevent.AddEventActivity;
import com.iti.jets.carpoolingV1.eventDetails.EventDetailsActivity;
import com.iti.jets.carpoolingV1.eventDetails.InvitedEventDetailsActivity;
import com.iti.jets.carpoolingV1.eventRequests.RequestsHome;
import com.iti.jets.carpoolingV1.eventshome.EventsHome;
import com.iti.jets.carpoolingV1.loginactivity.LoginActivity;

import com.iti.jets.carpoolingV1.registrationactivity.RegisterActivity;
import com.iti.jets.carpoolingV1.registrationactivity.RegisterFragment;

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

			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("addEvent").commit();
<<<<<<< HEAD
=======
		}
	}
	
	public static void getoEventHome(Activity ac){
	
	Fragment ff = new EventsHome();
	if (ff != null) {
		FragmentManager fragmentManager = ac.getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, ff).addToBackStack("home").commit();

	}
	}
	public static void getoRequestsHome(Activity ac, int eventId){
		
	Fragment ff = new RequestsHome();
	Bundle bundle = new Bundle();
	bundle.putInt("eventId", eventId);
	ff.setArguments(bundle);
	if (ff != null) {
		FragmentManager fragmentManager = ac.getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, ff).addToBackStack("home").commit();

	}
	}
	public static void goToEventDetails(Activity ac, int eventId , String userState){
		
		Bundle bundle = new Bundle();
		bundle.putInt("eventId", eventId);
		bundle.putString("userState", userState);
		
		if(userState.equals("Create")== true){
			Fragment fragment = new EventDetailsActivity();
			fragment.setArguments(bundle);
			
			if (fragment != null) {
				
				FragmentManager fragmentManager = ac.getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("eventDetails").commit();
	
			}
		}
		else if (userState.equals("Accepted")== true){
			
		}
		else { 
			Fragment fragment = new InvitedEventDetailsActivity();
			fragment.setArguments(bundle);
			
			if (fragment != null) {
				
				FragmentManager fragmentManager = ac.getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("eventDetails").commit();
	
			}
			
>>>>>>> branch 'master' of https://github.com/mokhtar-ahmed/CarpoolingMobile.git
		}
	}
	


	public static void goToEventDetails(Activity ac, int eventId){
		
		Bundle bundle = new Bundle();
		bundle.putInt("eventId", eventId);
		
		Fragment fragment = new EventDetailsActivity();
		fragment.setArguments(bundle);
		
		if (fragment != null) {
			
			FragmentManager fragmentManager = ac.getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("eventDetails").commit();

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
