package com.iti.jets.carpoolingV1.uimanager;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleFragment;
import com.iti.jets.carpoolingV1.addevent.AddEventActivity;
import com.iti.jets.carpoolingV1.common.Circle2;
import com.iti.jets.carpoolingV1.eventDetails.AcceptedEventDetailsActivity;
import com.iti.jets.carpoolingV1.eventDetails.EventDetailsActivity;
import com.iti.jets.carpoolingV1.eventDetails.InvitedEventDetailsActivity;
import com.iti.jets.carpoolingV1.eventRequests.RequestsHome;
import com.iti.jets.carpoolingV1.eventshome.EventsHome;
import com.iti.jets.carpoolingV1.eventshome.NoEventsHome;
import com.iti.jets.carpoolingV1.firstrun.NoUsersHome;
import com.iti.jets.carpoolingV1.loginactivity.LoginActivity;
import com.iti.jets.carpoolingV1.notificationHome.NoNotificationHome;

import com.iti.jets.carpoolingV1.registrationactivity.RegisterActivity;

import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment;
import com.iti.jets.carpoolingV1.sharedlayout.ConnectionFailedView;
import com.iti.jets.carpoolingV1.sharedlayout.MainActivity;


public class UIManagerHandler {

	public static void goToHome(Context context){

		context.startActivity(new Intent(context, MainActivity.class));
	}
	public static void goToLogin(Context context){

		context.startActivity(new Intent(context , LoginActivity.class));
	}
	public static void goToRegister(Context context, boolean flag){
		Intent intent = new Intent(context.getApplicationContext(),RegisterActivity.class);
		intent.putExtra("flag",flag);
		context.startActivity(intent);

	}
	public static void goToRegister(LoginActivity loginActivity, Bundle bundle,
			boolean flag, Bitmap userBitmap) {
		// TODO Auto-generated method stub


		Intent intent = new Intent(loginActivity.getApplicationContext(),RegisterActivity.class);
		intent.putExtra("flag", flag);
		intent.putExtra("userBitmap", userBitmap);
		intent.putExtras(bundle);
		loginActivity.startActivity(intent);


	}


	public static void goToRetrieveAllCircles(Context context){
		context.startActivity(new Intent(context , AllCirclesListFragment.class));
	}
	public static void goToNoUsersHome(Activity ac, int circle_Id, String circleName){


		Fragment fragment = new NoUsersHome();
		if (fragment != null) {


			ac.setRequestedOrientation(
		            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			FragmentManager fragmentManager = ac.getFragmentManager();
			Bundle bundle = new Bundle();
			bundle.putString("CircleName", circleName);
			bundle.putInt("circle_Id",circle_Id);
			fragment.setArguments(bundle);
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("addEvent").commit();
		}
	}
	public static void goToNoUsersHome2(Activity ac, int circle_Id,
			String circleName) {
		// TODO Auto-generated method stub
		Fragment fragment = new NoUsersHome2();
		if (fragment != null) {


			ac.setRequestedOrientation(
		            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			FragmentManager fragmentManager = ac.getFragmentManager();
			Bundle bundle = new Bundle();
			bundle.putString("CircleName", circleName);
			bundle.putInt("circle_Id",circle_Id);
			fragment.setArguments(bundle);


			fragmentManager.beginTransaction().replace(R.id.framee, fragment,"noNotification").addToBackStack("noNotification").commit();


		}
	}

	public static void goToAddEvent(Activity ac){

		Fragment fragment = new AddEventActivity();
		if (fragment != null) {

			FragmentManager fragmentManager = ac.getFragmentManager();

			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment,"addEvent").addToBackStack("addEvent").commit();
		}
	}
	public static void goToConnectionFailed(Activity ac){

		Fragment fragment = new ConnectionFailedView();
		if (fragment != null) {

			FragmentManager fragmentManager = ac.getFragmentManager();
			fragmentManager.popBackStack();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment,"connectionfails").addToBackStack("connectionfails").commit();
		}
	}
	public static void goToNoEvent(Activity ac){

		Fragment fragment = new NoEventsHome();
		if (fragment != null) {

			FragmentManager fragmentManager = ac.getFragmentManager();
			fragmentManager.popBackStack();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment,"noEvent").addToBackStack("noEvent").commit();
		}
	}

	public static void goToNoNotificationHome(Activity ac){

		Fragment fragment = new NoNotificationHome();
		
		if (fragment != null) {
			FragmentManager fragmentManager = ac.getFragmentManager();
			fragmentManager.popBackStack();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment,"noNotification").addToBackStack("noNotification").commit();
		}
	}
	public static void getoEventHome(Activity ac){

	Fragment ff = new EventsHome();
	if (ff != null) {
		ac.setRequestedOrientation(
	            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
		ac.setRequestedOrientation(
	            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

				ac.setRequestedOrientation(
			            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				FragmentManager fragmentManager = ac.getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.frame_container, fragment,"eventDetails").addToBackStack("eventDetails").commit();

			}
		}
		else if (userState.equals("Accepted")== true){

			ac.setRequestedOrientation(
		            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			Fragment fragment = new AcceptedEventDetailsActivity();
			fragment.setArguments(bundle);

			if (fragment != null) {

				FragmentManager fragmentManager = ac.getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.frame_container, fragment,"eventDetails").addToBackStack("eventDetails").commit();

			}


		}
		else { 
			ac.setRequestedOrientation(
		            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			Fragment fragment = new InvitedEventDetailsActivity();
			fragment.setArguments(bundle);

			if (fragment != null) {

				FragmentManager fragmentManager = ac.getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.frame_container, fragment,"eventDetails").addToBackStack("eventDetails").commit();

			}

		}
	}
	public static void goToEventDetails(Activity ac){

		Fragment fragment = new EventDetailsActivity();

		if (fragment != null) {
			ac.setRequestedOrientation(
		            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			FragmentManager fragmentManager = ac.getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
		}
	}

	public static void goToAddCircle(Activity ac){
		
		Fragment fragment = new AddCircleFragment();
		
		if (fragment != null) {
			ac.setRequestedOrientation(
		            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			FragmentManager fragmentManager = ac.getFragmentManager();
			CharSequence title = "New Circle";
			 ac.getActionBar().setTitle(title);
//			 fragmentManager.popBackStack();
			 
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment,"addCirc").addToBackStack("addCirc").commit();
			
			
			
		}
	}

	public static void goToAllCirclesList(Activity ac){
	
	Fragment fragment = new AllCirclesListFragment();
	
	if (fragment != null) {
		ac.setRequestedOrientation(
	            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		FragmentManager fragmentManager = ac.getFragmentManager();
		CharSequence title = "My Circles";
		 ac.getActionBar().setTitle(title);
//		 fragmentManager.popBackStack();
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, fragment,"home2d").addToBackStack("home2d").commit();
		
		
		
	}
}

	public static void goToCircleUsersFragment(Activity ac, Bundle args, String circleName){
	
	Fragment fragment = new CircleUsersFragment();
	
	if (fragment != null) {
		ac.setRequestedOrientation(
	            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		FragmentManager fragmentManager = ac.getFragmentManager();
		fragment.setArguments(args); 
//		fragmentManager.popBackStack();
		 ac.getActionBar().setTitle(circleName);
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, fragment,"oCircleUsers1").addToBackStack("oCircleUsers1").commit();

		
	}
}

	public static void goToCircleUsersFragment(String result, Activity ac, Bundle args, String circleName){
	
	Fragment fragment = new CircleUsersFragment();
	
	if (fragment != null) {
		ac.setRequestedOrientation(
	            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		FragmentManager fragmentManager = ac.getFragmentManager();
		
		fragment.setArguments(args); 
//		fragmentManager.popBackStack();
		 ac.getActionBar().setTitle(circleName);
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, fragment,"CircleUsers").addToBackStack("CircleUsers").commit();
		
		
		
	}
}
}
