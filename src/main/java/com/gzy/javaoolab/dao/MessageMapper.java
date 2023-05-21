package com.gzy.javaoolab.dao;

import com.gzy.javaoolab.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description message
 * @author gzy
 * @date 2023-05-22
 */
@Mapper
@Repository
public interface MessageMapper {

    /**
     * 新增
     * @author gzy
     * @date 2023/05/22
     **/
    int insert(Message message);

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
    int update(Message message);

    /**
     * 更新
     * @author gzy
     * @date 2023/05/22
     **/
    boolean setViewed(@Param("user") String user, @Param("beViewedUser") String beViewedUser);

    /**
     * 查询 根据主键 id 查询
     * @author gzy
     * @date 2023/05/22
     **/
    Message load(int id);

    /**
     * 获取两名用户之间的聊天消息
     * @author gzy
     * @date 2023/05/22
     **/
    List<Message> loadAll(@Param("from") String from, @Param("to") String to);

    /**
     * 获取发送到群聊的消息
     * @author gzy
     * @date 2023/05/22
     **/
    List<Message> loadGroup(String group);



    /**
     * 查询 分页查询
     * @author gzy
     * @date 2023/05/22
     **/
    List<Message> pageList(int offset,int pagesize);

    /**
     * 查询 分页查询 count
     * @author gzy
     * @date 2023/05/22
     **/
    int pageListCount(int offset,int pagesize);

}