package com.iti.jets.carpoolingV1.addevent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.R.array;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;

import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.text.style.BulletSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.iti.jets.carpoolingV1.pojos.*;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

public class AddEventActivity extends Fragment{

		EditText eventNameTxt;
		Button fromTxt;
		Button toTxt;
		Button no_of_slots;
		Button circles;
		Button dateBtn;
		TimePicker tp;
		DatePicker dp;
		
		String[] noOfSlotsArr = new String[]{"1","2","3","4","5"};
		
		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		
		
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        int mHour = c.get(Calendar.HOUR);
        int mMinute = c.get(Calendar.MINUTE);

        Boolean dateFlag=false;
        Boolean firstLocation =true;
		Boolean firstCircle = true;
		
        Date d; 
        
        ArrayList<Integer> selectedLocs = new ArrayList<Integer>();
        ArrayList<Integer> selectedCirs= new ArrayList<Integer>();
        ArrayList<Integer> selectedBlocked= new ArrayList<Integer>();
        
        int selectedFrom  = -1;
        int selectedNoOfSlots = 5;
        
		ArrayList<String> locs = new ArrayList<String>() ;
		ArrayList<String> locsFromList = new ArrayList<String>() ;
		ArrayList<String> locsToList = new ArrayList<String>() ;
		ArrayList<User> Users = new ArrayList<User>() ;
		ArrayList<String> cirs = new ArrayList<String>() ;
		ArrayList< String> ul = new ArrayList<String>();
		ArrayList<String> selectedToLocs = new ArrayList<String>();
		String selectedFromLoc="";
		ProgressDialog prog;
		
		AddEventController cont ;
		
	public AddEventActivity(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		 
		// super.onCreateView(inflater, container, savedInstanceState);
		 
        View rootView = inflater.inflate(R.layout.activity_add_event, container, false);

       eventNameTxt =   (EditText) rootView.findViewById(R.id.eventNameTxt);
       fromTxt =  (Button) rootView.findViewById(R.id.FromSpinner);
       toTxt =  (Button) rootView.findViewById(R.id.ToSpinner);
       no_of_slots = (Button) rootView.findViewById(R.id.avaliableSlots);
       circles = (Button) rootView.findViewById(R.id.circlesBtn); 
       dateBtn = (Button) rootView.findViewById(R.id.eventDateTxt);
       
       cont = new AddEventController(this);
  
      ArrayList<Location> l =  EntityFactory.getLocationsInstance();
      for(int i=0; i< l.size(); i++ ){
    	  
    	  locs.add(l.get(i).getAddress());
    	  
      }
      
     
      ArrayList<Circle> l1 =  EntityFactory.getCirclesInstance();
      for(int i=0; i< l1.size(); i++ ){
    	  
    	  cirs.add(l1.get(i).getCircleName());
    	  
      }
 
     
      
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

      circles.setOnClickListener(new View.OnClickListener() {
  		@Override
  		public void onClick(View v) {
  			// TODO Auto-generated method stub

  			   selectedCirs.clear();

  			   final Builder builderSingle = new AlertDialog.Builder(getActivity());
  		       
  		       builderSingle.setIcon(R.drawable.ic_action_group);
  		      
  		       builderSingle.setTitle("Select Circles");
  		       
  			   builderSingle.setMultiChoiceItems(cirs.toArray(new CharSequence[cirs.size()]), null,   new DialogInterface.OnMultiChoiceClickListener(){

  					@Override
  					public void onClick(DialogInterface dialog,
  							int which, boolean isChecked) {
  						if(firstCircle){
  							selectedCirs.clear();
  							firstCircle = false;
  						}
  						if(isChecked)
  							selectedCirs.add(which);
  						else 
  							selectedCirs.remove(which);
  						
  					}});
  		      
  		       builderSingle.setNegativeButton("Block",
  		               new DialogInterface.OnClickListener() {

  		                   @Override
  		                   public void onClick(DialogInterface dialog, int which) {
  		                	//   showBlockUserDialog();
  		                  
  		                	 if(selectedCirs != null){
  		                		 JSONObject circlesIds = new JSONObject();
  		                		 JSONArray circlesList = new JSONArray(); 
  		                		
  		       				for(Integer i  : selectedCirs){
  		       					int cirId = EntityFactory.getCircleByName(cirs.get(i.intValue())).getId();
  		       					
								circlesList.put(cirId);
  		       					System.out.println("retrive users in circle = "+i.intValue());	
  		       				}
  		       				
  		       			    try {
								circlesIds.put("circlesIds", circlesList);
								cont.getCirclesUsers(circlesIds);
		  		 
								 prog = new  ProgressDialog(getActivity());
								 prog.setMessage("Loading user's data ");
								 prog.show();
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
  		       			
  		       			}
  		                	 
  		                	  
  		                   }
  		               });
  		  
  		       builderSingle.setPositiveButton("Save",
  		               new DialogInterface.OnClickListener() {

  		                   @Override
  		                   public void onClick(DialogInterface dialog, int which) {
  		                	   	
  		                	   circles.setText("");
  		                	   
  		                	   for(int s : selectedCirs)
  		                		   circles.append(cirs.get(s)+",");
  		                
  		                   }
  		               });

  		    
  		       builderSingle.show();
  		       
  	
  		}
  	});
      

      setHasOptionsMenu(true);

      return rootView;

    } 
   
                
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.add_event_icon:
			saveEventHandler();
			
			return true;	
		

		default:
			return super.onOptionsItemSelected(item);
		}
		
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
		
		if(circles.getText().toString().equals("Choose Circles") == true ||  selectedCirs.isEmpty()){
			circles.setText("Field Required");
			flag = false;
		}
		if(fromTxt.getText().toString().equals("From") == true ||  selectedFrom == -1){
			fromTxt.setText("Field Required");
			flag = false;
		}
			
		if(toTxt.getText().toString().equals("To") == true ||  selectedLocs.isEmpty()){
			toTxt.setText("Field Required");
			flag = false;
		}
		
		if(dateBtn.getText().toString().equals("Date") == true || dateFlag == false){
			toTxt.setText("Field Required");
			flag = false;
		}
		
		return flag;
	}

	private void saveEvent() {

		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = formatter.format(d);
		System.out.println("selected date is  " + dateStr);
		
		
		JSONArray toLocations  = new JSONArray();
		JSONArray circlesList  = new JSONArray();
		String eventName = eventNameTxt.getText().toString();
		JSONObject input = new JSONObject();
		try {
			
			input.put("eventName",eventName);
			
			input.put("eventDate", dateStr);
			input.put("eventStatue", "new");
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
				
				for(String  str : selectedToLocs){
					
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
			
			if(selectedCirs != null){
				for(Integer i  : selectedCirs){
					int cirId = EntityFactory.getCircleByName(cirs.get(i.intValue())).getId();
					circlesList.put(cirId);
					System.out.println(i.intValue());	
				}
			}
		
			input.put("eventToLocations", locsTo);
			input.put("cirlclesId", circlesList);
			
			JSONArray blocked = new JSONArray();
			
			for(Integer i  : selectedBlocked){
				int usrId = Users.get(i.intValue()).getId();
				blocked.put(usrId);
				System.out.println(usrId +"user blocked  id ");	
			}
	            
	        input.put("blockUsers", blocked);
	            
	      // Toast.makeText(getActivity().getApplicationContext(), input.toString(), Toast.LENGTH_LONG).show();
	           
	        System.out.println(input.toString());
	     
	        prog = new ProgressDialog(getActivity());
	        prog.setMessage("Add event");
	        prog.show();
			cont.addEventHandler(input.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
		
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		 inflater.inflate(R.menu.add_event_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);

		
	}
	
    public void showBlockUserDialog(){
    	
    	  	//		selectedCirs.clear();
    	  		
   	   		selectedBlocked.clear();
    	
   	   		ul.clear();
   	   		
    	  			for(User u : Users)
    	  				ul.add(u.getName());
    	  				
    	  			
    	  		       final Builder builderSingle = new AlertDialog.Builder(getActivity());
    	  		       
    	  		       builderSingle.setIcon(R.drawable.ic_action_locate);
    	  		      
    	  		       builderSingle.setTitle("Block Users");
    	  		       
    	  			   builderSingle.setMultiChoiceItems(ul.toArray(new CharSequence[ul.size()]) , null,   new DialogInterface.OnMultiChoiceClickListener(){

    	  					@Override
    	  					public void onClick(DialogInterface dialog,
    	  							int which, boolean isChecked) {
    	  						
    	  						if(isChecked)
    	  							selectedBlocked.add(which);
    	  						else 
    	  							selectedBlocked.remove(which);
    	  						
    	  					}});
    	  		      
    	  		       builderSingle.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {

    	  		                   @Override
    	  		                   public void onClick(DialogInterface dialog, int which) {
    	  		                	   	
    	  		                	   selectedBlocked.clear();
    	  		                   }
    	  		               });
    	  		  
    	  		       builderSingle.setPositiveButton("Save", new DialogInterface.OnClickListener() {

    	  		                   @Override
    	  		                   public void onClick(DialogInterface dialog, int which) {
    	  		                
    	  		                	  circles.setText("");
    	  		                	   
    	  		                	   for(int s : selectedCirs)
    	  		                		   circles.append(cirs.get(s)+",");
    	  		                   }
    	  		               });

    	  		    


    	  		       builderSingle.show();
    	  
    	      
    	
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

		        dp.setMaxDate(new Date(currentYear+1 - 1900,currentMonth, currentDay).getTime());
		        
		        System.out.println("max date ");
		        
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

    
    
}
	


	

