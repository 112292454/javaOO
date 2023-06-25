package com.gzy.javaoolab.service;

import com.gzy.javaoolab.entity.User;

import java.util.Date;
import java.util.List;
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
	User load(Integer id);

	public List<User> loadAll();

	public User loadByName(String name);

	Date getLoginTime(Integer userId);

	void addLoginTime(Integer userId,Date time);

	public boolean contains(String name);
	/**
	 * 分页查询
	 */
	public Map<String,Object> pageList(int offset, int pagesize);

	/**
	 * 初始注册，即带有初始化的的插入
	 */
	public Object register(String name,String password);
}