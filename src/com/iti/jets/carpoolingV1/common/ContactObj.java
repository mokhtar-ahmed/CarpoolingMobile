package com.iti.jets.carpoolingV1.common;

public class ContactObj {

String name;
String phoneNo;

public ContactObj (String name,String phoneNo)
{
	this.name = name;
	this.phoneNo = phoneNo;
}

public void setName(String name)
{
	this.name = name;
}

public void setPhoneNo(String phoneNo)
{
	this.phoneNo = phoneNo;
}

public String getName()
{
	return this.name;
}

public String getPhoneNo()
{
	return this.phoneNo;
	
}
}

