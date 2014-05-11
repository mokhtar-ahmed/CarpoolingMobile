package com.iti.jets.carpoolingV1.eventshome;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.pojos.Event;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class EventsHome extends Fragment implements OnItemClickListener {

	View rootView;
	ListView eventsList;
	List<Event>values = new ArrayList<Event>();
	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
   
	rootView = inflater.inflate(R.layout.activity_events_home, container, false);
    
	eventsList = (ListView) rootView.findViewById(R.id.home_events_list);
	
	Event ev = new Event();
	ev.setId(1);
	ev.setName("event1");
	ev.setDate(new Date(92,12,12));
	values.add(ev);

	
	Activity ac = getActivity();
    CustomBaseAdapter adapter = new CustomBaseAdapter(ac, values);
    eventsList.setAdapter(adapter);
    eventsList.setOnItemClickListener(this);

	return super.onCreateView(inflater, container, savedInstanceState);
}

@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	// TODO Auto-generated method stub
	
}

}
