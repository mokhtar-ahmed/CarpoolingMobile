package com.iti.jets.carpoolingV1.editprofileactivity;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.common.ImageCompressionHandler;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfileFragement extends Fragment {

	View rootView;
	private ImageView userImgView,editNameImgView,editDobImgView;
	private TextView usernameTextView,dateTextView;
	private final int REQUEST_CODE_FROM_GALLERY = 01;
	private Uri selectedImageUri;
	Button doneBtn;
	String username,newDate;
	String flag = null;
	Bitmap imgBitmap;
	ImageCompressionHandler imageHandler;
	//EditProfileController controller;
	String filePath;
	RetrieveUserController controller;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
			 
	         rootView = inflater.inflate(R.layout.activity_edit_profile,container, false);
	         int userId = EntityFactory.getUserInstance().getId();
	        // controller = new RetrieveUserController(this, userId);
	         Intent intent = new Intent(getActivity().getApplicationContext(),EditProfileActivity.class);
	         startActivity(intent);
	         
	         
	         return rootView;
	  }
	public static void getResultFromWebService(String result) {
		// TODO Auto-generated method stub
		
	}
	
}
