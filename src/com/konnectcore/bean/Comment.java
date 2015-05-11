package com.konnectcore.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable{
		private int commentid;
		private String commentText;
		public String getCommentText() {
			return commentText;
		}

		public void setCommentText(String commentText) {
			this.commentText = commentText;
		}

		private String userID;
		private Timestamp commentTime;
		
		
		
		public void setCommentTime(){
			java.util.Date date= new java.util.Date();
			 System.out.println(new Timestamp(date.getTime()));
			 this.commentTime = new Timestamp(date.getTime());
		}
		
		@Override
		public boolean equals(Object v) {
	        boolean retVal = false;
	        if (v == null) return false;
	        if (v == this) return true;
	        if (v instanceof Comment){
	        	Comment ptr = (Comment) v;
	            if(ptr.commentText== this.commentText)
	            	retVal = true;
	        }

	     return retVal;
	  }

		public String getUserID() {
			return userID;
		}

		public void setUserID(String userID) {
			this.userID = userID;
		}

		public int getCommentid() {
			return commentid;
		}

		public void setCommentid(int commentid) {
			this.commentid = commentid;
		}

		public Timestamp getCommentTime() {
			return commentTime;
		}

		public void setCommentTime(Timestamp commentTime) {
			this.commentTime = commentTime;
		}
		
}
