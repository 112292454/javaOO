package com.gzy.javaoolab.serviceImpl;

import com.gzy.javaoolab.dao.MessageMapper;
import com.gzy.javaoolab.entity.Message;
import com.gzy.javaoolab.service.MessageService;
import com.gzy.javaoolab.utils.WebSocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

//	@Autowired
//	SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	MessageMapper messageMapper;


	@Override
	public boolean sendMsg(Message message) {
		boolean sendRes = WebSocketUtil.send(String.valueOf(message.getDest()), message);

		//设置from用户既然已经发消息了，那默认他已经看完了上面的历史记录
		setMessageViewed(message.getSourceUser()+"",message.getDest()+"");
		return sendRes;
	}

	@Override
	public boolean sendMsg(String from, String to, String msg) {
		return sendMsg(persistenceMessage(from, to, msg));
	}

	@Override
	public boolean persistenceMessage(Message message) {
		//TODO:要校验其他字段相同，send时间非常相近的就不插入吗？
		messageMapper.insert(message);
		return true;
	}

	@Override
	public Message persistenceMessage(String from, String to, String msg) {
		Message message = new Message(from,to,msg);
		persistenceMessage(message);
		return message;
	}

	@Override
	public Message persistenceGroupMessage(String from, String group, String msg) {
		Message message = new Message(from, group, msg);
		message.setGroupMsg(true);
		persistenceMessage(message);
		return message;
	}

	@Override
	public boolean setMessageViewed(String user, String target) {

		return messageMapper.setViewed(user, target);
	}

	@Override
	public List<Message> getMsgHistory(String from, String to) {
		List<Message> res=messageMapper.loadAll(from, to);
		setMessageViewed(from, to);
		res.addAll(messageMapper.loadAll(to, from));
		setMessageViewed(to, from);

		res.sort(Comparator.comparing(Message::getSendTime));

		return res;
	}

	@Override
	public List<Message> getGroupHistory(String group) {
		List<Message> res=messageMapper.loadGroup(group);

		res.sort(Comparator.comparing(Message::getSendTime));

		return res;
	}

	@Override
	public Message loadById(String id) {
		//TODO:好像用不到

		return new Message(1,1,"test msg");
	}
}
