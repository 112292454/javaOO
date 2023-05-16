package com.gzy.javaoolab.serviceImpl;

import com.gzy.javaoolab.entity.Message;
import com.gzy.javaoolab.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;


	@Override
	public Message sendMsg(Message message) {
		//TODO

		simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getSourceUser()), "/", message);

		//设置from用户既然已经发消息了，那默认他已经看完了上面的历史记录
		setMessageViewed(message.getSourceUser()+"",message.getDest()+"");
		return message;
	}

	@Override
	public Message sendMsg(String from, String to, String msg) {
		//TODO
		return sendMsg(persistenceMessage(from, to, msg));
	}

	@Override
	public boolean persistenceMessage(Message message) {
		//TODO
		return true;
	}

	@Override
	public Message persistenceMessage(String from, String to, String msg) {
		Message message = new Message(from,to,msg);
		//TODO

		return message;
	}

	@Override
	public Message persistenceGroupMessage(String from, String group, String msg) {
		Message message = new Message(Integer.parseInt(from), Integer.parseInt(group), msg);
		//TODO

		return message;
	}

	@Override
	public boolean setMessageViewed(String user, String target) {

		//TODO
		return true;
	}

	@Override
	public List<Message> getMsgHistory(String from, String to) {
		List<Message> res=new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			res.add(new Message(i,i,"test msg"+i));
		}
		//TODO

		return res;
	}

	@Override
	public Message loadById(String id) {
		//TODO


		return new Message(1,1,"test msg");
	}
}
