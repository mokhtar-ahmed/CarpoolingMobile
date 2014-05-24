package com.iti.jets.carpoolingV1.deletecircle;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment.FragmentCallback;
import com.iti.jets.carpoolingV1.common.Circle;
import com.iti.jets.carpoolingV1.httphandler.AddUserToCircleServiceHandler;
import com.iti.jets.carpoolingV1.httphandler.DeleteCircleServiceHandler;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;

public class DeleteCircleController {

	String returnServiceOutput;
	Circle circleObj;
	FragmentCallback fragmentCallback;
	private DeleteCircleServiceHandler delCircleHanler;
	private String uri;
	
	public void setArguments(Circle circleValues,FragmentCallback fragmentCallback2) {
			circleObj = circleValues; 
			this.fragmentCallback = fragmentCallback2;
			delCircleHanler = new DeleteCircleServiceHandler();
			uri = HttpConstants.SERVER_URL + HttpConstants.DELETE_CIRCLE_URL;
			delCircleHanler.connectToWebService(circleObj,fragmentCallback2 ,uri);
		// TODO Auto-generated method stub
		
	}

	
	

}
