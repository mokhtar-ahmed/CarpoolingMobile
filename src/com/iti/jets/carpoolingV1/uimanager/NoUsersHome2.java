package com.iti.jets.carpoolingV1.uimanager;

import android.app.Fragment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.addevent.AddEventActivity;
import com.iti.jets.carpoolingV1.firstrun.SyncContactsFragment2;
import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.pojos.Event;
import com.iti.jets.carpoolingV1.pojos.Notification;
import com.iti.jets.carpoolingV1.synccontactsactivity.SyncContactsFragment;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
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

public class NoUsersHome2 extends Fragment {

String selectedCircleName;
int circle_Id,user_Id;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
   

	View rootView = inflater.inflate(R.layout.no_users, container, false);
	setHasOptionsMenu(true);
   
 	this.circle_Id = getArguments().getInt("circle_Id");
 	this.user_Id = EntityFactory.getUserInstance().getId();
 	this.selectedCircleName = getArguments().getString("CircleName");	
	return rootView;
}

@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	// TODO Auto-generated method stub
    inflater.inflate(R.menu.no_users_menu, menu);
	super.onCreateOptionsMenu(menu, inflater);	
}


@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	switch (item.getItemId()) {
	case R.id.add:
		Fragment fragment = new SyncContactsFragment2(); 
		Bundle args = new Bundle();
	    args.putInt("circle_Id",this.circle_Id);
	    args.putInt("userId", this.user_Id);
	    args.putBoolean("flag", false);
	    args.putString("selectedCircleName", selectedCircleName );
	    
	    fragment.setArguments(args);
	    getActivity().setRequestedOrientation(
	            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.framee, fragment).addToBackStack("CircleUsersFragment").commit();
		break;
	}
	return true;
}

}

