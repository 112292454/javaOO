package com.gzy.javaoolab.serviceImpl;

import com.gzy.javaoolab.dao.GroupMapper;
import com.gzy.javaoolab.dao.UserGroupMapper;
import com.gzy.javaoolab.dao.UserMapper;
import com.gzy.javaoolab.entity.Group;
import com.gzy.javaoolab.entity.Message;
import com.gzy.javaoolab.entity.User;
import com.gzy.javaoolab.entity.UserInGroup;
import com.gzy.javaoolab.service.GroupService;
import com.gzy.javaoolab.service.MessageService;
import com.gzy.javaoolab.vo.Result;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	MessageService messageService;

	@Autowired
	UserMapper userMapper;

	@Autowired
	GroupMapper groupMapper;

	@Autowired
	UserGroupMapper userGroupMapper;

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Result<Group> senMsg(String from, String group, String msg) {
		//消息入库，给可能没有在线的to用户之后看
		Message message = messageService.persistenceGroupMessage(from, group, msg);
		Result<Group> groupResult = groupInfo(group);
		if(groupResult.isSuccess()){
			List<User> users = userMapper.loadByList(groupResult.getData().getMembers().keySet().stream().toList());
			for (User user : users) {
				CompletableFuture.supplyAsync(()->{
					return messageService.sendMsg(from,user.getId()+"", message);
				});
			}

			//群聊，不再在数据库做标记，只有定期删除，不管是不是有人妹看到
//			messageService.setMessageViewed(from, group);
			return Result.success("success");
		}else return Result.error("发送失败，目标群聊不存在");
	}

	@Override
	public List<Message> getGroupMsgHistory(String group) {
		return messageService.getGroupHistory(group);
	}

	@Override
	public Result<Group> createGroup(String user, String groupName) {
		Group res=new Group(groupName,Integer.parseInt(user));
		try {
			groupMapper.insert(res);
		} catch (Exception e) {
			return Result.error("群已存在！");
		}
		res=groupMapper.load(res.getOwner(), res.getGroupName());
		updateMembers(res);

		return Result.<Group>success().data(res);
	}

	private void updateMembers(Group res) {
		batchInsert(res);
	}

	@Override
	public Result<Group> exitGroup(String user, String group) {
		int res=userGroupMapper.delete(Integer.parseInt(user), Integer.parseInt(group));
		assert res<=1;
		return Result.<Group>success().data(groupInfo(group).getData());
	}

	@Override
	public Result<Group> inviteUser(String fromUser, String inviteUser, String group) {
		UserInGroup inGroup = new UserInGroup(inviteUser, group);
		assert userGroupMapper.load(Integer.parseInt(inviteUser), Integer.parseInt(group))==null;
		userGroupMapper.insert(inGroup);
		return Result.<Group>success().data(groupInfo(group).getData());
	}

	@Override
	public Result<Group> upgradeGroup(String owner, String group) {
		Group res=groupInfo(group).getData();
		res.setLevel(res.getLevel()+1);
		groupMapper.update(res);
		return Result.<Group>success().data(res);
	}

	@Override
	public Result<Group> groupInfo(String group) {
		Group res=groupMapper.loadById(Integer.parseInt(group));
		return groupInfo(res);
	}

	private Result<Group> groupInfo(Group group) {
		if(group==null) return Result.error("群聊不存在");
		List<UserInGroup> userInGroups = userGroupMapper.loadByGroup(group.getId());
		userInGroups.forEach(a->{
			if(a.isAdmin()) group.addAdmin(a.getUserId());
			else if(a.isMember()) group.addMember(a.getUserId());
		});
		return Result.<Group>success().data(group);
	}

	@Override
	public Result<List<Group>> loadGroups(String uid) {
		List<Group> groups = groupMapper.loadByUser(Integer.parseInt(uid));
		List<Group> res= groups.stream().map(a->groupInfo(a).getData()).collect(Collectors.toList());
		return Result.<List<Group>>success().data(res);
	}


	private void batchInsert(Group group) {
		SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
		UserGroupMapper userGroupMapper = sqlSession.getMapper(UserGroupMapper.class);
		group.getMembers().forEach((k, v)-> userGroupMapper.insert(new UserInGroup(k, group.getId(),v)));
		sqlSession.flushStatements();
	}
}
