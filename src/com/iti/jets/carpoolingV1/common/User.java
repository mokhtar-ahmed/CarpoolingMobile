package com.iti.jets.carpoolingV1.common;

// This object holds the user's information e.g.phone,name 

public class User {

	private String name,phone,imageURL,email,gender,date,password;
	private int userId;
	private boolean isSelected;
	public void setPassword(String password) {
		
		this.password= password;
	}
	public void setDate(String date)
	{
		this.date= date;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public void setGender(String gender)
	{
		this.gender= gender;
	}
	public void setPhone(String phone)
	{
		this.phone = phone; 
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	public void setImageURL(String imageURL)
	{
		this.imageURL = imageURL;
	}
	public void setIsSelected(boolean isSelected)
	{
		this.isSelected = isSelected;
	}
	public boolean getIsSelected()
	{
		return this.isSelected;
	}
	public String getName()
	{
		return this.name;
	}
	public String getPhone()
	{
		return this.phone;
	}
	public int getUserId()
	{
		return this.userId;
	}
	public String getImageURL()
	{
		return this.imageURL;
	}
	public String getEmail()
	{
		return this.email;
	}
	public String getGender()
	{
		return this.gender;
	}
	public String getDate()
	{
		return this.date;
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
}
