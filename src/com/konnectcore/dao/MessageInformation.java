package com.konnectcore.dao;

import com.konnectcore.bean.Messages;

public class MessageInformation {
	
	/*An interface to insert messages(Message, senderid, timestamp) into the DB*/
	public interface MessageInformationDAO {
		
		void insertMessageInfo(Messages messages);
	
	}
}
