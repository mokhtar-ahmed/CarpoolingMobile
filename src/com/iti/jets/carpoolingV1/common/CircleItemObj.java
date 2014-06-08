package com.iti.jets.carpoolingV1.common;

import android.graphics.Bitmap;

public class CircleItemObj {

	String name;
	Bitmap bitmap;
	
	public void setName(String name)
	{
		this.name = name;
	}
	public void setBitmap(Bitmap bitmap)
	{
		this.bitmap = bitmap;
	}
	
	public Bitmap getBitmap()
	{
		return this.bitmap;
	}
	public String getName()
	{
		return this.name;
	}
}


