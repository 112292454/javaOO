package com.zhidian.login.service;

import com.zhidian.login.entity.User;

import java.util.Map;
/**
 * @description user
 * @author gzy
 * @date 2022-11-09
 */
public interface UserService {

	/**
	 * 新增
	 */
	public Object insert(User user);

	/**
	 * 删除
	 */
	public Object delete(int id);

	/**
	 * 更新
	 */
	public Object update(User user);

	/**
	 * 根据主键 id 查询
	 */
	public User load(String id);

	public User loadByMail(String mail);

	public boolean contains(String mail);
	/**
	 * 分页查询
	 */
	public Map<String,Object> pageList(int offset, int pagesize);

	/**
	 * 初始注册，即带有初始化的的插入
	 */
	public Object register(String name,String mail,String password);
}