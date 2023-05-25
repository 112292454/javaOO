package com.gzy.javaoolab.controller;

import com.gzy.javaoolab.entity.User;
import com.gzy.javaoolab.service.RelationService;
import com.gzy.javaoolab.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/relation")
@RequestMapping(value = "/relation")
public class RelationController {

	Logger logger= LoggerFactory.getLogger(RelationController.class);

	@Autowired
	RelationService relationService;


	@PostMapping("add")
	public Result<?> sendRequest(String from, String to) {
		return relationService.addRequest(Integer.parseInt(from),Integer.parseInt(to));
	}

	@PostMapping("access")
	public Result<?> accessRequest(String from, String to) {
		return relationService.accessRequest(Integer.parseInt(from),Integer.parseInt(to));
	}

	@PostMapping("deny")
	public Result<?> denyRequest(String from, String to) {
		return relationService.denyRequest(Integer.parseInt(from),Integer.parseInt(to));
	}

	@PostMapping("friends")
	public Result<List<User>> getFriends(String user) {
		return relationService.getAllFriends(Integer.valueOf(user));
	}


	@PostMapping("request")
	public Result<List<User>> getRequests(String user) {
		//获取谁对user发起了好友请求
		return relationService.getAllRequest(Integer.valueOf(user));
	}

	@PostMapping("my_request")
	public Result<List<User>> getsentRequests(String user) {
		//获取user对谁发起了好友请求，包含被拒绝的
		return relationService.getSentRequest(Integer.valueOf(user));
	}

}