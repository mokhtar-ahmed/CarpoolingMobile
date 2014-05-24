package com.iti.jets.carpoolingV1.feedshome;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.addevent.AddEventActivity;
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

public class FeedsHome extends Fragment implements OnItemClickListener {

	View rootView;
	ListView eventsList;
	ListView notificationsList;
	List<Event>eventValues = new ArrayList<Event>();
	List<Notification>notificationValues = new ArrayList<Notification>();
	FeedsHomeController cont ;
	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
   

	rootView = inflater.inflate(R.layout.activity_feeds, container, false);
    
	eventsList = (ListView) rootView.findViewById(R.id.TopEvents);
	notificationsList = (ListView) rootView.findViewById(R.id.TopNotification);
	setHasOptionsMenu(true);
	
	cont = new FeedsHomeController(this);
	
	fillEventListViewData();
	fillNotificationtListViewData();

	return rootView;
}

public void fillEventListViewData(){
	
	Event ev = new Event();
	ev.setDate(new Date());
	ev.setId(1);
	ev.setName("Event1");
	
	Event ev1 = new Event();
	ev.setDate(new Date());
	ev.setId(1);
	ev.setName("Event3");
	
	
	Event ev2 = new Event();
	ev.setDate(new Date());
	ev.setId(1);
	ev.setName("Event2");
	
	eventValues.add(ev);
	eventValues.add(ev1);
	eventValues.add(ev2);
	
	Activity ac = getActivity();
	EventCustomBaseAdapter adapter = new EventCustomBaseAdapter(ac, eventValues);
    eventsList.setAdapter(adapter);
    eventsList.setOnItemClickListener(this);

}

public void fillNotificationtListViewData(){
	Event ev = new Event();
	ev.setDate(new Date());
	ev.setId(1);
	ev.setName("Event1");
	
	Event ev1 = new Event();
	ev.setDate(new Date());
	ev.setId(1);
	ev.setName("Event3");
	
	
	Event ev2 = new Event();
	ev.setDate(new Date());
	ev.setId(1);
	ev.setName("Event2");
	
	eventValues.add(ev);
	eventValues.add(ev1);
	eventValues.add(ev2);
	
	
	Notification no = new Notification();
	no.setEvent(ev);
	no.setEventState("goinig");
	no.setEventType("public");
	no.setNotificationDate(new Date());
	
	Notification no1 = new Notification();
	no1.setEvent(ev1);
	no1.setEventState("cancelled");
	no1.setEventType("public");
	no1.setNotificationDate(new Date());
	
	Notification no2 = new Notification();
	no2.setEvent(ev2);
	no2.setEventState("goinig");
	no2.setEventType("public");
	no2.setNotificationDate(new Date());
	

	
	notificationValues.add(no);
	notificationValues.add(no1);
	notificationValues.add(no2);

	Activity ac = getActivity();
	CustomBaseAdapter adapter = new CustomBaseAdapter(ac, notificationValues);
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
	
	switch(view.getId()){
	
	case R.id.TopEvents:break;
	case R.id.TopNotification:break;
	}
	

}

@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	// TODO Auto-generated method stub
//	 inflater.inflate(R.menu.home, menu);
	super.onCreateOptionsMenu(menu, inflater);
}




}
