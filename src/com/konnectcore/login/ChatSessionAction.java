package com.konnectcore.login;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.konnectcore.bean.Chat;
import com.konnectcore.bean.Messages;
import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.ChatSessionImplementation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ChatSessionAction extends ActionSupport implements ServletContextAware{
	
	
	private userInformation userInformation;
	private ServletContext servletContext;
	private Map session;
	private String friendToChat; 
	public List<Messages> msgs;
	private String currentTimestamp;
	String eid ; 
	String chatID ; 

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
		
	}
	
	public userInformation getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(userInformation userInformation) {
		this.userInformation = userInformation;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}
	
	public String getChatID() {
		return chatID;
	}

	public void setChatID(String chatID) {
		this.chatID = chatID;
	}
	
	public String getFriendToChat() {
		return friendToChat;
	}

	public void setFriendToChat(String friendToChat) {
		this.friendToChat = friendToChat;
	}
	
	public List<Messages> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<Messages> msgs) {
		this.msgs = msgs;
	}
	
	public String getCurrentTimestamp() {
		return currentTimestamp;
	}

	public void setCurrentTimestamp(String currentTimestamp) {
		this.currentTimestamp = currentTimestamp;
	}
	
	
	/*This generates a unique ID
	 * which is used as ChatID
	 * and is unique for every sender-receiver pair*/
	
	public String GenerateGUID(){

		 String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		 return uuid;
				
	}
		
	/*This is called before insertion in to chatsession table.
	 * It checks if a chatID already exists for a given sender-receiver.
	 * If it does not exist, it generates a chatID by calling GenerateGUID().
	 * If it exists, it simply returns null */
	public String checkUniqueChatid(SessionFactory sf){
		Session session = sf.openSession();
		
		try{
			
				System.out.println("ensuring chat id is unique");
				Transaction tx = session.beginTransaction();
				String sql = "from Chat where ((SenderID=:SenderID  and ReceiverID=:ReceiverID) or(SenderID=:SenderID1 and ReceiverID=:ReceiverID1)) and Active=:Active";
				Query query = session.createQuery(sql);
				query.setParameter("SenderID", userInformation.getUserID());
				query.setParameter("ReceiverID", this.friendToChat);
				query.setParameter("Active", 1);
				query.setParameter("SenderID1", this.friendToChat);
				query.setParameter("ReceiverID1", userInformation.getUserID());
		        session.getTransaction().commit();
				Chat chat = (Chat) query.uniqueResult();
				String existingChatID = null; 
				
				if( chat == null){
					existingChatID = GenerateGUID();
				}
				
				else{
					this.setEid(chat.getUuid());
					return null; 
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
	
	/*This is to insert all messages into the DB*/
	@Override
	public String execute()
	{
		session = ActionContext.getContext().getSession();
		
		if(session.containsKey("USERBEAN")){
    		
    		userInformation = (userInformation)session.get("USERBEAN");
		}
		
		System.out.println("I am in chat session action");
		System.out.println("Receiver id is " + this.friendToChat);
		System.out.println("First sender id is " + userInformation.getUserID());
		Chat chat = new Chat();
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		System.out.println(sf.toString());
        session = ActionContext.getContext().getSession();
		ChatSessionImplementation insert = new ChatSessionImplementation(sf);
		List<Messages> msgs = insert.getAllMessages(sf , userInformation.getUserID(), this.friendToChat);
		  this.setMsgs(msgs);
		  
		  if ( msgs.size() > 0){
			  int ts = msgs.get(msgs.size()  -1 ).getMessagesId();
			  this.setCurrentTimestamp(Integer.toString(ts));
		  }
		 
       
        if(session.containsKey("USERBEAN")){
        		
        		userInformation = (userInformation)session.get("USERBEAN");
        		String Chatid = checkUniqueChatid(sf);
        		
        		if (Chatid != null){
        			chat.setUuid(Chatid);
            		chat.setSenderID(userInformation.getUserID());
            		chat.setReceiverID(this.friendToChat);
            		chat.setActive(1);
            		insert.insertChatInfo(chat);
                    this.setChatID(Chatid);    
                    return SUCCESS;
                    
        		}
        		
        		else{
        			this.setChatID(this.getEid());
        			System.out.println("Uuid already in DB");
        			return SUCCESS;
        		}
        		}
        return ERROR;
	}

	

}
