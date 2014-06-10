package com.iti.jets.carpoolingV1.loginactivity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.common.ShowDialog;
import com.iti.jets.carpoolingV1.editprofileactivity.EditProfileFragement;
import com.iti.jets.carpoolingV1.firstrun.FirstRunActivity;
import com.iti.jets.carpoolingV1.httphandler.LoginServiceHandler;

import com.iti.jets.carpoolingV1.jsonhandler.JsonParser;
import com.iti.jets.carpoolingV1.pojos.Circle;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.pojos.Event;
import com.iti.jets.carpoolingV1.pojos.Notification;
import com.iti.jets.carpoolingV1.pojos.User;
import com.iti.jets.carpoolingV1.uimanager.UIManagerHandler;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.audiofx.NoiseSuppressor;
import android.preference.PreferenceManager;
import android.widget.Toast;


public class LoginController {

	LoginActivity activity;
	String name = "myPref";	
	String username;
	String password;
	public LoginController(	LoginActivity activity){
		this.activity = activity;
		
	}

	private void saveShared() {

		SharedPreferences myPrefs  = activity.getSharedPreferences(name, 0);
		SharedPreferences.Editor editor = myPrefs.edit();	
		editor.putString("email", username);
		editor.putString("password",password);
		editor.commit();
		
	}

	
	public String login(String username , String password){
		
		
		if(username.equals("")== false && password.equals("") == false){
		
			this.username = username;
			this.password = password;
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
		
		
		//Toast.makeText(activity,result, Toast.LENGTH_LONG).show();
		activity.prog.dismiss();
		
		if(result != null ){

			if(result.equals("No Connection") == false){
				
				try{
					
					JSONObject resultJson = new JSONObject(result);
					
					if(((String)resultJson.get("FaultsMsg")).equals("success") == true){
						
						User us = JsonParser.parseToUser(resultJson.getJSONObject("ResponseValue"));
						System.out.println(us.getName());
						EntityFactory.setUserInstance(us);
						
						ArrayList<Circle> cirs = new ArrayList<Circle>();
						JSONArray circlesJson = resultJson.getJSONArray("circles");
						
						for(int i =0 ; i<circlesJson.length(); i++){
							Circle cir = JsonParser.parseToCircleList(circlesJson.getJSONObject(i));
							System.out.println(cir.getId());
							
							if(cir != null)
								cirs.add(cir);
						}
						
						EntityFactory.setCirclesInstance(cirs);

						ArrayList<Event> evs = new ArrayList<Event>();
						JSONArray eventsJson = resultJson.getJSONArray("events");
						
						for(int i =0 ; i<eventsJson.length(); i++){
							Event ev = JsonParser.parseToEventList(eventsJson.getJSONObject(i));
							
							
							
							if(ev != null)
								evs.add(ev);
						}
						
						EntityFactory.setEvents(evs);
						
						
		
						ArrayList<Notification> notifs = new ArrayList<Notification>();
						JSONArray notifsJson = resultJson.getJSONArray("notifications");
						
						for(int i =0 ; i<notifsJson.length(); i++){
							Notification notifi = JsonParser.parseToNotifications(notifsJson.getJSONObject(i));
							
							
							if(notifi != null)
								notifs.add(notifi);
						}
						
						EntityFactory.setNotificationsInstance(notifs);
		
						
						
						saveShared();
						SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
						 JSONObject resultJsObj = new JSONObject(result);
						 if(resultJsObj.getString("circlesEmptyFlag").equals("true"))
						 {
							 Editor editor = sharedPreferences.edit();
							 editor.putBoolean("circlesEmptyFlag", true);
							 editor.commit();
						 }
						 else
						 {
							 Editor editor = sharedPreferences.edit();
							 editor.putBoolean("circlesEmptyFlag", false);
							 editor.commit();
						 }
						 
						 
					     boolean circlesEmptyFlag =sharedPreferences.getBoolean("circlesEmptyFlag", false);
					     if(circlesEmptyFlag)
					     {
					    	
						
					    	 
							 Intent intent = new Intent(activity.getApplicationContext(),FirstRunActivity.class);
							 activity.startActivity(intent);
							 
							 
					     }
					     else
					     {
					    	 UIManagerHandler.goToHome(activity);
					     }
					    		
						
					}
					else{
//						Toast.makeText(activity,(String)resultJson.get("FaultsMsg"), Toast.LENGTH_LONG).show();
						new ShowDialog().showDialog("Error","Connection error,please connect to the internet", activity); 
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
