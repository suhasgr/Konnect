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

public class FriendStatus extends FriendAction implements ServletContextAware
{
	private ServletContext servletContext;
	
	public static String ACCEPT="accept";
	public static String REJECT="reject";
	public static String WITHDRAW="withdraw";
	public static String SENDREQUEST="Send Request";
	public static String UNFRIEND="unfriend";
	public static String BLOCK="block";
	private String userID;
	private String status;
	private Map<String,Object> session;
	
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
	this.servletContext = servletContext;	
		
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
				if(userInformation != null || status != null || userID != null)
				{
					if(status.equals(ACCEPT))
					{
						acceptProcess();
					}
					else if(status.equals(REJECT))
					{
						rejectProcess();
					}
					else if(status.equals(WITHDRAW))
					{
						withdrawProcess();
					}
					else if(status.equals(SENDREQUEST))
					{
						sendRequestProcess();
					}
					else if(status.equals(UNFRIEND))
					{
						unFriendProcess();
					}
					else if(status.equals(BLOCK))
					{
						blockProcess();
					}
					else
					{
						addActionError("Somethig went Wrong. Try again--- ");
					}
					
					if(userID == null || userID.equals(""))
					{
						addActionError("There Was some problem with the process ");
						return SUCCESS;
					}
					
					getFriendDetails(sf);
					getPendingDetails(sf);
					getSentRequestDetails(sf);
					getSuggestedFriendsDetials(sf);
					return SUCCESS;
				}
			}
		}
		addActionError("Session Closed . You are logged out ");
		return ERROR;
	}
	
	private void AcceptRequestNotification(userInformation friendInformation)
	{
		ArrayList<String> notificationList = new ArrayList<String>();
		String[] notifications = friendInformation.getNotification();
		notificationList.addAll(Arrays.asList(notifications));
		
		if(notificationList.size() == 10)
		{
			notificationList.remove(9);
		}
		
		notificationList.add("Friend  Request Accepted : "+userInformation.getUserID());
		friendInformation.setNotification(notificationList.toArray(new String[notificationList.size()]));
	}
	
	private void SentRequestNotification(userInformation friendInformation)
	{
		ArrayList<String> notificationList = new ArrayList<String>();
		String[] notifications = friendInformation.getNotification();
		notificationList.addAll(Arrays.asList(notifications));
		
		if(notificationList.size() == 10)
		{
			notificationList.remove(9);
		}
		
		notificationList.add("New Friend  Request From : "+userInformation.getUserID());
		friendInformation.setNotification(notificationList.toArray(new String[notificationList.size()]));
	}
	
	private String acceptProcess()
	{
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		UserInformationImplementation userDetails = new UserInformationImplementation(sf);
		userInformation friendInformation = userDetails.getUserbyuserID(userID);
		
		String[] friendFriends = friendInformation.getFriends();
		String[] friendSentRequests = friendInformation.getSentRequests();
		String[] userFriends = userInformation.getFriends();
		String[] userPending = userInformation.getPending();
		ArrayList<String> friendFriendList = new ArrayList<String>();
		ArrayList<String> friendSentRequestsList = new ArrayList<String>();
		ArrayList<String> userFriendList = new ArrayList<String>();
		ArrayList<String> userPendingList = new ArrayList<String>();
		
 		
		if(friendFriends != null)
		{
			friendFriendList.addAll(Arrays.asList(friendFriends));
		}
		if(friendSentRequests != null)
		{
			friendSentRequestsList.addAll(Arrays.asList(friendSentRequests));
		}
		if(userFriends != null)
		{
			userFriendList.addAll(Arrays.asList(userFriends));
		}
		if(userPending != null)
		{
			userPendingList.addAll(Arrays.asList(userPending));
		}
		
		userFriendList.add(userID);
		if(userPendingList.contains(userID))
			userPendingList.remove(userID);
		
		userInformation.setFriends(userFriendList.toArray(new String[userFriendList.size()]));
		
		
		userInformation.setPending(userPendingList.toArray(new String[userPendingList.size()]));
		
		AcceptRequestNotification(friendInformation);
		
		userDetails.updateUserInformation(userInformation);
		
		friendFriendList.add(userInformation.getUserID());
		friendSentRequestsList.remove(userInformation.getUserID());
		
		friendInformation.setFriends(friendFriendList.toArray(new String[friendFriendList.size()]));
	
		friendInformation.setSentRequests(friendSentRequestsList.toArray(new String[friendSentRequestsList.size()]));
		
		userDetails.updateUserInformation(friendInformation);
		return SUCCESS;
	}
	
	private String unFriendProcess()
	{
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		UserInformationImplementation userDetails = new UserInformationImplementation(sf);
		userInformation friendInformation = userDetails.getUserbyuserID(userID);
		
		String[] friendFriends = friendInformation.getFriends();
		String[] userFriends = userInformation.getFriends();
		
		ArrayList<String> friendFriendList = new ArrayList<String>();
		ArrayList<String> userFriendList = new ArrayList<String>();
 		
		if(friendFriends != null)
		{
			friendFriendList.addAll(Arrays.asList(friendFriends));
		}
		if(userFriends != null)
		{
			userFriendList.addAll(Arrays.asList(userFriends));
		}
		
		if(userFriendList.contains(userID))
			userFriendList.remove(userID);
		
		userInformation.setFriends(userFriendList.toArray(new String[userFriendList.size()]));
				
		userDetails.updateUserInformation(userInformation);
		
		if(friendFriendList.contains(userInformation.getUserID()))
			friendFriendList.remove(userInformation.getUserID());
		
		friendInformation.setFriends(friendFriendList.toArray(new String[friendFriendList.size()]));		
		userDetails.updateUserInformation(friendInformation);
		return SUCCESS;
	}
	
	private String rejectProcess()
	{
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		UserInformationImplementation userDetails = new UserInformationImplementation(sf);
		
		userInformation friendInformation = userDetails.getUserbyuserID(userID);
		
		String[] userPending = userInformation.getPending();
		String[] friendSentRequests = friendInformation.getSentRequests();
		ArrayList<String> userPendingList = new ArrayList<String>();
		ArrayList<String> friendSentRequestsList = new ArrayList<String>();
		

		if(userPending != null)
		{
			userPendingList.addAll(Arrays.asList(userPending));
		}
		
		if(friendSentRequests != null)
		{
			friendSentRequestsList.addAll(Arrays.asList(friendSentRequests));
		}
		
		if(userPendingList.contains(userID))
			userPendingList.remove(userID);
		
		
		userInformation.setPending(userPendingList.toArray(new String[userPendingList.size()]));
		
		userDetails.updateUserInformation(userInformation);
		
		if(friendSentRequestsList.contains(userInformation.getUserID()))
			friendSentRequestsList.remove(userInformation.getUserID());
		
		friendInformation.setSentRequests(friendSentRequestsList.toArray(new String[friendSentRequestsList.size()]));
		
		userDetails.updateUserInformation(friendInformation);
		return SUCCESS;
	}
	
	private String withdrawProcess()
	{
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		UserInformationImplementation userDetails = new UserInformationImplementation(sf);
		userInformation friendInformation = userDetails.getUserbyuserID(userID);
		
		String[] userSentRequests = userInformation.getSentRequests();
		String[] friendPending = friendInformation.getPending();
		
		ArrayList<String> userSentRequestsList = new ArrayList<String>();
		ArrayList<String> friendPendingList = new ArrayList<String>();
		
		if(userSentRequests != null)
		{
			userSentRequestsList.addAll(Arrays.asList(userSentRequests));
		}
		if(friendPending != null)
		{
			friendPendingList.addAll(Arrays.asList(friendPending));
		}
		
		if(userSentRequestsList.contains(userID))
			userSentRequestsList.remove(userID);
		
		userInformation.setSentRequests(userSentRequestsList.toArray(new String[userSentRequestsList.size()]));
		
		userDetails.updateUserInformation(userInformation);
		
		if(friendPendingList.contains(userInformation.getUserID()))
			friendPendingList.remove(userInformation.getUserID());
		
		friendInformation.setPending(friendPendingList.toArray(new String[friendPendingList.size()]));
		
		userDetails.updateUserInformation(friendInformation);
		return SUCCESS;
	}
	private String sendRequestProcess()
	{
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		UserInformationImplementation userDetails = new UserInformationImplementation(sf);
		userInformation friendInformation = userDetails.getUserbyuserID(userID);
		
		String[] sentRequests = userInformation.getSentRequests();
		String[] friendPending = friendInformation.getPending();
		
		ArrayList<String> sentRequestsList = new ArrayList<String>();
		ArrayList<String> friendPendingList = new ArrayList<String>();
		
		if(sentRequests != null)
		{
			sentRequestsList.addAll(Arrays.asList(sentRequests));
		}
		if(friendPending != null)
		{
			friendPendingList.addAll(Arrays.asList(friendPending));
		}
		
		SentRequestNotification(friendInformation);
		
		sentRequestsList.add(userID);
		friendPendingList.add(userInformation.getUserID());
		
		userInformation.setSentRequests(sentRequestsList.toArray(new String[sentRequestsList.size()]));
		userDetails.updateUserInformation(userInformation);
		
		friendInformation.setPending(friendPendingList.toArray(new String[friendPendingList.size()]));
		userDetails.updateUserInformation(friendInformation);
		
		return SUCCESS;
	}
	
	private String blockProcess()
	{
		SessionFactory sf = (SessionFactory) servletContext.getAttribute("SessionFactory");
		UserInformationImplementation userDetails = new UserInformationImplementation(sf);
		
		String[] blockedUsers = userInformation.getBlockedUsers();
		
		ArrayList<String> blockedUsersList = new ArrayList<String>();
		
		if(blockedUsers != null)
		{
			blockedUsersList.addAll(Arrays.asList(blockedUsers));
		}
		
		blockedUsersList.add(userID);
		
		userInformation.setBlockedUsers(blockedUsersList.toArray(new String[blockedUsersList.size()]));
		userDetails.updateUserInformation(userInformation);
		
		return SUCCESS;
	}

}
