package com.iti.jets.carpoolingV1.notificationHome;


import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.widget.FacebookDialog;
import com.facebook.widget.FacebookDialog.ShareDialogBuilder;
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
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
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
		
		Notification notification=  values.get(position);
		
		if(notification.getMessage().contains("ccept")== true && 
		   notification.getEventType().equals("unread")){
			
			
			setEventAtCalendar(notification.getEvent().getName(),"Smart",notification.getEvent().getDate());
			fbShare();
			
		}
			
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

private void setEventAtCalendar(String eventName , String location , Date date){
	
	Intent intent = new Intent(Intent.ACTION_INSERT);
	intent.setType("vnd.android.cursor.item/event");
	
	intent.putExtra(Events.TITLE, eventName);
	intent.putExtra(Events.EVENT_LOCATION, location);
	intent.putExtra(Events.DESCRIPTION, "5odny ma3ak event");

	// Setting dates
	GregorianCalendar calDate = new GregorianCalendar(date.getYear(),date.getMonth()-1, 
									date.getDay(),date.getHours(),date.getMinutes());

	intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,calDate.getTimeInMillis());
	intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,calDate.getTimeInMillis());

	// make it a full day event
	intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);

	// make it a recurring Event
	intent.putExtra(Events.RRULE, "FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");

	// Making it private and shown as busy
	intent.putExtra(Events.ACCESS_LEVEL, Events.ACCESS_PRIVATE);
	intent.putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY);
	
	
	getActivity().startActivity(intent); 
}



public  void fbShare(){
	ShareDialogBuilder builder = new ShareDialogBuilder(this.getActivity())
	   	.setName("5odny M3ak events")
	    .setLink("https://www.facebook.com/5odnyMa3ak")
		.setPicture("https://scontent-a-ams.xx.fbcdn.net/hphotos-xfa1/t1.0-9/10393575_1416420081974633_8006832328250289632_n.png")
		.setDescription(EntityFactory.getUserInstance().getName()+"is now using 5odny M3ak Mobile Application");
	if (builder.canPresent()) {
		FacebookDialog dialog = builder.build();
		dialog.present();
	}
}


}
