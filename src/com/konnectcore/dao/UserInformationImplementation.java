package com.konnectcore.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.konnectcore.bean.*;
import com.konnectcore.dao.UserInformation.UserDAO;
import com.konnectcore.hibernate.HibernateUtil;

public class UserInformationImplementation implements UserDAO {
	     
	    private SessionFactory sf;
	     
	    public UserInformationImplementation(SessionFactory sf){
	        this.sf = sf;
	    }
	 
	    @Override
	    public userInformation getUserByCredentials(String email, String pass) {
	    	userInformation user = null;
	    	try{
		    	Session session = sf.openSession();
		        Transaction tx = session.beginTransaction();
		        Query query = session.createQuery("from userInformation where email=:email and pass=:pass");
		        query.setString("email", email); query.setString("pass", pass);
		        Object obj =  query.uniqueResult();
		        if(obj != null){
		        	//System.out.println("type: " +obj.getClass());
		        	if(obj instanceof userInformation){
		        		System.out.println("Yes!!!!!!!!!!!!!!");
		        		user = (userInformation) obj;
		        		System.out.println("User Retrieved from DB::"+user);
		        	}
		           
		        }
		       
		        tx.commit();session.close();
		    }
	        catch(HibernateException e){
	        	e.printStackTrace();
	        }
	        return user;
	    }

		@Override
		public userInformation getUserbyuserID(String userID) {
			// TODO Auto-generated method stub
			userInformation user = null;
	    	try{
		    	Session session = sf.openSession();
		        Transaction tx = session.beginTransaction();
		        Query query = session.createQuery("from userInformation where userID=:userID");
		        query.setString("userID", userID); 
		        Object obj =  query.uniqueResult();
		        if(obj != null){
		        	if(obj instanceof userInformation){
		        		user = (userInformation) obj;
		        	}		           
		        }
		       
		        tx.commit();session.close();
		    }
	        catch(HibernateException e){
	        	e.printStackTrace();
	        }
	        return user;
		}
		
		public boolean updateUserInformation(userInformation user)
		{
			boolean status = false;
			try{
		    	Session session = sf.openSession();
		        Transaction tx = session.beginTransaction();
		        session.update(user);
		        tx.commit();
		        session.close();
		        status = true;
			}
	        catch(HibernateException e){
	        	e.printStackTrace();
	        }
			
			return status;
		}
		
		@Override
		public void updateUserRecordForBlockedPost(userInformation userObj) {
			// TODO Auto-generated method stub
			Session session = sf.getCurrentSession();
			try{
				
				session.getTransaction().begin();
		        session.save(userObj);
				session.getTransaction().commit();
				
			}
			
			catch (HibernateException e)
			{
				e.printStackTrace();
				session.getTransaction().rollback();
				 throw e;
			}
			finally{
				HibernateUtil.closeSession();
			}
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public ArrayList<userInformation> getRandomUsers(int limit) {
			// TODO Auto-generated method stub
			ArrayList<userInformation> randomUsers = null;
			try{
		    	Session session = sf.openSession();
		        Transaction tx = session.beginTransaction();
		        Query query = session.createQuery("from userInformation");
		        query.setMaxResults(limit);
		        Object obj =  query.list();
		        if(obj != null){
		        	//System.out.println("type: " +obj.getClass());
		        	
					randomUsers = (ArrayList<userInformation>) obj; 
		        }
		       
		        tx.commit();session.close();
		    }
	        catch(HibernateException e){
	        	e.printStackTrace();
	        }
			
			return randomUsers;
		}
		
		
		public void insertSignUpInfo(String userid, String pass,String email) {
			// TODO Auto-generated method stub
			
			Session session = sf.openSession();
			try{
				Transaction tx = session.beginTransaction();
		        Query query = session.createSQLQuery("insert into userinformation (userid,pass,email) values (:userID , :pass, :email)");
		        query.setParameter("userID", userid); 
		        query.setParameter("pass", pass);
		        query.setParameter("email", email);
		        query.executeUpdate();
				session.getTransaction().commit();
			}
			
			catch (HibernateException e)
			{
				e.printStackTrace();
				
			}
			
		}
		
		@Override
		public boolean checkUserUniqueness(String email,String userid) {
	    	userInformation user = null;
	    	try{
		    	Session session = sf.openSession();
		        Transaction tx = session.beginTransaction();
		        Query query = session.createQuery("from userInformation where email=:email or userID=:userID");
		        query.setString("userID", userid);
		        query.setString("email", email);
		        List obj =  (List) query.list();
		        
		        if(obj.size() == 0)
		        {
		        	return true;	
		        }
		        tx.commit();session.close();
		    }
	        catch(HibernateException e){
	        	e.printStackTrace();
	        }
	        return false;
	    }

		

}

