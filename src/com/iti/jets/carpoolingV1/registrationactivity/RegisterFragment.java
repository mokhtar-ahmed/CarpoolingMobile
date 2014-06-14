package com.iti.jets.carpoolingV1.registrationactivity;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.*;
import com.facebook.model.*;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.iti.jets.carpoolingV1.R;

import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleActivity;
import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleFragment;
import com.iti.jets.carpoolingV1.common.DatePickerFragment;
import com.iti.jets.carpoolingV1.common.ImageCompressionHandler;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.pojos.User;

import com.iti.jets.carpoolingV1.editprofileactivity.EditProfileActivity;
import com.iti.jets.carpoolingV1.editprofileactivity.EditProfileController;
import com.iti.jets.carpoolingV1.editprofileactivity.EditProfileFragement;
import com.iti.jets.carpoolingV1.jsonhandler.JsonConstants;

import com.iti.jets.carpoolingV1.splashscreen.SplashScreen;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.content.LocalBroadcastManager;

public class RegisterFragment extends Fragment{

	boolean sessionFlag = false;
	boolean flag = true;
	boolean imageChoosedFlag = false;
	Uri selectedImageUri;
	Button registerBtn,loginBtn,calenderBtn;
	LoginButton registerFacebookBtn;
	EditText nameEditText,passwordEditText,phoneEditText,dateEditText,emailEditText;
	String genderData;
	ImageView userImgView ;
	User newUser = new User();	
	Bitmap imgBitmap;
	ImageCompressionHandler imageHandler;
	EditProfileController controller;
	String filePath;
	RadioGroup radioSexGroup;
	RadioButton maleRadioBtn,femaleRadioBtn;
	View rootView;
	public int year;
	public int month;
	public int day;
	Boolean trueFlag = false;
	public ProgressDialog dialog;
	public static final int  REQUEST_CODE_FROM_GALLERY = 1;
	private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String USERNAME_REGEX = "^[a-z0-9_-]";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	     rootView = inflater.inflate(R.layout.activity_registeration,container, false);
	     RegisterActivity regact = new RegisterActivity();
	     boolean flag = regact.getFlag();
	    
	     
	     userImgView = (ImageView) rootView.findViewById(R.id.userImage);
	    
		 registerBtn = (Button) rootView.findViewById(R.id.registerBtn);
		 nameEditText = (EditText)  rootView.findViewById(R.id.nameTxt);
		 passwordEditText = (EditText)  rootView.findViewById(R.id.passwordTxt);
		 phoneEditText = (EditText)  rootView.findViewById(R.id.PhoneTxt);
		 dateEditText = (EditText)  rootView.findViewById(R.id.dateTxt);
		 emailEditText = (EditText)  rootView.findViewById(R.id.EmailTxt);
		 radioSexGroup = (RadioGroup) rootView.findViewById(R.id.radioSexGroup);
		 maleRadioBtn = (RadioButton) rootView.findViewById(R.id.maleRadioBtn);
		 femaleRadioBtn = (RadioButton) rootView.findViewById(R.id.femaleRadioBtn);
		 dateEditText .setInputType(InputType.TYPE_NULL);
		 if(flag)
	     {
	    	nameEditText.setText(regact.getUserName());
	    	emailEditText.setText(regact.getEmail());
	    	if(regact.getGender().equalsIgnoreCase("female"))
	    	{
	    	    femaleRadioBtn.setChecked(true);
	    	}
	    	else
	    	{
	    		maleRadioBtn.setChecked(true);
	    	}
	    	Bitmap bitmap = regact.getBitmap();
	    	if(bitmap == null)
	    	{
	    		System.out.print("NULLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
	    	}
//	    	userImgView.setImageBitmap(regact.getBitmap());
	    	
	     }
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
		 Editor editor = sharedPreferences.edit();
		 editor.putBoolean("circlesEmptyFlag", true);
		 editor.commit();
		 phoneEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub

			}
		});
		 

		 dateEditText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDatePickerDialog();
			}
		});
 
		 
		 
//		 if(testFlag)
//		 {
//			 nameEditText.setText(EntityFactory.getUserInstance().getName());
//			 emailEditText.setText(EntityFactory.getUserInstance().getEmail());
//			 if(EntityFactory.getUserInstance().getGender().equalsIgnoreCase("female"))
//			 {
//				 femaleRadioBtn.setChecked(true);
//			 }
//			 else
//			 {
//				 maleRadioBtn.setChecked(true);
//			 }
//		 }
		 
		 registerBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if (phoneEditText.getText().toString().equals(""))
				{
					phoneEditText.setError("Phone field required");
				}
				else if(phoneEditText.getText().length()<11)
				{
					phoneEditText.setError("Phone field required");
				}
				else if(nameEditText.getText().toString().equals(""))
				{
					nameEditText.setError("Name field required");
				}
				else if(nameEditText.getText().toString().length()<3)
				{
					nameEditText.setError("At least 3 char");
				}
				else if(passwordEditText.getText().toString().length()<6)
				{
					passwordEditText.setError("At least 6 char");
				}
				else if(emailEditText.getText().toString().equals(""))
				{
					emailEditText.setError("Email field required");
				}
				else if(!Pattern.matches(EMAIL_REGEX,emailEditText.getText().toString().trim()))
				{
					emailEditText.setError("Invalid email");
				}
				else if(dateEditText.getText().toString().equals(""))
				{
					dateEditText.setError("Birthdate field required");
				}
				
				else if(imgBitmap == null)
				{
					imgBitmap = BitmapFactory.decodeResource(
							RegisterFragment.this.getResources(), R.drawable.ic_action_user);
					imageChoosedFlag = false;
//					showDialog("You didn't choose an image");
				}
				else
				{
					trueFlag = true;
					
					if(trueFlag)
					{
						newUser.setName(nameEditText.getText().toString());
						newUser.setPhone(phoneEditText.getText().toString());
						newUser.setEmail(emailEditText.getText().toString());
						Date thedate;
						
							
							Date d = new Date(year,month,day);						
							
							newUser.setDateOfBirth(d);
						
						
						newUser.setPassword(passwordEditText.getText().toString());
						int selectedId = radioSexGroup.getCheckedRadioButtonId();
						if(maleRadioBtn.isSelected())
						{
							genderData = maleRadioBtn.getText().toString();
						}
						else
						{
							genderData = femaleRadioBtn.getText().toString();
						}
						newUser.setGender(genderData);
						String notif = EntityFactory.getNotificationIdInstance().trim();
						if(notif == null)
							notif="";
						
						newUser.setPushNotificationId(notif);

						
					  RegisterationController controller = new RegisterationController(newUser,RegisterFragment.this,imgBitmap,filePath);

						}
				}
			
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
		 
		 
		return rootView;
		
	}

	 public void onActivityResult(int requestCode, int resultCode, Intent data) {

		 
//		 super.onActivityResult(requestCode, resultCode, data);
//	      Session.getActiveSession().onActivityResult(RegisterFragment.this.getActivity(), requestCode, resultCode, data);
			 
		
		    if (resultCode == RegisterFragment.this.getActivity().RESULT_OK) {
	                
	        	selectedImageUri = data.getData();

	        	
	        	imageHandler = new ImageCompressionHandler(data.getDataString(),RegisterFragment.this);
	          
	        } 

	    }

		public void sendBitMapImg(Bitmap bitmapImg,String filePath) {
			// TODO Auto-generated method stub
			this.filePath = filePath;
			if(bitmapImg == null)
			{
				//Toast.makeText(RegisterFragment.this.getActivity().getApplicationContext(), "NULLLLLLLLIMAGE", Toast.LENGTH_LONG).show();
			}
			imgBitmap = bitmapImg;
			userImgView.setImageBitmap(imgBitmap);
		}
		
		public void showDatePickerDialog() {
		   DatePickerFragment newFragment = new DatePickerFragment(RegisterFragment.this);
		   
		   newFragment.show(this.getFragmentManager(), "DatePicker");
		   
		}	
		
		public EditText getDateTxt()
		{
			return this.dateEditText;
		}
		
		public void getResultFromWebservice(String result) {
			
		if(result != null)
		{
			
		}
		
		
		//Toast.makeText(RegisterFragment.this.getActivity().getApplicationContext(), "ENTERED FINAL", Toast.LENGTH_LONG).show();
		//Toast.makeText(RegisterFragment.this.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
		JSONObject recievedResult = null;
		JSONObject ResponseValue;
		String HasError = null;
		String Exist = null;
	    try {
			 recievedResult = new JSONObject(result);
			 ResponseValue = recievedResult.getJSONObject("ResponseValue");
			 //Toast.makeText(RegisterFragment.this.getActivity().getApplicationContext(),  "TTTTTTTTTTTT"+"    "+ResponseValue.getString("register").toString() , Toast.LENGTH_LONG).show();
     		 Exist = ResponseValue.getString("register").toString();
     		 HasError = recievedResult.getString("HasError");
     		 
			 //Toast.makeText(RegisterFragment.this.getActivity().getApplicationContext(),Exist, Toast.LENGTH_LONG).show();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(HasError.equalsIgnoreCase("true"))
		{
			AlertDialog alertDialog = new AlertDialog.Builder(
                    RegisterFragment.this.getActivity()).create();

		    // Setting Dialog Title
		    alertDialog.setTitle("Error");
		
		    // Setting Dialog Message
		    try {
				alertDialog.setMessage(recievedResult.getString("FaultsMsg").toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
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
		else if(Exist.equals("true"))
		{
			
//			getActivity().finish();
			
			Intent intent = new Intent(RegisterFragment.this.getActivity().getApplicationContext(),LoginActivity.class);
			
			startActivity(intent);
	 
		}
	}
	
	public void showDialog(String msg)
	{
		AlertDialog alertDialog = new AlertDialog.Builder(
                RegisterFragment.this.getActivity()).create();

	    // Setting Dialog Title
	    alertDialog.setTitle("Error");
	
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
