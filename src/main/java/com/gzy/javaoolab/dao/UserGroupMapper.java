package com.gzy.javaoolab.dao;

import com.gzy.javaoolab.entity.UserInGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description user_group_map
 * @author gzy
 * @date 2023-05-24
 */
@Mapper
@Repository
public interface UserGroupMapper {

	/**
	 * 新增
	 * @author gzy
	 * @date 2023/05/24
	 **/
	int insert(UserInGroup userInGroup);

	/**
	 * 刪除
	 * @author gzy
	 * @date 2023/05/24
	 **/
	int delete(@Param("uid") int uid, @Param("gid") int gid);

	/**
	 * 更新
	 * @author gzy
	 * @date 2023/05/24
	 **/
	int update(UserInGroup userInGroup);

	/**
	 * 查询 根据主键 id 查询
	 * @author gzy
	 * @date 2023/05/24
	 **/
	UserInGroup load(@Param("uid") int uid,@Param("gid") int gid);

	List<UserInGroup> loadByGroup(int group);

	/**
	 * 查询 分页查询
	 * @author gzy
	 * @date 2023/05/24
	 **/
	List<UserInGroup> pageList(int offset, int pagesize);

	/**
	 * 查询 分页查询 count
	 * @author gzy
	 * @date 2023/05/24
	 **/
	int pageListCount(int offset,int pagesize);

}