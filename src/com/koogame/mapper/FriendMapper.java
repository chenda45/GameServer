package com.koogame.mapper;

import java.util.List;

import com.koogame.bean.Friend;
import com.koogame.bean.Mail;
import com.koogame.bean.Search;
import com.koogame.bean.UserDetail;



public interface FriendMapper {
	
	public UserDetail findUserDetailByFuid(int uid); 
	
	public List<Friend> findUserFriendByUid(int uid);
	
	public List<Integer> findRandomFuidByLevel(Search search); 
	
	public void addFriend(Friend friend);
	
	public void sendMail(Mail mail);
 
	public void requestOperation(Mail mail);
}
