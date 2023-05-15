package com.gzy.javaoolab.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * @description user
 * @author gzy
 * @date 2022-11-09
 */
@Data
@ApiModel("user")
public class User implements Serializable {

	private static final long serialVersionUID = 78456879546513268L;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	private Integer userId;

	/**
	 * 用户名
	 */
	@ApiModelProperty("用户名")
	private String username;

	/**
	 * 邮箱
	 */
	@ApiModelProperty("邮箱")
	private String mail;

	/**
	 * 头像token
	 */
	@ApiModelProperty("头像token")
	private String avatar;

	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	private String password;

	/**
	 * 注册ip
	 */
	@ApiModelProperty("注册ip")
	private String createIp;

	/**
	 * 最后登陆ip
	 */
	@ApiModelProperty("最后登陆ip")
	private String lastLoginIp;

	/**
	 * 个人简介
	 */
	@ApiModelProperty("个人简介")
	private String bio;

	/**
	 * 个人主页
	 */
	@ApiModelProperty("个人主页")
	private String blog;

	/**
	 * 关注我的人数
	 */
	@ApiModelProperty("关注我的人数")
	private Integer followerCount;

	/**
	 * 我关注的人数
	 */
	@ApiModelProperty("我关注的人数")
	private Integer followeeCount;

	/**
	 * 我关注的文章数
	 */
	@ApiModelProperty("我关注的文章数")
	private Integer followingArticleCount;

	/**
	 * 我关注的视频数
	 */
	@ApiModelProperty("我关注的视频数")
	private Integer followingVideoCount;

	/**
	 * 我发表的文章数量
	 */
	@ApiModelProperty("我发表的文章数量")
	private Integer articleCount;

	/**
	 * 我发表的视频数量
	 */
	@ApiModelProperty("我发表的视频数量")
	private Integer videoCount;

	/**
	 * 未读通知数
	 */
	@ApiModelProperty("未读通知数")
	private Integer notificationUnread;

	/**
	 * 未读私信数
	 */
	@ApiModelProperty("未读私信数")
	private Integer inboxUnread;

	/**
	 * 注册时间
	 */
	@ApiModelProperty("注册时间")
	private Integer createTime;

	/**
	 * 最后登录时间
	 */
	@ApiModelProperty("最后登录时间")
	private Integer lastLoginTime;

	public User() {}
}