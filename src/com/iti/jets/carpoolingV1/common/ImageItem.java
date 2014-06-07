package com.iti.jets.carpoolingV1.common;

import android.graphics.Bitmap;

public class ImageItem {

	int circleRes;
	Bitmap bitmap;
	
	public ImageItem(Bitmap bitmap,int circleRes)
	{
		this.bitmap = bitmap;
		this.circleRes = circleRes;
	}
	
	public void setCircleRes (int circleRes)
	{
		this.circleRes = circleRes;
	}
	public void setBitmap (Bitmap bitmap)
	{
		this.bitmap = bitmap;
	}
	public int getCircleRes ()
	{
		return this.circleRes;
	}
	public Bitmap getBitmap()
	{
		return this.bitmap;
	}
	
}

