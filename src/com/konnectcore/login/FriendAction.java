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

public class FriendAction extends ActionSupport implements ServletContextAware
{
	private ServletContext servletContext;
	protected userInformation userInformation;
	
	
	private ArrayList<userInformation> friends;
	private ArrayList<userInformation> pending;
	private ArrayList<userInformation> sentRequests;
	private ArrayList<userInformation> suggestedFriends;
	private Map<String,Object>  session;
	
	
	public FriendAction()
	{
		friends = new ArrayList<userInformation>();
		pending = new ArrayList<userInformation>();
		sentRequests = new ArrayList<userInformation>();
		suggestedFriends = new ArrayList<userInformation>();	
	}
		
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
	this.servletContext = servletContext;	
		
	}
	
	public userInformation getUserInformation() {
		return userInformation;
	}


	public void setUserInformation(userInformation userInformation) {
		this.userInformation = userInformation;
	}
	
	public String execute()
	{
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		UserInformationImplementation userDetails = new UserInformationImplementation(sf);
		session = ActionContext.getContext().getSession();
		if(session != null)
		{
			if(session.containsKey("USERBEAN"))
			{
				userInformation = (userInformation)session.get("USERBEAN");
				if(userInformation != null)
				{
					userInformation = userDetails.getUserbyuserID(userInformation.getUserID());
					getFriendDetails(sf);
					getPendingDetails(sf);
					getSentRequestDetails(sf);
					getSuggestedFriendsDetials(sf);
					return SUCCESS;
				}
				addActionError("No session object");
			}
		}
		addActionError("Session Closed . You are logged out ");
		return ERROR;
		
	}
	
	public void getFriendDetails(SessionFactory sf)
	{
		UserInformationImplementation userDetails = new UserInformationImplementation(sf);
		friends.clear();
		
		ArrayList<String> friendsList = new ArrayList<String>();
		String[] friendArray = userInformation.getFriends();
		if(friendArray != null)
		{
			friendsList.addAll(Arrays.asList(friendArray));
		}
		for(String friend: friendsList)
		{
			userInformation friendInfo = userDetails.getUserbyuserID(friend);
			friends.add(friendInfo);
		}
	}
	

	public void getPendingDetails(SessionFactory sf)
	{
		UserInformationImplementation userDetails = new UserInformationImplementation(sf);
		pending.clear();
		ArrayList<String> friendsList = new ArrayList<String>();
		ArrayList<String> blockedList = new ArrayList<String>();
		String[] pendingArray = userInformation.getPending();
		String[] blockedArray = userInformation.getBlockedUsers();
		if(pendingArray != null)
		{
			friendsList.addAll(Arrays.asList(pendingArray));
		}
		if(blockedArray != null)
		{
			blockedList.addAll(Arrays.asList(blockedArray));
		}
		
		for(String friend: friendsList)
		{
			if(!blockedList.contains(friend))
			{
				userInformation friendInfo = userDetails.getUserbyuserID(friend);
				pending.add(friendInfo);
			}
		}
	}
	
	public void getSentRequestDetails(SessionFactory sf)
	{
		UserInformationImplementation userDetails = new UserInformationImplementation(sf);
		sentRequests.clear();
		ArrayList<String> sentRequestList = new ArrayList<String>();
		String[] getSentRequestArray = userInformation.getSentRequests();
		if(getSentRequestArray != null)
		{
			sentRequestList.addAll(Arrays.asList(getSentRequestArray));
		}
		
		for(String friend: sentRequestList)
		{
			userInformation friendInfo = userDetails.getUserbyuserID(friend);
			sentRequests.add(friendInfo);
		}
	}
	
	public void getSuggestedFriendsDetials(SessionFactory sf)
	{
		UserInformationImplementation userDetails = new UserInformationImplementation(sf);
		suggestedFriends.clear();
		
		String[] friends = userInformation.getFriends();
		String[] pending = userInformation.getPending();
		String[] sentRequests = userInformation.getSentRequests();
		
		ArrayList<String> friendsList = new ArrayList<String>(Arrays.asList(friends));
		ArrayList<String> pendingList = new ArrayList<String>(Arrays.asList(pending));
		ArrayList<String> sentRequestsList = new ArrayList<String>(Arrays.asList(sentRequests));
		ArrayList<String> suggestedFriendsList = new ArrayList<String>();
		for(String p: friendsList)
		{
			userInformation friend = userDetails.getUserbyuserID(p);
			if(friend != null)
			{
				for(String s: friend.getFriends())
				{
					if(!pendingList.contains(s) && !sentRequestsList.contains(s) && !suggestedFriendsList.contains(s) && !s.equals(userInformation.getUserID()) && !friendsList.contains(s))
					{
						suggestedFriendsList.add(s);	
					}
				}
			}
		}
		
		if(suggestedFriendsList.size() > 20)
		{
			for(String s: suggestedFriendsList)
			{
				if(suggestedFriendsList.size() < 10)
				{
					break;
				}
				userInformation user = userDetails.getUserbyuserID(s);
				if(!containsCaseInsensitive(suggestedFriendsList,user.getLocation()))
				{
					suggestedFriendsList.remove(s);
				}
			}
		}
		
		if(suggestedFriendsList.size() > 20)
		{
			for(String s: suggestedFriendsList)
			{
				if(suggestedFriendsList.size() < 10)
				{
					break;
				}
				userInformation user = userDetails.getUserbyuserID(s);
				if(user != null)
				{
					if(!containsCaseInsensitive(suggestedFriendsList, user.getOccupation()))
					{
						suggestedFriendsList.remove(s);
					}
				}
			}
		}
		
		if(suggestedFriendsList.size() > 20)
		{
			for(String s: suggestedFriendsList)
			{
				if(suggestedFriendsList.size() < 10)
				{
					break;
				}
				userInformation user = userDetails.getUserbyuserID(s);
				if(user != null)
				{
					ArrayList<String> Userhobbies = new ArrayList<String>(Arrays.asList(user.getHobbies().split(",")));	
					int count = 0;
					for(String h: Userhobbies)
					{
						if(containsCaseInsensitive(suggestedFriendsList, h))
						{
							count++;
						}
					}
					if(count == 0)
					{
						suggestedFriendsList.remove(s);
					}
				}
			}
		}
		
		
		int count = 0;
		for(String p:suggestedFriendsList)
		{
			if(count++ >= 20)
				break;
			userInformation user = userDetails.getUserbyuserID(p);
			suggestedFriends.add(user);
		}
		
		if(count < 10)
		{
			ArrayList<userInformation> getRandomUsers = userDetails.getRandomUsers(20);
			for(userInformation user: getRandomUsers)
			{
				if(count++ > 10)
					break;
				if(!pendingList.contains(user.getUserID()) && !sentRequestsList.contains(user.getUserID()) && !suggestedFriendsList.contains(user.getUserID()) && !user.getUserID().equals(userInformation.getUserID()) && !friendsList.contains(user.getUserID()))
				{
					suggestedFriends.add(user);
				}
			}	
		}
	}
	
	private boolean containsCaseInsensitive(ArrayList<String> list, String s)
	{
		
		
		for(String l: list)
		{
			if(l.toLowerCase().contains(s.toLowerCase()))
				return true;
		}
		return false;
		
	}
	
	public ArrayList<userInformation> getFriends()
	{
		return friends;
	}
	
	public ArrayList<userInformation> getPending()
	{
		return pending;
	}
	
	public ArrayList<userInformation> getSentRequests()
	{
		return sentRequests;
	}
	
	public ArrayList<userInformation> getSuggestedFriends()
	{
		return suggestedFriends;
	}
	
}
