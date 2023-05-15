package com.gzy.javaoolab.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description message
 * @author zhengkai.blog.csdn.net
 * @date 2023-05-16
 */
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = 782674860786450L;

    /**
     * id
     */
    private Integer id;

    /**
     * source_user
     */
    private Integer sourceUser;

    /**
     * 可能是一个群id，也可能是用户的id
     */
    private Integer dest;

    /**
     * send_time
     */
    private Date sendTime;

    /**
     * viewed
     */
    private int viewed;

    /**
     * 若为true，dest就是群号
     */
    private int isgroupmsg;

    /**
     * content
     */
    private String content;

    public Message(String from, String to, String msg) {
        this(Integer.parseInt(from),Integer.parseInt(to),msg);
    }



    public Message(Integer sourceUser, Integer dest, String content) {
        this.sourceUser = sourceUser;
        this.dest = dest;
        this.content = content;
    }
}