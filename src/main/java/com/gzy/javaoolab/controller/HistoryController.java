package com.gzy.javaoolab.controller;

import com.gzy.javaoolab.entity.Message;
import com.gzy.javaoolab.service.MessageService;
import com.gzy.javaoolab.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/history")
@RequestMapping("/history")
public class HistoryController {
//TODO

	@Autowired
	MessageService messageService;

	@PostMapping("/friend")
	@ResponseBody
	public Result<List<Message>> getFriendHistory(String from, String to) {
		List<Message> msgHistory = messageService.getMsgHistory(from, to);
		messageService.setMessageViewed(from, to);

		
		return Result.<List<Message>>success("成功获取历史记录").data(msgHistory);

	}

	@PostMapping("/group")
	@ResponseBody
	//TODO
	public Result<?> getGroupHistory(String user, String group) {
		List<Message> msgHistory = messageService.getMsgHistory(user, group);
		messageService.setMessageViewed(user, group);

		return Result.<List<Message>>success("成功获取历史记录").data(msgHistory);	}
}