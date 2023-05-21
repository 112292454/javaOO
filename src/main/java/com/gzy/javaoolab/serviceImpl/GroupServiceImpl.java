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
	private UserMapper userMapper;

	@Override
	public Result<Group> senMsg(String from, String group, String msg) {
		//消息入库，给可能没有在线的to用户之后看
		Message message = messageService.persistenceGroupMessage(from, group, msg);
		Result<Group> groupResult = groupInfo(group);
		if(groupResult.isSuccess()){
			//TODO:batch
			List<User> users = batchLoad(/*groupResult.getData().getMembers()*/"");
			for (User user : users) {
				//TODO:并发
				messageService.sendMsg(message);
			}
			//群聊，不再在数据库做标记，只有定期删除，不管是不是有人妹看到
//			messageService.setMessageViewed(from, group);
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

	private String buildString(List<Integer> ids){

		return "";
	}

	private List<User> batchLoad(String ids){

		return new ArrayList<>();
	}
}
