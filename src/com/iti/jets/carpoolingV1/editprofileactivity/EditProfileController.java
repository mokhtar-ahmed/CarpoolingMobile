package com.iti.jets.carpoolingV1.editprofileactivity;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.common.ImageHandler;
import com.iti.jets.carpoolingV1.common.ImageLoadingUtils;
import com.iti.jets.carpoolingV1.httphandler.EditProfileServiceHandler;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;
import com.iti.jets.carpoolingV1.httphandler.SyncContactsServiceHandler;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
//Controller class collects the data and sends it to WebServiceHandler
public class EditProfileController {

	private ImageLoadingUtils utils;
	private Bitmap bmpScaled;
	private String imageString;
	private ImageHandler imgHandler ;
	private EditProfileServiceHandler editProfileHanlerObject;
	private String uri = HttpConstants.SERVER_URL+HttpConstants.Edit_Profile_URL;
	EditProfileFragement editProfileObj;

	
	public EditProfileController()
	{
		
	}
	public EditProfileController(EditProfileFragement editProfileFragement)
	{
		editProfileObj = editProfileFragement;
		imgHandler = new ImageHandler();
	}
	
	public void setArguments(String username,String dateOfBirth,Bitmap bitmapImage,boolean imageChangedFlag, String filePath, EditProfileFragement editProfileFragement)
	{
		utils = new ImageLoadingUtils(editProfileFragement.getActivity().getApplicationContext());
		if(!imageChangedFlag)
		{
			bmpScaled = editProfileFragement.imgBitmap;
		}
		else
		{
			bmpScaled = utils.decodeBitmapFromPath(filePath);
			
		}
		
		imgHandler = new ImageHandler();
		imageString = imgHandler.BitMapToString(bmpScaled);
		this.editProfileObj = editProfileFragement;
		JSONObject userDataObj = new JSONObject();
		JSONObject imgJsonObj = new JSONObject();
		
		try {
			userDataObj.put("userId", EntityFactory.getUserInstance().getId());
			userDataObj.put("username", username);
			userDataObj.put("dateOfBirth",dateOfBirth);
			imgJsonObj.put("image", imageString);
			imgJsonObj.put("imageChangedFlag", imageChangedFlag);
			editProfileHanlerObject = new EditProfileServiceHandler();
			editProfileHanlerObject.connectToWebService(userDataObj,imgJsonObj,uri,editProfileObj);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getServiceData(String result) {
		// TODO Auto-generated method stub
		editProfileObj.getServiceData(result);
	}
}
