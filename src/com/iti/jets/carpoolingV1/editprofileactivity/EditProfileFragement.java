package com.iti.jets.carpoolingV1.editprofileactivity;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.zip.Inflater;

import org.json.JSONException;
import org.json.JSONObject;
import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleFragment;
import com.iti.jets.carpoolingV1.common.DatePickerFragment;
import com.iti.jets.carpoolingV1.common.DatePickerFragment2;
import com.iti.jets.carpoolingV1.common.ImageCompressionHandler;
import com.iti.jets.carpoolingV1.common.ImageCompressionHandler2;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.registrationactivity.RegisterFragment;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Contacts.Intents.UI;
import android.support.v4.app.DialogFragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class EditProfileFragement extends Fragment {

	View rootView;
	public int resPath ;
	private ImageView userImgView,editNameImgView,editDobImgView;
	private TextView usernameTextView;
	public TextView dateTextView;
	private final int REQUEST_CODE_FROM_GALLERY = 01;
	private Uri selectedImageUri;
	public ProgressDialog dialog;
	Button doneBtn,cancelBtn;
	String username;
	String flag = null;
	Bitmap imgBitmap;
	ImageCompressionHandler2 imageHandler;
	String filePath;
	JSONObject userToRetrieve;
	String recievedUserName = null;
	int userId;
	EditProfileController controller;
	Date newDateObj;
	String newUserName = null;
	boolean imageChangedFlag = false;
	public int year;
	public int month;
	public int day;




	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
	            Bundle savedInstanceState) {
	 
	         rootView = inflater.inflate(R.layout.activity_edit_profile,container, false);
//            setHasOptionsMenu(false);
//	        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#17CED1"));     
//	        getActivity().getActionBar().setBackgroundDrawable(colorDrawable);
	        CharSequence mTitle = "My Profile";
	        getActivity().getActionBar().setTitle(mTitle); 
	         userId = EntityFactory.getUserInstance().getId();
	         controller = new EditProfileController(this);
	         cancelBtn = (Button)rootView.findViewById(R.id.cancelBtn);
	        userImgView = (ImageView)rootView.findViewById(R.id.userImgView);
	 		editNameImgView = (ImageView)rootView.findViewById(R.id.editnameImgView);
	 		editDobImgView = (ImageView)rootView.findViewById(R.id.editdateImgView);
	 		usernameTextView = (TextView)rootView.findViewById(R.id.NametextView);
	 		dateTextView = (TextView)rootView.findViewById(R.id.dateTextView);
	 		doneBtn = (Button)rootView.findViewById(R.id.doneBtn);
	 		int userId = EntityFactory.getUserInstance().getId();
	 		RetrieveUserController retController = new RetrieveUserController(this,userId);
	        cancelBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
				     boolean circlesEmptyFlag =sharedPreferences.getBoolean("circlesEmptyFlag", false);
				     if(circlesEmptyFlag)
				     {
				    	getFragmentManager().popBackStack();
				     }
				     else
				     {
				    	 UIManagerHandler.goToHome(EditProfileFragement.this.getActivity());
				     }
					
				}
			});
	 		doneBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					String user_name =  usernameTextView.getText().toString();
					String user_Dob  = dateTextView.getText().toString();
					controller = new EditProfileController();
					
					if(!imageChangedFlag)
					{
						imgBitmap = BitmapFactory.decodeResource(
								EditProfileFragement.this.getResources(), R.drawable.photo);
						 resPath =  R.drawable.photo;
						imageChangedFlag = false;
					}
					
					
					controller.setArguments(user_name,user_Dob,imgBitmap,imageChangedFlag,filePath,EditProfileFragement.this);
				}
			});
			
			userImgView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		  			intent.setType("image/*");
		  			startActivityForResult(intent, REQUEST_CODE_FROM_GALLERY); 
					
				}
			});
			editNameImgView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					final View promptView = inflater.inflate(R.layout.prompts, container,
							false);
					
					LayoutInflater li = LayoutInflater.from(getActivity());
					View promptsView = li.inflate(R.layout.prompts, null);
					 
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							getActivity());

					// set prompts.xml to alertdialog builder
					alertDialogBuilder.setView(promptsView);
					TextView textV = (TextView) promptsView.findViewById(R.id.textView11);
					textV.setText("Edit Your Full Name:");
					final EditText userInput = (EditText) promptsView
							.findViewById(R.id.editTextDialogUserInput);
					
					
//							inflater.R.id.editTextDialogUserInput;
					
					userInput.setText(recievedUserName);

					// set dialog message
					alertDialogBuilder
						.setCancelable(false)
						
						.setPositiveButton("Save",
						  new DialogInterface.OnClickListener() {
						    public void onClick(DialogInterface dialog,int id) {
							// get user input and set it to result
							// edit text 
						    	String newUserName = userInput.getText().toString();
						    	if(!newUserName.equals(""))
						    	{
						    		
							    	usernameTextView.setText(userInput.getText().toString());
						    	
						    	}
						    }
						  })
						.setNegativeButton("Cancel",
						  new DialogInterface.OnClickListener() {
						    public void onClick(DialogInterface dialog,int id) {
							dialog.cancel();
						    }
						  });

					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();

					// show it
					alertDialog.show();
				
				}
			}) ;
			editDobImgView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 DatePickerFragment2 dateFragment = new DatePickerFragment2(EditProfileFragement.this);
					 FragmentManager manger = EditProfileFragement.this.getFragmentManager();
					 dateFragment.show(manger,"Calender");
					 
					 
				}
			});
	         return rootView;
    
	  }

	
	
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == getActivity().RESULT_OK) {
            
    	selectedImageUri = data.getData();
    	
    	imageChangedFlag = true;
        imageHandler = new ImageCompressionHandler2(data.getDataString(),EditProfileFragement.this);
      
    } 

}

public void sendBitMapImg(Bitmap bitmapImg,String filePath) {
	// TODO Auto-generated method stub
	this.filePath = filePath;
	if(bitmapImg == null)
	{
		
	}else
	{
		imgBitmap = bitmapImg;
		if(imgBitmap == null)
		{
			System.out.println("Mawgoooooooooooooooooooodaaaa");
			
//			imgBitmap = 
		}
		else
		{
			System.out.println("yyyyyyyyyyyyyyyyyy"+imgBitmap);
			userImgView.setImageBitmap(imgBitmap);
	
		}

	}

}

public void getServiceData(String result) {
	// TODO Auto-generated method stub
	
//	Toast.makeText(getActivity().getApplicationContext(), "UUUUUUUUUUUUUUU "+"   "+result, Toast.LENGTH_LONG).show();
}




public void getResultFromWebService(String result) {
	// TODO Auto-generated method stub
	try {
		userToRetrieve = new JSONObject(result);
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	 try {
//		Toast.makeText(getApplicationContext(), userToRetrieve.getString("name"), Toast.LENGTH_LONG).show();
//		Toast.makeText(getApplicationContext(), userToRetrieve.getString("imageString"), Toast.LENGTH_LONG).show();
		 recievedUserName = userToRetrieve.getString("name");
		 usernameTextView.setText(userToRetrieve.getString("name"));
		 String dateStr = userToRetrieve.getString("DOB");
		 dateTextView.setText(dateStr); 
		 byte [] encodeByte=Base64.decode(userToRetrieve.getString("imageString"),Base64.DEFAULT);
		 System.out.println(userToRetrieve.getString("imageString"));
		 System.out.println(encodeByte);
	     Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
	      // Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(userToRetrieve.getString("imageString")));
	       userImgView.setImageBitmap(bitmap);
	       
         
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	 
	 
}	

//@Override
//public void onStop() {
//	// TODO Auto-generated method stub
//	super.onStop();
//	UIManagerHandler.goToHome(getActivity());
//}

	
}
