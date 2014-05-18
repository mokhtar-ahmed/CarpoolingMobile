package com.iti.jets.carpoolingV1.registrationactivity;
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;

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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;


public class RegisterActivity extends Activity{

	boolean flag = true;
	Uri selectedImageUri;
	Button registerBtn,loginBtn;
	EditText nameEditText,passwordEditText,phoneEditText,dateEditText,emailEditText;
	String genderData;
	ImageView userImgView ;
	User newUser = new User();	
	Bitmap imgBitmap;
	ImageCompressionHandler imageHandler;
	EditProfileController controller;
	String filePath;
	RadioButton maleRadioBtn,femaleRadioBtn;
	public static final int  REQUEST_CODE_FROM_GALLERY = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_registeration);
		 userImgView = (ImageView) findViewById(R.id.userImage);
		 registerBtn = (Button) findViewById(R.id.registerBtn);
		 loginBtn = (Button) findViewById(R.id.loginBtn);
		 nameEditText = (EditText) findViewById(R.id.nameTxt);
		 passwordEditText = (EditText) findViewById(R.id.passwordTxt);
		 phoneEditText = (EditText) findViewById(R.id.PhoneTxt);
		 dateEditText = (EditText) findViewById(R.id.dateTxt);
		 emailEditText = (EditText) findViewById(R.id.EmailTxt);
		 maleRadioBtn = (RadioButton) findViewById(R.id.maleRadioBtn);
		 femaleRadioBtn = (RadioButton) findViewById(R.id.femaleRadioBtn);
		 if(maleRadioBtn.isChecked())
		 {
			 genderData = "Male";
		 }
		 else
		 {
			 genderData = "Female";
		 }
		 loginBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				UIManagerHandler.goToLogin(RegisterActivity.this);
				
			}
		});
		 registerBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				newUser.setName(nameEditText.getText().toString());
				newUser.setPhone(phoneEditText.getText().toString());
				newUser.setEmail(emailEditText.getText().toString());
				newUser.setDate(dateEditText.getText().toString());
				newUser.setPassword(passwordEditText.getText().toString());
				newUser.setGender(genderData);
				
				if(flag)
					
				{
					Toast.makeText(getApplicationContext(), "ENTEREEEEEEEEED", Toast.LENGTH_LONG).show();
					RegisterationController controller = new RegisterationController(newUser,RegisterActivity.this,imgBitmap,filePath);
				
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
		 

	}

	 public void onActivityResult(int requestCode, int resultCode, Intent data) {
		    super.onActivityResult(requestCode, resultCode, data);
		    if (resultCode == RESULT_OK) {
	                
	        	selectedImageUri = data.getData();
	        	imageHandler = new ImageCompressionHandler(data.getDataString(),RegisterActivity.this);
	          
	        } 

	    }

		public void sendBitMapImg(Bitmap bitmapImg,String filePath) {
			// TODO Auto-generated method stub
			this.filePath = filePath;
			if(bitmapImg == null)
			{
				Toast.makeText(getApplicationContext(), "NULLLLLLLLIMAGE", Toast.LENGTH_LONG).show();
			}
			imgBitmap = bitmapImg;
			userImgView.setImageBitmap(imgBitmap);
		}
			
		
	public void getResultFromWebservice(String result) {

		Toast.makeText(getApplicationContext(), "ENTERED FINAL", Toast.LENGTH_LONG).show();
		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
		JSONObject recievedResult;
		JSONObject ResponseValue;
		String Exist = null;
	    try {
			 recievedResult = new JSONObject(result);
			 ResponseValue = recievedResult.getJSONObject("ResponseValue");
			 Toast.makeText(getApplicationContext(),  "TTTTTTTTTTTT"+"    "+ResponseValue.getString("register").toString() , Toast.LENGTH_LONG).show();
     		 Exist = ResponseValue.getString("register").toString();
			 Toast.makeText(getApplicationContext(),Exist, Toast.LENGTH_LONG).show();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(Exist.equals("true"))
		{
			Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
			
			startActivity(intent);
	 
		}
		else
		{
			AlertDialog alertDialog = new AlertDialog.Builder(
                    RegisterActivity.this).create();

		    // Setting Dialog Title
		    alertDialog.setTitle("Error");
		
		    // Setting Dialog Message
		    alertDialog.setMessage("This username already exists please choose different one");
		
		    // Setting Icon to Dialog
		    alertDialog.setIcon(R.drawable.tick3);
		    
		
		    
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
	
}
