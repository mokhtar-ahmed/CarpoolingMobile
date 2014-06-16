package com.iti.jets.carpoolingV1.firstrun;
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
import com.iti.jets.carpoolingV1.deletecircle.DeleteCircleController;
import com.iti.jets.carpoolingV1.*;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;
import com.iti.jets.carpoolingV1.httphandler.LoginServiceHandler;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.registrationactivity.RegisterFragment;
import com.iti.jets.carpoolingV1.renamecircle.RenameCircleServiceHandler;
import com.iti.jets.carpoolingV1.retrieveallcircles.CirclesCustomArrayAdapter;
import com.iti.jets.carpoolingV1.retrieveallcircles.CirclesCustomArrayAdapter.ViewHolder;
import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.RetrieveAllCirclesListController;
import com.iti.jets.carpoolingV1.retrieveallcircles.RetrieveCircleUsersController;
import com.iti.jets.carpoolingV1.synccontactsactivity.SyncContactsCustomArrayAdapter;
import com.iti.jets.carpoolingV1.synccontactsactivity.SyncContactsFragment;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;
import com.iti.jets.carpoolingV1.common.*;
import com.iti.jets.carpoolingV1.pojos.Circle;
import android.R.color;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class AllCirclesListFragment2 extends Fragment implements OnNavigationListener{

	ArrayList<Circle2> userCirclesList = new ArrayList<Circle2>();
	ArrayList<Circle> userCirclesList2 = new ArrayList<Circle>();
	ListView list;
	public ProgressDialog dialog2;
	String CircleName ;
    Circle2 circleValues;
	public CirclesCustomArrayAdapter2 adapter;
	ImageView addCircle,delCircle;
	View rootView;
	boolean delFlag;
	Button cancelDeletionBtn;
	public ProgressDialog dialog; 
   boolean flag=true;
	// action bar
    private ActionBar actionBar;
 

    
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

	public interface FragmentCallback2 {
	    //public void onTaskDone(String result);

		public void onTaskDone(String result);
	}
	
	public void getUserCircles(String result) {
		
		// TODO Auto-generated method stub
		JSONArray circlesJsArray;
		try {
			circlesJsArray = new JSONArray(result);
		
			userCirclesList=new ArrayList<Circle2>();
		for(int i=0;i<circlesJsArray.length();i++)
		{
			
			JSONObject jsObj = circlesJsArray.getJSONObject(i);
			System.out.println(jsObj);
			Circle2 tempCircle = new Circle2();
			Circle tempCircle2 = new Circle();
			tempCircle.setCircleName(jsObj.getString("circleName"));
			tempCircle.setCircleId(jsObj.getInt("circleId"));
			tempCircle.setCircleImage(jsObj.getString("circleImage"));
		
			tempCircle.setResId(jsObj.getInt("resId"));
			tempCircle2.setCircleName(jsObj.getString("circleName"));
			tempCircle2.setId(jsObj.getInt("circleId"));
			userCirclesList2.add(tempCircle2);
			userCirclesList .add(tempCircle);
			System.out.println("Size"+"  "+userCirclesList.size());
		}
		
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Resources res =getResources();
        list = ( ListView )   rootView.findViewById( R.id.list );   
        /**************** Create Custom Adapter *********/
        adapter=new CirclesCustomArrayAdapter2(this,userCirclesList,res);
        EntityFactory.setCirclesInstance(userCirclesList2);
        list.setAdapter(adapter);
        
	}

	public void onItemClick(int mPosition) {
		// TODO Auto-generated method stub
		Circle2 circleClickedValues = (Circle2) userCirclesList.get(mPosition);
		
		String selectedCircleName = circleClickedValues.getCircleName();
		circle_Id = circleClickedValues.getCircleId();
		CircleName = circleClickedValues.getCircleName();
		
		RetrieveCircleUsersController ruController = new RetrieveCircleUsersController();
		dialog2 = ProgressDialog.show(AllCirclesListFragment2.this.getActivity(), "", "Loading...Please wait...", true);
		dialog2.show();
		ruController.setArguments(AllCirclesListFragment2.this,circle_Id,new FragmentCallback2(){
			@Override
			public void onTaskDone(String result) {
				
				
				Bundle args = new Bundle();
				args.putString("CircleName", CircleName);
				args.putInt("circle_Id",circle_Id);
				args.putInt("user_Id",userId);
				args.putString("result",result);
				args.putBoolean("flag", false);
				JSONArray jsArray;
				try {
					jsArray =  new JSONArray(result);
					if(jsArray.length()==0)
					{
						UIManagerHandler.goToNoUsersHome2(AllCirclesListFragment2.this.getActivity(),circle_Id,CircleName);
					}
					else
					{
						Fragment fragment = new CircleUsersFragment2();
						
						if (fragment != null) {
						AllCirclesListFragment2.this.getActivity().setRequestedOrientation(
						            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
							FragmentManager fragmentManager = AllCirclesListFragment2.this.getActivity().getFragmentManager();
							fragment.setArguments(args); 
//							fragmentManager.popBackStack();
							AllCirclesListFragment2.this.getActivity().getActionBar().setTitle( CircleName);
							fragmentManager.beginTransaction()
									.replace(R.id.framee, fragment).commit();
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
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
			Fragment fragment = new AddCircleFragment2();
			
			if (fragment != null) {
				AllCirclesListFragment2.this.getActivity().setRequestedOrientation(
			            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				FragmentManager fragmentManager = AllCirclesListFragment2.this.getActivity().getFragmentManager();
				CharSequence title = "New Circle";
				AllCirclesListFragment2.this.getActivity().getActionBar().setTitle(title);
//				 fragmentManager.popBackStack();
				 
				fragmentManager.beginTransaction()
						.replace(R.id.framee, fragment).addToBackStack("addCirc").commit();
				
				
				
			}
			break;

		case R.id.del:
			delFlag = true;
			adapter.setCheckBoxVisible();
			list.invalidateViews();
			break;
		case R.id.update:
			delFlag = false;
			adapter.setCheckBoxVisible();
			list.invalidateViews();
			

			break;	
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void oncheckCircle(final Circle2 circleValues2) {
		System.out.println("///////////////////////////////////////////"+circleValues2.getCircleName());
		System.out.println("///////////////////////////////////////////"+circleValues2.getCircleId());
		this.circleValues = circleValues2;
		if(delFlag)
		{
			if((circleValues2.getCircleName().equals("Friends"))||circleValues2.getCircleName().equals("Family")||(circleValues2.getCircleName().equals("Work")))
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
	// 
				// set title
				alertDialogBuilder.setTitle("Confirm");
	// 
				// set dialog message
				alertDialogBuilder
					.setMessage("Are you sure you want to delete this circle?")
					.setCancelable(false)
					.setPositiveButton("Delete",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, close
							// current activity
							DeleteCircleController deleteCircleController = new DeleteCircleController();
							deleteCircleController.setArguments(circleValues2,new FragmentCallback2(){
							@Override
							public void onTaskDone(String result) {
								// TODO Auto-generated method stub
								
								Fragment fragment = new AllCirclesListFragment2();
								if (fragment != null) {
									AllCirclesListFragment2.this.getActivity().setRequestedOrientation(
								            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
									FragmentManager fragmentManager = AllCirclesListFragment2.this.getActivity().getFragmentManager();
									CharSequence title = "My Circles";
									AllCirclesListFragment2.this.getActivity().getActionBar().setTitle(title);
//									 fragmentManager.popBackStack();
									fragmentManager.beginTransaction()
											.replace(R.id.framee, fragment).addToBackStack("home4d").commit();
								}
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
	// 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	// 
					// show it
					alertDialog.show();
			}
		}
		else
		{
			LayoutInflater li = LayoutInflater.from(getActivity());
			View promptsView = li.inflate(R.layout.prompts, null);
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					getActivity());

			// set prompts.xml to alertdialog builder
			alertDialogBuilder.setView(promptsView);

			final EditText userInput = (EditText) promptsView
					.findViewById(R.id.editTextDialogUserInput);
			userInput.setText(circleValues2.getCircleName());

			// set dialog message
			alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("Save",
				  new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {
					// get user input and set it to result
					// edit text
				  
				    	String newCircleName = userInput.getText().toString();
				    	if(!newCircleName.equals(""))
				    	{
				    		RenameCircleServiceHandler serviceHandler = new RenameCircleServiceHandler();
					    	serviceHandler.connectToWebService(newCircleName,circleValues2,AllCirclesListFragment2.this);
				    	}
				    }
				  })
				.setNegativeButton("Cancel",
				  new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				    }
				  });

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
		}
		
	
	}
	

	  @Override
	    public void onResume() {
	    	// TODO Auto-generated method stub
	    	
	    	super.onResume();
	    	
	    }
	public void refresh() {
		// TODO Auto-generated method stub
		adapter.notifyDataSetChanged();
//		UIManagerHandler.goToAllCirclesList(AllCirclesListFragment.this.getActivity());
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}
 
//	@Override
//	public void onStop() {
//		// TODO Auto-generated method stub
//		super.onStop();
//		UIManagerHandler.goToHome(getActivity());
//	}
}
