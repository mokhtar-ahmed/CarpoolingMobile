package com.iti.jets.carpoolingV1.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.iti.jets.carpoolingV1.R;

public class ShowDialog {

	public void showDialog(String title,String msg, Activity activity)
	{
		AlertDialog alertDialog = new AlertDialog.Builder(
				activity).create();

	    // Setting Dialog Title
	    alertDialog.setTitle(title);
	
	    // Setting Dialog Message
	    alertDialog.setMessage(msg);
	
	    // Setting Icon to Dialog
	    alertDialog.setIcon(R.drawable.tick33);
	    
	
	    
	    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});


// Showing Alert Message
alertDialog.show();
	}
}
