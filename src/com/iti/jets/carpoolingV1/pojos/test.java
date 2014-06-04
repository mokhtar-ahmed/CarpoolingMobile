package com.iti.jets.carpoolingV1.pojos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ArrayList<Notification> nslist = new ArrayList<Notification>();
		
		Notification n1 = new Notification(4, 1, new Event(), new Date(1992,12,1), "type1", "state1", "msg1");
		Notification n2 = new Notification(3, 1, new Event(), new Date(1993,12,1), "type1", "state1", "msg1");
		Notification n3 = new Notification(2, 1, new Event(), new Date(1992,11,1), "type1", "state1", "msg1");
		Notification n4 = new Notification(1, 1, new Event(), new Date(1992,2,1), "type1", "state1", "msg1");
		Notification n5 = new Notification(5, 1, new Event(), new Date(1995,12,1), "type1", "state1", "msg1");
		
		nslist.add(n3);nslist.add(n5);nslist.add(n2);nslist.add(n1);nslist.add(n4);
		
		for (Notification n : nslist) {
			
			System.out.println(n.getId()+" =  id  ");
		}
		
		Collections.sort(nslist);
		
		for (Notification n : nslist) {
			
			System.out.println(n.getId()+" =  id  ");
		}
		
	}

}
