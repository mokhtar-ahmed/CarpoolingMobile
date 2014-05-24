package com.iti.jets.carpoolingV1.editprofileactivity;

import com.iti.jets.carpoolingV1.httphandler.RetrieveUserServiceHandler;

public class RetrieveUserController {

	EditProfileActivity editProfileActivity;
	
	public RetrieveUserController(EditProfileActivity editProfileActivity, int userId) {
		// TODO Auto-generated constructor stub
		this.editProfileActivity = editProfileActivity;
		System.out.print("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+"   "+userId);
		RetrieveUserServiceHandler handler = new RetrieveUserServiceHandler(this,userId);
		
	}

	public void getResultFromWebService(String result) {
		// TODO Auto-generated method stub
		
		
		editProfileActivity.getResultFromWebService(result);
		
	}
	
	
}
