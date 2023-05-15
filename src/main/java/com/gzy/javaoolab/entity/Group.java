package com.gzy.javaoolab.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description group
 * @author zhengkai.blog.csdn.net
 * @date 2023-05-16
 */
@Data
public class Group implements Serializable {

    private static final long serialVersionUID = 4267949248927L;

    @TableId(type = IdType.AUTO)

    /**
     * id
     */
    private Integer id;

    /**
     * group_name
     */
    private String groupName;

    /**
     * group_size
     */
    private Integer groupSize;

    /**
     * group_avatar_id
     */
    private Integer groupAvatarId;

    /**
     * owner
     */
    private Integer owner;

    /**
     * level
     */
    private Integer level;

    /**
     * members
     */
    private transient List<Integer> members;

    public Group() {}
}
