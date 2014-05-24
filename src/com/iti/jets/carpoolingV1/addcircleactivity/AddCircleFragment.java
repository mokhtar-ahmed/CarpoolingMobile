package com.iti.jets.carpoolingV1.addcircleactivity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.R.layout;
import com.iti.jets.carpoolingV1.R.menu;
import com.iti.jets.carpoolingV1.common.ImageCompressionHandler;
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.editprofileactivity.EditProfileActivity;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;
import com.iti.jets.carpoolingV1.retrieveallcircles.AddUserToCircletestAsyncTask;
import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.CirclesUsersArrayAdapter;
import com.iti.jets.carpoolingV1.retrieveallcircles.GoToAllCirclesListActivity;
import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment.FragmentCallback;
import com.iti.jets.carpoolingV1.sharedlayout.MainActivity;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class AddCircleFragment extends Fragment {

	private Button addCircleBtn;
	private EditText circleNameTxt;
	private ImageView circleImageView;
	private final int REQUEST_CODE_FROM_GALLERY = 01;
	private Uri selectedImageUri;
	private String filePath; 
	private Bitmap imgBitmap;
	private ImageCompressionHandler imageHandler;
	private AddCircleController controller;
	private int userId = 1;
	View rootView;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		rootView = inflater.inflate(R.layout.activity_add_circle,container, false);
		addCircleBtn = (Button) rootView.findViewById(R.id.addCircleBtn);
		circleNameTxt = (EditText)  rootView.findViewById(R.id.circleNameTxt);
		circleImageView = (ImageView)  rootView.findViewById(R.id.circleImgView);
		controller = new AddCircleController(this);
		Bundle newIntent = getActivity().getIntent().getExtras();
		if (newIntent != null) {
			userId = newIntent.getInt("userId");
		}
		circleImageView.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			  			intent.setType("image/*");
			  			startActivityForResult(intent, REQUEST_CODE_FROM_GALLERY); 
						
					}
				});
		addCircleBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String circleName = circleNameTxt.getText().toString();
				
				if(AddCircleFragment.this.getActivity() == null)
				{
					System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
				}
				controller.setArguments(circleName,imgBitmap,filePath,new FragmentCallback(){
					
					
					@Override
					public void onTaskDone(String result) {
						// TODO Auto-generated method stub
						
//						
						Fragment fragment = new AllCirclesListFragment(); 
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.beginTransaction()
								.replace(R.id.frame_container, fragment).commit();
						
					}
	             });
				
			}
		});
         
       
     
 	
        return rootView;
	}
	
	public interface FragmentCallback {
        //public void onTaskDone(String result);

		public void onTaskDone(String result);
    }
}

