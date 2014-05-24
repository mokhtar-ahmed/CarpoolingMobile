package com.iti.jets.carpoolingV1.addcircleactivity;

import org.apache.http.conn.HttpHostConnectException;
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.common.ImageHandler;
import com.iti.jets.carpoolingV1.common.ImageLoadingUtils;
import com.iti.jets.carpoolingV1.editprofileactivity.EditProfileActivity;
import com.iti.jets.carpoolingV1.httphandler.AddCircleServiceHandler;
import com.iti.jets.carpoolingV1.httphandler.EditProfileServiceHandler;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;

import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment;

import com.iti.jets.carpoolingV1.sharedlayout.MainActivity;
import com.iti.jets.carpoolingV1.synccontactsactivity.SyncContactsFragment;
import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleFragment.FragmentCallback;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class AddCircleController {

	private ImageLoadingUtils utils;
	private String imageStr = "EMPTY";
	private AddCircleFragment addCircleActObj;
	private ImageHandler imgHandler;
	private Bitmap bmpScaled;
	private String imageString;
	private AddCircleServiceHandler addCircleHanlerObject;
	private String uri = HttpConstants.SERVER_URL +  HttpConstants.ADD_CIRCLE_SERVICE_URL;
	private FragmentCallback fragmentCallback;
	
	public AddCircleController() {
		// TODO Auto-generated constructor stub
		
		imgHandler = new ImageHandler();
	}
	public AddCircleController(AddCircleFragment obj)
	{
		this.addCircleActObj = obj;
		//imgHandler = new ImageHandler();
	}
	
	public void setArguments(String circleName, Bitmap imgBitmap,
			String filePath, FragmentCallback fragmentCallback2) {
		// TODO Auto-generated method stub
		//bmpScaled = utils.decodeBitmapFromPath(filePath);
		//imageString = imgHandler.BitMapToString(bmpScaled);
		
		JSONObject circleDataObj = new JSONObject();
		JSONObject imgJsonObj = new JSONObject();
		fragmentCallback = fragmentCallback2;
		try {
			circleDataObj.put("circleName", circleName);
		
			imgJsonObj.put("image", imageStr);
			
			addCircleHanlerObject = new AddCircleServiceHandler(this);
			addCircleHanlerObject.connectToWebService(circleDataObj,imgJsonObj,uri);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void getServiceData(String result) {
		// TODO Auto-generated method stub
		Log.d("CONT",result);
		addCircleActObj = new AddCircleFragment();
		if(addCircleActObj == null)
		{
			Log.d("DA%LT","DA%LT");
		}
		else if((result != null))
		{
			Log.d("CONTENTERED",result);
			 fragmentCallback.onTaskDone(result);
			
		}
		else
		{
			Log.d("CONTEXIT",result);
		}
	}

}
