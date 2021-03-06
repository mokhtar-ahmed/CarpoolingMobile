package com.iti.jets.carpoolingV1.firstrun;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.R.layout;
import com.iti.jets.carpoolingV1.R.menu;
import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleFragment;
import com.iti.jets.carpoolingV1.common.Circle2;
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.deletecircle.DeleteCircleController;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;
import com.iti.jets.carpoolingV1.httphandler.LoginServiceHandler;
import com.iti.jets.carpoolingV1.httphandler.RetrieveCirclesAsyncTask;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.retrieveallcircles.AddUserToCircletestAsyncTask;
import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment.FragmentCallback;
import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.CirclesUsersArrayAdapter;
import com.iti.jets.carpoolingV1.retrieveallcircles.DeleteUserFromCircleController;
import com.iti.jets.carpoolingV1.synccontactsactivity.SyncContactsFragment;
import com.iti.jets.carpoolingV1.synccontactsactivity.SyncContactsCustomArrayAdapter;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

import android.R.array;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CircleUsersFragment2 extends Fragment {

	private TextView circleName;
	private ImageView addFriendImgView;
	private String selectedCircleName;
	private int circle_Id;
	private int user_Id;
	private boolean flag = false;
	private View rootView;
	private String result;
	private ArrayList<User> circleUsersList;
	private ArrayList<String> circleUsersNames;
	public ProgressDialog dialog;
	CirclesUsersArrayAdapter2 adapter;
	String circleRecName;
	ListView list;
	String result2;
	Button removeUsersBtn;
	public CircleUsersFragment2() {
		// TODO Auto-generated constructor stub
		
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
//		Toast.makeText(getActivity().getApplicationContext(),"YES",Toast.LENGTH_LONG).show();
         rootView = inflater.inflate(R.layout.circle_users_list,container, false);
         removeUsersBtn = (Button)rootView.findViewById(R.id.removeUsersBtn);
         list = ( ListView )rootView.findViewById(R.id.list );
        
        setHasOptionsMenu(true);
        this.flag = getArguments().getBoolean("flag");
    	this.circle_Id = getArguments().getInt("circle_Id");
    	this.circleRecName = getArguments().getString("CircleName");
    	removeUsersBtn.setOnClickListener(new View.OnClickListener() {
			
    		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final ArrayList<User> deletedUsers= new ArrayList<User>();
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				getActivity());
 
			// set title
			//alertDialogBuilder.setTitle("Alert");
 
			// set dialog message
		    
				alertDialogBuilder
				.setMessage("Are you sure you want to delete users from this circle?")
				.setCancelable(false)
				.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
					
						dialog.cancel();
						
					}
					
				}) 
				.setPositiveButton("Delete",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						for(int j=0;j<circleUsersList.size();j++)
						{
							User userToBeRemoved = new User();
							userToBeRemoved = circleUsersList.get(j);
							if(userToBeRemoved.getIsSelected())
							{
								deletedUsers.add(userToBeRemoved);
							}
						}
						JSONArray usersToRemoveJsArr = new JSONArray();
						for(int k=0;k<deletedUsers.size();k++)
						{
							JSONObject jsObj = new JSONObject();
							try {
								jsObj.put("userId",deletedUsers.get(k).getUserId());
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							usersToRemoveJsArr.put(jsObj);
						
						}
						DeleteUserFromCircleController deleteCircleController = new DeleteUserFromCircleController();
						deleteCircleController.setArguments(usersToRemoveJsArr,circle_Id,new FragmentCallback2() {
							
							@Override
							public void onTaskDone(String result) {
								// TODO Auto-generated method stub
								
//								adapter.notifyDataSetChanged();
//								list.invalidateViews();
//								UIManagerHandler.goToAllCirclesList(CircleUsersFragment.this.getActivity());
								Bundle args2 = new Bundle();
								args2.putInt("circle_Id",circle_Id);
					 			args2.putString("result", result);  
					 			Fragment fragment = new CircleUsersFragment2();
					 			
					 			if (fragment != null) {
					 				CircleUsersFragment2.this.getActivity().setRequestedOrientation(
					 			            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
					 				FragmentManager fragmentManager = CircleUsersFragment2.this.getActivity().getFragmentManager();
					 				fragment.setArguments(args2); 
//					 				fragmentManager.popBackStack();
					 				CircleUsersFragment2.this.getActivity().getActionBar().setTitle(circleRecName);
					 				fragmentManager.beginTransaction()
					 						.replace(R.id.framee, fragment).commit();

					 				
					 			}
							}
						});
						
					}
					
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
				
				
			}
		});
        if(flag)
        {
        	 this.result = getArguments().getString("selectedUsersJSArr");
        	 this.circle_Id = getArguments().getInt("circle_Id");
      		 this.user_Id = getArguments().getInt("user_Id");
      		 
      		 
        	 final JSONArray circleUsersJsArray;
 			 User tempUser;
 			 JSONObject userTempJS;
 			 final ArrayList<User> circleActualUsersList; 
 			
 				 try {
					circleUsersJsArray = new JSONArray(result);
					circleActualUsersList = new ArrayList<User>();
					circleUsersNames = new ArrayList<String>();
					for(int i=0;i<circleUsersJsArray.length();i++)
	 				{
	 					userTempJS = new JSONObject();
	 					tempUser = new User();
	 					userTempJS = circleUsersJsArray.getJSONObject(i);
	 					tempUser.setUserId(userTempJS.getInt("userId"));
	 					tempUser.setName(userTempJS.getString("Name"));
	 					System.out.println("9999999999999999999999      "+userTempJS.getString("Name"));
//	 					tempUser.setImageURL(userTempJS.getString("Image"));
	 					tempUser.setPhone(userTempJS.getString("Phone"));
	 					circleActualUsersList.add(tempUser);
	 					circleUsersNames.add(userTempJS.getString("Name"));
	 					
	 				}
	 				
					 AddUserToCircletestAsyncTask testAsyncTask = new  AddUserToCircletestAsyncTask(CircleUsersFragment2.this,new FragmentCallback2() {
									@Override
									public void onTaskDone(String result) {
										System.out.println("ET@AKED" + result);
									    JSONArray usersJSArr;
									    ArrayList<User> usersList = new ArrayList<User>();
									    User user;
										try {
											usersJSArr = new JSONArray(result);
											 JSONObject tempu ;
											 
											 for(int i=0;i<usersJSArr.length();i++)
											 {
												 tempu = usersJSArr.getJSONObject(i);
												 user = new User();
												 user.setUserId(tempu.getInt("userId"));
											
												 user.setImageURL(tempu.getString("image"));
												 user.setPhone(tempu.getString("Phone"));
												 user.setName(tempu.getString("Name"));
												 usersList.add(user);
												
											 }
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									   
						 				Resources res = getResources();
						 	             // List defined in XML ( See Below )
						 	             
						 	            /**************** Create Custom Adapter *********/
						 	           adapter = new  CirclesUsersArrayAdapter2(CircleUsersFragment2.this,usersList,res);
						 	           list.setAdapter( adapter );
						 	          JSONObject input = new JSONObject();
						 				try {
						 					input.put("username", EntityFactory.getUserInstance().getUsername());
						 					input.put("password", EntityFactory.getUserInstance().getPassword());
						 					new LoginServiceHandler(getActivity()).execute(new String[]{input.toString()});
						 				} catch (JSONException e) {
						 					// TODO Auto-generated catch block
						 					e.printStackTrace();
						 				} 
										
									}
					             },this.circle_Id,circleActualUsersList);
						
					 String URL= HttpConstants.SERVER_URL+HttpConstants.ADD_USERTO_CIRCLE_SERVICE_URL; 
			     	 testAsyncTask.execute(URL); 
			     	 
			         dialog = ProgressDialog.show(CircleUsersFragment2.this.getActivity(), "", "Loading...Please wait...", true);
			 		 dialog.show();
	 				
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 				
     		
        }
        else
        {
        	 
        	this.circle_Id = getArguments().getInt("circle_Id");
        	String result = getArguments().getString("result");
        	
        	JSONArray resultsJsarr;
        	
			try {
				resultsJsarr = new JSONArray(result);
			
	        	JSONObject tempObj;
	        	User tempUser;
	        	circleUsersList = new ArrayList<User>();
	        	circleUsersNames = new ArrayList<String>();
	        	for(int i=0;i<resultsJsarr.length();i++)
	        	{
	        		 tempObj = new JSONObject();
	        		 tempObj = resultsJsarr.getJSONObject(i);
	        		 tempUser = new User();
	        		 tempUser.setUserId(tempObj.getInt("userId"));
	        		 tempUser.setPhone(tempObj.getString("Phone"));
	        		 tempUser.setImageURL(tempObj.getString("image"));
	        		 tempUser.setName(tempObj.getString("Name"));
	        		 circleUsersList.add(tempUser);
	        		 circleUsersNames.add(tempObj.getString("Name"));
	        	}
	        	
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	Resources res = getResources();
	        ListView list = ( ListView )rootView.findViewById(R.id.list );  
	             
	            /**************** Create Custom Adapter *********/
	        adapter = new  CirclesUsersArrayAdapter2(CircleUsersFragment2.this,circleUsersList,res);
	        list.setAdapter( adapter );
	
        }
 	
        return rootView;
	}
	public interface FragmentCallback2 {
	        //public void onTaskDone(String result);

			public void onTaskDone(String result);
	    }
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		

		inflater.inflate(R.menu.circle_users2, menu);	
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
		    args.putStringArrayList("ExistingUsers", circleUsersNames);
		    fragment.setArguments(args);
		    getActivity().setRequestedOrientation(
		            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.framee, fragment).addToBackStack("CircleUsersFragment").commit();
			break;

//		case R.id.del:
//			
//			removeUsersBtn.setVisibility(View.VISIBLE);
//			
//				adapter.setCheckBoxVisible();
//				list.invalidateViews();
//			
//			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	  @Override
	    public void onResume() {
	    	// TODO Auto-generated method stub
	    	getActivity().invalidateOptionsMenu();
	    	super.onResume();
	    	
	    }
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		
		super.onStop();
		
	}

	
}
