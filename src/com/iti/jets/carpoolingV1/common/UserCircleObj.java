package com.iti.jets.carpoolingV1.common;



public class UserCircleObj {
	
	private int userId;
	private int circleId;
	
	public UserCircleObj()
	{
		
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
				
	}
	public void setCircleId(int circleId)
	{
		this.circleId = circleId;
				
	}
	public int getUserId()
	{
		return this.userId;
	}
	
	public int getCircleId()
	{
		return this.circleId;
	}
	
}
