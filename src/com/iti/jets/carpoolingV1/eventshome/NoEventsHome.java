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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NoEventsHome extends Fragment implements OnItemClickListener {


	ImageView image;
	TextView text;
	Button addEvent;
	View rootView;
	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {

	
	rootView = inflater.inflate(R.layout.no_notification, container, false);
    
	image = (ImageView) rootView.findViewById(R.id.errorImage);
	text = (TextView) rootView.findViewById(R.id.errotText);
	addEvent = (Button) rootView.findViewById(R.id.button1);
	
	addEvent.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			UIManagerHandler.goToAddEvent(getActivity());
		}
	});
	
	image.setImageResource(R.drawable.large_event_icon);
	text.setText("No Event");
	
	getActivity().getActionBar().setTitle("Events");

	return rootView;
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
	
}

@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	// TODO Auto-generated method stub
	// inflater.inflate(R.menu.home, menu);
	super.onCreateOptionsMenu(menu, inflater);
}




}
