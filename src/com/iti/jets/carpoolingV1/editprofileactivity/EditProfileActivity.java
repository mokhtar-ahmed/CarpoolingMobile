package com.iti.jets.carpoolingV1.editprofileactivity;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.common.ImageCompressionHandler;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class EditProfileActivity extends Activity {

	private ImageView userImgView,editNameImgView,editDobImgView;
	private TextView usernameTextView,dateTextView;
	private final int REQUEST_CODE_FROM_GALLERY = 01;
	private Uri selectedImageUri;
	Button doneBtn;
	String username,newDate;
	String flag = null;
	Bitmap imgBitmap;
	ImageCompressionHandler imageHandler;
	EditProfileController controller;
	String filePath;
	JSONObject userToRetrieve;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
		setContentView(R.layout.activity_edit_profile);
		controller = new EditProfileController(this);
		userImgView = (ImageView)findViewById(R.id.userImgView);
		editNameImgView = (ImageView)findViewById(R.id.editnameImgView);
		editDobImgView = (ImageView)findViewById(R.id.editdateImgView);
		usernameTextView = (TextView)findViewById(R.id.NametextView);
		dateTextView = (TextView)findViewById(R.id.dateTextView);
		doneBtn = (Button)findViewById(R.id.doneBtn);
		int userId = EntityFactory.getUserInstance().getId();
		RetrieveUserController retController = new RetrieveUserController(this,userId);
		
		 
		
		
		doneBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				String user_name =  usernameTextView.getText().toString();
				String user_Dob  = dateTextView.getText().toString();
				controller.setArguments(user_name,user_Dob,imgBitmap,filePath);
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
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),EditNameActivity.class);
				intent.putExtra("username",usernameTextView.getText());
				startActivity(intent);
				
				
			}
		}) ;
		editDobImgView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),EditDateActivity.class);
				intent.putExtra("date",dateTextView.getText());
				startActivity(intent);
				
			}
		});
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    if (resultCode == RESULT_OK) {
                
        	selectedImageUri = data.getData();
        	//imageHandler = new ImageCompressionHandler(data.getDataString(),EditProfileActivity.this);
          
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

	public void getServiceData(String result) {
		// TODO Auto-generated method stub
		
		Toast.makeText(getApplicationContext(), "UUUUUUUUUUUUUUU "+"   "+result, Toast.LENGTH_LONG).show();
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
			Toast.makeText(getApplicationContext(), userToRetrieve.getString("name"), Toast.LENGTH_LONG).show();
			Toast.makeText(getApplicationContext(), userToRetrieve.getString("imageString"), Toast.LENGTH_LONG).show();
			 usernameTextView.setText(userToRetrieve.getString("name"));
			 
//			 
//			 byte[] imagBytes;
//			try {
//				imagBytes = userToRetrieve.getString("imageString").getBytes("UTF8");
//				System.out.println(imagBytes);
//	        	Bitmap bitmap = new BitmapFactory().decodeFile(userToRetrieve.getString("imageString"));
////			// Bitmap bitMapImage = BitmapFactory.decodeFile(userToRetrieve.getString("imageString"));
//			 System.out.println(bitmap);
//	         userImgView.setImageBitmap(bitmap);
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			 String strBase64=Base64.encodeToString(imagBytes, 0);
//	        	//byte[] imagBytes = imageString.getBytes();
//			 Log.v("++++++++++++++++++++++++++test",""+Base64.DEFAULT);
//			// Bitmap bitmap = Base64.decode(userToRetrieve.getString("imageString").getBytes(), REQUEST_CODE_FROM_GALLERY);.
			 
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
	

}
