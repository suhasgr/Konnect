package com.konnectcore.login;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.konnectcore.chat.*;
import com.konnectcore.bean.Chat;
import com.konnectcore.bean.Messages;
import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.ChatSessionImplementation;
import com.konnectcore.dao.MessageInformationImplementation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MessageAction extends ActionSupport implements ServletContextAware{
	
	private userInformation userInformation;
	private ServletContext servletContext;
	private Map session;
	private String chatID;
	private String sender;
	private String textchat;
	java.util.Date dateone= new java.util.Date();
	Timestamp date = new Timestamp(dateone.getTime());
	private String currentTimestamp;
	private int messagesId;
	
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	 
	public String getMsgtime() {
		return currentTimestamp;
	}
	public void setMsgtime(String currentTimestamp) {
		this.currentTimestamp = currentTimestamp;
	}
	
	public int getMessagesId() {
		return messagesId;
	}
	public void setMessagesId(int messagesId) {
		this.messagesId = messagesId;
	}
	public String getTextchat() {
		return textchat;
	}
	public void setTextchat(String textchat) {
		this.textchat = textchat;
	}
	public Timestamp getDate() {
		return (Timestamp) date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getChatID() {
		return chatID;
	}
	public void setChatID(String chatID) {
		this.chatID = chatID;
	}
	public userInformation getUserInformation() {
		return userInformation;
	}
	public void setUserInformation(userInformation userInformation) {
		this.userInformation = userInformation;
	}
	public ServletContext getServletContext() {
		return servletContext;
	}
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}
	
	
	/*This is to get chatID before insertion of messages into DB*/
	public String getChatID(SessionFactory sf,String SenderID, String ReceiverID){
		

		Session session = sf.openSession();
		
			try{
				
					System.out.println("fetching chat id");
					Transaction tx = session.beginTransaction();
					String sql = "from Chat where ((SenderID=:SenderID) or (SenderID=:SenderID1)) and Active=:Active";
					Query query = session.createQuery(sql);
					query.setParameter("SenderID", SenderID);
					query.setParameter("SenderID1", ReceiverID);
					query.setParameter("Active", 1);
			        session.getTransaction().commit();
					System.out.println("printing chat id from chat sess impl");
					Chat chat = (Chat) query.uniqueResult();
					String existingChatID = null; 
					
					if( chat != null){
						existingChatID = chat.getUuid(); 
					}
					
			        System.out.println(existingChatID);
			        return existingChatID;
		       }
				
				catch (HibernateException e)
				{
					e.printStackTrace();
					session.getTransaction().rollback();
				}
			
			return null;
			
	}

	/*This is to insert messages into DB*/
	@Override
	public String execute()
	{
		System.out.println("In Message session action");
		Messages messages = new Messages();
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
        session = ActionContext.getContext().getSession();
        System.out.println(session.toString());
		MessageInformationImplementation insert = new MessageInformationImplementation(sf);
		this.setUserInformation((userInformation)session.get("USERBEAN"));
		
        if(session.containsKey("USERBEAN")){
        	
        		if ( sf == null){
        			System.out.println("SF Null.");
        		}
        		
        		System.out.println("I am putting messages in DB");
        		System.out.println("User info" + userInformation.toString());
     			System.out.println("done this newly sender is" +userInformation.getUserID());
        		String send = userInformation.getUserID();
        		System.out.println("why are yo not printing");
        		messages.setChatID(chatID);
    			messages.setMessages(textchat);
    			messages.setMsgTime(date);
    			messages.setSender(send);
        		System.out.println("In Message action");
                insert.insertMessageInfo(messages);
                return SUCCESS;	
                
    	}
        
        return ERROR;
	}
	
	
/*	Not used currently
 * public String loadMessage(SessionFactory sf,String receiverId){
		
		Session session = sf.openSession();
		try{
			String chatid = getChatID(sf, userInformation.getUserID(), receiverId);
    		Transaction tx = session.beginTransaction();
			//String sql = "Select Messages from Message where ChatID=:chatid and MsgTime:=msgtime";
			List<Messages> msgs = (List<Messages>) session.createQuery(
				    "from Message where ChatID=:chatid and MsgTime:>msgtime")
				    .setParameter("chatid", chatid)
				    .setParameter("msgtime", currentTimestamp)
				    .list();
			session.getTransaction().commit();
			StringBuilder sb = new StringBuilder();
				for ( Messages temp : msgs ) {
				    sb.append(temp.getMessages());
				    sb.append("#");
				}
				System.out.println(sb.toString());
			//Query query = session.createQuery("from ChatSession where SenderID=:SenderID and ReceiverID=:ReceiverID and Active=:Active");
	        Query query = session.createQuery(sql);
			//query.setParameter("SenderID", SenderID);
			query.setParameter("chatid", chatid);
			query.setParameter("msgtime", date);
			System.out.println("printing chat id from chat sess impl");
			Chat chat = (Chat) query.uniqueResult();
			String existingChatID = null; 
			if( chat != null){
				existingChatID = chat.getUuid(); 
			}
	        System.out.println(existingChatID);
	        //List results = query.list();
	        Messages msg = (Messages) query.list();
	        msg.getMessages();
	      }
			
			catch (HibernateException e)
			{
				e.printStackTrace();
				session.getTransaction().rollback();
			}
		
		return null;
	}*/

}
