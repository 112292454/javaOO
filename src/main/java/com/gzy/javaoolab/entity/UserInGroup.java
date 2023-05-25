package com.gzy.javaoolab.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @description user_group_map
 * @author gzy
 * @date 2023-05-24
 */
@Data
@AllArgsConstructor
public class UserInGroup implements Serializable {

    private static final long serialVersionUID = 8416941605840L;

    @TableId(type = IdType.AUTO)

    private Integer id;

    /**
     * 用户a
     */
    private Integer userId;

    /**
     * 在b群中
     */
    private Integer groupId;

    /**
     * 上一次查看该群的时间（为了推送未读消息）
     */
    private Date lastViewed;

    /**
     * member:群员、admin:管理员、owner:群主
     */
    private String type;

    /**
     * join_time
     */
    private Date joinTime;

    public UserInGroup() {}

    public UserInGroup(String user, String group) {
        this(Integer.parseInt(user),Integer.parseInt(group));
    }

    public boolean isMember() {
        return type.equals("member");
    }

    public boolean isAdmin() {
        return type.equals("admin");
    }

    public boolean isOwner() {
        return type.equals("owner");
    }

    public UserInGroup(Integer userId, Integer groupId) {
       this(userId,groupId,"member");
    }

    public UserInGroup(Integer userId, Integer groupId, String type) {
        this.userId = userId;
        this.groupId = groupId;
        this.type = type;
    }
}