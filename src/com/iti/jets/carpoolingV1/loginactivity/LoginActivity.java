package com.iti.jets.carpoolingV1.loginactivity;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.facebook.*;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.ProfilePictureView;
import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.sharedlayout.MainActivity;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.iti.jets.carpoolingV1.common.MyApplication;
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.editprofileactivity.EditProfileActivity;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.registrationactivity.RegisterActivity;
import com.iti.jets.carpoolingV1.registrationactivity.RegisterFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.widget.Toast;


public class LoginActivity extends Activity{

	EditText usernameTxt;
	EditText passwordTxt;
	Button	 loginBtn;
	Button   registerBtn;
	Bitmap userBitmap = null;
	String name = "myPref";
    LoginButton  registerFacebookBtn;
	ProgressDialog prog;
	LoginController controller;
	public static boolean flag = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 
		
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        
   
		setContentView(R.layout.activity_login);

		 controller = new LoginController(this);
		 
		 usernameTxt = (EditText) findViewById(R.id.EmailTxt);
		 passwordTxt = (EditText) findViewById(R.id.passwordTxt);
		 loginBtn	 = (Button) findViewById(R.id.LoginBtn);
		 registerBtn = (Button) findViewById(R.id.registerBtn);
		 registerFacebookBtn = (LoginButton)findViewById(R.id.registerFacebookBtn);
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
					
						prog = new ProgressDialog(LoginActivity.this);
						prog.setMessage("Logining.....");
						prog.show();
						
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
					
					UIManagerHandler.goToRegister(LoginActivity.this,flag);
				}
			});
		 

		 registerFacebookBtn.setReadPermissions(Arrays.asList("basic_info", "email"));

		 registerFacebookBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Session.openActiveSession(com.iti.jets.carpoolingV1.loginactivity.LoginActivity.this, true, new Session.StatusCallback() {

				      // callback when session changes state
				      @Override
				      public void call(Session session, SessionState state, Exception exception) {
				        if (session.isOpened()) {

				          // make request to the /me API
				          Request.newMeRequest(session, new Request.GraphUserCallback() {
				          	  
				            // callback after Graph API response with user object
				            @Override
				            public void onCompleted(GraphUser user, Response response) {
				              if (user != null) {
				             
//				            	Map<String,Object> userMAp = user.asMap();
				            	
				            	JSONObject tempJs = new JSONObject();   
//								Toast.makeText(getApplicationContext(), "Us"+user.getName(),Toast.LENGTH_LONG
//										   ).show();
//								Toast.makeText(getApplicationContext(), "Us"+user.asMap().get("email").toString(),Toast.LENGTH_LONG
//										   ).show();
								try {
//									Toast.makeText(getApplicationContext(), "Us"+user.getInnerJSONObject().getString("gender"),Toast.LENGTH_LONG
//											   ).show();
//									Toast.makeText(getApplicationContext(), "Us"+user.getInnerJSONObject().getString("link"),Toast.LENGTH_LONG
//											   ).show();
//									Toast.makeText(getApplicationContext(), "Us"+user.getId(),Toast.LENGTH_LONG
//											   ).show();
								
									getFacebookPictureAsyncTask task = new getFacebookPictureAsyncTask ();
									task.execute(user.getId());
									flag = true;
									Bundle bundle = new Bundle();
									bundle.putString("userName",user.getName());
									bundle.putString("gender",user.getInnerJSONObject().getString("gender"));
									bundle.putString("email",user.asMap().get("email").toString());
									
									UIManagerHandler.goToRegister(LoginActivity.this,bundle,flag,userBitmap);
									
		                            
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							
				              }
				            }
				          }).executeAsync();
				        }
				      }
				    });
			}
		});
	
		 
		 
	}
	
	
	
	
	
	public Bitmap getPhotoFacebook(final String id) {

	    Bitmap bitmap=null;
	    final String nomimg = "https://graph.facebook.com/"+id+"/picture?type=large";
	    URL imageURL = null;

	    try {
	        imageURL = new URL(nomimg);
	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    }

	    try {
	        HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
	        connection.setDoInput(true);
	        connection.setInstanceFollowRedirects( true );
	        connection.connect();
	        InputStream inputStream = connection.getInputStream();
	        //img_value.openConnection().setInstanceFollowRedirects(true).getInputStream()
	        bitmap = BitmapFactory.decodeStream(inputStream);

	    } catch (IOException e) {

	        e.printStackTrace();
	    }
	    return bitmap;

	}
	
	
	 @Override
	  public void onActivityResult(int requestCode, int resultCode, Intent data) {
	      super.onActivityResult(requestCode, resultCode, data);
	      Session.getActiveSession().onActivityResult(com.iti.jets.carpoolingV1.loginactivity.LoginActivity.this, requestCode, resultCode, data);
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

	private class getFacebookPictureAsyncTask extends AsyncTask<String, Void, Bitmap> {
       
		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			Bitmap newBitmap = getPhotoFacebook(params[0]);
			return newBitmap;
		}

        @Override
        protected void onPostExecute(Bitmap result) {
        	// TODO Auto-generated method stub
        	super.onPostExecute(result);
        	LoginActivity.this.userBitmap=result;
        }
      }	
	
}
