package com.iti.jets.carpoolingV1.addevent;

import java.util.ArrayList;



public class EventShared {

	private static  ArrayList<Integer>  circles ;
	private static  ArrayList<Integer>  locations  ;
	
	public static void setSelectedCircles( ArrayList<Integer>  cir ){
		circles = cir;
	}
	public static void setSelectedLocations( ArrayList<Integer>  loc ){
		locations = loc;
	}
	public static  ArrayList<Integer> getSelectedCircles( ){
		return circles ;
	}
	public static  ArrayList<Integer> getSelectedLocations(  ){
		return locations ;
	}
}
