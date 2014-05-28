package com.iti.jets.carpoolingV1.addevent;

import android.widget.Toast;

import com.iti.jets.carpoolingV1.httphandler.AddEvent;

public class AddEventController {

	AddEventActivity addEventActivity;
	
	public AddEventController(AddEventActivity addEventActivity) {
		// TODO Auto-generated constructor stub
		this.addEventActivity = addEventActivity;
	}

	public void onPostExecute(String result) {
		Toast.makeText(addEventActivity.getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
		
	}

	public void addEventHandler(String parm) {
		new AddEvent(this).execute(new String []{parm});
	}

}
