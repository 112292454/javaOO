package com.gzy.javaoolab.controller;

import com.gzy.javaoolab.service.RelationService;
import com.gzy.javaoolab.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/relation")
@RequestMapping(value = "/relation")
public class RelationController {

	@Autowired
	RelationService relationService;


	@PostMapping("add")
	public Result<?> sendRequest(String from, String to) {
		return relationService.addRequest(from, to);
	}

	@PostMapping("access")
	public Result<?> accessRequest(String from, String to) {
		return relationService.accessRequest(from, to);
	}

	@PostMapping("deny")
	public Result<?> denyRequest(String from, String to) {
		return relationService.denyRequest(from, to);
	}


}