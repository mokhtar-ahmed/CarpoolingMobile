package com.iti.jets.carpoolingV1.addcircleactivity;

import java.util.ArrayList;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleFragment.FragmentCallback;
import com.iti.jets.carpoolingV1.common.CircleItemObj;
import com.iti.jets.carpoolingV1.common.ImageCompressionHandler;
import com.iti.jets.carpoolingV1.common.ImageItem;
import com.iti.jets.carpoolingV1.common.ShowDialog;
import com.iti.jets.carpoolingV1.pojos.Circle;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.sharedlayout.MainActivity;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class AddCircleActivity  extends Activity{
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
	
	public static CircleItemObj newCircobj;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_circle);
		
		addCircleBtn = (Button) rootView.findViewById(R.id.addCircleBtn);
		circleNameTxt = (EditText) rootView.findViewById(R.id.circleNameTxt);
		circleImageView = (ImageView) rootView.findViewById(R.id.circleImgView);
//		controller = new AddCircleController(this);
		
		Bundle newIntent = this.getIntent().getExtras();
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
							getResources(), R.drawable.p1);
				}
				else
				{
					userIcon1 = BitmapFactory.decodeResource(
							getResources(), R.drawable.p1);
				}
				if(userIcon2 != null)
				{
					userIcon2.recycle();
					userIcon2 = BitmapFactory.decodeResource(
							getResources(), R.drawable.p6);
				}
				else
				{
					userIcon2 = BitmapFactory.decodeResource(
							getResources(), R.drawable.p6);
				}
				if(userIcon3 != null)
				{
					userIcon3.recycle();
					userIcon3 = BitmapFactory.decodeResource(
							getResources(), R.drawable.p12);
				}
				else
				{
					userIcon3 = BitmapFactory.decodeResource(
							getResources(), R.drawable.p12);
				}
				if(userIcon4 != null)
				{
					userIcon4.recycle();
					userIcon4 = BitmapFactory.decodeResource(
							getResources(), R.drawable.p4);
				}
				else
				{
					userIcon4 = BitmapFactory.decodeResource(
							getResources(), R.drawable.p4);
				}
				if(userIcon5 != null)
				{
					userIcon5.recycle();
					userIcon5 = BitmapFactory.decodeResource(
							getResources(), R.drawable.pp7);
				}
				else
				{
					userIcon5 = BitmapFactory.decodeResource(
							getResources(), R.drawable.pp7);
				}
				if(userIcon6 != null)
				{
					userIcon6.recycle();
					userIcon6 = BitmapFactory.decodeResource(
							getResources(), R.drawable.pp8);
				}
				else
				{
					userIcon6 = BitmapFactory.decodeResource(
							getResources(), R.drawable.pp8);
				}
				if(userIcon7 != null)
				{
					userIcon7.recycle();
					userIcon7 = BitmapFactory.decodeResource(
							getResources(), R.drawable.pp9);
				}
				else
				{
					userIcon7 = BitmapFactory.decodeResource(
							getResources(), R.drawable.pp9);
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
//				gridView = (GridView)circleImgView.findViewById(R.id.gridview1);
//				
//				customGridAdapter = new CustomGridViewAdapter(this,R.layout.circle_image, gridArray);
//				gridView.setAdapter(customGridAdapter);
//				// Create custom dialog object
////                final Dialog dialog = new Dialog(this);
//                
//                // Include dialog.xml file
////                dialog.setContentView(circleImgView);
//                // Set dialog title
////                dialog.setTitle("Choose icon");
//                
//                // set values for custom dialog components - text, image and button
////                TextView text = (TextView) dialog.findViewById(R.id.textDialog);
////                text.setText("Custom dialog Android example.");
////                ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
////                image.setImageResource(R.drawable.image0);
// 
//                dialog.show();
//				gridView.setOnItemClickListener(new OnItemClickListener() {
//					@Override
//					public void onItemClick(AdapterView<?> arg0, View v,
//							int position, long arg3) {
//						
//						item = (ImageItem) gridArray.get(position);
//						currentBimap = item.getBitmap();
//						dialog.dismiss();
//						circleImageView.setImageBitmap(currentBimap );
//						
//						
//					}
//				});

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
//						ShowDialog showDialogInstance = new ShowDialog();
//						showDialogInstance.showDialog("Error",
//								"Circle Already Exists",
//								this);
					} else {
//						newCircobj= new CircleItemObj();
//						newCircobj.setName(circleName);
//						newCircobj.setBitmap(currentBimap);
						
//						controller.setArguments(circleName,item.getCircleRes(),
//								filePath, new FragmentCallback() {
//									//
//									
//									@Override
//									public void onTaskDone(String result) {
//										// TODO Auto-generated method stub
//
//										UIManagerHandler
//												.goToAllCirclesList(AddCircleActivity.this
//														);
//
//									}
//								});
					}

				}

			}
		});

		
		
	}

	public interface FragmentCallback {
		// public void onTaskDone(String result);

		public void onTaskDone(String result);
	}
	
@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	Toast.makeText(getApplicationContext(), "ONFINISH", Toast.LENGTH_LONG).show();
	super.onDestroy();
}
}
