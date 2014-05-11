package com.iti.jets.carpoolingV1.pojos;


import java.util.Date;

public class Event implements java.io.Serializable {


  private Integer id;
  private String name;
  private Date date;

 public Event() {
 }

 public void setDate(Date date){
	 this.date = date;
 }
 public Date getDate(){
	 return date;
 }
 
 public Integer getId() {
     return this.id;
 }
 
 public void setId(Integer id) {
     this.id = id;
 }
 public String getName() {
     return this.name;
 }
 
 public void setName(String name) {
     this.name = name;
 }
 
}


