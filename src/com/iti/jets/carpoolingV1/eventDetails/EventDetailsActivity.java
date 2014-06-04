package com.iti.jets.carpoolingV1.eventDetails;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
import android.widget.VideoView;
import android.widget.AdapterView.OnItemClickListener;


public class EventDetailsActivity extends Fragment {
	
	
	EditText eventNameTxt;
	Button fromTxt;
	Button toTxt;
	Button no_of_slots;
	ListView user;
	ListView comments;
	TimePicker tp;
	DatePicker dp;
	Button dateBtn;
	Button userJoin;
	Button sendComment;
	EditText writeComment;
	
	CustomUserBaseAdapter usersAdapter;
	CustomCommentBaseAdapter CommentsAdapter;
	  
	String[] noOfSlotsArr = new String[]{"1","2","3","4","5"};
	
	Calendar c = Calendar.getInstance();
	Calendar c1 = Calendar.getInstance();
	
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);
    int mHour = c.get(Calendar.HOUR);
    int mMinute = c.get(Calendar.MINUTE);

    Boolean dateFlag=true;
    Boolean firstLocation =true;
   
    Date d = new Date(); 
    
    ArrayList<Integer> selectedLocs = new ArrayList<Integer>();
    ArrayList<Integer> selectedCirs= new ArrayList<Integer>();
    ArrayList<Integer> selectedBlocked= new ArrayList<Integer>();
    
    int selectedFrom  = 0;
    int selectedNoOfSlots = 5;
    
	ArrayList<String> locs = new ArrayList<String>() ;
	ArrayList<String> locsFromList = new ArrayList<String>() ;
	ArrayList<String> locsToList = new ArrayList<String>() ;
	ArrayList<User> Users = new ArrayList<User>() ;
	ArrayList<String> cirs = new ArrayList<String>() ;
	ArrayList< String> ul = new ArrayList<String>();
	Set<String> selectedToLocs = new HashSet(0);
	String selectedFromLoc="";
	ProgressDialog prog;
	

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
         dateBtn = (Button) rootView.findViewById(R.id.eventDateTxt);
         sendComment = (Button) rootView.findViewById(R.id.sendComment);
         fromTxt =  (Button) rootView.findViewById(R.id.FromSpinner);
         toTxt =  (Button) rootView.findViewById(R.id.ToSpinner);
         no_of_slots = (Button) rootView.findViewById(R.id.avaliableSlots);
         user = (ListView) rootView.findViewById(R.id.usersList); 
         comments = (ListView) rootView.findViewById(R.id.commentsList); 
       
         controller = new EventDetialsController(this);
         
         ArrayList<Location> l =  EntityFactory.getLocationsInstance();
         for(int i=0; i< l.size(); i++ ){
       	  
       	  locs.add(l.get(i).getAddress());
       	  
         }
    
         
         
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
         
         dateBtn.setOnClickListener(new View.OnClickListener() {
     		
     		@Override
     		public void onClick(View v) {
     			
     			showTimePickerDialog();
     			
     		}
     	});
        
         toTxt.setOnClickListener(new View.OnClickListener() {
     		@Override
     		public void onClick(View v) {
     			// TODO Auto-generated method stub

     			   
     			
     		       final Builder builderSingle = new AlertDialog.Builder(getActivity());
     		       
     		       builderSingle.setIcon(R.drawable.ic_action_locate);
     		      
     		       builderSingle.setTitle("Select Location");
     		       
     		      locsToList.clear();
     		      
     		       for(String l : locs)
     		    	   locsToList.add(l);
       	
     		       if(selectedFromLoc.equals("") == false )
     		    	   locsToList.remove(selectedFromLoc);
     		       
     			   builderSingle.setMultiChoiceItems(locsToList.toArray(new CharSequence[locsToList.size()]),null,   new DialogInterface.OnMultiChoiceClickListener(){

     					@Override
     					public void onClick(DialogInterface dialog,
     							int which, boolean isChecked) {
     				
     						if(firstLocation){
     							selectedLocs.clear();
     							firstLocation = false;
     						}
     						
     						if(isChecked)
     							selectedLocs.add(which);
     						else
     							selectedLocs.remove(which);
     					}});
     		      
     		       builderSingle.setNegativeButton("Cancel",
     		               new DialogInterface.OnClickListener() {

     		                   @Override
     		                   public void onClick(DialogInterface dialog, int which) {
     		                	   	
     		                	   selectedLocs.clear();
     		                   }
     		               });
     		       builderSingle.setPositiveButton("Save",
     		               new DialogInterface.OnClickListener() {

     		                   @Override
     		                   public void onClick(DialogInterface dialog, int which) {
     		                	  
     		                	   if(selectedLocs.isEmpty()){
     		                		   toTxt.setError("Field Required");
     		                		   
     		                	   }else{
     		                	  
     		                		   toTxt.setText("");
     		                		   selectedToLocs.clear();
     		                	   	for(int s : selectedLocs){
     		                	   		toTxt.append(locsToList.get(s)+",");
     		                	   		selectedToLocs.add(locsToList.get(s));
     		                	   	}
     		                	   }
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
       		       
       		       locsFromList.clear();
       		     
       		       for(String l : locs)
       		    	   locsFromList.add(l);
       	
       		       for(String in : selectedToLocs){
       		    	 locsFromList.remove(in);
       		       }
       		       
       			   builderSingle.setSingleChoiceItems(locsFromList.toArray(new CharSequence[locsFromList.size()]),-1 ,   new DialogInterface.OnClickListener() {
     				
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
       		                	   	
       		                	   if(selectedFrom != -1){
       		                		   fromTxt.setText(locsFromList.get(selectedFrom));
       		                		   selectedFromLoc = locsFromList.get(selectedFrom);
       		                	   }
       		                	   else 
       		                		   fromTxt.setText("Field Required");
       		                
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
         		                	   	
         		                	   selectedNoOfSlots=5;
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
		 inflater.inflate(R.menu.details_event, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.update:
			saveEventHandler();
			return true;	
		case R.id.request:
			UIManagerHandler.getoRequestsHome(getActivity(),idEvent);
			return true;
		case R.id.cancel:
			prog.setMessage("cancelling the event");
			prog.show();
			JSONObject obj = new JSONObject();
			try {
				obj.put("idEvent", idEvent);
				controller.cancelEventHandler(obj.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return true;

		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	
	private void saveEvent() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = formatter.format(d);
		System.out.println("selected date is  " + dateStr);
		
		String eventName = eventNameTxt.getText().toString();
		
		JSONObject input = new JSONObject();
		try {
			
			input.put("eventName",eventName);
			input.put("idEvent",idEvent);

			input.put("eventDate", dateStr);
			input.put("eventStatue", "update");
			input.put("noOfSlots", selectedNoOfSlots);

			  JSONObject loc = new JSONObject();
			  Location l = EntityFactory.getLocationByAddress(selectedFromLoc);
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
				
				
				Iterator<String> it = selectedToLocs.iterator();
			while(it.hasNext()){
					
			     	String str = it.next();
					JSONObject eventLocationJson = new JSONObject();
					JSONObject locJson = new JSONObject();
					 Location ll = EntityFactory.getLocationByAddress(str);
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
			e.printStackTrace();
		}
	
}
		
	public void showTimePickerDialog() {
			
			LayoutInflater inflater=LayoutInflater.from(getActivity());
			  
			final Dialog dialog = new Dialog(getActivity());
			View root = inflater.inflate(R.layout.date_time_layout, null);
			//View root = findViewById(R.layout.date_time_layout);
		
		    dialog.setContentView(root);
		    dialog.setTitle("Select Event Date");
		    
		     dp = (DatePicker)root.findViewById(R.id.datePicker1);
		     tp = (TimePicker)root.findViewById(R.id.timePicker1);
			 Button setBtn = (Button)root.findViewById(R.id.setBtn);
			 Button cancelBtn = (Button)root.findViewById(R.id.cancelBtn);
			
			
			setBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					mDay = dp.getDayOfMonth();
					mMonth = dp.getMonth();
					mYear = dp.getYear();
					mMinute = tp.getCurrentMinute();
				    mHour = tp.getCurrentHour();
			
				    int currentYear = c1.get(Calendar.YEAR);
			        int currentMonth = c1.get(Calendar.MONTH);
			        int currentDay = c1.get(Calendar.DAY_OF_MONTH);
			        int currentHour = c1.get(Calendar.HOUR);
			        int currentMinute = c1.get(Calendar.MINUTE);

			        tp.setCurrentMinute(currentMinute);
			        tp.setCurrentHour(currentHour);
			        dp.init(currentYear, currentMonth, currentDay, null);

					 d = new Date(mYear-1900,mMonth,mDay,mHour,mMinute);
				     Date d1 = new Date(currentYear-1900,currentMonth,currentDay,currentHour,currentMinute);
					
				     if(d.compareTo(d1) <= 0){
						
						dateBtn.setText("Wrong Date");
						dateFlag = false;
						
					}else{
						
						dateFlag = true;
						dateBtn.setText(d.toString());
					
					}
					
				   
					dialog.dismiss();
				}
				
				
			});
			
			cancelBtn.setOnClickListener(new View.OnClickListener() {
				
		
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			
		
			dialog.show();
		}

	private void saveEventHandler(){
			
			Builder b = new AlertDialog.Builder(getActivity());
			b.setTitle("Save Event");
			b.setMessage("You want to save ?");
			b.setPositiveButton("Save", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					if(checkError())
						saveEvent();
				}
			});
			
			b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
				}
			});
			
			b.show();
			
			
		}
	
	private void exitHandler(){
			
			Builder b = new AlertDialog.Builder(getActivity());
			b.setTitle("Save Event");
			b.setMessage("You want to Exit ?");
			b.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			
			b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
				}
			});
			
			b.show();
			
			
		}

	private boolean checkError() {
			
			Boolean flag = true;
			
			if(eventNameTxt.getText().toString().equals("") == true){
				eventNameTxt.setError("Field Required");
				flag = false;
			}
			
			if(no_of_slots.getText().toString().equals("Avaliable Slots") == true ||
					no_of_slots.getText().toString().equals("Field Required") == true	){
				no_of_slots.setText("Field Required");
				flag = false;
			}
			
			if(dateFlag == false){
				dateBtn.setText("Field Required");
				flag = false;
			}	
		
			if(fromTxt.getText().toString().equals("From") == true ||  selectedFromLoc.equals("") == true){
				fromTxt.setText("Field Required");
				flag = false;
			}
				
			if(toTxt.getText().toString().equals("To") == true ||  selectedToLocs.isEmpty()){
				toTxt.setText("Field Required");
				flag = false;
			}
			
			if(dateBtn.getText().toString().equals("Date") == true || dateFlag == false){
				toTxt.setText("Field Required");
				flag = false;
			}
			
			return flag;
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
	
	
}
