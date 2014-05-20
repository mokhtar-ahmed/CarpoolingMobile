package com.iti.jets.carpoolingV1.eventDetails;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.comment.CommentController;
import com.iti.jets.carpoolingV1.common.FragmentCallback;


import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class EventDetailsActivity extends Fragment{

	TextView eventName;
	TextView eventDate;
	TextView eventFrom;
	TextView eventTo;
	TextView noOfSlots;
	TextView comments;
	TextView members;
	View rootView ;
	EventDetialsController controller;
	ImageView sendCommentIcon;
	EditText userComment;
	String commentToSend = null;
	CommentController commentControl;
	
	public interface FragmentCallback {
	    //public void onTaskDone(String result);

		public void onTaskDone(String result);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		 
	//	 super.onCreateView(inflater, container, savedInstanceState);
		 
         rootView = inflater.inflate(R.layout.activity_event_details, container, false);
        
         eventName= (TextView) rootView.findViewById(R.id.eventName);
         eventDate= (TextView) rootView.findViewById(R.id.eventDate);
         eventFrom= (TextView) rootView.findViewById(R.id.eventFrom);
         eventTo  = (TextView) rootView.findViewById(R.id.eventTo);
         noOfSlots= (TextView) rootView.findViewById(R.id.noOfSlots);
         members  = (TextView) rootView.findViewById(R.id.members);
         comments = (TextView) rootView.findViewById(R.id.comments);
         userComment = (EditText) rootView.findViewById(R.id.userCommentTxt);
         sendCommentIcon = (ImageView) rootView.findViewById(R.id.sendCommentIcon);

         
         sendCommentIcon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!userComment.getText().equals(""))
				{
					commentToSend = userComment.getText().toString();
					userComment.setText("");
					commentControl = new CommentController();
//					commentControl.setArguments(commentToSend, new FragmentCallback() {
//						
//						@Override
//						public void onTaskDone(String result) {
//							// TODO Auto-generated method stub
//							
//							Toast.makeText(getActivity().getApplicationContext(), result,Toast.LENGTH_LONG).show();
//							
//						}
//					});
					
				}
				
			}
		});
         
         
         
         eventName.setText("");
         eventDate.setText("");
         eventFrom.setText("");
         eventTo.setText("");
         noOfSlots.setText("");
         members.setText("");
         comments.setText("");
         
         setHasOptionsMenu(true);
         
         controller = new EventDetialsController(this);
         
         JSONObject obj = new JSONObject();
         try {
			obj.put("idEvent", 1);
			controller.retrieveEventHandler(obj.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        return rootView;
    }

	
	
}

