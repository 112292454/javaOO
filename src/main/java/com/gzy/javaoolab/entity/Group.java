package com.gzy.javaoolab.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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

    public Group addMember(Integer member) {
        if(this.members==null) this.members=new HashMap<>();
        this.members.put(member,"member");
        return this;
    }
    public Group addAdmin(Integer member) {
        if(this.members==null) this.members=new HashMap<>();
        this.members.put(member,"member");
        return this;
    }


    public void setLevel(Integer level) {
        this.level = level;
        this.groupSize = level2Size.get(this.level);
    }

    static Map<Integer,Integer> level2Size=new HashMap<>();
    static {
        level2Size.put(0, 10);
        level2Size.put(1,200);
        level2Size.put(2,500);
        level2Size.put(3,1000);
        level2Size.put(4, 2000);
    }

    /**
     * members
     */
    private transient Map<Integer,String> members;

    public Group() {
        this.members=new HashMap<>();
    }

    public Group(String groupName, Integer owner) {
        this(groupName,owner,1);
    }


    public Group(String groupName, Integer owner, Integer level) {
        this(groupName,level2Size.get(level),owner,level,new HashMap<>());
    }

    public Group(String groupName, Integer groupSize, Integer owner, Integer level, Map<Integer,String> members) {
        this.groupName = groupName;
        this.groupSize = groupSize;
        this.owner = owner;
        this.level = level;
        this.members = members;
        if(this.members.isEmpty()) this.members.put(owner,"owner");
    }
}
