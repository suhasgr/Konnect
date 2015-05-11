package com.konnectcore.login;


import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.UserInformationImplementation;
import com.opensymphony.xwork2.ActionSupport;

public class SignUpForm extends ActionSupport implements ServletContextAware
{
	private String username;
	private String password;
	private String email;
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	private ServletContext servletContext;
	
	
	public ServletContext getServletContext() {
		return servletContext;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}




	public String execute()
	{
		
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		Session session = sf.openSession();
		
		
		
		UserInformationImplementation userDetail = new UserInformationImplementation(sf);
			try{
				
					boolean unique = userDetail.checkUserUniqueness(email, username);
					
					if(unique)
					{
						if(password.length() >= 8)
						{
							System.out.println(username + password+email);
							userDetail.insertSignUpInfo(username,password,email);
							addActionError("Account Succesfullty Created : Login to Enter");
							return SUCCESS;
						}
						addActionError("Password size should be minimum of 8 characters ");
						return "retry";
					}
					else
					{
						addActionError("Account username or email is taken. ");
						return "retry";
					}					
			}
			
			catch (HibernateException e)
			{
				e.printStackTrace();
				session.getTransaction().rollback();
			}
		return ERROR;
	}
	
	
}
