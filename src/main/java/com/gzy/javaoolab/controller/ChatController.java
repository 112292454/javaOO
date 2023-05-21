package com.gzy.javaoolab.controller;


import com.gzy.javaoolab.entity.Message;
import com.gzy.javaoolab.service.MessageService;
import com.gzy.javaoolab.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@Controller("/chat")
@RequestMapping(value = "/chat")
public class ChatController {

	Logger logger= LoggerFactory.getLogger(ChatController.class);


	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	MessageService messageService;

	HashMap<Integer, Message> waitForAck=new HashMap<>();


	@PostMapping("/send")
	@ResponseBody
	public Result<?> sendMsgToUser(String from, String to, String msg) {
		//消息入库，给可能没有在线的to用户之后看
		Message message=messageService.sendMsg(from, to, msg);
		//转发给to，这里待定to要不要回一个ack，来立刻确认to已读这条信息
		waitForAck.put(message.getId(), message);


		CompletableFuture.supplyAsync(()->{
			//TODO:要有這個嗎
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			if(waitForAck.containsKey(message.getId())){
				simpMessagingTemplate.convertAndSendToUser(from, "/ack",
						Result.success("对方在线").data(message.getDest()));
			}else{
				simpMessagingTemplate.convertAndSendToUser(from, "/ack",
						Result.error("对方离线").data(message.getDest()));
			}
			waitForAck.remove(message.getId());
			return null;
		});

		return Result.success("成功发送");
	}

	@PostMapping("/ack")
	@ResponseBody
	public Result<?> ackMsg(String from,String msgId) {

		waitForAck.remove(Long.parseLong(msgId));

		//设置from用户对消息来源的用户做出ack
//		messageService.setMessageViewed(from,message.getFrom());
		return Result.success("完成确认");
	}


}
