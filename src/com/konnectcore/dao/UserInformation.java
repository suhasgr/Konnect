package com.konnectcore.dao;
import java.util.ArrayList;

import com.konnectcore.bean.*;
public class UserInformation {
	
	 
	public interface UserDAO {
	 
		userInformation getUserByCredentials(String email, String password);
		userInformation getUserbyuserID(String userID);
		boolean updateUserInformation(userInformation uf);
		void updateUserRecordForBlockedPost(userInformation tempuser);
		ArrayList<userInformation> getRandomUsers(int limit);
		void insertSignUpInfo(String userid, String pass,String email);
		boolean checkUserUniqueness(String email, String userid);
	}
}
