package com.konnectcore.dao;

import com.konnectcore.bean.Comment;
import com.konnectcore.bean.CommentForum;

public class CommentForumInformation {

	public interface CommentForumInformationDAO {
		 
		void insertCommentInfo(CommentForum comment);
		int getMaxCommentNumber(String forumID);
		int getMaxBlockNumber(String commentID);
		void incrementBlockCount(String commentID,String userID);
		void deleteComment(String commentID);
	}
}
