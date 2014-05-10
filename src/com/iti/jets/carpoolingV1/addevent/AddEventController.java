package com.iti.jets.carpoolingV1.addevent;

import com.iti.jets.carpoolingV1.httphandler.AddEvent;

public class AddEventController {

	public void onPostExecute(String result) {
		
		
	}

	public void addEventHandler(String parm) {
		new AddEvent(this).execute(new String []{parm});
	}

}
