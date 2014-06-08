package com.iti.jets.carpoolingV1.SearchHome;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.addevent.AddEventActivity;
import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.pojos.Event;
import com.iti.jets.carpoolingV1.pojos.Notification;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;
import com.iti.jets.carpoolingV1.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchHome extends Fragment implements OnItemClickListener {

	View rootView;
	ListView resultList;
	Button searchBtn;
	Spinner searchType;
	EditText searchTxt;
	ProgressDialog prog ;
	
	List<Event>values = new ArrayList<Event>();
	SearchHomeController cont ;
	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
   

	rootView = inflater.inflate(R.layout.activity_search_event, container, false);
    
	resultList = (ListView) rootView.findViewById(R.id.event_list);
	searchBtn = (Button) rootView.findViewById(R.id.searchBtn);
	searchType = (Spinner) rootView.findViewById(R.id.searchType);
	searchTxt = (EditText) rootView.findViewById(R.id.searchTxt);
	
	ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, new String[]{"Driver","Location"});
	
	searchType.setAdapter(adapter1);
	
	searchBtn.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			search();
		}
	});
	
	
	setHasOptionsMenu(true);
	
	cont = new SearchHomeController(this);
		return rootView;
}

private void search(){
	
	String txt = searchTxt.getText().toString();
	
	if(txt.equals("")== false){
		
		prog = new ProgressDialog(getActivity());
		prog.setMessage("load the events");
		prog.show();
		if(searchType.getSelectedItem() == "Driver"){
			
	
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("userName", txt);
				cont.searchByDriver(jsonObject.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}else if (searchType.getSelectedItem() == "Location"){
			
			JSONObject jsonObject = new JSONObject();
			
			try {
				
				jsonObject.put("locationId", EntityFactory.SearchLocationId(txt));
				System.out.println("the location id of "+txt+" is "+ jsonObject.toString());
				cont.searchByLocation(jsonObject.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}else{
		searchTxt.setError("enter text to search");
		
		//Toast.makeText(getActivity(), "", duration)
	}
}
public void fillListViewData(){
	
    CustomBaseAdapter adapter = new CustomBaseAdapter(getActivity(), values);
    resultList.setAdapter(adapter);
    resultList.setOnItemClickListener(this);

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
	
	
	
}

@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	// TODO Auto-generated method stub
	// inflater.inflate(R.menu.home, menu);
	super.onCreateOptionsMenu(menu, inflater);
}




}
