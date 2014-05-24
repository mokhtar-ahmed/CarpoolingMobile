package com.iti.jets.carpoolingV1.eventDetails;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;


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
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		 
	//	 super.onCreateView(inflater, container, savedInstanceState);
		 
         rootView = inflater.inflate(R.layout.activity_event_details, container, false);
        
//         eventName= (TextView) rootView.findViewById(R.id.eventName);
//         eventDate= (TextView) rootView.findViewById(R.id.eventDate);
//         eventFrom= (TextView) rootView.findViewById(R.id.eventFrom);
//         eventTo  = (TextView) rootView.findViewById(R.id.eventTo);
//         noOfSlots= (TextView) rootView.findViewById(R.id.noOfSlots);
//         members  = (TextView) rootView.findViewById(R.id.members);
//         comments = (TextView) rootView.findViewById(R.id.comments);
//         

         eventName.setText("");
         eventDate.setText("");
         eventFrom.setText("");
         eventTo.setText("");
         noOfSlots.setText("");
         members.setText("");
         comments.setText("");
         
         setHasOptionsMenu(true);
         
         controller = new EventDetialsController(this);
         
         Bundle args = getArguments();
         int eventId = args.getInt("eventId", 0);
         
         System.out.println("event id at event details fragemnet = " + eventId);
         
         JSONObject obj = new JSONObject();
         try {
			obj.put("idEvent", eventId);
			controller.retrieveEventHandler(obj.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        return rootView;
    }

	
}
