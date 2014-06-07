package com.iti.jets.carpoolingV1.editprofileactivity;

import com.iti.jets.carpoolingV1.httphandler.RetrieveUserServiceHandler;

public class RetrieveUserController {

	public EditProfileFragement editProfileActivity;
	
	public RetrieveUserController(EditProfileFragement editProfileFragement, int userId) {
		// TODO Auto-generated constructor stub
		this.editProfileActivity = editProfileFragement;
		System.out.print("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+"   "+userId);
		RetrieveUserServiceHandler handler = new RetrieveUserServiceHandler(this,userId);
		
	}

	public RetrieveUserController(EditProfileFragement editProfileFragement) {
		// TODO Auto-generated constructor stub
	}

	public void getResultFromWebService(String result) {
		// TODO Auto-generated method stub
		
		
		editProfileActivity.getResultFromWebService(result);
		
	}
	
	
}
