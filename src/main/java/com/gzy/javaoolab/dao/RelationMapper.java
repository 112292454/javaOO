package com.gzy.javaoolab.dao;

import com.gzy.javaoolab.entity.Relation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description relation
 * @author gzy
 * @date 2023-05-22
 */
@Mapper
@Repository
public interface RelationMapper {

    /**
     * 新增
     * @author gzy
     * @date 2023/05/22
     **/
    int insert(Relation relation);

    /**
     * 刪除
     * @author gzy
     * @date 2023/05/22
     **/
    int delete(int id);

    /**
     * 更新
     * @author gzy
     * @date 2023/05/22
     **/
    int update(Relation relation);


    /**
     * 查询 根据联合主键查询，两个用户的id组合应该是唯一的——俩人只能有一种关系
     * @author gzy
     * @date 2023/05/22
     **/
    Relation load(@Param("from") Integer from, @Param("to") Integer to);

    /**
     * 查询以user为目标的所有关系
     * @author gzy
     * @date 2023/05/22
     **/
    List<Relation> loadAllRelationFor(Integer user);

    /**
     * 查询以user发起的的所有关系
     * @author gzy
     * @date 2023/05/22
     **/
    List<Relation> loadAllRelationFrom(Integer user);

    /**
     * 查询 分页查询
     * @author gzy
     * @date 2023/05/22
     **/
    List<Relation> pageList(int offset,int pagesize);

    /**
     * 查询 分页查询 count
     * @author gzy
     * @date 2023/05/22
     **/
    int pageListCount(int offset,int pagesize);

}