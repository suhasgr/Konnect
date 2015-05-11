package com.konnectcore.login;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import com.konnectcore.bean.commentData;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.konnectcore.bean.Comment;
import com.konnectcore.bean.userInformation;
import com.konnectcore.dao.NewsFeedImplementation;
import com.konnectcore.dao.PostInformationImplementation;
import com.konnectcore.dao.UserInformationImplementation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.konnectcore.bean.Post;
public class NewsFeedActionAJAX extends ActionSupport implements ServletContextAware{

	private InputStream stream;
	private Map session;
	private userInformation userInformation;
	private ServletContext ServletContext;
	//private String userID;
	private List<Post> listOfArticles;
	private String ctext;
	private Integer postID;
	private commentData data;
	
	
	
	public NewsFeedActionAJAX() {
		listOfArticles = new ArrayList<Post>();
	}

	public userInformation getUserInformation() {
		return userInformation;
	}

	public void setSession(Map session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	@Override
	public String execute()
	{
		
		SessionFactory sf = (SessionFactory) ServletContext.getAttribute("SessionFactory");
        NewsFeedImplementation fetchNewsFeed = new NewsFeedImplementation(sf);
        session = ActionContext.getContext().getSession();
        try{
	        if(session.containsKey("USERBEAN")){
	        		System.out.println("Session contains");
	        		userInformation = (userInformation)session.get("USERBEAN");
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
			        System.out.println("User who likes a news feed: " +userInformation.getUserID());
			        return SUCCESS;
	        		
	        	}
        }
        catch(Exception e){
        	e.printStackTrace();
        	//return ERROR;
        }
       
        return null;
	}
	
	public List<Post> convertImagesToByteArray() {
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
	

	public String addLikesToPost(){
		session = ActionContext.getContext().getSession();
		List<Post> tempList = (List<Post>) session.get("PostList");
		System.out.println("Size of list: " +tempList.size());
		Integer temp = null;
		for(Post d : tempList){
	        if(d.getPostID() == getPostID()){
	        	System.out.println("Match!!!!!!!!!!!!!");
	        	temp = d.incrementLikes();
	           try{
	        	   SessionFactory sf = (SessionFactory) ServletContext.getAttribute("SessionFactory");
	        	   NewsFeedImplementation updateLikedUsers = new NewsFeedImplementation(sf);
	        	   
	        	   userInformation = (userInformation)session.get("USERBEAN");
	        	   if(updateLikedUsers.updateLikedUsersColumn(getPostID(),userInformation.getUserID())){
	        		   PostInformationImplementation updatePost = new PostInformationImplementation(sf);
		        	   updatePost.updatePostInfo(d);  
	        	   stream = new ByteArrayInputStream(temp.toString().getBytes());
	       	    		return SUCCESS;
	        	   }
	        	   
	           }
	           catch(Exception e){
	        	   e.printStackTrace();
	        	   }
	           }
	       
	    }

	    return SUCCESS;
		
	}
	
	
	
	public String blockThisPost(){
		session = ActionContext.getContext().getSession();
		 try{
	        	   
	        	   SessionFactory sf = (SessionFactory) ServletContext.getAttribute("SessionFactory");
	        	   userInformation userObj = (userInformation)session.get("USERBEAN");
	        	   //userObj.addPostIDToBlockedSet(postID);
	        	   UserInformationImplementation uii = new UserInformationImplementation(sf);
	        	   uii.updateUserRecordForBlockedPost(userObj);
	        	   
		 }
           catch(Exception e){
        	   e.printStackTrace();
        	   }
		 return null;
    }
	    
	public String addCommentToPost(){
		String ctext = data.getCtext();
		Integer postedon = data.getPostID();
		System.out.println("Inside commenting code:" +ctext);
		System.out.println("Comment: " +postedon);
		session = ActionContext.getContext().getSession();
		List<Post> tempList = (List<Post>) session.get("PostList");
		System.out.println("Size of list: " +tempList.size());
		for(Post d : tempList){
			System.out.println("Inside for loop");
	        if(d.getPostID().intValue() == postedon.intValue()){
	        	System.out.println("Matched");
	        	Comment comObj = new Comment();
	        	userInformation userObj = (userInformation)session.get("USERBEAN");
	        	comObj.setCommentText(ctext);
	        	//comObj.setUserID(userObj.getUserID());
	        	comObj.setUserID(userObj.getUserID());
	        	comObj.setCommentTime();
	        	d.getComments().add(comObj);
	        	try{
	        	   System.out.println("Updating comments");
	        	   SessionFactory sf = (SessionFactory) ServletContext.getAttribute("SessionFactory");
	        	   PostInformationImplementation updatePost = new PostInformationImplementation(sf);
	        	   updatePost.updatePostInfo(d);
	           }
	           catch(Exception e){
	        	   e.printStackTrace();
	        	   }
	           }
	       
	    }
		//stream = new ByteArrayInputStream(data.getCtext().getBytes(StandardCharsets.UTF_8));
	    return SUCCESS;
	
	}	

	


	public Map getSession() {
		return session;
	}

	public ServletContext getServletContext() {
		return ServletContext;
	}

	public void setUserInformation(userInformation userInformation) {
		this.userInformation = userInformation;
	}

	public List<Post> getListOfArticles() {
		return listOfArticles;
	}

	public void setListOfArticles(ArrayList<Post> listOfArticles) {
		this.listOfArticles = listOfArticles;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.ServletContext = servletContext;
	}

	public Integer getPostID() {
		return postID;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	public String getCtext() {
		return ctext;
	}

	public void setCtext(String ctext) {
		this.ctext = ctext;
	}

	public commentData getData() {
		return data;
	}

	public void setData(commentData data) {
		this.data = data;
	}
	
}
