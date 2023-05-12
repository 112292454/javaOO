package com.gzy.javaoolab.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * @description login_state
 * @author gzy
 * @date 2022-11-17
 */
@Data
@ApiModel("login_state")
public class LoginState implements Serializable {

    private static final long serialVersionUID = 4650426489876098745L;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private int user_id;

    /**
     * 上次登陆的ip
     */
    @ApiModelProperty("上次登陆的ip")
    private String lastloginip;

    /**
     * 上次登陆时间
     */
    @ApiModelProperty("上次登陆时间")
    private String lastlogintime;

    /**
     * 现在是否在线
     */
    @ApiModelProperty("现在是否在线")
    private boolean isonline;

    /**
     * token
     */
    @ApiModelProperty("token")
    private String token;

    public LoginState() {}
}