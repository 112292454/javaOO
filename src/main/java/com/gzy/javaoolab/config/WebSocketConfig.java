package com.gzy.javaoolab.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**

 * @author buhao

 * @version WebSocketConfig.java, v 0.1 2019-10-21 16:32 buhao

 */

@Configuration

@EnableWebSocketMessageBroker

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override

    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // 配置客户端尝试连接地址

        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();

    }

    @Override

    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // 设置广播节点

        registry.enableSimpleBroker("/group", "/chat");

        // 客户端向服务端发送消息需有/app 前缀

        registry.setApplicationDestinationPrefixes("/app");

        // 指定用户发送（一对一）的前缀 /user/

        registry.setUserDestinationPrefix("/chat/");

    }

}