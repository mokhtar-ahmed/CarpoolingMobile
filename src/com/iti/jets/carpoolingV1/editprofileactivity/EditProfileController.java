package com.iti.jets.carpoolingV1.editprofileactivity;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.common.ImageHandler;
import com.iti.jets.carpoolingV1.common.ImageLoadingUtils;
import com.iti.jets.carpoolingV1.httphandler.EditProfileServiceHandler;
import com.iti.jets.carpoolingV1.httphandler.SyncContactsServiceHandler;

import android.graphics.Bitmap;
import android.os.Bundle;
//Controller class collects the data and sends it to WebServiceHandler
public class EditProfileController {

	private ImageLoadingUtils utils;
	private Bitmap bmpScaled;
	private String imageString;
	private ImageHandler imgHandler ;
	private EditProfileServiceHandler editProfileHanlerObject;
	private String uri = "http://192.168.1.4:8088/Carpoolingbackend/services/editprofileservice/edit";
	EditProfileActivity editProfileObj;
	
	public EditProfileController()
	{
		
	}
	public EditProfileController(EditProfileActivity obj)
	{
		editProfileObj = obj;
		imgHandler = new ImageHandler();
	}
	
	public void setArguments(String username,String dateOfBirth,Bitmap bitmapImage,String filePath)
	{
		
		bmpScaled = utils.decodeBitmapFromPath(filePath);
		imageString = imgHandler.BitMapToString(bmpScaled);
		JSONObject userDataObj = new JSONObject();
		JSONObject imgJsonObj = new JSONObject();
		
		try {
			userDataObj.put("name", username);
			userDataObj.put("dateOfBirth",dateOfBirth);
			imgJsonObj.put("image", imageString);
			
			editProfileHanlerObject = new EditProfileServiceHandler();
			editProfileHanlerObject.connectToWebService(userDataObj,imgJsonObj,uri);
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
