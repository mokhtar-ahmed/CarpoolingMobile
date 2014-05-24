package com.iti.jets.carpoolingV1.retrieveallcircles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.R.layout;
import com.iti.jets.carpoolingV1.R.menu;
import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleFragment;
import com.iti.jets.carpoolingV1.common.Circle;
import com.iti.jets.carpoolingV1.deletecircle.DeleteCircleController;
import com.iti.jets.carpoolingV1.*;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.registrationactivity.RegisterFragment;
import com.iti.jets.carpoolingV1.synccontactsactivity.SyncContactsCustomArrayAdapter;
import com.iti.jets.carpoolingV1.synccontactsactivity.SyncContactsFragment;
import com.iti.jets.carpoolingV1.common.*;
import android.R.color;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class AllCirclesListFragment extends Fragment {

	ArrayList<Circle> userCirclesList = new ArrayList<Circle>();
	ListView list;
	
	CirclesCustomArrayAdapter adapter;
	ImageView addCircle,delCircle;
	  View rootView;
	
	com.iti.jets.carpoolingV1.pojos.User loggingUser = EntityFactory.getUserInstance();
	int userId = loggingUser.getId();
	int circle_Id = 0;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
         rootView = inflater.inflate(R.layout.activity_allcircles_list,container, false);
         setHasOptionsMenu(true);
		
		Log.d("ENTERED","ENTERED");
		RetrieveAllCirclesListController contoller =	new RetrieveAllCirclesListController(userId,this);
		return rootView;
		
	}

	public interface FragmentCallback {
	    //public void onTaskDone(String result);

		public void onTaskDone(String result);
	}
	public void getUserCircles(String result) {
		
		// TODO Auto-generated method stub
		JSONArray circlesJsArray;
		try {
			circlesJsArray = new JSONArray(result);
		
		for(int i=0;i<circlesJsArray.length();i++)
		{
			JSONObject jsObj = circlesJsArray.getJSONObject(i);
			System.out.println(jsObj);
			Circle tempCircle = new Circle();
			tempCircle.setCircleName(jsObj.getString("circleName"));
			tempCircle.setCircleId(jsObj.getInt("circleId"));
			tempCircle.setCircleImage(jsObj.getString("circleImage"));
			
			userCirclesList .add(tempCircle);
			//Toast.makeText(getActivity().getApplicationContext(),tempCircle.getCircleName(),Toast.LENGTH_LONG).show();
			System.out.println("Size"+"  "+userCirclesList.size());
		}
		
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Resources res =getResources();
        list = ( ListView )   rootView.findViewById( R.id.list );   
        /**************** Create Custom Adapter *********/
        adapter=new CirclesCustomArrayAdapter(this,userCirclesList,res);
        list.setAdapter(adapter);
	}

	public void onItemClick(int mPosition) {
		// TODO Auto-generated method stub
		Circle circleClickedValues = (Circle) userCirclesList.get(mPosition);
		
		String selectedCircleName = circleClickedValues.getCircleName();
		circle_Id = circleClickedValues.getCircleId();
		//Toast.makeText(getActivity().getApplicationContext(), "SESES   "+selectedCircleName, Toast.LENGTH_LONG).show();
		//Toast.makeText(getActivity().getApplicationContext(), "SESES   "+circleClickedValues.getCircleId(), Toast.LENGTH_LONG).show();
	
		
		RetrieveCircleUsersController ruController = new RetrieveCircleUsersController();
		
		ruController.setArguments(circle_Id,new FragmentCallback(){
			@Override
			public void onTaskDone(String result) {
				Fragment fragment = new CircleUsersFragment();
				Bundle args = new Bundle();
				args.putInt("circle_Id",circle_Id);
				args.putInt("user_Id",userId);
				args.putString("result",result);
				args.putBoolean("flag", false);
				fragment.setArguments(args);
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();  
				

			}
         });
		
 
 
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.circles_home, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.add:
			Fragment fragment = new AddCircleFragment(); 
			FragmentManager fragmentManager = this.getFragmentManager();
			Bundle args = new Bundle();
			
		    fragment.setArguments(args);
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();
			break;

		case R.id.del:
			CirclesCustomArrayAdapter.deleteflag = true;	
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void oncheckCircle(final Circle circleValues) {
		System.out.println("///////////////////////////////////////////"+circleValues.getCircleName());
		System.out.println("///////////////////////////////////////////"+circleValues.getCircleId());
		if((circleValues.getCircleId() == 1)||(circleValues.getCircleId() == 2)||(circleValues.getCircleId() == 3))
		{
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					getActivity());
	 
				// set title
				alertDialogBuilder.setTitle("Error");
	 
				// set dialog message
				alertDialogBuilder
					.setMessage("Circle Can't be deleted!")
					.setCancelable(false)
					.setPositiveButton("OK",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							
						}
					  });
					
	 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();
		}
		else
		{
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				getActivity());
 
			// set title
			alertDialogBuilder.setTitle("Confirm");
 
			// set dialog message
			alertDialogBuilder
				.setMessage("Are you sure you want to delete this circle?")
				.setCancelable(false)
				.setPositiveButton("Delete",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						DeleteCircleController deleteCircleController = new DeleteCircleController();
						deleteCircleController.setArguments(circleValues,new FragmentCallback(){
						@Override
						public void onTaskDone(String result) {
							// TODO Auto-generated method stub
							
							Fragment fragment = new AllCirclesListFragment(); 
							FragmentManager fragmentManager = AllCirclesListFragment.this.getFragmentManager();
							fragmentManager.beginTransaction()
									.replace(R.id.frame_container, fragment).commit();
							
						}
			         });
					}
				  })
				.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
		}	
	}
 
}
