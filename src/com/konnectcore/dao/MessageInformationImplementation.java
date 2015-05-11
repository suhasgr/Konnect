package com.konnectcore.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.konnectcore.bean.Messages;
import com.konnectcore.dao.MessageInformation.MessageInformationDAO;

public class MessageInformationImplementation implements MessageInformationDAO{
	
	private SessionFactory sf;
	private String result;
    
    public MessageInformationImplementation(SessionFactory sf){
        this.sf = sf;
    }

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public void insertMessageInfo(Messages messages) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
		
		try{
			Transaction tx = session.beginTransaction();
			session.save(messages);
			session.getTransaction().commit();
			}
		
		catch (HibernateException e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

}
