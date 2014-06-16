package com.iti.jets.carpoolingV1.pojos;

public class Circle  implements java.io.Serializable {


     private Integer id;
     private User user;
     private String circleName;


    public Circle() {
    }

	
    public Circle(User user, String circleName) {
        this.user = user;
        this.circleName = circleName;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getCircleName() {
        return this.circleName;
    }
    
    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }


	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		Circle c = (Circle) o;
		
		if(c.getId().intValue()== this.id.intValue())
			return true;
		else 
			return false;
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 17+5*this.id.intValue();
	}


}


