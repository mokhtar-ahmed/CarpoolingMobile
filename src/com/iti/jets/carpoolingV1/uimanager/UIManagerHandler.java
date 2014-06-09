package com.iti.jets.carpoolingV1.uimanager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.widget.Toast;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleActivity;
import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleFragment;
import com.iti.jets.carpoolingV1.addevent.AddEventActivity;
import com.iti.jets.carpoolingV1.eventDetails.AcceptedEventDetailsActivity;
import com.iti.jets.carpoolingV1.eventDetails.EventDetailsActivity;
import com.iti.jets.carpoolingV1.eventDetails.InvitedEventDetailsActivity;
import com.iti.jets.carpoolingV1.eventRequests.RequestsHome;
import com.iti.jets.carpoolingV1.eventshome.EventsHome;
import com.iti.jets.carpoolingV1.loginactivity.LoginActivity;
import com.iti.jets.carpoolingV1.notificationHome.NoNotificationHome;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;

import com.iti.jets.carpoolingV1.registrationactivity.RegisterActivity;

import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment;
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
		
		
		Intent intent = new Intent(ac.getApplicationContext(),AddCircleActivity.class);
		intent.putExtra("userId", EntityFactory.getUserInstance().getId());
		ac.startActivity(intent);
		
//		Fragment fragment = new AddEventActivity();
//		if (fragment != null) {
//			
//			FragmentManager fragmentManager = ac.getFragmentManager();
//
//			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("addEvent").commit();
//		}
	}

	public static void goToNoNotificationHome(Activity ac){
		
		Fragment fragment = new NoNotificationHome();
		if (fragment != null) {
			
			FragmentManager fragmentManager = ac.getFragmentManager();

			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("addEvent").commit();
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
	
			Fragment fragment = new AcceptedEventDetailsActivity();
			fragment.setArguments(bundle);
			
			if (fragment != null) {
				
				FragmentManager fragmentManager = ac.getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("eventDetails").commit();
	
			}
	
			
		}
		else { 
			Fragment fragment = new InvitedEventDetailsActivity();
			fragment.setArguments(bundle);
			
			if (fragment != null) {
				
				FragmentManager fragmentManager = ac.getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("eventDetails").commit();
	
			}
			
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

public static void goToAddCircle(Activity ac){
		
		Fragment fragment = new AddCircleFragment();
		
		if (fragment != null) {
			
			FragmentManager fragmentManager = ac.getFragmentManager();
			
			CharSequence title = "New Circle";
			 ac.getActionBar().setTitle(title);
//			 fragmentManager.popBackStack();
			 
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("KILL").commit();
		}
	}

public static void goToAllCirclesList(final Activity ac){
	
	Fragment fragment = new AllCirclesListFragment();
	
	if (fragment != null) {
		
		FragmentManager fragmentManager = ac.getFragmentManager();
		fragmentManager.addOnBackStackChangedListener(new OnBackStackChangedListener() {    
            public void onBackStackChanged() {
               
               Toast.makeText(ac.getApplicationContext(),"LLLLLLLLLLLLLL",Toast.LENGTH_LONG).show();
                
            }
        });
		CharSequence title = "My Circles";
		 ac.getActionBar().setTitle(title);
//		 fragmentManager.popBackStack();
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, fragment).addToBackStack("home2").commit();

		
	}
 }

public static void goToCircleUsersFragment(String result, Activity ac, Bundle args, String circleName){
	
	Fragment fragment = new CircleUsersFragment();
	
	if (fragment != null) {
		
		FragmentManager fragmentManager = ac.getFragmentManager();
		fragment.setArguments(args); 
//		fragmentManager.popBackStack();
		 ac.getActionBar().setTitle(circleName);
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, fragment).disallowAddToBackStack().commit();
		
		
		
	}
}

public static void goToCircleUsersFragment(Activity ac, Bundle args, String circleName){
	
	Fragment fragment = new CircleUsersFragment();
	
	if (fragment != null) {
		
		FragmentManager fragmentManager = ac.getFragmentManager();
		fragment.setArguments(args); 
//		fragmentManager.popBackStack();
		 ac.getActionBar().setTitle(circleName);
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, fragment).disallowAddToBackStack().commit();
		
		
		
	}
}
}
