package com.iti.jets.carpoolingV1.pojos;

import java.util.Date;

public class Comment  implements java.io.Serializable , Comparable<Comment> {


     private Integer id;
     private String username;
     private String text;
     private String image;
     private Date date;
     
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Comment(Integer id, String username, String text, String image,Date date) {
		
		this.id = id;
		this.username = username;
		this.text = text;
		this.image = image;
		this.date = date;
	}
	public Comment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Comment another) {
		
		if(this.date.compareTo(another.getDate()) <= 0)
			return 1;
		else
			return -1;
	}


  

}


