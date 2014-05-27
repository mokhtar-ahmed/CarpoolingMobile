package com.iti.jets.carpoolingV1.addcomment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.Resources;
import android.widget.ListView;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.common.Circle;
import com.iti.jets.carpoolingV1.pojos.Comment;
import com.iti.jets.carpoolingV1.retrieveallcircles.CirclesCustomArrayAdapter;

public class AddCommentController {

	
	
	AddCommentFragment addCommentFragment;
	AddCommentServiceHandler serviceHandler;
	String comment;
	public void setArguments(String comment,
			AddCommentFragment addCommentFragment) {
		// TODO Auto-generated method stub
		this.addCommentFragment = addCommentFragment;
		this.comment = comment; 
		serviceHandler = new AddCommentServiceHandler();
		serviceHandler.connectToWebService(AddCommentController.this,comment);
		
	}
	public void getAllEventComments(String result) {
		// TODO Auto-generated method stub
		addCommentFragment.getAllEventComments(result);
		
	
	}

}
