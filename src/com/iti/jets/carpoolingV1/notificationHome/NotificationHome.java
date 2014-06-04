package com.iti.jets.carpoolingV1.notificationHome;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.addevent.AddEventActivity;
import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.pojos.Event;
import com.iti.jets.carpoolingV1.pojos.Notification;
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

public class NotificationHome extends Fragment implements OnItemClickListener {

	View rootView;
	ListView notificationsList;
	List<Notification>values = new ArrayList<Notification>();
	NotificationsHomeController cont ;
	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
   

	rootView = inflater.inflate(R.layout.activity_events_home, container, false);
    
	notificationsList = (ListView) rootView.findViewById(R.id.home_events_list);
	
	setHasOptionsMenu(true);
	
	cont = new NotificationsHomeController(this);
	
	JSONObject input = new JSONObject();
	try {
		input.put("userId", EntityFactory.getUserInstance().getId());
		System.out.println(input.toString());
		cont.retriveAllNotification(input.toString());
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return rootView;
}

public void fillListViewData(){
	
    CustomBaseAdapter adapter = new CustomBaseAdapter(getActivity(), values);
    notificationsList.setAdapter(adapter);
    notificationsList.setOnItemClickListener(this);

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
	
	
	JSONObject input = new JSONObject();
	try {
		
		input.put("notificationId", values.get(position).getId());
		cont.markAsReaded(input.toString());
		int idev = values.get(position).getEvent().getId().intValue();
		String state = values.get(position).getEvent().getUserStatue();
		UIManagerHandler.goToEventDetails(getActivity(),idev,state);
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	// TODO Auto-generated method stub
	// inflater.inflate(R.menu.home, menu);
	super.onCreateOptionsMenu(menu, inflater);
}




}
