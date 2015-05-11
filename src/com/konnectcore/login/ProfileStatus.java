package com.konnectcore.login;

import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.UserInformationImplementation;

public class ProfileStatus extends ActionSupport implements ServletContextAware 
{
	
	private ServletContext servletContext;
	private String status;
	private userInformation userInformation;
	private Map<String,Object> session;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public userInformation getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(userInformation userInformation) {
		this.userInformation = userInformation;
	}

	@Override
	public void setServletContext(ServletContext servletContext) 
	{
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
	}
	
	public String execute()
	{
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		UserInformationImplementation userDetails = new UserInformationImplementation(sf);
		
		if(userInformation == null)
		{
			addActionError("Something went Wrong. Count Complete teh process");
			return "cancel";
		}
		
		session = ActionContext.getContext().getSession();
		if(session != null)
		{
			if(session.containsKey("USERBEAN"))
			{
				userInformation user = (userInformation)session.get("USERBEAN");
				if(user != null && status != null)
				{
					if(userInformation.getPass().length() < 8)
					{
						addActionError("Password length should be atleast 8 characters");
						return "cancel";
					}
					if(status.equals("save"))
					{
						user.setPass(userInformation.getPass());
						user.setHobbies(userInformation.getHobbies());
						user.setLocation(userInformation.getLocation());
						user.setName(userInformation.getName());
						user.setLastname(userInformation.getLastname());
						user.setOccupation(userInformation.getOccupation());
						user.setGender(userInformation.getGender());
						
						userDetails.updateUserInformation(user);
						userInformation = user;;
						return "save";
					}
					else if(status.equals("cancel"))
					{
						return "cancel";
					}
					else if(status.equals("delete"))
					{
						return "cancel";
					}
				}
			}
		}
		
		addActionError("There was some problem . Session Closed . You are logged out ");
		return ERROR;
	}
	

}
