package com.iti.jets.carpoolingV1.pojos;


import java.util.Date;

public class User  implements java.io.Serializable {


  private int id;
  private String name;
  private String username;
  private String password;
  private String gender;
  private Date dateOfBirth;
  private String pushNotificationId;
  private String rank;
  private String userImage;
  private String email;
  private String facebookKey;
  private String phone;

 public User() {
 }

	
 public User(String name, String username, String password, String phone) {
     this.name = name;
     this.username = username;
     this.password = password;
     this.phone = phone;
 }
 public User(String name, String username, String password, String gender, Date dateOfBirth, String pushNotificationId, String rank, String userImage, String email, String facebookKey, String phone) {
    this.name = name;
    this.username = username;
    this.password = password;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
    this.pushNotificationId = pushNotificationId;
    this.rank = rank;
    this.userImage = userImage;
    this.email = email;
    this.facebookKey = facebookKey;
    this.phone = phone;
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
 public String getUsername() {
     return this.username;
 }
 
 public void setUsername(String username) {
     this.username = username;
 }
 public String getPassword() {
     return this.password;
 }
 
 public void setPassword(String password) {
     this.password = password;
 }
 public String getGender() {
     return this.gender;
 }
 
 public void setGender(String gender) {
     this.gender = gender;
 }
 public Date getDateOfBirth() {
     return this.dateOfBirth;
 }
 
 public void setDateOfBirth(Date dateOfBirth) {
     this.dateOfBirth = dateOfBirth;
 }
 public String getPushNotificationId() {
     return this.pushNotificationId;
 }
 
 public void setPushNotificationId(String pushNotificationId) {
     this.pushNotificationId = pushNotificationId;
 }
 public String getRank() {
     return this.rank;
 }
 
 public void setRank(String rank) {
     this.rank = rank;
 }
 public String getUserImage() {
     return this.userImage;
 }
 
 public void setUserImage(String userImage) {
     this.userImage = userImage;
 }
 public String getEmail() {
     return this.email;
 }
 
 public void setEmail(String email) {
     this.email = email;
 }
 public String getFacebookKey() {
     return this.facebookKey;
 }
 
 public void setFacebookKey(String facebookKey) {
     this.facebookKey = facebookKey;
 }
 public String getPhone() {
     return this.phone;
 }
 
 public void setPhone(String phone) {
     this.phone = phone;
 }
 
}


