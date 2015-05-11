package com.konnectcore.chat;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;

import java.sql.Timestamp;

import java.util.List;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.konnectcore.bean.Chat;
import com.konnectcore.bean.Messages;
import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.ChatSessionImplementation;
import com.konnectcore.dao.MessageInformationImplementation;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server extends ActionSupport implements ServletContextAware{
	
 
	String Message;
	public InputStream inputStream;
	private String textchat; 
	//private ServletContext servletcontext;
	String chatID ;
	java.util.Date dateone= new java.util.Date();
	Timestamp date = new Timestamp(dateone.getTime());
	private String currentTimestamp;
	String friendToChat;
	String outMsg ; 
	private ServletContext servletContext;
	Map session;
	private userInformation userInformation;
	private String userID;
	private String pass;
	private String friends;
	
	public String getChatID() {
		return chatID;
	}
	public void setChatID(String chatID) {
		this.chatID = chatID;
	}
	public String getCurrentTimestamp() {
		return currentTimestamp;
	}
	public void setCurrentTimestamp(String currentTimestamp) {
		this.currentTimestamp = currentTimestamp;
	}
	 
	
	
	public String getFriendToChat() {
		return friendToChat;
	}
	public void setFriendToChat(String friendToChat) {
		this.friendToChat = friendToChat;
	}
	
	public String getOutMsg() {
		return outMsg;
	}
	public void setOutMsg(String outMsg) {
		this.outMsg = outMsg;
	}
	
	public String getTextchat(){
		return textchat ; 
	}
	public void setTextchat(String textchat){
		this.textchat = textchat; 
	}
	public String getMessage(){
		System.out.println(userInformation.getUserID()+":");
		return Message;
		
	}
	
	public void setMessage(String Message){
		this.Message = Message;
	}
	
	public userInformation getUserInformation() {
		return userInformation;
	}



	public void setUserInformation(userInformation userInformation) {
		this.userInformation = userInformation;
	}
	
	public void getSession(Session session) 
	{
		this.session =  (Map) session;
	}
	
	public void setSession(Session session) 
	{
		this.session =  (Map) session;
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
	
	public ServletContext getServletContext() {
		return servletContext;
	}

	public Session getSession() {
		return (Session) session;
	}
	
	
	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		this.servletContext = arg0;
		
	}
	
	/*To fetch messages during an on-going chat session between two users.
	 * It takes the sender id and receiver id of a particular message
	 * and also stores in Database
	*/
	public String FetchMessages() throws Exception{
		
		Map attriutes = ActionContext.getContext().getSession();
		System.out.println(attriutes.toString());
		System.out.println("Hello");
        session = ActionContext.getContext().getSession();
        
		   if(session.containsKey("USERBEAN")){
			   userInformation = (userInformation)session.get("USERBEAN");
		   }
		   
		System.out.println("I am in chat session action");
		System.out.println("Receiver id is " + this.friendToChat);
		String userid = userInformation.getUserID();
		Chat chat = new Chat();
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		System.out.println(sf.toString());
        session = ActionContext.getContext().getSession();
		ChatSessionImplementation insert = new ChatSessionImplementation(sf);
		List<Messages> msgs = insert.getAllMessagesAfterTimestamp(sf , userid, this.friendToChat, this.currentTimestamp);
        StringBuilder s = new StringBuilder();
        s.append("[");
        
        if ( msgs == null){
			s.append("{}");
		}
        
		for ( int i  = 0 ; i < msgs.size(); i++){
        	s.append("{\"msg\":\""); 
        	s.append(StringEscapeUtils.escapeJavaScript(msgs.get(i).getMessages()));
        	s.append("\",\"ts\":\"");
        	s.append(msgs.get(msgs.size()-1).getMessagesId());
        	s.append("\",\"senderid\":\"");
        	s.append(msgs.get(i).getSender());
        	s.append("\",\"rid\":\"");
        	s.append(userid);
        	s.append("\"}");
        	
        	if ( i != msgs.size() -1 ){
        		s.append(",");
        	}
        	
        	else{
        		  this.setCurrentTimestamp(msgs.get(i).getMsgTime().toString());		
        	}
        	
        }
		
		s.append("]");
		inputStream = IOUtils.toInputStream(s.toString());
		return SUCCESS; 
	}
	
	
	/*This is to send the message to the other user.
	 * It takes message, timestamp, senderID as input
	 * and stores the message in DB*/
	public String SendMessage() {
		System.out.println("In Message session action");
		Messages messages = new Messages();
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
        session = ActionContext.getContext().getSession();
		MessageInformationImplementation insert = new MessageInformationImplementation(sf);
		
        if(session.containsKey("USERBEAN")){
        	
        		if ( sf == null){
        			System.out.println("SF Null.");
        		}
        		
        		System.out.println("I am putting messages in DB");
        		userInformation = (userInformation)session.get("USERBEAN");
        		messages.setChatID(chatID);
    			messages.setMessages(textchat);
    			messages.setMsgTime(date);
    			messages.setSender(userInformation.getUserID());
        		System.out.println("In Message action");
                insert.insertMessageInfo(messages);
        		inputStream = IOUtils.toInputStream("");
                return SUCCESS;	
                
    	}
        
		inputStream = IOUtils.toInputStream( "");
		return SUCCESS;
	}
	
	
	

/*
	This method gets the userInformation for use of other methods
	in this class*/
	@Override
	 public String execute(){
		 
		System.out.println("Inside execute of server");
        session = ActionContext.getContext().getSession();
        try{
	        if(session.containsKey("USERBEAN")){
	        		
	        		userInformation = (userInformation)session.get("USERBEAN");
	        		System.out.println(userInformation.getUserID());
	                return SUCCESS;
	        	}
        }
        catch(Exception e){
        	e.printStackTrace();
        	return ERROR;
        }
		
        return SUCCESS;	 
	 }
}
