package com.konnectcore.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.konnectcore.hibernate.*;

import com.konnectcore.bean.Comment;
import com.konnectcore.bean.Forum;
import com.konnectcore.bean.ForumRequests;
import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.ForumInformation.ForumInformationDAO;

public class ForumInformationImplementation implements ForumInformationDAO {
	private SessionFactory sf;
	private List<Comment> comments = null;
	private List<ForumRequests> requestList = null;
	private List<Forum> forumList = null;
	private List<Forum> myForumList = null;
	private List<Forum> myMemberList = null;

	public ForumInformationImplementation(SessionFactory sf) {
		this.sf = sf;
	}

	// Method to get the forum ID number required for the individual comment
	// threads to link
	public String getForumIDnumber(Timestamp time) {
		Forum forum = null;
		String fid = null;
		try {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Forum where time=:time");
			query.setTimestamp("time", time);
			Object obj = query.uniqueResult();
			System.out.println(obj.toString());
			if (obj != null) {
				if (obj instanceof Forum) {
					forum = (Forum) obj;
					tx.commit();
					session.close();
					return forum.getForumid();
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return fid;
	}

	@Override
	public Forum getForumInfo(String userid, String forumid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertForumInfo(Forum forum) {
		// Method to insert into Forum
		Session session = sf.openSession();

		try {
			Transaction tx = session.beginTransaction();
			session.save(forum);
			tx.commit();
			session.close();
		}

		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Override
	public List<Comment> getCommentsFromDB(String forumid) {
		// Method to get List of COmments to display to user

		try {

			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("from CommentForum where forumID=:forumID order by time desc");
			query.setString("forumID", forumid);
			comments = query.list();
			if (comments != null)  {

				System.out.println("Received Comment Obeject from DB!");

			} else {
				System.out.println("Not Received Comment Obeject from DB!");
			}

			tx.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return comments;
	}

	@Override
	public List<Forum> getForumsFromDB(String category) {
		// TODO Auto-generated method stub

		try {

			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Forum where category=:category");
			query.setString("category", category);
			forumList = query.list();
			// Object obj = query.list();
			if (forumList != null) {

				System.out.println("Received Comment Obeject from DB!");

			} else {
				System.out.println("Not Received Comment Obeject from DB!");
			}

			tx.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return forumList;
	}

	@Override
	public void insertForumRequestInfo(ForumRequests forumRequests) {
		// TODO Auto-generated method stub
		
		Session session = sf.openSession();

		try {
			Transaction tx = session.beginTransaction();
			session.save(forumRequests);
			tx.commit();
			session.close();
		}

		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		
	}

	@Override
	public ForumRequests getForumRequestInfo(String fid) {
		// TODO Auto-generated method stub
		ForumRequests forumRequests = null;
		try{
	    	Session session = sf.openSession();
	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery("from ForumRequests where forumID=:forumID and ownerFlag='t'");
	        query.setString("forumID", fid);
	        Object obj =  query.uniqueResult();
	        if(obj != null){
	        	//System.out.println("type: " +obj.getClass());
	        	if(obj instanceof ForumRequests){
	        		forumRequests = (ForumRequests) obj;
	        		System.out.println("Forum Requests Retrieved from DB");
	        	}
	           
	        }
	       
	        tx.commit();session.close();
	    }
        catch(HibernateException e){
        	e.printStackTrace();
        }
		
		return forumRequests;
	}

	@Override
	public void updateRequestForum(ForumRequests forumRequests,String userID) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();

		try {
			Transaction tx = session.beginTransaction();
			//if already a row is there, then update; else create
			
			Query query = session.createQuery("from ForumRequests where forumID=:forumID and requestUserID=:requestUserID");
			query.setString("requestUserID", userID);
	        query.setString("forumID", forumRequests.getForumID());
	        Object obj =  query.uniqueResult();
	        if(obj != null){
	        	session.update(forumRequests);
				tx.commit();
				session.close();
	        	
	        }
	        else{
	        	session.save(forumRequests);
				tx.commit();
				session.close();
	        	
	        }
			
			
			
			
			
		}

		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		
		
		
	}

	@Override
	public List<ForumRequests> getRequestListFromDB(String requestUserID,String forumID) {
		// TODO Auto-generated method stub
		
		try {
			int pendingFlag = 0;
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from ForumRequests where forumID=:forumID and pendingFlag='t'");
			//query.setString("requestUserID", requestUserID);
			query.setString("forumID", forumID);
			 
			//query.setString("pendingFlag", "t");
			//query.setBoolean(pendingFlag, true);
			requestList = query.list();
			
			
			
			
			// Object obj = query.list();
			if (requestList != null) {

				System.out.println("Received requests list Object from DB!");

			} else {
				System.out.println("Not Received Comment Obeject from DB!");
			}

			tx.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return requestList;
	}

	public List<ForumRequests> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<ForumRequests> requestList) {
		this.requestList = requestList;
	}

	@Override
	public ForumRequests getForumRequestUniqueInfo(String fid, String userID) {
		// TODO Auto-generated method stub
		ForumRequests forumRequests = null;
		try{
	    	Session session = sf.openSession();
	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery("from ForumRequests where forumID=:forumID and requestUserID=:requestUserID");
	        query.setString("forumID", fid);
	        query.setString("requestUserID", userID);
	        Object obj =  query.uniqueResult();
	        if(obj != null){
	        	if(obj instanceof ForumRequests){
	        		forumRequests = (ForumRequests) obj;
	        		System.out.println("Forum Requests Retrieved from DB");
	        	}
	           
	        }
	        else{
	        	
	        	query = session.createQuery("from ForumRequests where forumID=:forumID and ownerFlag='t'");
		        query.setString("forumID", fid);
		        obj =  query.uniqueResult();
		        forumRequests = (ForumRequests) obj;
		        System.out.println("Null value with 2 parameters!!!");
		        //System.out.println("Retrieved:"+forumRequests.getForumID());
	        	
	        	
	        }
	       
	        tx.commit();session.close();
	    }
        catch(HibernateException e){
        	e.printStackTrace();
        }
		
		return forumRequests;
		
		
	}

	// Method to get maximum number of forums per user
	@Override
	public int getMaxForumNumber(String userID) {
		long count = -1;
		try{
	    	Session session = sf.openSession();
	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery("select COUNT(*) from Forum where userID=:userID");
	        query.setString("userID", userID);
	        //Object obj =  query.getQueryString();
	        count = (Long)query.uniqueResult();
	        count=(int)count;
	        
	        tx.commit();session.close();
	    }
        catch(HibernateException e){
        	e.printStackTrace();
        }
		
		
		
		return (int) count;
	}

	public boolean checkIfOwner(String fid, String userID) {
		// Method to check if the user is owner
		long count = 0;
		try{
	    	Session session = sf.openSession();
	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery("select COUNT(*) from Forum where forumid=:forumid and userID=:userID");
	        query.setString("userID", userID);
	        query.setString("forumid", fid);
	        //Object obj =  query.getQueryString();
	        count = (Long)query.uniqueResult();
	        count=(int)count;
	        if(count==0){
	        	tx.commit();
		        session.close();
		        return false;
	        }
	        
	        tx.commit();
	        session.close();
	    }
        catch(HibernateException e){
        	e.printStackTrace();
        }
		
		
		return true;
	}

	@Override
	public void updateRequestApproveForum(ForumRequests forumRequests) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();

		try {
			Transaction tx = session.beginTransaction();
			session.update(forumRequests);
			tx.commit();
			session.close();
		}

		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		
	}

	@Override
	public void forumDelete(String fid) {
		// Delete the forum selected by owner
		Session session = sf.openSession();

		try {
			Transaction tx = session.beginTransaction();
			//session.delete(fid);
			Query query = session.createQuery("delete Forum where forumid=:forumid");
	        query.setString("forumid", fid);
	        
	        //Object obj =  query.getQueryString();
	        int result = query.executeUpdate();
	        
			tx.commit();
			session.close();
		}

		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		
		
	}
	
	@Override
	public List<Forum> getMyForumsFromDB(String category,String userID) {
		// TODO Auto-generated method stub

		try {

			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Forum where userID=:userID");
			//query.setString("category", category);
			query.setString("userID", userID);
			myForumList = query.list();
			// Object obj = query.list();
			if (myForumList != null) {

				System.out.println("Received Comment Obeject from DB!");

			} else {
				System.out.println("Not Received Comment Obeject from DB!");
			}

			tx.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return myForumList;
	}

	public List<Forum> getMyMemberForumsFromDB(String category,String userID) {
		// TODO Auto-generated method stub

		try {

			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			//Query query = session.createQuery("select ForumRequests.requestUserID,Forum.description,Forum.forumname,Forum.forumid from ForumRequests RIGHT JOIN FORUM ON Forum.forumid=ForumRequests.forumID WHERE ForumRequests.memberFlag='1' and ForumRequests.requestUserID=:userID");
			//Query query = session.createQuery("select ForumRequests requestUserID,Forum description,Forum forumname,Forum forumid from ForumRequests RIGHT JOIN FORUM ON Forum forumid=ForumRequests forumID WHERE ForumRequests memberFlag='1' and ForumRequests requestUserID=:userID");
			//query.setString("category", category);
			//query.setString("userID", userID);
			//myMemberList = query.list();
			// Object obj = query.list();
			if (myMemberList != null) {

				System.out.println("Received Comment Obeject from DB!");

			} else {
				System.out.println("Not Received Comment Obeject from DB!");
			}

			tx.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return myMemberList;
	}

	public List<Forum> getMyMemberList() {
		return myMemberList;
	}

	public void setMyMemberList(List<Forum> myMemberList) {
		this.myMemberList = myMemberList;
	}


	

}
