package com.konnectcore.login;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.persistence.metamodel.SetAttribute;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.konnectcore.bean.Comment;
import com.konnectcore.bean.CommentForum;
import com.konnectcore.bean.Forum;
import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.CommentForumImplementation;
import com.konnectcore.dao.ForumInformationImplementation;
import com.konnectcore.dao.UserInformation.UserDAO;
import com.konnectcore.dao.UserInformationImplementation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CommentForumAction extends ActionSupport implements
		ModelDriven<userInformation>, ServletContextAware {

	private CommentForum comment = new CommentForum();
	private Forum forum;
	private userInformation userInformation;
	private ServletContext servletContext;
	Map session;
	private List<Comment> comments;
	private String comID;

	@Override
	public userInformation getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public CommentForum getComment() {
		return comment;
	}

	public void setComment(CommentForum comment) {
		this.comment = comment;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public userInformation getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(userInformation userInformation) {
		this.userInformation = userInformation;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String execute() {

		session = ActionContext.getContext().getSession();
		SessionFactory sf = (SessionFactory) servletContext
				.getAttribute("SessionFactory");
		CommentForumImplementation ci = new CommentForumImplementation(sf);
		ForumInformationImplementation fi = new ForumInformationImplementation(
				sf);
		if (session.containsKey("USERBEAN")) {
			userInformation = (userInformation) session.get("USERBEAN");
			comment.setUserID(userInformation.getUserID());
			if(comment.getTime()==null){
				java.util.Date date= new java.util.Date();

				Timestamp timeST=new Timestamp(date.getTime());
				
				comment.setTime(timeST);
			}
			
			String fid = (String) session.get("forumID");
			comment.setForumID(fid);
			
			int numberOfComments;
			numberOfComments=ci.getMaxCommentNumber(fid);
			
			if(numberOfComments>=30){
				
				comments = fi.getCommentsFromDB(fid);
				//return "COMMENTLIMIT";
				addActionError("Comments limit reached! Only 30 comments allowed in a forum");
				return SUCCESS;
				
			}
			
			
			//Insert the comment into DB
			ci.insertCommentInfo(comment);
			comments = fi.getCommentsFromDB(fid);
			
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
			return ERROR;
		}

	}
	
	
	
	public String blockThisComment(){
		
		
		
		session = ActionContext.getContext().getSession();
		SessionFactory sf = (SessionFactory) servletContext
				.getAttribute("SessionFactory");
		CommentForumImplementation ci = new CommentForumImplementation(sf);
		ForumInformationImplementation fi = new ForumInformationImplementation(
				sf);
		if (session.containsKey("USERBEAN")) {
			userInformation = (userInformation) session.get("USERBEAN");
			comment.setUserID(userInformation.getUserID());
			if(comment.getTime()==null){
				java.util.Date date= new java.util.Date();

				Timestamp timeST=new Timestamp(date.getTime());
				
				comment.setTime(timeST);
			}
			
			String fid = (String) session.get("forumID");
			comment.setForumID(fid);
			
			int numberOfBlocks;
			//String CID=comment.getCommentID();
			String comID=getComID();
			numberOfBlocks=ci.getMaxBlockNumber(comID);
			
			if(numberOfBlocks>=2){
				//delete comment
				ci.deleteComment(comID);
				
				//comments = fi.getCommentsFromDB(fid);
				//return "COMMENTLIMIT";
				//addActionError("Comments deleted as reported by 20 users");
				
				
			}
			else{
				
				ci.incrementBlockCount(comID,userInformation.getUserID());
			}
			
			
			//Insert the comment into DB
			//ci.insertCommentInfo(comment);
			comments = fi.getCommentsFromDB(fid);
			
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
			return ERROR;
		}
		
		
	}

	/**
	 * @return the comID
	 */
	public String getComID() {
		return comID;
	}

	/**
	 * @param comID the comID to set
	 */
	public void setComID(String comID) {
		this.comID = comID;
	}

	/**
	 * @return the comment2
	 */

}
