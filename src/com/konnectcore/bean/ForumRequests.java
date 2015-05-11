package com.konnectcore.bean;

import java.sql.Timestamp;

public class ForumRequests implements java.io.Serializable  {
	
	private String requestID;
	private String forumID;
	private String ownerUserID;
	private String requestUserID;
	private boolean approveFlag;
	private boolean pendingFlag;
	private boolean memberFlag;
	private boolean ownerFlag;
	
	public String getRequestID() {
		return requestID;
	}
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	public String getForumID() {
		return forumID;
	}
	public void setForumID(String forumID) {
		this.forumID = forumID;
	}
	public String getOwnerUserID() {
		return ownerUserID;
	}
	public void setOwnerUserID(String ownerUserID) {
		this.ownerUserID = ownerUserID;
	}
	public boolean isApproveFlag() {
		return approveFlag;
	}
	public void setApproveFlag(boolean approveFlag) {
		this.approveFlag = approveFlag;
	}
	public boolean isPendingFlag() {
		return pendingFlag;
	}
	public void setPendingFlag(boolean pendingFlag) {
		this.pendingFlag = pendingFlag;
	}
	public boolean isMemberFlag() {
		return memberFlag;
	}
	public void setMemberFlag(boolean memberFlag) {
		this.memberFlag = memberFlag;
	}
	public boolean isOwnerFlag() {
		return ownerFlag;
	}
	public void setOwnerFlag(boolean ownerFlag) {
		this.ownerFlag = ownerFlag;
	}
	public String getRequestUserID() {
		return requestUserID;
	}
	public void setRequestUserID(String requestUserID) {
		this.requestUserID = requestUserID;
	}
	

}
