package com.gzy.javaoolab.controller;

import com.gzy.javaoolab.entity.Group;
import com.gzy.javaoolab.service.GroupService;
import com.gzy.javaoolab.service.MessageService;
import com.gzy.javaoolab.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/group")
@RequestMapping(value = "/group")
public class GroupController {

	Logger logger= LoggerFactory.getLogger(GroupController.class);


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
	@PostMapping("/send")
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
	 * @param name 群名
	 */
	@PostMapping("/create")
	@ResponseBody
	public Result<Group> createGroup(String user, String name){
		return groupService.createGroup(user, name);
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
	@PostMapping("/exit")
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
	@PostMapping("/invite")
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
	@PostMapping("/upgrade")
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
	@PostMapping("/info")
	@ResponseBody
	public Result<Group> groupInfo(String group){
		return groupService.groupInfo(group);
	}



}
