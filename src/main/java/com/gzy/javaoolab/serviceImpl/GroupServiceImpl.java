package com.gzy.javaoolab.serviceImpl;

import com.gzy.javaoolab.dao.UserMapper;
import com.gzy.javaoolab.entity.Group;
import com.gzy.javaoolab.entity.Message;
import com.gzy.javaoolab.entity.User;
import com.gzy.javaoolab.service.GroupService;
import com.gzy.javaoolab.service.MessageService;
import com.gzy.javaoolab.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	MessageService messageService;

	@Autowired
	UserMapper userMapper;

	@Override
	public Result<Group> senMsg(String from, String group, String msg) {
		//消息入库，给可能没有在线的to用户之后看
		Message message = messageService.persistenceGroupMessage(from, group, msg);
		Result<Group> groupResult = groupInfo(group);
		if(groupResult.isSuccess()){
			List<User> users = userMapper.loadAllInList(groupResult.getData().getMembers());
			for (User user : users) {
				//TODO:并发
				messageService.sendMsg(message);
			}
			//设置from用户既然已经发消息了，那默认他已经看完了上面的历史记录
			messageService.setMessageViewed(from, group);
			return Result.success("success");
		}else return Result.error("发送失败，目标群聊不存在");
	}

	@Override
	public List<Message> getGroupMsgHistory(String from, String to) {
		List<Message> res=new ArrayList<>();
		//TODO

		return res;
	}

	@Override
	public Result<Group> createGroup(String user, String groupName) {
		Group res=new Group();
		//TODO

		return Result.<Group>success().data(res);
	}

	@Override
	public Result<Group> exitGroup(String user, String groupName) {
		Group res=new Group();
		//TODO

		return Result.<Group>success().data(res);
	}

	@Override
	public Result<Group> inviteUser(String fromUser, String inviteUser, String group) {
		Group res=new Group();
		//TODO

		return Result.<Group>success().data(res);
	}

	@Override
	public Result<Group> upgradeGroup(String owner, String group) {
		Group res=new Group();
		//TODO

		return Result.<Group>success().data(res);
	}

	@Override
	public Result<Group> groupInfo(String group) {
		Group res=new Group();
		//TODO

		return Result.<Group>success().data(res);
	}
}
