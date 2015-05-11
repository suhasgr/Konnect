package com.konnectcore.login;


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
import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.ForumInformationImplementation;
import com.konnectcore.dao.UserInformation.UserDAO;
import com.konnectcore.dao.UserInformationImplementation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ForumSelect extends ActionSupport implements
ModelDriven<userInformation>, ServletContextAware {

	
	private Forum forum = new Forum();
	private List<Forum> forumList;
	private List<Forum> myForumList;
	private List<Forum> myMemberList;
	private userInformation userInformation;
	private ServletContext servletContext;
	Map session;

	
	public List<Forum> getForumList() {
		return forumList;
	}

	public void setForumList(List<Forum> forumList) {
		this.forumList = forumList;
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

	@Override
	public com.konnectcore.bean.userInformation getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Forum> getMyMemberList() {
		return myMemberList;
	}


	public void setMyMemberList(List<Forum> myMemberList) {
		this.myMemberList = myMemberList;
	}


	public List<Forum> getMyForumList() {
		return myForumList;
	}

	public void setMyForumList(List<Forum> myForumList) {
		this.myForumList = myForumList;
	}

	@Override
	public String execute() {

		session = ActionContext.getContext().getSession();
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		ForumInformationImplementation insert = new ForumInformationImplementation(sf);
		if (session.containsKey("USERBEAN")) {
			userInformation = (userInformation) session.get("USERBEAN");
					
			String category= forum.getCategory();
			forumList= insert.getForumsFromDB(category);
			for(Forum fm : forumList) {
		          System.out.println(fm.getDescription());
		    }
		
			return SUCCESS;
		} else {
			// Change to error later
			return SUCCESS;
		}

	}
	

	public String executeMyList() {

		session = ActionContext.getContext().getSession();
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		ForumInformationImplementation fi = new ForumInformationImplementation(sf);
		if (session.containsKey("USERBEAN")) {
			userInformation = (userInformation) session.get("USERBEAN");
					
			String category= forum.getCategory();
			myForumList= fi.getMyForumsFromDB(category,userInformation.getUserID());
			for(Forum fm : myForumList) {
		          System.out.println(fm.getDescription());
		    }

			//myMemberList= fi.getMyMemberForumsFromDB(category,userInformation.getUserID());
			
			return SUCCESS;
		} else {
			// Change to error later
			return SUCCESS;
		}

	}


	

}
