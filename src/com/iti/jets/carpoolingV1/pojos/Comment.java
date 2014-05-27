package com.iti.jets.carpoolingV1.pojos;

public class Comment  implements java.io.Serializable{
	
	String commentText;
	String commentOwnerName;
	int id;
	
	public void setCommentText(String commentText)
	{
		this.commentText = commentText;
	}

	public void setCommentOwnerName(String commentOwnerName)
	{
		this.commentOwnerName = commentOwnerName;
	}
	public void setCommentId(int id)
	{
		this.id = id;
	}
	public String getCommentText()
	{
		return this.commentText;
	}
	
	public String getCommentOwnerName()
	{
		return this.commentOwnerName;
	}
	
	public int getCommentId()
	{
		return this.id;
	}
}
