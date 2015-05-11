package com.konnectcore.bean;

import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.UUID;

public class Forum implements java.io.Serializable {

	private String forumid;
	private String forumname;
	private String category;
	private String userid;
	private String description;
	private Timestamp time;
	private int commentcount;
		
	public String getForumid() {
		return forumid;
	}
	public void setForumid(String forumid) {
		this.forumid = forumid;
	}
	public String getForumname() {
		return forumname;
	}
	public void setForumname(String forumname) {
		this.forumname = forumname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getCommentcount() {
		return commentcount;
	}
	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
	
}
