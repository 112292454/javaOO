package com.gzy.javaoolab.service;


import com.gzy.javaoolab.entity.User;
import com.gzy.javaoolab.vo.Result;

import java.util.List;

public interface RelationService {

	Result<User> addRequest(Integer from, Integer to);

	Result<User> accessRequest(Integer from,Integer to);

	Result<User> denyRequest(Integer from,Integer to);

	Result<List<User>> getAllFriends(Integer user);

	Result<List<User>> getAllRequest(Integer user);


	Result<List<User>> getSentRequest(Integer user);
}
