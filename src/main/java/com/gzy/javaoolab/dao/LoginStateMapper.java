package com.zhidian.login.dao;

import com.zhidian.login.entity.LoginState;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description login_state
 * @author gzy
 * @date 2022-11-17
 */
@Mapper
@Repository
public interface LoginStateMapper {

	/**
	 * 新增
	 * @author gzy
	 * @date 2022/11/17
	 **/
	int insert(LoginState loginState);

	/**
	 * 刪除
	 * @author gzy
	 * @date 2022/11/17
	 **/
	int delete(int id);

	/**
	 * 更新
	 * @author gzy
	 * @date 2022/11/17
	 **/
	int update(LoginState loginState);

	/**
	 * 查询 根据主键 id 查询
	 * @author gzy
	 * @date 2022/11/17
	 **/
	LoginState load(int id);

	/**
	 * 查询 分页查询
	 * @author gzy
	 * @date 2022/11/17
	 **/
	List<LoginState> pageList(int offset,int pagesize);

	/**
	 * 查询 分页查询 count
	 * @author gzy
	 * @date 2022/11/17
	 **/
	int pageListCount(int offset,int pagesize);

}