package com.iti.jets.carpoolingV1.loginactivity;
import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.sharedlayout.MainActivity;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends Activity{

	EditText usernameTxt;
	EditText passwordTxt;
	Button	 loginBtn;
	Button 	 faceBookBtn;
	Button   googleBtn;
	Button   registerBtn;
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
		 faceBookBtn = (Button) findViewById(R.id.FacebookBtn);
		 googleBtn 	 = (Button) findViewById(R.id.googleBtn);
		 registerBtn = (Button) findViewById(R.id.registerBtn);
 		 
		 loginBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (usernameTxt.getText().length() < 5){
					usernameTxt.setError("At least 6 char");
				}
				else if(passwordTxt.getText().length() < 5){
					passwordTxt.setError("At least 6 char");
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
		 
		 faceBookBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
		 
		 
		 
		 googleBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
		 
	
		 
		 
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
