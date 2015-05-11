package com.konnectcore.dao;

import java.util.List;

import com.konnectcore.bean.Post;

public class NewsFeedInformation {
	public interface NewsFeedInformationDAO {
		 
		
		void blockPostInfoFromNewsFeed(Post post);
		
		List<Post> getMyFriendsPosts(String userID);

		List<Post> getMyPosts(String userID);
		boolean updateLikedUsersColumn(Integer postID, String userID);
	}
}
