package com.gzy.javaoolab.serviceImpl;

import com.alibaba.fastjson2.JSON;
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
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


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
					return messageService.sendMsg(from,user.getId()+"", JSON.toJSONString(message));
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
		groupMapper.insert(res);
		res=groupMapper.load(res.getOwner(), res.getGroupName());
		updateMembers(res);

		return Result.<Group>success().data(res);
	}

	private void updateMembers(Group res) {
		Group finalRes = res;
		res.getMembers().forEach((k,v)-> userGroupMapper.insert(new UserInGroup(k, finalRes.getId())));
	}

	@Override
	public Result<Group> exitGroup(String userId, String groupId) {
		int res=userGroupMapper.delete(Integer.parseInt(userId), Integer.parseInt(groupId));
		assert res<=1;
		return Result.<Group>success().data(groupInfo(groupId).getData());
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

	@Overridegroup
	public Result<Group> groupInfo(String group) {
		Group res=groupMapper.loadById(Integer.parseInt(group));
		if(group==null) return Result.error("群聊不存在");
		List<UserInGroup> userInGroups = userGroupMapper.loadByGroup(Integer.parseInt(group));
		userInGroups.forEach(a->{
			if(a.isAdmin()) res.addAdmin(a.getUserId());
			else res.addMember(a.getUserId());
		});
		return Result.<Group>success().data(res);
	}


//	private List<User> batchLoad(List<Integer> ids) {
//		SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
//		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//		ids.forEach(mapper::load);
//		sqlSession.flushStatements().stream().map(a->a.);
//	}
}
