package com.iti.jets.carpoolingV1.eventRequests;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.addevent.AddEventActivity;
import com.iti.jets.carpoolingV1.pojos.CustomUser;
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

public class RequestsHome extends Fragment implements OnItemClickListener {

	View rootView;
	ListView eventsList;
    int idEvent;
    ArrayList<CustomUser>userRequest = new ArrayList<CustomUser>();
	List<CustomUser>values = new ArrayList<CustomUser>();
	CustomBaseAdapter adapter;
	RequestsHomeController cont;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
   

	rootView = inflater.inflate(R.layout.activity_event_request, container, false);
    
	eventsList = (ListView) rootView.findViewById(R.id.requests_list);
	
    Bundle args = getArguments();
    
    idEvent = args.getInt("eventId", 0);

    values = EntityFactory.getUsersCustom();

    getActivity().getActionBar().setTitle("Requests");
    for(CustomUser us : values){
    	
    	if(us.getUserStatue().equals("Join") == true){
    		userRequest.add(us);
    		
    	}
    }
    cont = new RequestsHomeController(this);
    
    fillListViewData();
	setHasOptionsMenu(true);
	
	return rootView;
}

public void fillListViewData(){

	Activity ac = getActivity();
    adapter = new CustomBaseAdapter(ac, userRequest , idEvent ,cont , this);
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
	
	
	//Toast.makeText(getActivity().getApplicationContext(), values.get(position).getName(), Toast.LENGTH_LONG).show();
//	
//	int eventId = values.get(position).getId();
//	String state = values.get(position).getUserStatue();
//	UIManagerHandler.goToEventDetails(getActivity(), eventId , state);
}

@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	// TODO Auto-generated method stub
	 inflater.inflate(R.menu.home, menu);
	super.onCreateOptionsMenu(menu, inflater);
}




}
