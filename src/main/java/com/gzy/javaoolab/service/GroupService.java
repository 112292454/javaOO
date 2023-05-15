package com.gzy.javaoolab.service;

import com.gzy.javaoolab.entity.Group;
import com.gzy.javaoolab.entity.Message;
import com.gzy.javaoolab.vo.Result;

import java.util.List;

public interface GroupService {

	Result<Group> senMsg(String from, String group, String msg);

	List<Message> getGroupMsgHistory(String from, String to);

	Result<Group> createGroup(String user, String groupName);

	Result<Group> exitGroup(String user,String groupName);

	Result<Group> inviteUser(String fromUser,String inviteUser,String group);

	Result<Group> upgradeGroup(String owner,String group);

	Result<Group> groupInfo(String group);
}
