package com.gzy.javaoolab.serviceImpl;

import com.gzy.javaoolab.dao.RelationMapper;
import com.gzy.javaoolab.dao.UserMapper;
import com.gzy.javaoolab.entity.Relation;
import com.gzy.javaoolab.entity.User;
import com.gzy.javaoolab.service.RelationService;
import com.gzy.javaoolab.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RelationServiceImpl implements RelationService {

	@Autowired
	RelationMapper relationMapper;

	@Autowired
	UserMapper userMapper;

	@Override
	public Result<User> addRequest(Integer from, Integer to) {
		Relation check=relationMapper.load(from, to);
		if(check!=null) {
			if(check.isRequest()) return Result.error("已发送好友请求，请勿重复添加");
			else if(check.isFriend()) return Result.error("已成为好友，请勿重复添加");
			else if(check.isDenied()) relationMapper.delete(check.getId());
		}

		Relation add=new Relation(from,to);
		relationMapper.insert(add);
		return Result.success("发送好友请求成功");
	}

	//TODO:这几个同意拒绝之类的，直接变成操作一次数据库的update
	@Override
	public Result<User> accessRequest(Integer from, Integer to) {
		Relation check=relationMapper.load(from, to);
		if(check==null) return Result.error("无此人好友请求");
		else if(check.isFriend()) return Result.error("已同意好友请求，请勿重复确认");
		else if(check.isDenied()) return Result.error("已拒绝好友请求，无法变为同意");


		Relation todo=relationMapper.load(from, to);
		todo.setFriend();
		relationMapper.update(todo);
		return Result.success("已同意好友请求");
	}

	@Override
	public Result<User> denyRequest(Integer from, Integer to) {
		Relation todo=relationMapper.load(from, to);
		todo.setDenied();
		relationMapper.update(todo);
		return Result.success("已拒绝好友请求");
	}

	@Override
	public Result<List<Integer>> getAllFriends(Integer user) {
		List<Relation> allRelation = relationMapper.loadAllRelationFor(user);
		allRelation.addAll(relationMapper.loadAllRelationFrom(user));


		List<Integer> friendIds=allRelation.stream()
				.filter(Relation::isFriend)
				.mapToInt(a-> a.getSourceUser().equals(user) ?a.getDestUser():a.getSourceUser())
				.boxed().toList();

		return Result.<List<Integer>>success("共"+friendIds.size()+"个好友").data(friendIds);
	}

	@Override
	public Result<List<Integer>> getAllRequest(Integer user) {
		List<Relation> allRelation = relationMapper.loadAllRelationFor(user);

		List<Integer> friendIds=allRelation.stream()
				.filter(Relation::isRequest)
				.mapToInt(Relation::getSourceUser)
				.boxed().toList();

		return Result.<List<Integer>>success("共"+friendIds.size()+"个好友请求").data(friendIds);
	}

	@Override
	public Result<List<Integer>> getSentRequest(Integer user) {
		List<Relation> allRelation = relationMapper.loadAllRelationFrom(user);

		List<Integer> friendIds=allRelation.stream()
				.filter(a->!a.isFriend())//只要不是已添加的好友，都是要显示为请求的
				.mapToInt(Relation::getDestUser)
				.boxed().toList();

		return Result.<List<Integer>>success("共"+friendIds.size()+"个好友请求").data(friendIds);
	}


}
