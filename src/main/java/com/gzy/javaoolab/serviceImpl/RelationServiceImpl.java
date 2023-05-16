package com.gzy.javaoolab.serviceImpl;

import com.gzy.javaoolab.entity.User;
import com.gzy.javaoolab.service.RelationService;
import com.gzy.javaoolab.vo.Result;
import org.springframework.stereotype.Service;


@Service
public class RelationServiceImpl implements RelationService {

	@Override
	public Result<User> addRequest(String from, String to) {
		//TODO

		return Result.success("test add friend");
	}

	@Override
	public Result<User> accessRequest(String from, String to) {
		//TODO

		return Result.success("test access ");
	}

	@Override
	public Result<User> denyRequest(String from, String to) {
		//TODO
		return Result.success("test deny");
	}
}
