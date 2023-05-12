package com.gzy.javaoolab.controller;

import com.gzy.javaoolab.entity.Message;
import com.gzy.javaoolab.service.GroupService;
import com.gzy.javaoolab.service.MessageService;
import com.gzy.javaoolab.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;

@Controller("/group")
public class GroupController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	MessageService messageService;

	@Autowired
	GroupService groupService;


	/**
	 * 用户发送消息到某群
	 *
	 * @Author  Guo
	 * @CreateTime   2023-05-09 02:48
	 * @Return com.gzy.javaoolab.vo.Result<java.lang.String>
	 * @Discription
	 * @param from 发送用户id
	 * @param group 群id
	 * @param msg 消息内容
	 */
	@GetMapping("/sendTo")
	@ResponseBody
	//TODO:未做
	public Result<String> sendMsgToGroup(String from, String group, String msg) {
		List<String > test=new ArrayList<>();
		System.out.println("GroupController.sendMsgToGroup");

		//消息入库，给可能没有在线的to用户之后看
		Message message = messageService.persistenceGroupMessage(from, group, msg);
		simpMessagingTemplate.convertAndSend("/topic", msg);

		//设置from用户既然已经发消息了，那默认他已经看完了上面的历史记录
		messageService.setMessageViewed(from, group);

		return Result.success("success");
	}

	/**
	 *
	 *
	 * @Author  Guo
	 * @CreateTime   2023-05-09 02:48
	 * @Return com.gzy.javaoolab.vo.Result<java.lang.String>
	 * @Discription 创建群聊
	 * @param user 创建者
	 * @param groupName 群名
	 */
	@GetMapping("/create_group")
	@ResponseBody
	public Result<String> createGroup(String user,String groupName){

	}

	/**
	 *
	 *
	 * @Author  Guo
	 * @CreateTime   2023-05-09 02:47
	 * @Return com.gzy.javaoolab.vo.Result<java.lang.String>
	 * @Discription 褪裙
	 * @param user
	 * @param groupName
	 */
	@GetMapping("/exit_group")
	@ResponseBody
	public Result<String> exitGroup(String user,String groupName){
	}

	/**
	 *
	 *
	 * @Author  Guo
	 * @CreateTime   2023-05-09 02:46
	 * @Return com.gzy.javaoolab.vo.Result<java.lang.String>
	 * @Discription 某用户拉入另一个用户到群里
	 * @param fromUser 发起请求的用户
	 * @param inviteUser 被拉的用户
	 * @param group 群
	 */
	@GetMapping("/invite_user")
	@ResponseBody
	public Result<String> addUser(String fromUser,String inviteUser,String group){
	}

	/**
	 *
	 *
	 * @Author  Guo
	 * @CreateTime   2023-05-09 02:45
	 * @Return com.gzy.javaoolab.vo.Result<java.lang.String>
	 * @Discription 升级群聊（提高人数上限）
	 * @param owner
	 * @param group
	 */
	@GetMapping("/upgrade_group")
	@ResponseBody
	public Result<String> upgradeGroup(String owner,String group){

	}


	/**
	 *
	 *
	 * @Author  Guo
	 * @CreateTime   2023-05-09 02:46
	 * @Return com.gzy.javaoolab.vo.Result<java.lang.String>
	 * @Discription 群名、id、当前/最大人数、用户列表等
	 * @param group
	 */
	@GetMapping("/group_info")
	@ResponseBody
	public Result<String> groupInfo(String group){

	}



}
