package com.iti.jets.carpoolingV1.eventshome;



import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NoEventsHome extends Fragment {

	Button addEvent;
	ImageView errorImage;
	TextView errorTxt;
	View rootView;
	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {

	
	rootView = inflater.inflate(R.layout.no_notification, container, false);
	
	errorImage = (ImageView) rootView.findViewById(R.id.errorImage);
	errorTxt = (TextView) rootView.findViewById(R.id.errotText);
	errorImage.setImageResource(R.drawable.large_event_icon);
	errorTxt.setText("No Event");
			addEvent = (Button) rootView.findViewById(R.id.button1);
			addEvent.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					UIManagerHandler.goToAddEvent(getActivity());
					
				}
			});
			
	return rootView;
}




}
