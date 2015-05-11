package com.konnectcore.login;

import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.konnectcore.bean.Post;
import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.NewsFeedImplementation;
import com.konnectcore.dao.UserInformationImplementation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.hibernate.SessionFactory;
public class Profile extends ActionSupport implements ServletContextAware
{
	private ServletContext servletContext;
	private userInformation userInformation;
	private String userID;
	private userInformation personInformation;
	private ArrayList<userInformation> mutualFriends;
	private ArrayList<userInformation> allFriends;
	private Map<String,Object> session;
	
	private List<Post> listOfArticles;
	
	public Profile()
	{
		mutualFriends = new ArrayList<userInformation>();
		allFriends = new ArrayList<userInformation>();
		listOfArticles = new ArrayList<Post>();
	}
	public void setUserID(String userID)
	{
		this.userID = userID;
	}
	
	public String execute()
	{
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		session = ActionContext.getContext().getSession();
		if(session != null)
		{
			if(session.containsKey("USERBEAN"))
			{
				userInformation = (userInformation)session.get("USERBEAN");
				if(userInformation != null)
				{
					if(userInformation.getUserID().equals(userID) || userID==null)
					{
						personInformation = null;
						insertListofArticles();
						return "own";
					}	
					else 
					{
						UserInformationImplementation userDetail= new UserInformationImplementation(sf); 
						personInformation = userDetail.getUserbyuserID(userID);
						ArrayList<String> friendsList = new ArrayList<String>();
						friendsList.addAll(Arrays.asList(userInformation.getFriends()));
						
						allFriends.clear();
						mutualFriends.clear();
						
						for(String s: personInformation.getFriends())
						{
							allFriends.add(userDetail.getUserbyuserID(s));
							if(friendsList.contains(s))
							{
								mutualFriends.add(userDetail.getUserbyuserID(s));
							}
						}
						
						if(friendsList.contains(userID) || userID.equals("friend"))
						{
							return "friend";
						}
						return "unknown";
					}
				}
			}
		}
		addActionError("There Was some problem. Session Closed . You are logged out ");
		return ERROR;
		
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
	this.servletContext = servletContext;	
		
	}
	
	public userInformation getPersonInformation()
	{
		return personInformation;
	}
	
	public ArrayList<userInformation> getAllFriends()
	{
		return allFriends;
	}
	
	public ArrayList<userInformation> getMutualFriends()
	{
		return mutualFriends;
	}
	
	public userInformation getUserInformation()
	{
		return userInformation;
	}
	
	private void insertListofArticles()
	{
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
        NewsFeedImplementation fetchNewsFeed = new NewsFeedImplementation(sf);
		String[] friendArray = userInformation.getFriends();
		listOfArticles = fetchNewsFeed.getMyPosts(userInformation.getUserID());
		if(friendArray == null){
    		for(String friend: friendArray){
    			listOfArticles.addAll(fetchNewsFeed.getMyFriendsPosts(friend));
    		}
		}
		if(listOfArticles != null){
	        listOfArticles = convertImagesToByteArray();
	        session.put("PostList", listOfArticles);
		}
	}
	
	private List<Post> convertImagesToByteArray() {
		List<Post> temp = getListOfArticles();
		for(Post d: temp){
			if(d.getImage() != null){
				
				if(d.getImageEndode() != null && d.getImageEndode() != ""){
					byte[] tempImage = d.getImage();
					String str = new String(new org.apache.commons.codec.binary.Base64().encode(tempImage));
					d.setImageEndode(str);
				}
			}
		}
		return temp;
	}
	
	public List<Post> getListOfArticles() {
		return listOfArticles;
	}
}
