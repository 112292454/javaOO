package com.gzy.javaoolab.controller;

import com.gzy.javaoolab.entity.Group;
import com.gzy.javaoolab.service.GroupService;
import com.gzy.javaoolab.service.MessageService;
import com.gzy.javaoolab.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public Result<Group> sendMsgToGroup(String from, String group, String msg) {

		return groupService.senMsg(from, group, msg);
	}

	/**
	 *
	 *
	 * @Author  Guo
	 * @CreateTime   2023-05-09 02:48
	 * @Return com.gzy.javaoolab.vo.Result<java.lang.String>
	 * @Discription 创建群聊
	 * @param user 创建者
	 * @param groupId 群id
	 */
	@GetMapping("/create_group")
	@ResponseBody
	public Result<Group> createGroup(String user, String groupId){
		return groupService.createGroup(user, groupId);
	}

	/**
	 *
	 *
	 * @Author  Guo
	 * @CreateTime   2023-05-09 02:47
	 * @Return com.gzy.javaoolab.vo.Result<java.lang.String>
	 * @Discription 褪裙
	 * @param user
	 * @param groupId
	 */
	@GetMapping("/exit_group")
	@ResponseBody
	public Result<Group> exitGroup(String user,String groupId){
		return groupService.exitGroup(user, groupId);
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
	public Result<Group> addUser(String fromUser,String inviteUser,String group){
		return groupService.inviteUser(fromUser, inviteUser, group);
	}

	/**
	 *
	 *
	 * @Author  Guo
	 * @CreateTime   2023-05-09 02:45
	 * @Return com.gzy.javaoolab.vo.Result<java.lang.String>
	 * @Discription 升级群聊（提高人数上限）
	 * @param owner
	 * @param groupId
	 */
	@GetMapping("/upgrade_group")
	@ResponseBody
	public Result<Group> upgradeGroup(String owner,String groupId){
		return groupService.upgradeGroup(owner, groupId);
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
	public Result<Group> groupInfo(String group){
		return groupService.groupInfo(group);
	}



}
