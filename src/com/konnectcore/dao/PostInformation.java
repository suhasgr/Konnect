package com.konnectcore.dao;

import com.konnectcore.bean.Post;

public class PostInformation {
	
	 
	public interface PostInformationDAO {
	 
		
		boolean insertPostInfo(Post post);
		
		Post getPostInfo(Integer PostID);
		boolean updatePostInfo(Post post);
	
	}
}
