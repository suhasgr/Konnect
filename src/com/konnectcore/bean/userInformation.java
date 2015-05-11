package com.konnectcore.bean;

import java.util.Set;

//import com.mysql.jdbc.Blob;


/*import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;*/
public class userInformation 
{
	private String userID;
	private String pass;
	private String[] friends;
	private String[] pending;
	private String[] sentRequests;
	private String[] blockedUsers;
	private String occupation;
	private String gender; 
	private String email;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private byte[] image;
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String[] notification;
	
	public String[] getNotification() {
		return notification;
	}
	public void setNotification(String[] notification) {
		this.notification = notification;
	}

	private String location;
	private String hobbies;
	private String name;
	
	private String lastname;
	
	public String[] getPending() {
		return pending;
	}
	public void setPending(String[] pending) {
		this.pending = pending;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String[] getFriends() {
		return friends;
	}
	public void setFriends(String[] friends) {
		this.friends = friends;
	}
	public void setSentRequests(String[] sentRequests)
	{
		this.sentRequests = sentRequests;
	}
	public String[] getSentRequests()
	{
		return sentRequests;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String[] getBlockedUsers()
	{
		return blockedUsers;
	}
	
	public void setBlockedUsers(String[] blockedUsers)
	{
		this.blockedUsers = blockedUsers;
	}
	
		
	}
