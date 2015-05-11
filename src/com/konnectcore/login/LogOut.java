package com.konnectcore.login;


import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogOut extends ActionSupport 
{
	private Map<String,Object> session;
	
	public String execute()
	{
		session = ActionContext.getContext().getSession();
		if(session != null)
		{
			if(session.containsKey("USERBEAN"))
			{
				session.clear();
				return SUCCESS;
			}
		}
		addActionError("Session Closed.");
		return ERROR;
	}

}
