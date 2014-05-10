package com.iti.jets.carpoolingV1.loginactivity;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.httphandler.LoginServiceHandler;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

import android.app.Activity;
import android.widget.Toast;

public class LoginController {

	Activity activity;
	
	public LoginController(	Activity activity){
		this.activity = activity;
		
	}
	public String login(String username , String password){
		
		if(username.equals("")== false && password.equals("") == false){
			
			JSONObject input = new JSONObject();
			try {
				input.put("username", username);
				input.put("password", password);
				new LoginServiceHandler(this).execute(new String[]{input.toString()});
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
		}else{
			
			
		} 
		return "";
	}

	public void onPostExcuteResult(String result) {
		
		
		Toast.makeText(activity,result, Toast.LENGTH_LONG).show();
		
		if(result != null ){

			if(result.equals("No Connection") == false){
				
				try{
					
					JSONObject resultJson = new JSONObject(result);
					if(((String)resultJson.get("FaultsMsg")).equals("success")== true){
						
						UIManagerHandler.goToHome(activity);
					}
					else{
						Toast.makeText(activity,(String)resultJson.get("FaultsMsg"), Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else{
				
				Toast.makeText(activity,"Connect to internet", Toast.LENGTH_LONG).show();
				
			}
			
		}
			System.out.println(result);
	}
}
