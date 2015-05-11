package com.konnectcore.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.apache.commons.validator.UrlValidator;



public class Post implements Serializable{
	
	private String userID;
	private Integer postID;
	

	private String postText;
	private Timestamp postTime;
	private Integer likes =0;
	//private ArrayList<String> blocked = new ArrayList<String>();
	private List<Comment> comments;
	private byte[] image;
	private String imageEndode;
	private String firstname;
	private String lastname;
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	//private String[] likedusers;
	/*private ArrayList<String> tags = new ArrayList<String>();
	private ArrayList<String> privacy = new ArrayList<String>();
	
	
	
	*/
	/*public ArrayList<String> getBlocked() {
		return blocked;
	}

	public void setBlocked(ArrayList<String> blocked) {
		this.blocked = blocked;
	}
*/
	/*public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}*/

	/*public ArrayList<String> getLikes() {
		return likes;
	}

	public void setLikes(ArrayList<String> likes) {
		this.likes = likes;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
*/
	/*public String getPostID() {
		return postID;
	}

	public void setPostID(String postID) {
		this.postID = postID;
	}*/

	public Timestamp getPostTime() {
		return postTime;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setPostTime(Timestamp timestamp) {
		this.postTime = timestamp;
	}

	/*public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public ArrayList<String> getPrivacy() {
		return privacy;
	}
*/
	public String getUserID() {
		return userID;
	}

	public String getPostText() {
		return postText;
	}

	public Integer incrementLikes(){
		++likes;
		return this.likes;
	}
	
	
	
	// image 
	public void setPostTime(){
		java.util.Date date= new java.util.Date();
		 System.out.println(new Timestamp(date.getTime()));
		 this.postTime = new Timestamp(date.getTime());
	}
	
	/*public void setPrivacy(ArrayList<String> users){
			
				for(String str: users){
					if(this.privacy.size() <=10){
						if(!privacy.contains(str))
							this.privacy.add(str);
					}
					else break;
				}
	}
	
	public void setBlocking(ArrayList<String> users){
		
		for(String str: users){
			if(this.blocked.size() <=10){
				if(!blocked.contains(str))
					this.blocked.add(str);
			}
			else break;
		}
}
	public void tagFriends(ArrayList<String> users){
		
		for(String str: users){
			if(this.tags.size() <=10){
				if(!tags.contains(str))
					this.tags.add(str);
			}
			else break;
		}
}
	
	
	public void addLike(String userID){
		if(!likes.contains(userID))
			this.likes.add(userID);
	}
	void setBlocked(ArrayList<String> users){
		this.blocked.addAll(users);
	}
	
	public int getNumberOfLikes(){
		return this.likes.size();
	}
*/	
	
	
	public void setUserID(String string){
		this.userID = string;
	}
	
	/*public void setPostID(){
		this.postID = UUID.randomUUID().toString();
	}*/
	
	public void setPostText(String str){
		if(str != null){
			if(str.length() < 250){
				//if(validateTextForAlphaNumericHTTP(str)){
					this.postText = str;
				//}
			}
		}
	}
	
	
	
	/*public boolean addCommentToPost(String comment){
		if(comments.size() < 100){
		if(validateTextForAlphaNumericHTTP(comment)){
					Comment tempComment = new Comment();
					tempComment.setComment(comment);
					tempComment.setCommentTime();
					if(this.comments.contains(tempComment))
						return this.comments.add(tempComment);
			}
			return false;
		}
		return false;
	}*/
	
	
	
	public boolean validateTextForAlphaNumericHTTP(String str){
		if(str.length() <= 100){
			//if(isAlphaNumeric(str)) return true;
			if(validateURL(str)) return true;
		}
		return false;
	}
	
	public boolean isAlphaNumeric(String s){
	    String pattern= "([ ]*+[0-9A-Za-z]++[ ]*+)+";
	        if(s.matches(pattern)){
	            return true;
	        }
	        return false;   
	}
	
	public boolean validateURL(String str){
		UrlValidator urlValidator = new UrlValidator(); // import apache commons url validator jar
		return urlValidator.isValid(str);
	}
	
	
	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}


	public Integer getPostID() {
		return postID;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public String getByteArrayString() throws UnsupportedEncodingException{
		//return new String(this.image,  "UTF-8");
		return this.imageEndode;
	}

	public String getImageEndode() {
		return new String(new org.apache.commons.codec.binary.Base64().encode(getImage()));
	}

	public void setImageEndode(String imageEndode) {
		this.imageEndode = imageEndode;
	}

	/*public String[] getLikedusers() {
		return likedusers;
	}

	public void setLikedusers(String[] likedusers) {
		
		this.likedusers = likedusers;
	}*/

	

}
