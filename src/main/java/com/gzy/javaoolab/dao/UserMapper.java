package com.gzy.javaoolab.dao;

import com.gzy.javaoolab.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description user
 * @author gzy
 * @date 2022-11-09
 */
@Mapper
@Repository
public interface UserMapper {

	/**
	 * 新增
	 * @author gzy
	 * @date 2022/11/09
	 **/
	int insert(User user);

	/**
	 * 刪除
	 * @author gzy
	 * @date 2022/11/09
	 **/
	int delete(int id);

	/**
	 * 更新
	 * @author gzy
	 * @date 2022/11/09
	 **/
	int update(User user);

	/**
	 * 查询 根据主键 id 查询
	 * @author gzy
	 * @date 2022/11/09
	 **/
	User load(Integer id);

	User loadByMail(String mail);

	List<User> loadAllInList(List<Integer> ids);
	/**
	 * 查询 分页查询
	 * @author gzy
	 * @date 2022/11/09
	 **/
	List<User> pageList(int offset, int pagesize);

	/**
	 * 查询 分页查询 count
	 * @author gzy
	 * @date 2022/11/09
	 **/
	int pageListCount(int offset,int pagesize);

}