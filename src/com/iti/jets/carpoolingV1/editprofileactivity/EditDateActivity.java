package com.iti.jets.carpoolingV1.editprofileactivity;

import java.util.Calendar;
import com.iti.jets.carpoolingV1.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class EditDateActivity extends Activity {

	EditText dateEditText;
	Button doneBtn;
	String date,newDate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_date);
		
		dateEditText = (EditText)findViewById(R.id.dateEditText);
		Bundle newIntent = getIntent().getExtras();
		if (newIntent != null) {
			date = newIntent.getString("date");
		}
		dateEditText.setText(date);
		doneBtn = (Button) findViewById(R.id.doneBtn);
		doneBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				newDate = dateEditText.getText().toString();
				Intent intent = new Intent(getApplicationContext(),EditProfileActivity.class);
				intent.putExtra("newDate", newDate);
				intent.putExtra("flag", "1");
				startActivity(intent);
			}
		});
		  
		
		
	}



}
