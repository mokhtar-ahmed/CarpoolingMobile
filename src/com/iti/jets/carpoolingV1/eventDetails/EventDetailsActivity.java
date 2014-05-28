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
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

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


public class EventDetailsActivity extends Fragment implements OnItemClickListener{
	
	
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
	
	EventDetialsController controller;
	
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
         tp = (Button) rootView.findViewById(R.id.eventTimeTxt);
         dp = (Button) rootView.findViewById(R.id.eventDateTxt);
        
         controller = new EventDetialsController(this);
         
         ArrayList<Location> l =  EntityFactory.getLocationsInstance();
         for(int i=0; i< l.size(); i++ ){
       	  
       	  locs.add(l.get(i).getAddress());
       	  
         }
         ArrayList<Circle> l1 =  EntityFactory.getCirclesInstance();
         for(int i=0; i< l1.size(); i++ ){
       	  
       	  cirs.add(l1.get(i).getCircleName());
       	  
         }
         
         
         sendComment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				String commentText = writeComment.getText().toString();

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
						
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
		});
         
         toTxt.setOnClickListener(new View.OnClickListener() {
     		@Override
     		public void onClick(View v) {
     			// TODO Auto-generated method stub

     			selectedLocs.clear();
     		       final Builder builderSingle = new AlertDialog.Builder(getActivity());
     		       
     		       builderSingle.setIcon(R.drawable.ic_action_locate);
     		      
     		       builderSingle.setTitle("Select Location");
     		       
     			   builderSingle.setMultiChoiceItems(locs.toArray(new CharSequence[locs.size()]),null,   new DialogInterface.OnMultiChoiceClickListener(){

     					@Override
     					public void onClick(DialogInterface dialog,
     							int which, boolean isChecked) {
     						
     						if(isChecked)
     							selectedLocs.add(which);
     						else
     							selectedLocs.remove(which);
     					}});
     		      
     		       builderSingle.setNegativeButton("Cancel",
     		               new DialogInterface.OnClickListener() {

     		                   @Override
     		                   public void onClick(DialogInterface dialog, int which) {
     		                	   	
     		                
     		                   }
     		               });
     		       builderSingle.setPositiveButton("Save",
     		               new DialogInterface.OnClickListener() {

     		                   @Override
     		                   public void onClick(DialogInterface dialog, int which) {
     		                	  
     		                	   toTxt.setText("");
     		                	   	for(int s : selectedLocs)
     		                	   		toTxt.append(locs.get(s)+",");
     		                   }
     		               });


     		       builderSingle.show();
     		       
     	
     		}
     	});
         
         
           fromTxt.setOnClickListener(new View.OnClickListener() {
       		@Override
       		public void onClick(View v) {
       			// TODO Auto-generated method stub

       		       final Builder builderSingle = new AlertDialog.Builder(getActivity());
       		       
       		       builderSingle.setIcon(R.drawable.ic_action_locate);
       		      
       		       builderSingle.setTitle("Select Location");
       		       
       			   builderSingle.setSingleChoiceItems(locs.toArray(new CharSequence[locs.size()]),-1 ,   new DialogInterface.OnClickListener() {
     				
     				@Override
     				public void onClick(DialogInterface dialog, int which) {
     					// TODO Auto-generated method stub
     					selectedFrom = which;
     				}
     			});
       		      
       		       builderSingle.setNegativeButton("Cancel",
       		               new DialogInterface.OnClickListener() {

       		                   @Override
       		                   public void onClick(DialogInterface dialog, int which) {
       		                	   	
       		                
       		                   }
       		               });
       		       builderSingle.setPositiveButton("Save",
       		               new DialogInterface.OnClickListener() {

       		                   @Override
       		                   public void onClick(DialogInterface dialog, int which) {
       		                	   	
       		                	   fromTxt.setText(locs.get(selectedFrom));
       		                
       		                   }
       		               });

       		       builderSingle.show();
       		       
       	
       		}
       	});
           
           
           no_of_slots.setOnClickListener(new View.OnClickListener() {
         		@Override
         		public void onClick(View v) {
         			// TODO Auto-generated method stub

         		       final Builder builderSingle = new AlertDialog.Builder(getActivity());
         		       
         		       builderSingle.setIcon(R.drawable.ic_action_locate);
         		      
         		       builderSingle.setTitle("Avaliable Slots");
         		       
         			   builderSingle.setSingleChoiceItems(noOfSlotsArr,-1 ,   new DialogInterface.OnClickListener() {
       				
       				@Override
       				public void onClick(DialogInterface dialog, int which) {
       					// TODO Auto-generated method stub
       					
       					selectedNoOfSlots = Integer.parseInt(noOfSlotsArr[which]);
       				}
       			});
         		      
         		       builderSingle.setNegativeButton("Cancel",
         		               new DialogInterface.OnClickListener() {

         		                   @Override
         		                   public void onClick(DialogInterface dialog, int which) {
         		                	   	
         		                
         		                   }
         		               });
         		       builderSingle.setPositiveButton("Save",
         		               new DialogInterface.OnClickListener() {

         		                   @Override
         		                   public void onClick(DialogInterface dialog, int which) {
         		                	   	
         		                	   no_of_slots.setText(""+selectedNoOfSlots);
         		                   }
         		               });

         		       builderSingle.show();
         		       
         	
         		}
         	});

           dp.setOnClickListener(new View.OnClickListener() {

       		@Override
       		public void onClick(View v) {
       			// TODO Auto-generated method stub
       			new DatePickerDialog(getActivity(),mDateSetListener,mYear, mMonth, mDay).show();
       		}
       		
       	
       	});
             
             tp.setOnClickListener(new View.OnClickListener() {

       		@Override
       		public void onClick(View v) {
       			// TODO Auto-generated method stub
       			new TimePickerDialog(getActivity(),mTimeSetListener,mHour, mMinute,false).show();
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
		CustomUserBaseAdapter adapter = new CustomUserBaseAdapter(ac, usersList);
	    user.setAdapter(adapter);
	    user.setOnItemClickListener(this);

	    
	}
	void fillCommentList(){
		Activity ac = getActivity();
	    CustomCommentBaseAdapter adapter = new CustomCommentBaseAdapter(ac, commentsList);
	    comments.setAdapter(adapter);
	    comments.setOnItemClickListener(this);

	}
	   private DatePickerDialog.OnDateSetListener mDateSetListener =  new DatePickerDialog.OnDateSetListener() {
           
           public void onDateSet(DatePicker view, int yearSelected,int monthOfYear, int dayOfMonth) {
                            mYear = yearSelected;
                            mMonth = monthOfYear;
                            mDay = dayOfMonth;
                  
                            
                            dp.setText(""+mDay+"-"+mMonth+"-"+mYear);
                 }
};
       
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
	                
	public void onTimeSet(TimePicker view, int hourOfDay, int min) {
	                                 mHour = hourOfDay;
	                                 mMinute = min;
	                                 tp.setText(""+mHour+"-"+mMinute);
	                               }
	};
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		 inflater.inflate(R.menu.details_event, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.update:
			saveEvent();return true;	
		case R.id.request:
			UIManagerHandler.getoRequestsHome(getActivity(),idEvent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	private void saveEvent() {
		// TODO Auto-generated method stub
	
		Date d = new Date(mYear,mMonth,mDay,mHour,mMinute);
		Date d1 = new Date(currentYear,currentMonth,currentDay,currentHour,currentMinute);
		
		if(d.compareTo(d1) <= 0){
			
			dp.setError("Wrong date");
		}
		
		else {
			
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = formatter.format(d);
		System.out.println("selected date is  " + dateStr);
		
		
		JSONArray toLocations  = new JSONArray();
		JSONArray circlesList  = new JSONArray();
		String eventName = eventNameTxt.getText().toString();
		JSONObject input = new JSONObject();
		try {
			
			input.put("eventName",eventName);
			input.put("idEvent",idEvent);

			input.put("eventDate", dateStr);
			input.put("eventStatue", "update");
			input.put("noOfSlots", selectedNoOfSlots);

			  JSONObject loc = new JSONObject();
			  Location l = EntityFactory.getLocationByAddress(locs.get(selectedFrom));
	          loc.put("idLocation", l.getId());
	          loc.put("address", l.getAddress());
	        
	        input.put("location", loc);
	        
	        int userId = EntityFactory.getUserInstance().getId();
	        JSONObject user = new JSONObject();
            user.put("id", userId);
            input.put("user", user);
            
            
            JSONArray locsTo = new JSONArray();

			if(selectedLocs != null){
				
				int order = 1;
				
				for(Integer i  : selectedLocs){
					
					JSONObject eventLocationJson = new JSONObject();
					JSONObject locJson = new JSONObject();
					 Location ll = EntityFactory.getLocationByAddress(locs.get( i.intValue()));
					 locJson.put("id",ll.getId());
					eventLocationJson.put("toOrder",order++);
	                eventLocationJson.put("location", locJson);    
	                locsTo.put(eventLocationJson);
					System.out.println(ll.getId());	
				}
			}
			
		
			input.put("eventToLocations", locsTo);
	            
	      // Toast.makeText(getActivity().getApplicationContext(), input.toString(), Toast.LENGTH_LONG).show();
	           
	        System.out.println(input.toString());
			controller.updateEventHandler(input.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
		
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
