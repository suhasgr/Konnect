package com.konnectcore.login;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.konnectcore.bean.Post;
import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.PostInformationImplementation;
import com.konnectcore.dao.UserInformationImplementation;
import com.konnectcore.dao.UserInformation.UserDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;

public class PostAction extends ActionSupport implements ServletContextAware {
	private String pText;
	private Post post = new Post();
	private userInformation userInformation;
	private ServletContext ServletContext;
	private Map session;
	
	
	public String getPText() {
		return pText;
	}

	public void setPText(String pText) {
		if(pText == null) this.pText = "Image";
		this.pText = pText;
	}

	
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.ServletContext = servletContext;
	}
	
	public Post getPost() {
		return post;
	}
	
	public userInformation getUserInformation() {
		return userInformation;
	}

	public void setSession(Map session) 
	{
		this.session = session;
	}

	public void setUserInformation(userInformation userInformation) {
		this.userInformation = userInformation;
	}
	
	public void setPost(Post post){
		this.post = post;
	}
	
	@Override
	public String execute()
	{
		
		SessionFactory sf = (SessionFactory) ServletContext.getAttribute("SessionFactory");
        PostInformationImplementation insert = new PostInformationImplementation(sf);
        session = ActionContext.getContext().getSession();
        if(session.containsKey("USERBEAN")){
        		userInformation = (userInformation)session.get("USERBEAN");
        		post.setUserID(userInformation.getUserID());
        		post.setPostTime();
        		System.out.println("Name:" +userInformation.getName());
        		System.out.println("LName: " +userInformation.getLastname());
        		post.setFirstname(userInformation.getName());
        		post.setLastname(userInformation.getLastname());
        		if(session.containsKey("imageInBytes")){
        			byte[] fileInBytes = (byte[]) session.get("imageInBytes");
        			if(fileInBytes != null){
            			post.setImage(fileInBytes);
            			post.setPostText("ImageUpload");
            			session.remove("imageInBytes");
            		}
        		}
        		else post.setPostText(pText);
                insert.insertPostInfo(post);
                return SUCCESS;
        	}
        return ERROR;
	}
}