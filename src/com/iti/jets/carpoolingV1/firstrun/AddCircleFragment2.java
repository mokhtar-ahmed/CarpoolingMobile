package com.iti.jets.carpoolingV1.firstrun;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.R.layout;
import com.iti.jets.carpoolingV1.R.menu;
import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleController;
import com.iti.jets.carpoolingV1.addcircleactivity.CustomGridViewAdapter;
import com.iti.jets.carpoolingV1.common.CircleItemObj;
import com.iti.jets.carpoolingV1.common.ImageCompressionHandler;
import com.iti.jets.carpoolingV1.common.ImageItem;
import com.iti.jets.carpoolingV1.common.ShowDialog;
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.editprofileactivity.EditProfileActivity;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;
import com.iti.jets.carpoolingV1.pojos.Circle;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.retrieveallcircles.AddUserToCircletestAsyncTask;
import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.CirclesUsersArrayAdapter;
import com.iti.jets.carpoolingV1.retrieveallcircles.GoToAllCirclesListActivity;
import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment.FragmentCallback;
import com.iti.jets.carpoolingV1.sharedlayout.MainActivity;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.Log;
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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class AddCircleFragment2 extends Fragment {

	private Button addCircleBtn;
	private EditText circleNameTxt;
	private ImageView circleImageView;
	private final int REQUEST_CODE_FROM_GALLERY = 01;
	Bitmap userIcon1 = null;
	Bitmap userIcon2,userIcon3,userIcon4,userIcon5,userIcon6,userIcon7,userIcon8;
	private Uri selectedImageUri;
	private String filePath;
	private Bitmap imgBitmap;
	private ImageCompressionHandler imageHandler;
	private AddCircleController controller;
	private int userId = 1;
	private boolean circleExistflag = false;
	ArrayList<ImageItem> gridArray = new ArrayList<ImageItem>();
	Bitmap currentBimap;
	View rootView;
	ImageItem item;
	//public static HashSet<CircleItemObj> circleItemList = new HashSet<CircleItemObj>();
	public static CircleItemObj newCircobj;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.activity_add_circle, container,
				false);
		final View circleImgView = inflater.inflate(R.layout.circles_images_list, container,
				false);
		setHasOptionsMenu(false);
		addCircleBtn = (Button) rootView.findViewById(R.id.addCircleBtn);
		circleNameTxt = (EditText) rootView.findViewById(R.id.circleNameTxt);
		circleImageView = (ImageView) rootView.findViewById(R.id.circleImgView);
		controller = new AddCircleController(this);
		Bundle newIntent = getActivity().getIntent().getExtras();
		if (newIntent != null) {
			userId = newIntent.getInt("userId");
		}
		circleImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				GridView gridView;
				
				CustomGridViewAdapter customGridAdapter;

				if(userIcon1 != null)
				{
					userIcon1.recycle();
					userIcon1 = BitmapFactory.decodeResource(
							AddCircleFragment2.this.getResources(), R.drawable.p1);
				}
				else
				{
					userIcon1 = BitmapFactory.decodeResource(
							AddCircleFragment2.this.getResources(), R.drawable.p1);
				}
				if(userIcon2 != null)
				{
					userIcon2.recycle();
					userIcon2 = BitmapFactory.decodeResource(
							AddCircleFragment2.this.getResources(), R.drawable.p6);
				}
				else
				{
					userIcon2 = BitmapFactory.decodeResource(
							AddCircleFragment2.this.getResources(), R.drawable.p6);
				}
				if(userIcon3 != null)
				{
					userIcon3.recycle();
					userIcon3 = BitmapFactory.decodeResource(
							AddCircleFragment2.this.getResources(), R.drawable.p12);
				}
				else
				{
					userIcon3 = BitmapFactory.decodeResource(
							AddCircleFragment2.this.getResources(), R.drawable.p12);
				}
				if(userIcon4 != null)
				{
					userIcon4.recycle();
					userIcon4 = BitmapFactory.decodeResource(
							AddCircleFragment2.this.getResources(), R.drawable.p4);
				}
				else
				{
					userIcon4 = BitmapFactory.decodeResource(
							AddCircleFragment2.this.getResources(), R.drawable.p4);
				}
				if(userIcon5 != null)
				{
					userIcon5.recycle();
					userIcon5 = BitmapFactory.decodeResource(
							AddCircleFragment2.this.getResources(), R.drawable.pp7);
				}
				else
				{
					userIcon5 = BitmapFactory.decodeResource(
							AddCircleFragment2.this.getResources(), R.drawable.pp7);
				}
				if(userIcon6 != null)
				{
					userIcon6.recycle();
					userIcon6 = BitmapFactory.decodeResource(
							AddCircleFragment2.this.getResources(), R.drawable.pp8);
				}
				else
				{
					userIcon6 = BitmapFactory.decodeResource(
							AddCircleFragment2.this.getResources(), R.drawable.pp8);
				}
				if(userIcon7 != null)
				{
					userIcon7.recycle();
					userIcon7 = BitmapFactory.decodeResource(
							AddCircleFragment2.this.getResources(), R.drawable.pp9);
				}
				else
				{
					userIcon7 = BitmapFactory.decodeResource(
							AddCircleFragment2.this.getResources(), R.drawable.pp9);
				}
//				if(userIcon8 != null)
//				{
//					userIcon8.recycle();
//					userIcon8 = BitmapFactory.decodeResource(
//							AddCircleFragment.this.getResources(), R.drawable.pp10);
//				}
//				else
//				{
//					userIcon8 = BitmapFactory.decodeResource(
//							AddCircleFragment.this.getResources(), R.drawable.pp10);
//				}

				gridArray.add(new ImageItem(userIcon1,R.drawable.p1));
				gridArray.add(new ImageItem(userIcon2,R.drawable.p6));
				gridArray.add(new ImageItem(userIcon3,R.drawable.p12));
				gridArray.add(new ImageItem(userIcon4,R.drawable.p4));
				gridArray.add(new ImageItem(userIcon5,R.drawable.pp7));
				gridArray.add(new ImageItem(userIcon6,R.drawable.pp8));
				gridArray.add(new ImageItem(userIcon7,R.drawable.pp9));
				gridArray.add(new ImageItem(userIcon8,R.drawable.pp10));
				gridView = (GridView)circleImgView.findViewById(R.id.gridview1);
				customGridAdapter = new CustomGridViewAdapter(AddCircleFragment2.this.getActivity(),
						R.layout.circle_image, gridArray);
				gridView.setAdapter(customGridAdapter);
				// Create custom dialog object
                final Dialog dialog = new Dialog(AddCircleFragment2.this.getActivity());
                
                // Include dialog.xml file
                dialog.setContentView(circleImgView);
                // Set dialog title
                dialog.setTitle("Choose icon");
                
                // set values for custom dialog components - text, image and button
//                TextView text = (TextView) dialog.findViewById(R.id.textDialog);
//                text.setText("Custom dialog Android example.");
//                ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
//                image.setImageResource(R.drawable.image0);
 
                dialog.show();
				gridView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View v,
							int position, long arg3) {
						
						item = (ImageItem) gridArray.get(position);
						currentBimap = item.getBitmap();
						dialog.dismiss();
						circleImageView.setImageBitmap(currentBimap );
						
						
					}
				});

			}
		});
		addCircleBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String circleName = circleNameTxt.getText().toString();
				if (circleName.equals("")) {
					CharSequence error = "Can't submit empty field";
					circleNameTxt.setError(error);
				} else {
					 
					ArrayList<Circle> currentCircles = EntityFactory
							.getCirclesInstance();
					for (int i = 0; i < currentCircles.size(); i++) {
						Circle tempCircle = currentCircles.get(i);
						if (tempCircle.getCircleName().equalsIgnoreCase(
								circleName)) {

							circleExistflag = true;
							break;
						} else {
							circleExistflag = false;
						}

					}
					if (circleExistflag) {
						ShowDialog showDialogInstance = new ShowDialog();
						showDialogInstance.showDialog("Error",
								"Circle Already Exists",
								AddCircleFragment2.this.getActivity());
					} else {
//						newCircobj= new CircleItemObj();
//						newCircobj.setName(circleName);
//						newCircobj.setBitmap(currentBimap);
						
						controller.setArguments(circleName,item.getCircleRes(),
								filePath, new FragmentCallback2() {
									//
									
									@Override
									public void onTaskDone(String result) {
										// TODO Auto-generated method stub

										Fragment fragment = new AllCirclesListFragment2();
										
										if (fragment != null) {
											AddCircleFragment2.this.getActivity().setRequestedOrientation(
										            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
											FragmentManager fragmentManager = AddCircleFragment2.this.getActivity().getFragmentManager();
											CharSequence title = "My Circles";
											AddCircleFragment2.this.getActivity().getActionBar().setTitle(title);
//											 fragmentManager.popBackStack();
											 
											fragmentManager.beginTransaction()
													.replace(R.id.framee, fragment).addToBackStack("addCirc").commit();
											
											
											
										}

									}
								});
					}

				}

			}
		});

		return rootView;
	}

	public interface FragmentCallback2 {
		// public void onTaskDone(String result);

		public void onTaskDone(String result);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub

		inflater.inflate(R.menu.circles_home, menu);
		// super.onCreateOptionsMenu(menu, inflater);
		for (int i = 0; i < menu.size(); i++) {
			menu.getItem(i).setVisible(false);
		}

	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Fragment fragment = new AllCirclesListFragment2();
		
		if (fragment != null) {
			AddCircleFragment2.this.getActivity().setRequestedOrientation(
		            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			FragmentManager fragmentManager = AddCircleFragment2.this.getActivity().getFragmentManager();
			CharSequence title = "My Circles";
			AddCircleFragment2.this.getActivity().getActionBar().setTitle(title);
//			 fragmentManager.popBackStack();
			 
			fragmentManager.beginTransaction()
					.replace(R.id.framee, fragment).addToBackStack("addCirc").commit();
		}
	}

}
