package com.iti.jets.carpoolingV1.firstrun;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.editprofileactivity.EditProfileFragement;
import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment;


import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
public class FirstRunFragment extends Fragment {

    View rootView;	
    ImageView profileImg,circleImg;
    TextView profileTxt,circlesTxt;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
         rootView = inflater.inflate(R.layout.first_run_fragment,container, false);
         setHasOptionsMenu(true);
         profileImg = (ImageView) rootView.findViewById(R.id.profileImg);
         circleImg = (ImageView) rootView.findViewById(R.id.circleImg);
         profileTxt = (TextView) rootView.findViewById(R.id.profileTxt);
         circlesTxt = (TextView) rootView.findViewById(R.id.circleTxt); 
         
         profileImg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				EditProfileFragement fragment = new EditProfileFragement(); 
//				android.support.v4.app.FragmentManager fragmentManager = FirstRunFragment.this.getFragmentManager();
//				fragmentManager.beginTransaction()
//						.replace(R.id.frame_container, fragment).commit();
				
				
			}
		});
         circleImg.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View arg0) {
 				// TODO Auto-generated method stub
// 				Fragment fragment = new AllCirclesListFragment(); 
//				FragmentManager fragmentManager = FirstRunFragment.this.getFragmentManager();
//				fragmentManager.beginTransaction()
//						.replace(R.id.frame_container, fragment).commit();
 				
 				
 			}
 		});
         profileTxt.setOnClickListener(new View.OnClickListener() {
  			
  			@Override
  			public void onClick(View arg0) {
  				// TODO Auto-generated method stub
//  				Fragment fragment = new EditProfileFragement(); 
//				FragmentManager fragmentManager = FirstRunFragment.this.getFragmentManager();
//				fragmentManager.beginTransaction()
//						.replace(R.id.frame_container, fragment).commit();
  				
  				
  			}
  		});
         circlesTxt.setOnClickListener(new View.OnClickListener() {
   			
   			@Override
   			public void onClick(View arg0) {
   				// TODO Auto-generated method stub
//   				Fragment fragment = new AllCirclesListFragment(); 
//				FragmentManager fragmentManager = FirstRunFragment.this.getFragmentManager();
//				fragmentManager.beginTransaction()
//						.replace(R.id.frame_container, fragment).commit();
   				
   			}
   		}); 
		 return rootView;
		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.circles_home, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
}
