package com.iti.jets.carpoolingV1.addevent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;

import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
		Button tp;
		Button dp;
		String[] noOfSlotsArr = new String[]{"1","2","3","4","5"};
		Calendar c = Calendar.getInstance();
        int  mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        int mHour = c.get(Calendar.HOUR);
        int mMinute = c.get(Calendar.MINUTE);

        ArrayList<Integer> selectedLocs = new ArrayList<Integer>();
        ArrayList<Integer> selectedCirs= new ArrayList<Integer>();
        int selectedFrom ;
        int selectedNoOfSlots ;
        
		ArrayList<String> locs = new ArrayList<String>() ;
		ArrayList<String> cirs = new ArrayList<String>() ;

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
       tp = (Button) rootView.findViewById(R.id.eventTimeTxt);
       dp = (Button) rootView.findViewById(R.id.eventDateTxt);
       cont = new AddEventController(this);
  
      ArrayList<Location> l =  EntityFactory.getLocationsInstance();
      for(int i=0; i< l.size(); i++ ){
    	  
    	  locs.add(l.get(i).getAddress());
    	  
      }
      ArrayList<Circle> l1 =  EntityFactory.getCirclesInstance();
      for(int i=0; i< l1.size(); i++ ){
    	  
    	  cirs.add(l1.get(i).getCircleName());
    	  
      }
 
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
  						
  						if(isChecked)
  							selectedCirs.add(which);
  						else 
  							selectedCirs.remove(which);
  						
  					}});
  		      
  		       builderSingle.setNegativeButton("Block",
  		               new DialogInterface.OnClickListener() {

  		                   @Override
  		                   public void onClick(DialogInterface dialog, int which) {
  		                	   showBlockUserDialog();
  		                
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

        return rootView;

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
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.add_event_icon:
			saveEvent();
			
		return true;	
		

		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	
	private void saveEvent() {
		// TODO Auto-generated method stub
	
		Date d = new Date(mYear,mMonth,mDay,mHour,mMinute);
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String dateStr = formatter.format(d);
		System.out.println("selected date is  " + dateStr);
		
		
		JSONArray toLocations  = new JSONArray();
		JSONArray circlesList  = new JSONArray();
		String eventName = eventNameTxt.getText().toString();
		JSONObject input = new JSONObject();
		try {
			
			input.put("eventName",eventName);
			input.put("fromLocation", selectedFrom);
			input.put("date", dateStr);
			input.put("noOfSlots", selectedNoOfSlots);

			if(selectedLocs != null){
				for(Integer i  : selectedLocs){
					toLocations.put(i.intValue());
					System.out.println(i.intValue());	
				}
			}
			
			if(selectedCirs != null){
				for(Integer i  : selectedCirs){
					circlesList.put(i.intValue());
					System.out.println(i.intValue());	
				}
			}
		
			input.put("to", toLocations);
			input.put("circles", circlesList);
			
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



    private void showBlockUserDialog(){
    	
    	  			selectedCirs.clear();
    	  		       final Builder builderSingle = new AlertDialog.Builder(getActivity());
    	  		       
    	  		       builderSingle.setIcon(R.drawable.ic_action_locate);
    	  		      
    	  		       builderSingle.setTitle("Block Users");
    	  		       
    	  			   builderSingle.setMultiChoiceItems(new String[]{"Mokhtar" , "Sarah" , "Norhan"} , null,   new DialogInterface.OnMultiChoiceClickListener(){

    	  					@Override
    	  					public void onClick(DialogInterface dialog,
    	  							int which, boolean isChecked) {
    	  						
    	  						//if(isChecked)
    	  						//	selectedCirs.add(which);
    	  						//else 
    	  							//selectedCirs.remove(which);
    	  						
    	  					}});
    	  		      
    	  		       builderSingle.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {

    	  		                   @Override
    	  		                   public void onClick(DialogInterface dialog, int which) {
    	  		                	   	
    	  		                
    	  		                   }
    	  		               });
    	  		  
    	  		       builderSingle.setPositiveButton("Save", new DialogInterface.OnClickListener() {

    	  		                   @Override
    	  		                   public void onClick(DialogInterface dialog, int which) {
    	  		                
    	  		                   }
    	  		               });

    	  		    


    	  		       builderSingle.show();
    	  
    	      
    	
    }
}
	


	

