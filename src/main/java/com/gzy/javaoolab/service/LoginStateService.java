package com.zhidian.login.service;

import com.zhidian.login.entity.LoginState;

import java.util.Map;
/**
 * @description login_state
 * @author gzy
 * @date 2022-11-17
 */
public interface LoginStateService {

	/**
	 * 新增
	 */
	public Object insert(LoginState loginState);

	/**
	 * 删除
	 */
	public Object delete(int id);

	/**
	 * 更新
	 */
	public Object update(LoginState loginState);

	/**
	 * 根据主键 id 查询
	 */
	public LoginState load(int id);

	/**
	 * 分页查询
	 */
	public Map<String,Object> pageList(int offset, int pagesize);

}