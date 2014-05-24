package com.iti.jets.carpoolingV1.registrationactivity;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;

import com.iti.jets.carpoolingV1.common.DatePickerFragment;
import com.iti.jets.carpoolingV1.common.ImageCompressionHandler;
import com.iti.jets.carpoolingV1.common.User;

import com.iti.jets.carpoolingV1.editprofileactivity.EditProfileActivity;
import com.iti.jets.carpoolingV1.editprofileactivity.EditProfileController;
import com.iti.jets.carpoolingV1.loginactivity.LoginActivity;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class RegisterFragment extends Fragment{

	boolean flag = true;
	Uri selectedImageUri;
	Button registerBtn,loginBtn,calenderBtn;
	EditText nameEditText,passwordEditText,phoneEditText,dateEditText,emailEditText;
	String genderData;
	ImageView userImgView ;
	User newUser = new User();	
	Bitmap imgBitmap;
	ImageCompressionHandler imageHandler;
	EditProfileController controller;
	String filePath;
	RadioGroup radioSexGroup;
	RadioButton maleRadioBtn;
	View rootView;
	public static final int  REQUEST_CODE_FROM_GALLERY = 1;
	private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PHONE_REGEX = "\\d{11}";
	private static final String USERNAME_REGEX = "^[a-z0-9_-]{3,15}$";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	     rootView = inflater.inflate(R.layout.activity_registeration,container, false);
	     userImgView = (ImageView) rootView.findViewById(R.id.userImage);
		 registerBtn = (Button) rootView.findViewById(R.id.registerBtn);
		 loginBtn = (Button)  rootView.findViewById(R.id.loginBtn);
		 calenderBtn = (Button)  rootView.findViewById(R.id.calenderBtn);
		 nameEditText = (EditText)  rootView.findViewById(R.id.nameTxt);
		 passwordEditText = (EditText)  rootView.findViewById(R.id.passwordTxt);
		 phoneEditText = (EditText)  rootView.findViewById(R.id.PhoneTxt);
		 dateEditText = (EditText)  rootView.findViewById(R.id.dateTxt);
		 emailEditText = (EditText)  rootView.findViewById(R.id.EmailTxt);
		 radioSexGroup = (RadioGroup) rootView.findViewById(R.id.radioSexGroup);
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
		 Editor editor = sharedPreferences.edit();
		 editor.putBoolean("firstRunFlag", true);
		 editor.commit();
	
		 loginBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				UIManagerHandler.goToLogin(RegisterFragment.this.getActivity());
				
			}
		});
		 calenderBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDatePickerDialog();
			}
		});
		 registerBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if (phoneEditText.getText().toString().equals(""))
				{
					phoneEditText.setError("Phone field required");
				}
				else if(!Pattern.matches(PHONE_REGEX,phoneEditText.getText().toString().trim()))
				{
					phoneEditText.setError("Invalid phonenumber");
				}
				else if(nameEditText.getText().toString().equals(""))
				{
					nameEditText.setError("Name field required");
				}
				else if(!Pattern.matches(USERNAME_REGEX,nameEditText.getText().toString().trim()))
				{
					nameEditText.setError("Invalid Username");
				}
				else if(passwordEditText.getText().toString().equals(""))
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
				else
				{
					newUser.setName(nameEditText.getText().toString());
					newUser.setPhone(phoneEditText.getText().toString());
					newUser.setEmail(emailEditText.getText().toString());
					newUser.setDate(dateEditText.getText().toString());
					newUser.setPassword(passwordEditText.getText().toString());
					int selectedId = radioSexGroup.getCheckedRadioButtonId();
					maleRadioBtn = (RadioButton) rootView.findViewById(selectedId);
					genderData = maleRadioBtn.getText().toString();
					newUser.setGender(genderData);
					
					if(flag)
						
					{
						//Toast.makeText(getActivity().getApplicationContext(), "ENTEREEEEEEEEED", Toast.LENGTH_LONG).show();
						if(imgBitmap == null)
						{
				        	
//				        		Uri path = Uri.parse("android.resource://com.iti.jets.carpoolingV1/" + R.drawable.photo);
//				        		try {
//									imgBitmap = MediaStore.Images.Media.getBitmap(RegisterFragment.this.getActivity().getContentResolver(), path);
//								} catch (FileNotFoundException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								} catch (IOException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
				        	
							showDialog("You didn't choose an image");
						}
						else
						{
							RegisterationController controller = new RegisterationController(newUser,RegisterFragment.this,imgBitmap,filePath);
						}
						
					
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
		    super.onActivityResult(requestCode, resultCode, data);
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

		//Toast.makeText(RegisterFragment.this.getActivity().getApplicationContext(), "ENTERED FINAL", Toast.LENGTH_LONG).show();
		//Toast.makeText(RegisterFragment.this.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
		JSONObject recievedResult;
		JSONObject ResponseValue;
		String Exist = null;
	    try {
			 recievedResult = new JSONObject(result);
			 ResponseValue = recievedResult.getJSONObject("ResponseValue");
			 //Toast.makeText(RegisterFragment.this.getActivity().getApplicationContext(),  "TTTTTTTTTTTT"+"    "+ResponseValue.getString("register").toString() , Toast.LENGTH_LONG).show();
     		 Exist = ResponseValue.getString("register").toString();
			 //Toast.makeText(RegisterFragment.this.getActivity().getApplicationContext(),Exist, Toast.LENGTH_LONG).show();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(Exist.equals("true"))
		{
			Intent intent = new Intent(RegisterFragment.this.getActivity().getApplicationContext(),LoginActivity.class);
			
			startActivity(intent);
	 
		}
		else
		{
			AlertDialog alertDialog = new AlertDialog.Builder(
                    RegisterFragment.this.getActivity()).create();

		    // Setting Dialog Title
		    alertDialog.setTitle("Error");
		
		    // Setting Dialog Message
		    alertDialog.setMessage("This username already exists please choose different one");
		
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