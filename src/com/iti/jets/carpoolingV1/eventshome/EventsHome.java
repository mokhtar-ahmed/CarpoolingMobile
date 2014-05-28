package com.iti.jets.carpoolingV1.eventshome;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.addevent.AddEventActivity;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.pojos.Event;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class EventsHome extends Fragment implements OnItemClickListener {

	View rootView;
	ListView eventsList;

	List<Event>values = new ArrayList<Event>();

	EventsHomeController cont;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
   

	rootView = inflater.inflate(R.layout.activity_events_home, container, false);
    
	eventsList = (ListView) rootView.findViewById(R.id.home_events_list);
	
	setHasOptionsMenu(true);
	
	cont = new EventsHomeController(this);
	
	
	JSONObject input = new JSONObject();
	int id = EntityFactory.getUserInstance().getId();
	System.out.println("user id at event retrive : " + id);
	
	try {
		input.put("userId", id);
		cont.retriveAllEvents(input.toString());
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return rootView;
}

public void fillListViewData(){

	Activity ac = getActivity();
    CustomBaseAdapter adapter = new CustomBaseAdapter(ac, values);
    eventsList.setAdapter(adapter);
    eventsList.setOnItemClickListener(this);

}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	switch (item.getItemId()) {
	case R.id.addEvent:
		UIManagerHandler.goToAddEvent(getActivity());
	return true;	
	

	default:
		return super.onOptionsItemSelected(item);
	}
	
}
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	// TODO Auto-generated method stub
	
	
	Toast.makeText(getActivity().getApplicationContext(), values.get(position).getName(), Toast.LENGTH_LONG).show();
	
	int eventId = values.get(position).getId();
	String state = values.get(position).getUserStatue();
	UIManagerHandler.goToEventDetails(getActivity(), eventId , state);
}

@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	// TODO Auto-generated method stub
	 inflater.inflate(R.menu.home, menu);
	super.onCreateOptionsMenu(menu, inflater);
}




}
