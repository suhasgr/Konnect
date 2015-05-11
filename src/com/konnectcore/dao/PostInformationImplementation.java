package com.konnectcore.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.konnectcore.bean.Post;
import com.konnectcore.dao.PostInformation.PostInformationDAO;
import com.konnectcore.hibernate.HibernateUtil;

import com.konnectcore.bean.userInformation;
public class PostInformationImplementation implements PostInformationDAO {

	private SessionFactory sf;
    
    public PostInformationImplementation(SessionFactory sf){
        this.sf = sf;
    }
	
	/*@Override
	public Post getPostInfo(Integer postID) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		Post post;
		try{
			session.getTransaction().begin();
			Query query = session.createQuery("from Post where postID=:postID");
			query.setParameter("postID", postID);
			post = (Post) query.uniqueResult();
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

		return post;
	}*/

    @Override
   	public Post getPostInfo(Integer postID) {
   		// TODO Auto-generated method stub
   		//Session session = sf.getCurrentSession();
   		Post post = null;  
   		Session session = sf.getCurrentSession();
   		try{
   			Transaction tx = session.beginTransaction();
	        Query query = session.createQuery("from Post where postID=:postID");
	        query.setParameter(postID, "postID"); 
	        Object obj =  query.uniqueResult();
	        if(obj != null){
	        	if(obj instanceof Post){
	        		post = (Post) obj;
	        	}		           
	        }
	       
	        session.getTransaction().commit();
	    }
        catch(HibernateException e){
        	e.printStackTrace();
        	session.getTransaction().rollback();
        }
        return post;
	}
    
    @Override
	public boolean insertPostInfo(Post post) {
		// TODO Auto-generated method stub
    	boolean status = false;
    	Session session = sf.getCurrentSession();
        
		try{
		Transaction tx = session.beginTransaction();
		session.save(post);
		session.getTransaction().commit();
		
		}
		
		catch (HibernateException e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return status;
   }
    
    public boolean updatePostInfo(Post post) {
		// TODO Auto-generated method stub
    	boolean status = false;
		Session session = sf.getCurrentSession();
        
		try{
		Transaction tx = session.beginTransaction();
		session.update(post);
		session.getTransaction().commit();
		
		}
		
		catch (HibernateException e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		  return status;
    }

	/*@Override
	public void insertPostInfo(Post post) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
        
		try{
			session.getTransaction().begin();
			System.out.println("Came here");
			session.save(post);
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
	}*/
	
  
}
