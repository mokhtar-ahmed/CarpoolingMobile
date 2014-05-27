package com.iti.jets.carpoolingV1.loginactivity;
import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.sharedlayout.MainActivity;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;
import org.json.JSONException;
import org.json.JSONObject;
import com.iti.jets.carpoolingV1.common.MyApplication;
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.editprofileactivity.EditProfileActivity;
import com.iti.jets.carpoolingV1.registrationactivity.RegisterFragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.widget.Toast;


public class LoginActivity extends Activity{

	EditText usernameTxt;
	EditText passwordTxt;
	Button	 loginBtn;
	Button   registerBtn;

	String name = "myPref";

	LoginController controller;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_login);
		 
		 controller = new LoginController(this);
		 
		 usernameTxt = (EditText) findViewById(R.id.EmailTxt);
		 passwordTxt = (EditText) findViewById(R.id.passwordTxt);
		 loginBtn	 = (Button) findViewById(R.id.LoginBtn);
		 registerBtn = (Button) findViewById(R.id.registerBtn);
 		 
		 passwordTxt.setText(loadPassword());
		 usernameTxt.setText(loadUsername());
		 
		 loginBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (usernameTxt.getText().length() < 3){

					usernameTxt.setError("At least 3 char");
				}
				else if(passwordTxt.getText().length() < 5){
					passwordTxt.setError("At least 5 char");
				}else{
						controller.login(usernameTxt.getText().toString(),
								passwordTxt.getText().toString());
				}
				
				//sUIManagerHandler.goToHome(LoginActivity.this);
				
			}
		});
		 
		 registerBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					UIManagerHandler.goToRegister(LoginActivity.this);
				}
			});
		 
	
	
		 
		 
	}
	private String loadUsername(){
	
		SharedPreferences myPrefs  = getSharedPreferences(name, 0);
		String userData  = myPrefs.getString("email", "");
		return userData;
	}
	private String loadPassword(){
		SharedPreferences myPrefs  = getSharedPreferences(name, 0);
		String userData  = myPrefs.getString("password", "");	
		return userData;
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);

	}

	
}
