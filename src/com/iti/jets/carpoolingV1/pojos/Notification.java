package com.iti.jets.carpoolingV1.pojos;

import java.util.Date;

public class Notification  implements java.io.Serializable  , Comparable<Notification>{


    private int id;
    private int user;
    private Event event;
    private Date notificationDate;
    private String eventType;
    private String eventState;
    private String message;


	public Notification(){
		
	}
	public Notification(int id, int user, Event event, Date notificationDate,
			String eventType, String eventState, String message) {
	
		this.id = id;
		this.user = user;
		this.event = event;
		this.notificationDate = notificationDate;
		this.eventType = eventType;
		this.eventState = eventState;
		this.message = message;

	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Date getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventState() {
		return eventState;
	}
	public void setEventState(String eventState) {
		this.eventState = eventState;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public int compareTo(Notification another) {
		
		if(this.notificationDate.compareTo(another.getNotificationDate()) <= 0)
			return 1;
		else
			return -1;
	}

    
    
    
    
}
