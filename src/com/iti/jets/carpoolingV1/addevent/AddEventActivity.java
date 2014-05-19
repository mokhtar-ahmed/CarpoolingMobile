package com.iti.jets.carpoolingV1.addevent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.iti.jets.carpoolingV1.pojos.*;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

public class AddEventActivity extends Fragment{

		EditText eventNameTxt;
		Spinner fromTxt;
		Spinner toTxt;
		Spinner no_of_slots;
		Spinner circles;
		TimePicker tp;
		DatePicker dp;
		ArrayList<String> locs = new ArrayList<String>() ;
		ArrayList<String> cirs = new ArrayList<String>() ;
		ArrayAdapter<String> adapter;
		ArrayAdapter<String> adapter1;
		ArrayAdapter<String> adapter2;
		
	AddEventController cont ;
		
	public AddEventActivity(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		 
		// super.onCreateView(inflater, container, savedInstanceState);
		 
        View rootView = inflater.inflate(R.layout.activity_add_event, container, false);
  
        
        
       eventNameTxt =   (EditText) rootView.findViewById(R.id.eventNameTxt);
       fromTxt =  (Spinner) rootView.findViewById(R.id.FromSpinner);
       toTxt =  (Spinner) rootView.findViewById(R.id.ToSpinner);
       no_of_slots = (Spinner) rootView.findViewById(R.id.avaliableSlots);
       circles = (Spinner) rootView.findViewById(R.id.circlesSpinner); 
       tp = (TimePicker) rootView.findViewById(R.id.eventTimeTxt);
       dp = (DatePicker) rootView.findViewById(R.id.eventDateTxt);
       cont = new AddEventController();
       
      ArrayList<Location> l =  EntityFactory.getLocationsInstance();
      for(int i=0; i< l.size(); i++ ){
    	  
    	  locs.add(l.get(i).getAddress());
    	  
      }
      
      ArrayList<Circle> c =  EntityFactory.getCirclesInstance();
      for(int i=0; i< c.size(); i++ ){
    	  
    	  cirs.add(c.get(i).getCircleName());
    	  
      }
      
      
       adapter  = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, locs);
       adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, cirs);
       adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, new String[]{"1","2","3","4","5"});
       
       fromTxt.setAdapter(adapter);
       toTxt.setAdapter(adapter);
       circles.setAdapter(adapter1);
       no_of_slots.setAdapter(adapter2);
       
       setHasOptionsMenu(true);
       
        return rootView;
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.add_event_icon:
			//UIManagerHandler.goToAddEvent(getActivity());
		return true;	
		

		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	private void saveEvent() {
		// TODO Auto-generated method stub
		
		System.out.println(eventNameTxt.getText().toString());
		System.out.println(locs.get(fromTxt.getSelectedItemPosition()));
		System.out.println(locs.get(toTxt.getSelectedItemPosition()));
		System.out.println(cirs.get(circles.getSelectedItemPosition()));
		System.out.println(no_of_slots.getSelectedItem().toString());
		
		dp.getDayOfMonth();
		dp.getMonth();
		dp.getYear();
		
		tp.getCurrentHour();
		tp.getCurrentMinute();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date d = new Date();
		
		d.setMonth(dp.getMonth());
		d.setYear(dp.getYear());
		d.setDate(dp.getDayOfMonth());
		d.setHours(tp.getCurrentHour());
		d.setMinutes(tp.getCurrentMinute());
		
		String dateStr = formatter.format(d);
		System.out.println(dateStr);
		
		Toast.makeText(getActivity().getApplicationContext(),dateStr , Toast.LENGTH_LONG).show();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		 inflater.inflate(R.menu.add_event_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}


	
}
