package com.gzy.javaoolab.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @description relation
 * @author gzy
 * @date 2023-05-22
 */
@Data
public class Relation implements Serializable {

    public static final String REQUEST="request";
    public static final String FRIEND="friend";
    public static final String DENIED="denied";


    private static final long serialVersionUID = 7829708976878690L;

    @TableId(type = IdType.AUTO)

    /**
     * id
     */
    private Integer id;

    /**
     * source_user
     */
    private Integer sourceUser;

    /**
     * dest_user
     */
    private Integer destUser;

    /**
     * status
     */
    private String status;

    /**
     * setTime
     */
    private Date setTime;

    public Relation() {}

    public Relation(Integer sourceUser, Integer destUser) {
        this(sourceUser,destUser,REQUEST,new Date());
    }

    public Relation(Integer sourceUser, Integer destUser, String status, Date setTime) {
        this.sourceUser = sourceUser;
        this.destUser = destUser;
        this.status = status;
        this.setTime = setTime;
    }

    public Relation(String from, String to) {
        this(Integer.parseInt(from),Integer.parseInt(to));
    }

    public boolean isRequest(){
        return status.equals(REQUEST);
    }

    public boolean isFriend(){
        return status.equals(FRIEND);
    }

    public boolean isDenied(){
        return status.equals(DENIED);
    }

    public Relation setRequest(){
        status=REQUEST;
        return this;
    }

    public Relation setFriend(){
        status=FRIEND;
        return this;
    }

    public Relation setDenied(){
        status=DENIED;
        return this;
    }

}