package com.konnectcore.login;

import java.util.Map;

import javax.persistence.metamodel.SetAttribute;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.UserInformation.UserDAO;
import com.konnectcore.dao.UserInformationImplementation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<userInformation>, ServletContextAware{	  
	private userInformation userInformation;// = new userBean();
	
	private ServletContext servletContext;
	Map<String, Object> session;
	
	public userInformation getUserInformation() {
		return userInformation;
	}



	public void setUserInformation(userInformation userInformation) {
		this.userInformation = userInformation;
	}


	@Override
	public String execute()
	{
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
        UserDAO userDAO = new UserInformationImplementation(sf);
        userInformation userFromDB = userDAO.getUserByCredentials(userInformation.getEmail(), userInformation.getPass());
        session = ActionContext.getContext().getSession();
        if(userFromDB == null) {
        	addActionError("Email and password doesn't match! Please retry..");
        	return ERROR;
        }
        
        else {
        	System.out.println("UserId: " +userFromDB.getUserID());
        	System.out.println("User Password: " +userFromDB.getPass());
        	session.put("USERBEAN", userFromDB);
        	return SUCCESS;
        	}
	}
	
	@Override
	public userInformation getModel() {
		// TODO Auto-generated method stub
		return userInformation;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}


	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
