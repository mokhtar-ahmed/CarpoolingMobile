package com.iti.jets.carpoolingV1.uimanager;

import android.content.Context;
import android.content.Intent;

import com.iti.jets.carpoolingV1.addevent.AddEventActivity;
import com.iti.jets.carpoolingV1.loginactivity.LoginActivity;

import com.iti.jets.carpoolingV1.registrationactivity.RegisterActivity;
import com.iti.jets.carpoolingV1.sharedlayout.MainActivity;

public class UIManagerHandler {

	public static void goToHome(Context context){
		
		context.startActivity(new Intent(context, MainActivity.class));
	}
	public static void goToLogin(Context context){
		
		context.startActivity(new Intent(context , LoginActivity.class));
	}
	public static void goToRegister(Context context){
		context.startActivity(new Intent(context , RegisterActivity.class));
	}
	public static void goToAddEvent(Context context){
		context.startActivity(new Intent(context , AddEventActivity.class));
	}

}

