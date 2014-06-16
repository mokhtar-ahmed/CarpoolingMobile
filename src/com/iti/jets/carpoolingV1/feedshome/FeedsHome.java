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
import android.view.KeyEvent;
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

public class FeedsHome extends Fragment  {

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
	getActivity().getActionBar().setTitle("Feeds");
	
	cont = new FeedsHomeController(this);
	
	JSONObject input = new JSONObject();
	try {
		
		input.put("userId", EntityFactory.getUserInstance().getId());
		cont.retriveAllEvents(input.toString());		
		cont.retriveAllNotifications(input.toString());
	
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return rootView;
}

public void fillEventListViewData(){

	Activity ac = getActivity();
	EventCustomBaseAdapter adapter = new EventCustomBaseAdapter(ac, eventValues);
    eventsList.setAdapter(adapter);
    eventsList.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
		 UIManagerHandler.goToEventDetails(getActivity(), eventValues.get(position).getId(),
				 eventValues.get(position).getUserStatue());	
		}
	});

}

public void fillNotificationtListViewData(){
	
	Activity ac = getActivity();
	CustomBaseAdapter adapter = new CustomBaseAdapter(ac, notificationValues);
    notificationsList.setAdapter(adapter);
    notificationsList.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
		 UIManagerHandler.goToEventDetails(getActivity(), notificationValues.get(position).getEvent().getId(),
				 notificationValues.get(position).getEvent().getUserStatue());	
		}
	});
}



@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	// TODO Auto-generated method stub
	 //inflater.inflate(R.menu.home, menu);
	super.onCreateOptionsMenu(menu, inflater);
}




}
