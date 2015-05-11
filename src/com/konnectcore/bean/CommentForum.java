package com.konnectcore.bean;

import java.sql.Timestamp;

public class CommentForum
{
	
	private String commentID;
	private String forumID;
	private String description;
	private String userID;
	private Timestamp time;
	private int blockCount;
	
	public String getCommentID() {
		return commentID;
	}
	public void setCommentID(String commentID) {
		this.commentID = commentID;
	}
	public String getForumID() {
		return forumID;
	}
	public void setForumID(String forumID) {
		this.forumID = forumID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {

		this.time = time;
	}
	public int getBlockCount() {
		return blockCount;
	}
	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}
	
	
	
}
