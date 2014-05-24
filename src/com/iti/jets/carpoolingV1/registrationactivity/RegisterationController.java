package com.iti.jets.carpoolingV1.registrationactivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.iti.jets.carpoolingV1.common.ImageHandler;
import com.iti.jets.carpoolingV1.common.ImageLoadingUtils;
import com.iti.jets.carpoolingV1.common.User;

import com.iti.jets.carpoolingV1.httphandler.HttpConstants;
import com.iti.jets.carpoolingV1.httphandler.RegisterationServiceHandler;



public class RegisterationController {

	public RegisterationServiceHandler serviceHandler;
	RegisterFragment registerActivity;
	private Bitmap bmpScaled;
	private String imageString;
	private ImageHandler imgHandler ;
	private ImageLoadingUtils utils;
	public RegisterationController(User newUser,RegisterFragment registerActivity, Bitmap imgBitmap, String filePath) {
		
		
		utils = new ImageLoadingUtils(registerActivity.getActivity().getApplicationContext());
		bmpScaled = utils.decodeBitmapFromPath(filePath);
		imgHandler = new ImageHandler();
		imageString = imgHandler.BitMapToString(bmpScaled);
		this.registerActivity = registerActivity;
		
		
		
		//Toast.makeText(registerActivity.getApplicationContext(), "EnteredController", Toast.LENGTH_LONG).show();
		serviceHandler = new RegisterationServiceHandler(RegisterationController.this);
		String url =  HttpConstants.SERVER_URL+ HttpConstants.REGISTER_SERVICE_URL;
     	serviceHandler.connectToWebService(newUser,url,imageString);	
     	
		
	}
	public void getResultFromWebservice(String result) {
		// TODO Auto-generated method stub
		
		registerActivity.getResultFromWebservice(result);
	
	}
	
	public Activity getRef()
	{
		return registerActivity.getActivity();
	}

	
}

