package com.gzy.javaoolab.service;

import com.gzy.javaoolab.entity.Message;

import java.util.List;

public interface GroupService {

	List<Message> getGroupMsgHistory(String from, String to);

	boolean createGroup(String user,String groupName);

	boolean exitGroup(String user,String groupName);

	boolean inviteUser(String fromUser,String inviteUser,String group);

	boolean upgradeGroup(String owner,String group);
}
