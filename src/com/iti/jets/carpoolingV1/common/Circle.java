package com.iti.jets.carpoolingV1.common;

public class Circle {

	private String circleName,circleImage;
	private int circleId;
	public Circle()
	{
		
	}
	
	public void setCircleName(String circleName)
	{
		this.circleName = circleName;
	}
	
	public void setCircleImage(String circleImage)
	{
		this.circleImage =  circleImage;
				
	}
	
	public void setCircleId(int circleId)
	{
		this.circleId = circleId;
	}
	
	public String getCircleName()
	{
		return this.circleName;
	}
	
	public String getCircleImage()
	{
		return this.circleImage;
		
	}
	public int getCircleId()
	{
		return this.circleId;
	}
	
	
	
}


