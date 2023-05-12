package com.gzy.javaoolab.controller;


import com.gzy.javaoolab.entity.Message;
import com.gzy.javaoolab.service.MessageService;
import com.gzy.javaoolab.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@Controller("/chat")
public class ChatController {
//TODO

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	MessageService messageService;

	HashMap<Long,Message> waitForAck=new HashMap<>();

	//从from收到，入库，并转发给to
//	@PostMapping("/send")
//	public Result<?> sendMessage(String from, String to, String msg) {
//
//	}

	@GetMapping("/sendMsgToUser")
	@ResponseBody
	public Result<?> sendMsgToUser(String from, String to, String msg) {
		//消息入库，给可能没有在线的to用户之后看
		Message message=messageService.persistenceMessage(from, to, msg);
		//转发给to，这里待定to要不要回一个ack，来立刻确认to已读这条信息
		waitForAck.put(message.getId(), message);
		simpMessagingTemplate.convertAndSendToUser(from, "/", message);

		//设置from用户既然已经发消息了，那默认他已经看完了上面的历史记录
		messageService.setMessageViewed(from,to);

		CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			if(waitForAck.containsKey(message.getId())){
				simpMessagingTemplate.convertAndSendToUser(from, "/ack",
						Result.success("对方在线").data(message.getTo()));
			}else{
				simpMessagingTemplate.convertAndSendToUser(from, "/ack",
						Result.error("对方离线").data(message.getTo()));
			}
			waitForAck.remove(message.getId());
			return null;
		});

		return Result.success("成功发送");
	}

	@GetMapping("/ackMsg")
	@ResponseBody
	public Result<?> ackMsg(String from,String msgID) {
//		Message message=messageService.loadById(msgID);

		waitForAck.remove(Long.parseLong(msgID));

		//设置from用户对消息来源的用户做出ack
//		messageService.setMessageViewed(from,message.getFrom());
		return Result.success("完成确认");
	}


}
