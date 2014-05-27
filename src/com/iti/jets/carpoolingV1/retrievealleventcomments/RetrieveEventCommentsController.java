package com.iti.jets.carpoolingV1.retrievealleventcomments;

import com.iti.jets.carpoolingV1.addcomment.AddCommentController;

public class RetrieveEventCommentsController {

	
	AddCommentController addCommentController;
	RetrieveEventCommentsServiceHandler serviceHandler;
	public void setArguments(AddCommentController addCommentController,
			int eventId) {
		// TODO Auto-generated method stub
		this.addCommentController = addCommentController;
		serviceHandler = new RetrieveEventCommentsServiceHandler();
		serviceHandler.connectToWebservice(addCommentController,eventId);
		
	}

}
