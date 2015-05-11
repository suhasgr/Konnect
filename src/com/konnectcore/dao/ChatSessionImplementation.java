package com.konnectcore.dao;


import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.konnectcore.bean.Chat;
import com.konnectcore.bean.Messages;
import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.ChatSession.ChatSessionDAO;

public class ChatSessionImplementation implements ChatSessionDAO{
	
	private SessionFactory sf;
	private String result;
	private userInformation userInformation;
	
    public userInformation getUserInformation() {
		return userInformation;
	}
    
    public void setUserInformation(userInformation userInformation) {
		this.userInformation = userInformation;
	}

	public ChatSessionImplementation(SessionFactory sf){
        this.sf = sf;
    }
   
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	
	
	@Override
	public void insertChatInfo(Chat chat) {
		
		Session session = sf.openSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(chat);
			tx.commit();
			}
		
		catch (HibernateException e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	
	/*This method verifies if a chatID is present for a pair of users
	 * if it exists, then it retrieves that ChatID*/
	public String getChatID(SessionFactory sf, String SenderID, String ReceiverID){
		
		Session session = sf.openSession();
		
			try{
				
					Transaction tx = session.beginTransaction();
					String sql = "Select ChatID from ChatSession where SenderID=:SenderID";
					Query query = session.createQuery(sql);
					query.setParameter("SenderID", SenderID);
					List l = query.list();
					session.getTransaction().commit();
					System.out.println("printing chat id from chat sess impl");
					System.out.println(l.get(0).toString());
			        return l.get(0).toString();
			     
		      }
				
				catch (HibernateException e)
				{
					
					e.printStackTrace();
					session.getTransaction().rollback();
					
				}
	
			return null;	
	}
	
	
	/*This method is to get all messages from the DB based on sender-receiver id
	 * and unique chat id. This method is called when the user opens a chat session 
	 * for the first time */
	public List<Messages> getAllMessages(SessionFactory sf, String sender , String receiver){
		
		Session session = sf.openSession();
		
		try{
			
				Transaction tx = session.beginTransaction();
				String sql = "select MessageID, ChatID,Messages, MsgTime, Sender from Messages M where M.ChatID IN "
						+ "( select Uuid from ChatSession c where ((c.SenderID =:SenderID and c.ReceiverID=:ReceiverID) or"
						+ " (c.SenderID =:SenderID1 and ReceiverID=:ReceiverID1)))";
				SQLQuery query = session.createSQLQuery(sql);
				System.out.println("Setting class"); 
				query.addEntity(Messages.class);
				query.setParameter("SenderID", sender);
				query.setParameter("ReceiverID", receiver);
				query.setParameter("SenderID1", receiver);
				query.setParameter("ReceiverID1", sender);
				
				System.out.println("List size "+ query.list().size());
				
				List<Messages> msgs = (List<Messages>) query.list();
			
				session.getTransaction().commit();
				return msgs;
			}
		
		catch (HibernateException e)
			{
				e.printStackTrace();
				session.getTransaction().rollback();
			}
		
		return null;
	}
	

	
	/*This method is called when an chat session is already open and is in progress.
	 * It gets the messages based on ChatID */
	public List<Messages> getAllMessagesAfterTimestamp(SessionFactory sf, String sender , String receiver, String timestamp) throws Exception{
			Session session = sf.openSession();
			
			try{
				
					Transaction tx = session.beginTransaction();
					String sql = "select MessageID, ChatID,Messages, MsgTime, Sender from Messages M where MessageID >:msgid and M.ChatID = "
							+ "( select Uuid from ChatSession where ((SenderID =:SenderID and ReceiverID=:ReceiverID) or"
							+ " (SenderID =:SenderID1 and ReceiverID=:ReceiverID1))) ";
					SQLQuery query = session.createSQLQuery(sql);
					System.out.println("Setting class"); 
					query.addEntity(Messages.class);
					query.setParameter("SenderID", sender);
					query.setParameter("ReceiverID", receiver);
					query.setParameter("SenderID1", receiver);
					query.setParameter("ReceiverID1", sender);
					System.out.println("sender is"+sender);
					System.out.println("receiver is"+receiver);
					System.out.println("Testing id is " + timestamp);
					query.setParameter("msgid", Integer.parseInt(timestamp));
					System.out.println("List size "+ query.list().size());
					List<Messages> msgs = (List<Messages>) query.list();
					session.getTransaction().commit();
					
					return msgs;
						
				}
			
			catch (HibernateException e)
				{
					e.printStackTrace();
					session.getTransaction().rollback();
				}
			return null;
	}

	
}


