package com.iti.jets.carpoolingV1.deletecomment;

import com.iti.jets.carpoolingV1.addcomment.AddCommentFragment;
import com.iti.jets.carpoolingV1.pojos.Comment;

public class DeleteCommentController {

	public void setArguments(AddCommentFragment fragment, Comment commentValues) {
		// TODO Auto-generated method stub
		DeleteCommentServiceHandler serviceHandler = new DeleteCommentServiceHandler();
		serviceHandler.connectToWebservice(commentValues,fragment);
		
	}

}
