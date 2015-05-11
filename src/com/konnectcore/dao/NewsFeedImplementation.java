package com.konnectcore.dao;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javassist.bytecode.Descriptor.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.konnectcore.bean.Post;
import com.konnectcore.dao.NewsFeedInformation.NewsFeedInformationDAO;
import com.konnectcore.hibernate.HibernateUtil;

public class NewsFeedImplementation implements NewsFeedInformationDAO {

	private SessionFactory sf;
	private List<Post> resultList;// = new CopyOnWriteArrayList<Post>();
	private List<Integer> blockedPosts;
	public NewsFeedImplementation(SessionFactory sf) {
		// TODO Auto-generated constructor stub	
		this.sf = sf;
	}

	@Override
	public List<Post> getMyFriendsPosts(String userID) {
			// TODO Auto-generated method stub
		
		Session session = sf.getCurrentSession();
		
		try {
            
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Post where postID NOT IN(select blockedpost from blockedposts where userID=:userID)");
			
					query.setParameter("userID", userID);
					//query.setFirstResult(0);
					//query.setMaxResults(5);
			        resultList = query.list();
					session.getTransaction().commit();
				
					if (!tx.wasCommitted()) {
	                    tx.commit();
	                }

	                if (session.isOpen()) {
	                    session.close();
	                }

	                
	        }
			catch (HibernateException e)
			{
				e.printStackTrace();
				session.getTransaction().rollback();
				 throw e;
			}
			
		return resultList;
				

		}
	@Override
	public List<Post> getMyPosts(String userID) {
		// TODO Auto-generated method stub
		
		Session session = sf.getCurrentSession();
		try {
				
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Post where postID NOT IN(select blockedpost from blockedposts where userID=:userID)");
					query.setParameter("userID", userID);
					//query.setFirstResult(0);
					//query.setMaxResults(5);
			        resultList = query.list();
					session.getTransaction().commit();
					
				

					if (!tx.wasCommitted()) {
	                    tx.commit();
	                }

	                if (session.isOpen()) {
	                    session.close();
	                }
	        }
			catch (HibernateException e)
			{
				e.printStackTrace();
				session.getTransaction().rollback();
				 throw e;
			}
		
		return resultList;
			
	}

	
	public boolean updateLikedUsersColumn(Integer postID, String userID) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		boolean status = false;
		try {
					
					System.out.println("postID:" +postID);
					System.out.println("userID:" +postID);
					Transaction tx = session.beginTransaction();
					String myQuery = "UPDATE post SET likedusers = likedusers || '{\""+userID+"\"}' WHERE  ('"+userID+"' = ANY(likedusers)) IS NOT TRUE AND postid = "+postID;
					Query query = session.createSQLQuery(myQuery);
			       	if(query.executeUpdate() == 1)  status = true;
					session.getTransaction().commit();
				
					if (!tx.wasCommitted()) {
	                    tx.commit();
	                }

	                if (session.isOpen()) {
	                    session.close();
	                }
	               
	        }
			catch (HibernateException e)
			{
				e.printStackTrace();
				session.getTransaction().rollback();
				 throw e;
			}
		return status;

	}

	@Override
	public void blockPostInfoFromNewsFeed(Post post) {
		// TODO Auto-generated method stub
		
	}





}
