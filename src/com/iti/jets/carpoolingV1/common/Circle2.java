package com.iti.jets.carpoolingV1.common;

public class Circle2 {

	private String circleName;
	private int circleId;
	private String circleImage;
	private int resId;
	public Circle2()
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
	public int getResId()
	{
		return this.resId;
	}
	
	public void setResId(int resId)
	{
		this.resId = resId;
	}
	
}


