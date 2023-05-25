package com.gzy.javaoolab.dao;

import com.gzy.javaoolab.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description group
 * @author gzy
 * @date 2023-05-24
 */
@Mapper
@Repository
public interface GroupMapper {

	/**
	 * 新增
	 * @author gzy
	 * @date 2023/05/24
	 **/
	int insert(Group group);

	/**
	 * 刪除
	 * @author gzy
	 * @date 2023/05/24
	 **/
	int delete(int id);

	/**
	 * 更新
	 * @author gzy
	 * @date 2023/05/24
	 **/
	int update(Group group);

	/**
	 * 查询 根据主键 id 查询
	 * @author gzy
	 * @date 2023/05/24
	 **/
	Group loadById(int id);

	List<Group> loadByUser(int uid);

	Group load(@Param("owner") int owner, @Param("name") String groupName);

	/**
	 * 查询 分页查询
	 * @author gzy
	 * @date 2023/05/24
	 **/
	List<Group> pageList(int offset,int pagesize);

	/**
	 * 查询 分页查询 count
	 * @author gzy
	 * @date 2023/05/24
	 **/
	int pageListCount(int offset,int pagesize);

}