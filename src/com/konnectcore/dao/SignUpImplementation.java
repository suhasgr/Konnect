package com.konnectcore.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.konnectcore.bean.userInformation;
/*
public class SignUpImplementation implements SignUpDAO{
	
	private SessionFactory sf;
	
	public SignUpImplementation(SessionFactory sf) {
		this.sf = sf;

	}
	
	

	@Override
	public void insertSignUpInfo(userInformation user) {
		// TODO Auto-generated method stub
		
		Session session = sf.openSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}
		
		catch (HibernateException e)
		{
			e.printStackTrace();
			
		}
		
	}
	
}

*/
