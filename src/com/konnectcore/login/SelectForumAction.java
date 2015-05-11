package com.konnectcore.login;

import java.util.List;
import java.util.Map;

import javax.persistence.metamodel.SetAttribute;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.konnectcore.bean.Comment;
import com.konnectcore.bean.Forum;
import com.konnectcore.bean.ForumRequests;
import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.CommentForumImplementation;
import com.konnectcore.dao.ForumInformationImplementation;
import com.konnectcore.dao.UserInformation.UserDAO;
import com.konnectcore.dao.UserInformationImplementation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SelectForumAction extends ActionSupport implements ModelDriven<userInformation>, ServletContextAware {
	
	private Forum forum = new Forum();
	
	//private Comment commentObject = new Comment();
	private userInformation userInformation;
	private ForumRequests forumRequests = new ForumRequests();
	public static String ACCEPT="accept";
	public static String REJECT="reject";
	private String status;
	private ServletContext servletContext;
	Map session;
	
	//declare array list
	private List<Comment> comments;
	private List<ForumRequests> requestList;
	String forumRequestorID;

	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public userInformation getModel() {
		return null;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public userInformation getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(userInformation userInformation) {
		this.userInformation = userInformation;
	}

	public ForumRequests getForumRequests() {
		return forumRequests;
	}

	public void setForumRequests(ForumRequests forumRequests) {
		this.forumRequests = forumRequests;
	}
	
	public List<ForumRequests> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<ForumRequests> requestList) {
		this.requestList = requestList;
	}
	
	public String getForumRequestorID() {
		return forumRequestorID;
	}

	public void setForumRequestorID(String forumRequestorID) {
		this.forumRequestorID = forumRequestorID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String execute() {

		session = ActionContext.getContext().getSession();
		SessionFactory sf = (SessionFactory) servletContext
				.getAttribute("SessionFactory");
		ForumInformationImplementation fi = new ForumInformationImplementation(
				sf);
		if (session.containsKey("USERBEAN")) {
			userInformation = (userInformation) session.get("USERBEAN");
				
			String fid = forum.getForumid();
			String fname = forum.getForumname();
			
			//Setting forum ID and name into variables 
			session.put("forumID",fid);
			session.put("forumName",fname);
			session.put("forumDescription",forum.getDescription());
			
			
			
			
			//check if the user is owner
			//some code
		/*	boolean owner=fi.checkIfOwner(fid,userInformation.getUserID());
			
			//if its owner, send to owner's view
			if(owner){
				//Get comments and display in new tab in forumRetrieveOwner.jsp
				comments=fi.getCommentsFromDB(fid);
				System.out.println("Inside Owner");
				//return "OWNER";
				return SUCCESS;
			}
			
			*/
			
			//get the object by querying the db
			forumRequests=fi.getForumRequestUniqueInfo(fid,userInformation.getUserID());
			
			if(forumRequests==null){
				
				System.out.println("Inside Owner");
				
				comments=fi.getCommentsFromDB(fid);
				
				//get the list of friend requests 
				//requestList getRequestListFromDB(String requestID,String forumid);
				requestList=fi.getRequestListFromDB(userInformation.getUserID(), fid);
				for (ForumRequests cm : requestList) {
					System.out.println(cm.getRequestUserID());
				}
				
				return "OWNER";
				
			}
			
			//check if the user is a member or pending for approval
			else if(forumRequests.isPendingFlag())
			{
				System.out.println("Inside Pending");
				return "PENDING";
			}
			
			//if its member, send to member's page with comments to display
			else if(forumRequests.isMemberFlag()){
				//Get comments and display in forumRetrieve.jsp
				session.put("Member","Yes");
				comments=fi.getCommentsFromDB(fid);
				
				/*
				for(Comment cm : comments) {
		            System.out.println(cm.getDescription());
		        }
				*/
				System.out.println("Inside Member");
				return "MEMBER";

			}
			//if not a member, send to member request page
			else if(!forumRequests.isMemberFlag() && !(forumRequests.getOwnerUserID().equals(userInformation.getUserID()))){
				//Add a button to ask to join as a member in forumRetrieveNotMember.jsp
				session.put("Member","No");
				System.out.println("Inside Not Member");
				return "NOTMEMBER";
				
			}
			else{
				System.out.println("Inside Owner");
				
				comments=fi.getCommentsFromDB(fid);
				
				//get the list of friend requests 
				//requestList getRequestListFromDB(String requestID,String forumid);
				requestList=fi.getRequestListFromDB(userInformation.getUserID(), fid);
				for (ForumRequests cm : requestList) {
					System.out.println(cm.getRequestUserID());
				}
				return "OWNER";
				
			}
			
			
			
			
			
			
			//comments=insert.getCommentsFromDB(fid);
			

			//return SUCCESS;
		} else {
			// Change to error later
			return SUCCESS;
		}

	}
	
	
	public String executeJoinForum() {

		session = ActionContext.getContext().getSession();
		SessionFactory sf = (SessionFactory) servletContext
				.getAttribute("SessionFactory");
		ForumInformationImplementation update = new ForumInformationImplementation(
				sf);
		if (session.containsKey("USERBEAN")) {
			userInformation = (userInformation) session.get("USERBEAN");
		
		
		String fid = (String) session.get("forumID");
		System.out.println("Before entering pending");
		forumRequests=update.getForumRequestInfo(fid);
		System.out.println("Request ID:"+forumRequests.getRequestID());
		forumRequests.setPendingFlag(true);
		forumRequests.setOwnerFlag(false);
		forumRequests.setRequestUserID(userInformation.getUserID());
		forumRequests.setRequestUserID(userInformation.getUserID());
		
		update.updateRequestForum(forumRequests,userInformation.getUserID());
		
		
		}
		return SUCCESS;
	}
	
	public String executeAcceptForumRequest(){
		session = ActionContext.getContext().getSession();
		SessionFactory sf = (SessionFactory) servletContext
				.getAttribute("SessionFactory");
		ForumInformationImplementation update = new ForumInformationImplementation(
				sf);
		if (session.containsKey("USERBEAN")) {
			userInformation = (userInformation) session.get("USERBEAN");
		
		String requesterid = forumRequests.getRequestUserID();
		//String requesterid2 = this.getForumRequestorID();
		System.out.println("Requester ID"+requesterid);
		
		String fid = (String) session.get("forumID");
		System.out.println("Before entering pending");
		forumRequests=update.getForumRequestUniqueInfo(fid, requesterid);
		System.out.println("Request ID:"+forumRequests.getRequestID());
		
		
		
		
		if(status.equals(ACCEPT))
		{
			forumRequests.setPendingFlag(false);
			forumRequests.setOwnerFlag(false);
			forumRequests.setApproveFlag(true);
			forumRequests.setMemberFlag(true);
		}
		else if(status.equals(REJECT))
		{

			forumRequests.setPendingFlag(false);
			forumRequests.setOwnerFlag(false);
			forumRequests.setApproveFlag(false);
			forumRequests.setMemberFlag(false);
		}
		
		
		
		
		update.updateRequestApproveForum(forumRequests);
		
		
		}
		return SUCCESS;
		
	}
	
	public String executeDeleteForum(){
		session = ActionContext.getContext().getSession();
		SessionFactory sf = (SessionFactory) servletContext
				.getAttribute("SessionFactory");
		ForumInformationImplementation delete = new ForumInformationImplementation(
				sf);
		if (session.containsKey("USERBEAN")) {
			userInformation = (userInformation) session.get("USERBEAN");
		
		//String requesterid = forumRequests.getRequestUserID();
		//String forumid=forumRequests.getForumID();
		//String requesterid2 = this.getForumRequestorID();
		//System.out.println("Requester ID"+requesterid);
		
		String fid = (String) session.get("forumID");
		System.out.println("Before deleting forum");
		//forumRequests=update.getForumRequestUniqueInfo(fid, requesterid);
		//System.out.println("Request ID:"+forumRequests.getRequestID());
		
		
		delete.forumDelete(fid);
		
		
		}
		return SUCCESS;
		
	}
	




}
