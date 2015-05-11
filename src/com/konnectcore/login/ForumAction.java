package com.konnectcore.login;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.metamodel.SetAttribute;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.konnectcore.bean.Forum;
import com.konnectcore.bean.Comment;
import com.konnectcore.bean.ForumRequests;
import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.ForumInformationImplementation;
import com.konnectcore.dao.UserInformation.UserDAO;
import com.konnectcore.dao.UserInformationImplementation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ForumAction extends ActionSupport implements
		ModelDriven<userInformation>, ServletContextAware {

	private Forum forum = new Forum();
	
	//private Comment commentObject = new Comment();
	private userInformation userInformation;
	private ForumRequests forumRequest = new ForumRequests();
	private ServletContext servletContext;
	Map session;
	
	//declare array list
	private List<Comment> comments;

	
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
	
	public ForumRequests getForumRequest() {
		return forumRequest;
	}

	public void setForumRequest(ForumRequests forumRequest) {
		this.forumRequest = forumRequest;
	}

	

	//This method is to retrieve the comments on the selected Forum
	@Override
	public String execute() {

		session = ActionContext.getContext().getSession();
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		ForumInformationImplementation fi = new ForumInformationImplementation(
				sf);
		if (session.containsKey("USERBEAN")) {
			userInformation = (userInformation) session.get("USERBEAN");
			forum.setUserid(userInformation.getUserID());
			
			if(forum.getTime()==null){
				java.util.Date date= new java.util.Date();

				Timestamp timeST=new Timestamp(date.getTime());
				
				forum.setTime(timeST);
			}
			
			//check for maximum forum entries of 5
			int numberOfForums;
			numberOfForums=fi.getMaxForumNumber(userInformation.getUserID());
			
			if(numberOfForums>5){
				
				return "FORUMLIMIT";
				
			}
			
			//insert the new entry to forum table
			fi.insertForumInfo(forum);
			String fid = fi.getForumIDnumber(forum.getTime());
			
			//Setting forum ID and name into variables 
			session.put("forumID",fid);
			session.put("forumName",forum.getForumname());
			session.put("forumDescription",forum.getDescription());
			session.put("forumOwnerID",userInformation.getUserID());
		
			
			//Set the ForumRequests object before inserting into forum requests table in DB
			forumRequest.setForumID(forum.getForumid());
			forumRequest.setOwnerUserID(userInformation.getUserID());
			forumRequest.setOwnerFlag(true); 	 //Set to true since he is the owner
			forumRequest.setApproveFlag(false); // Set to false since not needed yet
			forumRequest.setMemberFlag(false);
			forumRequest.setPendingFlag(false);
			fi.insertForumRequestInfo(forumRequest); //inserting into forum requests table
			
			
			
			//Retrieve comments from database to display in the forum page
			comments=fi.getCommentsFromDB(fid);
			
			//if owner go to owner page or else member page
			boolean owner=fi.checkIfOwner(fid, userInformation.getUserID());
			
			if(owner){
				return "OWNER";
			}
			else{
				return SUCCESS;
			}
		} else {
			// Change to error later
			return SUCCESS;
		}

	}



}
