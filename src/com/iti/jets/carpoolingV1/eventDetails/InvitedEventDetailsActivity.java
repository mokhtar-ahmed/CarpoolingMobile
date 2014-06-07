package com.iti.jets.carpoolingV1.eventDetails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.addevent.AddEventController;
import com.iti.jets.carpoolingV1.eventshome.CustomBaseAdapter;
import com.iti.jets.carpoolingV1.pojos.Circle;
import com.iti.jets.carpoolingV1.pojos.Comment;
import com.iti.jets.carpoolingV1.pojos.CustomUser;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.pojos.Location;
import com.iti.jets.carpoolingV1.pojos.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemClickListener;


public class InvitedEventDetailsActivity extends Fragment implements OnItemClickListener{
	
	
	EditText eventNameTxt;
	Button fromTxt;
	Button toTxt;
	Button no_of_slots;
	ListView user;
	ListView comments;
	Button tp;
	Button dp;
	Button userJoin;


	int idEvent;
	
	View rootView ;
	String userState="Invited";
	
	ArrayList<CustomUser> usersList = new ArrayList<CustomUser>();
	ArrayList<Comment> commentsList = new ArrayList<Comment>();
	
	ProgressDialog prog;
	InvitedEventDetialsController controller;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		 

		 
         rootView = inflater.inflate(R.layout.activity_invited_event_details, container, false);
         
         eventNameTxt =   (EditText) rootView.findViewById(R.id.eventNameTxt);
         fromTxt =  (Button) rootView.findViewById(R.id.FromSpinner);
         toTxt =  (Button) rootView.findViewById(R.id.ToSpinner);
         no_of_slots = (Button) rootView.findViewById(R.id.avaliableSlots);
         
        // tp = (Button) rootView.findViewById(R.id.eventTimeTxt);
         dp = (Button) rootView.findViewById(R.id.eventDateTxt);
        
         controller = new InvitedEventDetialsController(this);
 
         setHasOptionsMenu(true);
          
         Bundle args = getArguments();
         
         idEvent = args.getInt("eventId", 0);
         userState  = args.getString("userState", "Invited");
         
         System.out.println("event id at event details fragemnet = " + idEvent + "****"+userState);
         
         JSONObject obj = new JSONObject();
         try {
			obj.put("idEvent", idEvent);
			controller.retrieveEventHandler(obj.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        return rootView;
    }

	void fillUsersList(){
	 
	}
	void fillCommentList(){

	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		if(userState.equals("Accepted")== true)
			inflater.inflate(R.menu.accepted_details_event, menu);
		else 
			inflater.inflate(R.menu.invited_details_event, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.join:
			JSONObject ob  = new JSONObject();
			int userId = EntityFactory.getUserInstance().getId();
			try {
				ob.put("eventId", idEvent);
				ob.put("userId", userId);
				controller.joinEventHandler(ob.toString());
				prog = ProgressDialog.show(getActivity(), "connection", "wait to store your request",true);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;	
		

		default:
			return super.onOptionsItemSelected(item);
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

	public void onPostExecute(String result) {
		// TODO Auto-generated method stub
		
	}
	
	
}
