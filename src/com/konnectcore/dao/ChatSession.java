package com.konnectcore.dao;

import com.konnectcore.bean.Chat;


public class ChatSession {
	/*An interface for inserting chat info(ChatID, Sender, Receiver) into the DB*/
	public interface ChatSessionDAO {
		
		void insertChatInfo(Chat chat);
		
		}

}
