package com.konnectcore.bean;


import java.sql.Timestamp;

public class Messages {
	
	private int messagesId;
	private String chatID;
	private String messages;
	private Timestamp msgTime;
	private String sender;
	
	
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public int getMessagesId() {
		return messagesId;
	}
	public void setMessagesId(int messagesId) {
		this.messagesId = messagesId;
	}
	public String getChatID() {
		return chatID;
	}
	public void setChatID(String chatID) {
		this.chatID = chatID;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	public Timestamp getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(Timestamp msgTime) {
		this.msgTime = msgTime;
	}
	
	
	
	
	

}
