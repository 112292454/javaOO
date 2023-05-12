package com.gzy.javaoolab.serviceImpl;

import com.gzy.javaoolab.entity.Message;
import com.gzy.javaoolab.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {

	@Override
	public boolean persistenceMessage(Message message) {
		return false;
	}

	@Override
	public Message persistenceMessage(String from, String to, String msg) {
		Message message=new Message();


		return message;
	}

	@Override
	public boolean setMessageViewed(String user, String target) {
		return false;
	}

	@Override
	public List<Message> getMsgHistory(String from, String to) {
		return null;
	}

	@Override
	public Message loadById(String id) {
		return null;
	}
}
