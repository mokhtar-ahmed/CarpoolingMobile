package com.iti.jets.carpoolingV1.pojos;


import java.util.Date;

public class Event implements java.io.Serializable {


  private Integer id;
  private String name;
  private Date date;
  private String userStatue;

 public Event() {
 }

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public String getUserStatue() {
	return userStatue;
}

public void setUserStatue(String userStatue) {
	this.userStatue = userStatue;
}


 
 
}


