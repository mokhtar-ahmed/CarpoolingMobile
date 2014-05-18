package com.iti.jets.carpoolingV1.common;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.util.Base64;

public class ImageHandler {

	public ImageHandler()
	{
		
	}
	
	  public String BitMapToString(Bitmap bitmap) {
	    ByteArrayOutputStream baos=new  ByteArrayOutputStream();
	    bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
	    byte [] arr=baos.toByteArray();
	    String result=Base64.encodeToString(arr, Base64.DEFAULT);
	    return result;
	}
}
