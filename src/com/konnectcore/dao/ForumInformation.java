package com.konnectcore.dao;

import java.util.List;

import com.konnectcore.bean.Comment;
import com.konnectcore.bean.Forum;
import com.konnectcore.bean.ForumRequests;

public class ForumInformation {

	public interface ForumInformationDAO {

		Forum getForumInfo(String userId, String forumid);

		void insertForumInfo(Forum forum);

		void insertForumRequestInfo(ForumRequests forumRequests);

		List<Comment> getCommentsFromDB(String forumid);

		List<Forum> getForumsFromDB(String category);

		ForumRequests getForumRequestInfo(String fid);

		ForumRequests getForumRequestUniqueInfo(String fid, String userID);

		void updateRequestForum(ForumRequests forumRequests,String userID); 
		
		void updateRequestApproveForum(ForumRequests forumRequests);

		List<ForumRequests> getRequestListFromDB(String requestUserID,String forumid);
		
		int getMaxForumNumber(String userID);
		
		public boolean checkIfOwner(String fid, String userID);
		void forumDelete(String fid);

		List<Forum> getMyForumsFromDB(String category, String userID);
		List<Forum> getMyMemberForumsFromDB(String category, String userID);

	}
}
