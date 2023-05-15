package com.gzy.javaoolab.controller;

import com.gzy.javaoolab.service.RelationService;
import com.gzy.javaoolab.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("/relation")
public class RelationController {

	@Autowired
	RelationService relationService;


	public Result<?> sendRequest(String from, String to) {
		return relationService.addRequest(from, to);
	}

	public Result<?> accessRequest(String from, String to) {
		return relationService.accessRequest(from, to);
	}

	public Result<?> denyRequest(String from, String to) {
		return relationService.denyRequest(from, to);
	}


}