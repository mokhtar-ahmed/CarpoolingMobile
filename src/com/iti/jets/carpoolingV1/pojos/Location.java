package com.iti.jets.carpoolingV1.pojos;

public class Location  implements java.io.Serializable {


     private Integer id;
     private String longitude;
     private String lattitude;
     private String altitude;
     private String address;
  
    public Location() {
    }

	
    public Location(String longitude, String lattitude, String altitude, String address) {
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.altitude = altitude;
        this.address = address;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLattitude() {
        return this.lattitude;
    }
    
    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }
    public String getAltitude() {
        return this.altitude;
    }
    
    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }


	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof Location){
			if(((Location) o).getId().intValue() == this.id.intValue())
				return true;
			else 
				return false;
			
		}else return false;
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 17*(17+id);
	}



}


