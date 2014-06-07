package com.iti.jets.carpoolingV1.eventDetails;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.internal.ed;
import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.addevent.AddEventController;
import com.iti.jets.carpoolingV1.eventshome.CustomBaseAdapter;
import com.iti.jets.carpoolingV1.httphandler.AddCommentServiceHandler;
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
import android.widget.VideoView;
import android.widget.AdapterView.OnItemClickListener;


public class AcceptedEventDetailsActivity extends Fragment {
	
	
	EditText eventNameTxt;
	Button fromTxt;
	Button toTxt;
	Button no_of_slots;
	ListView user;
	ListView comments;
	Button tp;
	Button dp;
	Button userJoin;
	Button sendComment;
	EditText writeComment;
	
	String[] noOfSlotsArr = new String[]{"1","2","3","4","5"};
	
	Calendar c = Calendar.getInstance();
	Calendar c1 = Calendar.getInstance();
	
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);
    int mHour = c.get(Calendar.HOUR);
    int mMinute = c.get(Calendar.MINUTE);

    int currentYear = c1.get(Calendar.YEAR);
    int currentMonth = c1.get(Calendar.MONTH);
    int currentDay = c1.get(Calendar.DAY_OF_MONTH);
    int currentHour = c1.get(Calendar.HOUR);
    int currentMinute = c1.get(Calendar.MINUTE);

    CustomUserBaseAdapter usersAdapter;
	CustomCommentBaseAdapter CommentsAdapter;
	
    
    ArrayList<Integer> selectedLocs = new ArrayList<Integer>();
    ArrayList<Integer> selectedCirs= new ArrayList<Integer>();
    ArrayList<Integer> selectedBlocked= new ArrayList<Integer>();
    
    int selectedFrom ;
    int selectedNoOfSlots ;
    
	ArrayList<String> locs = new ArrayList<String>() ;
	ArrayList<User> Users = new ArrayList<User>() ;
	ArrayList<String> cirs = new ArrayList<String>() ;
	ArrayList< String> ul = new ArrayList<String>();
	Dialog prog ;

	int idEvent;
	View rootView ;
	 
	ArrayList<CustomUser> usersList = new ArrayList<CustomUser>();
	ArrayList<Comment> commentsList = new ArrayList<Comment>();
	
	AcceptedEventDetialsController controller;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		 

         rootView = inflater.inflate(R.layout.activity_event_details, container, false);
         
         eventNameTxt =   (EditText) rootView.findViewById(R.id.eventNameTxt);
         writeComment =   (EditText) rootView.findViewById(R.id.writeCommentTxt);
         
         sendComment = (Button) rootView.findViewById(R.id.sendComment);
         fromTxt =  (Button) rootView.findViewById(R.id.FromSpinner);
         toTxt =  (Button) rootView.findViewById(R.id.ToSpinner);
         no_of_slots = (Button) rootView.findViewById(R.id.avaliableSlots);
         user = (ListView) rootView.findViewById(R.id.usersList); 
         comments = (ListView) rootView.findViewById(R.id.commentsList); 
         //tp = (Button) rootView.findViewById(R.id.eventTimeTxt);
         dp = (Button) rootView.findViewById(R.id.eventDateTxt);
        
         controller = new AcceptedEventDetialsController(this);
           
         sendComment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				String commentText = writeComment.getText().toString();
				writeComment.setText("");
				JSONObject commentJson = new JSONObject();
				JSONObject owner = new JSONObject();
				JSONObject event = new JSONObject();
				int id = EntityFactory.getUserInstance().getId();
				
					try {
						owner.put("id", id);
						event.put("id", idEvent);
						
						commentJson.put("owner",owner);
						commentJson.put("event",event);
						commentJson.put("text", commentText);
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    //get current date time with Date()
						Date date = new Date();
						System.out.println(dateFormat.format(date));
					 
						//get current date time with Calendar()
						Calendar cal = Calendar.getInstance();
						System.out.println(dateFormat.format(cal.getTime()));
						commentJson.put("date",dateFormat.format(date));
		
						new AddCommentServiceHandler(commentJson.toString());
						
						Comment newComment = new Comment();
						newComment.setDate(date);
						newComment.setImage("");
						newComment.setText(commentText);
						newComment.setUsername( EntityFactory.getUserInstance().getUsername());
						
						commentsList.add(newComment);
						CommentsAdapter.notifyDataSetChanged();
			
						
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
		});
      
         setHasOptionsMenu(true);
          
         Bundle args = getArguments();
         
         idEvent = args.getInt("eventId", 0);
    
         System.out.println("event id at event details fragemnet = " + idEvent);
         
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
		Activity ac = getActivity();
		usersAdapter = new CustomUserBaseAdapter(ac, usersList);
	    user.setAdapter(usersAdapter);
	    user.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				showUsersListDialog();
			}
		});

	    
	}
	
	void fillCommentList(){
		Activity ac = getActivity();
	    CommentsAdapter = new CustomCommentBaseAdapter(ac, commentsList);
	    comments.setAdapter(CommentsAdapter);
	    comments.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			
				showCommentsListDialog();
			}
		});

	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		 inflater.inflate(R.menu.accepted_details_event, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}


	private void showCommentsListDialog() {
	
	
	LayoutInflater inflater=LayoutInflater.from(getActivity());
	View root = inflater.inflate(R.layout.activity_events_home, null);
	ListView newCommentList = (ListView) root.findViewById(R.id.home_events_list);

	  CustomCommentBaseAdapter adapter = new CustomCommentBaseAdapter(getActivity(), commentsList);
	  newCommentList.setAdapter(adapter);
	    
	Dialog d = new Dialog(getActivity());
	d.setTitle("Comments");
	d.setContentView(root);
	d.show();
}

	private void showUsersListDialog() {
	// TODO Auto-generated method stub
	LayoutInflater inflater=LayoutInflater.from(getActivity());
	View root = inflater.inflate(R.layout.activity_events_home, null);

	ListView newUsersList = (ListView) root.findViewById(R.id.home_events_list);

	CustomUserBaseAdapter adapter = new CustomUserBaseAdapter(getActivity(), usersList);
	newUsersList.setAdapter(adapter);
	//newUsersList.setOnItemClickListener(this);

	Dialog d = new Dialog(getActivity());
	d.setTitle("Users");
	d.setContentView(root);
	d.show();
}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.leave:
			JSONObject obj = new JSONObject();
			try {
				
				obj.put("eventId", idEvent);
				obj.put("userId", EntityFactory.getUserInstance().getId().intValue());
				
				controller.leaveEventHandler(obj.toString());
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;	
		

		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	
	

	
	
}
