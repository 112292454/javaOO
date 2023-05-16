package com.gzy.javaoolab.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description user
 * @author zhengkai.blog.csdn.net
 * @date 2023-05-16
 */
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)

	/**
	 * name
	 */
	private Integer id;

	/**
	 * name
	 */
	private String name;

	/**
	 * last_login
	 */
	private Date lastLogin;

	/**
	 * email
	 */
	private String email;

	/**
	 * avatar_id
	 */
	private Integer avatarId;

	/**
	 * password
	 */
	private String password;

	public User() {}
}