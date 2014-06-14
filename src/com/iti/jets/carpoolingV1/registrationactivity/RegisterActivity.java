package com.iti.jets.carpoolingV1.registrationactivity;



import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.google.android.gms.internal.ex;
import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.R.id;
import com.iti.jets.carpoolingV1.R.layout;
import com.iti.jets.carpoolingV1.deletecircle.DeleteCircleController;
import com.iti.jets.carpoolingV1.loginactivity.LoginActivity;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment.FragmentCallback;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Entity;
import android.content.Intent;
import android.graphics.Bitmap;

import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

public class RegisterActivity extends FragmentActivity {

	static boolean flag = false;
	static String userName,email,gender;
	Bitmap bitmap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registeration_main); 
		
    // Create an instance of ExampleFragment
		Bundle extras = getIntent().getExtras(); 
		flag = extras.getBoolean("flag");
		if(flag)
		{
			RegisterFragment registerFragment = new  RegisterFragment ();
			
			// In case this activity was started with special instructions from an Intent,
		    // pass the Intent's extras to the fragment as arguments
			  bitmap = getIntent().getParcelableExtra("userBitmap");
			  System.out.print(extras.getString("gender"));
			  userName = extras.getString("userName");
			  email = extras.getString("email");
			  gender = extras.getString("gender");
			  Bundle n = new Bundle();
			  n.putBoolean("flag", flag);
			  
			  registerFragment.setArguments(n);
			// Add the fragment to the 'fragment_container' FrameLayout
			    getSupportFragmentManager().beginTransaction()
			            .replace(R.id.framee2, registerFragment).commit();
		}		
		else
		{
			RegisterFragment registerFragment = new  RegisterFragment ();
			
			// In case this activity was started with special instructions from an Intent,
		    // pass the Intent's extras to the fragment as arguments
//			   registerFragment.setArguments(getIntent().getExtras());
			 Bundle n = new Bundle();
			  n.putBoolean("flag", flag);
			
			  registerFragment.setArguments(n);
			// Add the fragment to the 'fragment_container' FrameLayout
			    getSupportFragmentManager().beginTransaction()
			            .replace(R.id.framee2, registerFragment).commit();
		}
	   
	}

	boolean getFlag()
	{
		return flag;
	}
	
	String getUserName()
	{
		return userName;
	}
	String getEmail()
	{
		return email;
	}
	String getGender()
	{
		return gender;
	}
	Bitmap getBitmap()
	{
		return bitmap;
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		super.onBackPressed();
		Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
		startActivity(intent);
//		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//				RegisterActivity.this);
//// 
//			// set title
//			alertDialogBuilder.setTitle("Confirm");
//// 
//			// set dialog message
//			alertDialogBuilder
//				.setMessage("Are you sure you want leave this screen?")
//				
//				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog,int id) {
//						// if this button is clicked, close
//						// current activity
//						Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
//						startActivity(intent);
//					}
//				  })
//				.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog,int id) {
//						// if this button is clicked, just close
//						// the dialog box and do nothing
//						dialog.cancel();
//					}
//				});
//// 
//				// create alert dialog
//				AlertDialog alertDialog = alertDialogBuilder.create();
//// 
//				// show it
//				alertDialog.show();
		
	}
}
