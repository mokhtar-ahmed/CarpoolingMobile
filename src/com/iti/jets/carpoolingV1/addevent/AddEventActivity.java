package com.iti.jets.carpoolingV1.addevent;
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddEventActivity extends Fragment{

		EditText eventNameTxt;
		EditText fromTxt;
		EditText toTxt;
		EditText dateTxt;
		EditText no_of_slots;
		Button circlesBtn;
		Button addBtn;
		AddEventController cont = new AddEventController();
	public AddEventActivity(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activity_add_event, container, false);
         
       eventNameTxt =   (EditText) rootView.findViewById(R.id.eventNameTxt);
       fromTxt =  (EditText) rootView.findViewById(R.id.fromTxt);
       toTxt =  (EditText) rootView.findViewById(R.id.toTxt);
       dateTxt =  (EditText) rootView.findViewById(R.id.dateTxt);
       no_of_slots = (EditText) rootView.findViewById(R.id.no_of_slots);
       circlesBtn =  (Button) rootView.findViewById(R.id.circlesBtn);
       addBtn =  (Button) rootView.findViewById(R.id.addBtn);
       
       addBtn.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			JSONObject input = new JSONObject();
			try {
				input.put("eventName", eventNameTxt.getText().toString());
				input.put("", dateTxt.getText().toString());
				input.put("noOfSlots", no_of_slots.getText().toString());
				input.put("eventStatue", no_of_slots.getText().toString());
				
				cont.addEventHandler(input.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
	});
       
        
        return rootView;
    }

	
}
