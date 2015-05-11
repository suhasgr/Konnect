package com.konnectcore.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.konnectcore.bean.Comment;
import com.konnectcore.bean.CommentForum;
import com.konnectcore.bean.Forum;
import com.konnectcore.dao.CommentForumInformation.CommentForumInformationDAO;
import com.konnectcore.dao.ForumInformation.ForumInformationDAO;

public class CommentForumImplementation implements CommentForumInformationDAO {
	
	private SessionFactory sf;
	
    
    public CommentForumImplementation(SessionFactory sf){
        this.sf = sf;
    }
    
	

	@Override
	public void insertCommentInfo(CommentForum comment) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        
		try{
		Transaction tx = session.beginTransaction();
		session.save(comment); //into db
		tx.commit();
		session.close();
		}
		
		catch (HibernateException e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}



	@Override
	public int getMaxCommentNumber(String forumID) {
		// TODO Auto-generated method stub
		
		long count = -1;
		try{
	    	Session session = sf.openSession();
	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery("select COUNT(*) from CommentForum where forumID=:forumID");
	        query.setString("forumID", forumID);
	        count = (Long)query.uniqueResult();
	        count=(int)count;
	        
	        tx.commit();session.close();
	    }
        catch(HibernateException e){
        	e.printStackTrace();
        }
		return (int) count;
		
	}



	@Override
	public int getMaxBlockNumber(String commentID) {

		int count = 0;
		try{
	    	Session session = sf.openSession();
	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery("select blockCount from CommentForum where commentID=:commentID");
	        query.setString("commentID", commentID);
	        count = (int)query.uniqueResult();
	        
	        tx.commit();session.close();
	    }
        catch(HibernateException e){
        	e.printStackTrace();
        }
		return (int) count;
	}



	@Override
	public void incrementBlockCount(String commentID, String userID) {
		
		int count = 0;
		try{
	    	Session session = sf.openSession();
	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery("select blockCount from CommentForum where commentID=:commentID");
	        query.setString("commentID", commentID);
	        count = (int)query.uniqueResult();
	        //count=(int)count;
	        count++;
	        Query query2 = session.createQuery("update CommentForum set blockCount=:blockCount where commentID=:commentID");
	        query2.setInteger("blockCount", count);
	        query2.setString("commentID", commentID);
	        int result = query2.executeUpdate();
	        
	        tx.commit();session.close();
	    }
        catch(HibernateException e){
        	e.printStackTrace();
        }
		
	}



	@Override
	public void deleteComment(String commentID) {
	
		
		try{
	    	Session session = sf.openSession();
	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery("delete CommentForum where commentID=:commentID");
	        query.setString("commentID", commentID);
	        int result = query.executeUpdate();
	        tx.commit();session.close();
	    }
        catch(HibernateException e){
        	e.printStackTrace();
        }
		
	}

}
