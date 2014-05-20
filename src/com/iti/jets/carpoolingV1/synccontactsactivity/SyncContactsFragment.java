package com.iti.jets.carpoolingV1.synccontactsactivity;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.R.layout;
import com.iti.jets.carpoolingV1.R.menu;
import com.iti.jets.carpoolingV1.common.Circle;
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.CirclesUsersArrayAdapter;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SyncContactsFragment extends Fragment {

	ListView list;
	CheckBox checkBox;
    SyncContactsCustomArrayAdapter adapter;
    Button addFriendToCircleBtn ;
    private ArrayList<Integer> selectedUsersIds = new ArrayList<Integer>();
    private  ArrayList<User> registeredFriendsList = new ArrayList<User>();
    private  ArrayList<User> selectedUsers = new ArrayList<User>();
    private AddUserToCircleController addUserToCircleCont;
    private User tempUser;
    private int userId,circleId; 
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		
         rootView = inflater.inflate(R.layout.sync_contacts_view,container, false);
         circleId = SyncContactsFragment.this.getArguments().getInt("circle_Id");
	         userId =  SyncContactsFragment.this.getArguments().getInt("user_Id");
	         System.out.println("RRRRRRRRr"+circleId);
	         System.out.println("RRRRRRRRr"+ userId);
         addFriendToCircleBtn = (Button)rootView.findViewById(R.id.addFriendBtn);
// 		Bundle newIntent = getActivity().getIntent().getExtras();
// 		if (newIntent != null) {
// 			
// 			circleId = newIntent.getInt("circle_Id");
// 			//Toast.makeText(getActivity().getApplicationContext(), "YES@@  "+circleId, Toast.LENGTH_LONG).show();
// 			userId = newIntent.getInt("user_Id");
// 		}
      
 		//Toast.makeText(getActivity().getApplicationContext(), "AFTERYES@@  "+circleId, Toast.LENGTH_LONG).show();
     	ContentResolver contentResolver = getActivity().getContentResolver();
 		SyncContactsController controller = new SyncContactsController(contentResolver,this);
 		addUserToCircleCont = new AddUserToCircleController(this);
 		
 		
 		addFriendToCircleBtn.setOnClickListener(new View.OnClickListener() {
 			
 			JSONArray selectedUsersJSArr = new JSONArray();
		    JSONObject temp ;
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 			  
 				for(int i=0 ; i<registeredFriendsList.size();i++)
 				{
 					tempUser = new User();
 					tempUser = registeredFriendsList.get(i);
 					if (tempUser.getIsSelected())
 					{
 						//Toast.makeText(getActivity().getApplicationContext(), tempUser.getName()+" %%%", Toast.LENGTH_SHORT).show();
 						selectedUsers.add(tempUser);
 						try {
 							temp = new JSONObject();
							temp.put("userId",tempUser.getUserId());
							temp.put("Name",tempUser.getName());
							System.out.println(tempUser.getName());
	 						temp.put("Phone",tempUser.getPhone());
	 						temp.put("Image",tempUser.getImageURL());
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
 						
 						selectedUsersJSArr.put(temp);
 						
 					}
 				}
// 				addUsersAsyncTask testAsyncTask = new  addUsersAsyncTask(new FragmentCallback() {
// 					
// 					                 public void onTaskDone() {
// 					                     //methodThatDoesSomethingWhenTaskIsDone();
//					                	 Resources res = getResources();
//					 	 	            ListView list = ( ListView )rootView.findViewById(R.id.list );  // List defined in XML ( See Below )
//				 	 	             
//					 	 	            /**************** Create Custom Adapter *********/
// 					 	 	           CirclesUsersArrayAdapter adapter = new  CirclesUsersArrayAdapter(this.getActivity(),circleActualUsersList,res);
// 					 	 	           list.setAdapter( adapter );
// 					                 }
// 					
// 									@Override
// 									public void onTaskDone(String result) {
// 										// TODO Auto-generated method stub
// 										System.out.println(result);										
// 									}
// 					             },this.circle_Id);
// 					     		String URL= HttpConstants.SERVER_URL+HttpConstants.RETRIEVE_CIRCLE_USRES_URL; 
//					     		testAsyncTask.execute( URL); 
 				
 				//userId = tempUser.getUserId();
 				Fragment fragment = new CircleUsersFragment();
 				Bundle args = new Bundle();
 			    args.putBoolean("flag", true);
 			    args.putString("selectedUsersJSArr", selectedUsersJSArr.toString());
 			    args.putInt("circle_Id", circleId);
 			    args.putInt("user_Id",userId);
 			    fragment.setArguments(args);
 				FragmentManager fragmentManager = getFragmentManager();
 				fragmentManager.beginTransaction()
 						.replace(R.id.frame_container, fragment).commit();
 			    
 				
 				
 			}
 		});
 	
        return rootView;
	}
	
    public void getResultFromService(String result) {
		// TODO Auto-generated method stub
		
		  //Toast.makeText(getActivity().getApplicationContext(), "TESTTESTTEST", Toast.LENGTH_LONG).show();
		  registeredFriendsList = new ArrayList<User>();
		  ArrayList<String> namesList = new ArrayList<String>();
      	try {
//      		Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG).show();
			JSONArray registeredFriendsJsArray = new JSONArray(result);
			for(int i=0;i<registeredFriendsJsArray.length();i++)
			{
				JSONObject jsObj = registeredFriendsJsArray.getJSONObject(i);
				System.out.println(jsObj);
				User tempUser = new User();
				tempUser.setName(jsObj.getString("name"));
				tempUser.setPhone(jsObj.getString("phone"));
				tempUser.setUserId(jsObj.getInt("id"));
				
				if(jsObj.getString("image") == null)
				{
					tempUser.setImageURL("image");
				}
				else
				{
					tempUser.setImageURL(jsObj.getString("image"));
				}
				
				namesList.add(tempUser.getName());
				registeredFriendsList.add(tempUser);
				Toast.makeText(getActivity().getApplicationContext(),tempUser.getName(),Toast.LENGTH_LONG).show();
				System.out.println("Size"+"  "+registeredFriendsList.size());
				
			}			
			
			
	
			Resources res =getResources();
            list = ( ListView )rootView.findViewById( R.id.list );  // List defined in XML ( See Below )
             
            /**************** Create Custom Adapter *********/
            adapter=new SyncContactsCustomArrayAdapter(this,registeredFriendsList,res );
            list.setAdapter( adapter );
            
            
            
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		
	}
	public void onItemClick(int mPosition) {
		
		// TODO Auto-generated method stub
//		User userClickedValues = (User) registeredFriendsList.get(mPosition);
//		Toast.makeText(getApplicationContext(), userClickedValues.getName(),Toast.LENGTH_LONG).show();
		
	}
		
}