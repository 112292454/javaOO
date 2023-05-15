package com.gzy.javaoolab.service;


import com.gzy.javaoolab.entity.User;
import com.gzy.javaoolab.vo.Result;

public interface RelationService {

	Result<User> addRequest(String from, String to);

	Result<User> accessRequest(String from,String to);

	Result<User> denyRequest(String from,String to);


}
