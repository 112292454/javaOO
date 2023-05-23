package com.gzy.javaoolab.controller;


import com.gzy.javaoolab.entity.Message;
import com.gzy.javaoolab.service.MessageService;
import com.gzy.javaoolab.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller("/chat")
@RequestMapping(value = "/chat")
public class ChatController {

	Logger logger= LoggerFactory.getLogger(ChatController.class);


	@Autowired
	MessageService messageService;

	HashMap<Integer, Message> waitForAck=new HashMap<>();


	@PostMapping("/send")
	@ResponseBody
	public Result<?> sendMsgToUser(String from, String to, String msg) {
		//消息入库，给可能没有在线的to用户之后看
		boolean sendRes=messageService.sendMsg(from, to, msg);
		//转发给to，这里待定to要不要回一个ack，来立刻确认to已读这条信息
		Result<?> result = Result.success("成功发送");
		if(!sendRes){
			result.setMsg("已发送，但对方离线");
			result.setStatusCode(201);
		}
		return result;
	}



}
