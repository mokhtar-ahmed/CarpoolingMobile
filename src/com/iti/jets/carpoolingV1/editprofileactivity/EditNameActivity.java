package com.iti.jets.carpoolingV1.editprofileactivity;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.R.layout;
import com.iti.jets.carpoolingV1.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNameActivity extends Activity {

	EditText usernameEditText;
	String username,newUsername;
	Button saveBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_name);
		
		usernameEditText = (EditText)findViewById(R.id.usernameEditText);
		Bundle newIntent = getIntent().getExtras();
		if (newIntent != null) {
			username = newIntent.getString("username");
		}
		usernameEditText.setText(username);
		saveBtn = (Button) findViewById(R.id.saveBtn);
		saveBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				newUsername = usernameEditText.getText().toString();
				Intent intent = new Intent(getApplicationContext(),EditProfileActivity.class);
				intent.putExtra("username", newUsername);
				intent.putExtra("flag", "0");
				startActivity(intent);
			}
		});
		
	}



}
