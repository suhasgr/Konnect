package com.konnectcore.navigation;

import java.util.Map;

import javax.servlet.ServletContext;


import com.konnectcore.bean.userInformation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class urlAction extends ActionSupport 
{
	userInformation userInformation; 
	
	private ServletContext servletContext;
	private Map<String,Object> session;
	
	public String execute() throws Exception
	{
		session = ActionContext.getContext().getSession();
		if(session != null)
		{
			if(session.containsKey("USERBEAN"))
			{
				userInformation = (userInformation)session.get("USERBEAN");
				if(userInformation != null)
				{
					return SUCCESS;
				}
			}
		}
		addActionError("There Was some problem. Session Closed . You are logged out ");
		return ERROR;
	}
	
	
	public void setUserInformation(userInformation userInformation) {
		this.userInformation = userInformation;
	}

	public userInformation getUserInformation() {
		return userInformation;
	}
	
	
	
}
