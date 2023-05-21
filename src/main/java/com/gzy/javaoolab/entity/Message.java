package com.gzy.javaoolab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description message
 * @author zhengkai.blog.csdn.net
 * @date 2023-05-16
 */
@Data
@AllArgsConstructor
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
     * 是否被dest用户查看过了，若dest为群号，则无用
     */
    private int viewed;

    /**
     * 若为true，dest就是群号
     */
    private boolean isGroupMsg;

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
        this.setSendTime(new Date());
    }
}