package com.iti.jets.carpoolingV1.retrieveallcircles;


import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.R.layout;
import com.iti.jets.carpoolingV1.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class GoToAllCirclesListActivity extends Activity {

	Button goToCirclesListBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_go_to_circles);
		goToCirclesListBtn = (Button)findViewById(R.id.circlesBtn);
		goToCirclesListBtn.setTextColor(Color.parseColor("white"));
		goToCirclesListBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), AllCirclesListFragment.class);
				int userId = 1;
				intent.putExtra("userId", userId);
				startActivity(intent);
				
			}
		});
		
	}

	
}
