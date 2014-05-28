package com.iti.jets.carpoolingV1.pojos;

import java.util.Date;

public class CustomUser  implements java.io.Serializable {


     private Integer id;
     private String username;
     private String userStatue;
     private String image;
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
	public String getUserStatue() {
		return userStatue;
	}
	public void setUserStatue(String userStatue) {
		this.userStatue = userStatue;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public CustomUser(Integer id, String username, String userStatue,
			String image) {
		
		this.id = id;
		this.username = username;
		this.userStatue = userStatue;
		this.image = image;
	}

	public CustomUser(){

  
	}
}


