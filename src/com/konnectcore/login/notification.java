package com.konnectcore.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.UserInformationImplementation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class notification extends ActionSupport implements ServletContextAware
{
	private ServletContext servletContext;
	private userInformation userInformation;
	private String status;
	private Map<String,Object> session;
	private ArrayList<String> notifications;
	
	public notification()
	{
		notifications = new ArrayList<String>();
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
	}
	
	
	public String execute()
	{
		session = ActionContext.getContext().getSession();
		if(session != null)
		{
			if(session.containsKey("USERBEAN"))
			{
				userInformation = (userInformation)session.get("USERBEAN");
				if(userInformation != null)
				{
					SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
					UserInformationImplementation userDetails = new UserInformationImplementation(sf);
					userInformation = userDetails.getUserbyuserID(userInformation.getUserID());
					notifications.clear();
					if(status== null)
					{
						if(userInformation.getNotification().length > 0)
						{
							notifications.addAll(Arrays.asList(userInformation.getNotification()));
						}
						else
						{
							addActionError("No Notifications");
						}
						return SUCCESS;
					}
					else if(status.equals("clear"))
					{
						clearNotifications();
						return SUCCESS;
					}
				}
			}
		}
		addActionError("No session object");
		return ERROR;
	}
	
	private void clearNotifications()
	{
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		UserInformationImplementation userDetails = new UserInformationImplementation(sf);
		ArrayList<String> notificationList = new ArrayList<String>();
		userInformation.setNotification(notificationList.toArray(new String[notificationList.size()]));
		userDetails.updateUserInformation(userInformation);

	}
	
	public ArrayList<String> getNotifications()
	{
		return notifications;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
}
