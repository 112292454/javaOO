package com.gzy.javaoolab.service;


import com.gzy.javaoolab.entity.Message;

import java.util.List;


public interface MessageService {


	 boolean persistenceMessage(Message message);

	 Message persistenceMessage(String from, String to, String msg);

	Message persistenceGroupMessage(String from, String group, String msg);

	/**user已经看过了他与target的所有消息
	 *
	 * @param user
	 * @param target
	 * @return
	 */
	 boolean setMessageViewed(String user,String target);

	List<Message> getMsgHistory(String from, String to);

	Message loadById(String id);

}
