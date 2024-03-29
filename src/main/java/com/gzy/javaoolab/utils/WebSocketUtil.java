package com.gzy.javaoolab.utils;

import com.alibaba.fastjson2.JSON;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

public class WebSocketUtil {
	//concurrent 包的线程安全 Set ，用来存放每个客户端对应的 MyWebSocket 对象。
	private static Hashtable<String,WebSocketServer> webSocketMap = new Hashtable<>();

	public static List<WebSocketServer> getAllWS() {
		return webSocketMap.values().stream().toList();
	}

	public static WebSocketServer get(String uid) {
		return webSocketMap.get(uid);
	}


	public static void add(String id,WebSocketServer ws) {
		webSocketMap.put(id,ws);
	}
	public static void add(WebSocketServer ws) {
		webSocketMap.put(ws.getUid(),ws);
	}

	public static void remove(WebSocketServer ws) {
		webSocketMap.remove(ws.getUid());
	}

	/**
	 * 实现服务器主动推送
	 */
	public static boolean send(String uid, String message) {
		if(get(uid)==null){//如果没获取到socket，也就是对方没有登录，就返回false
			return false;
		}else{
			try {
				get(uid).sendMessage(message);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return true;
		}
	}

	public static boolean send(String uid, Object message) {
		return send(uid,JSON.toJSONString(message));
	}

	/**
	 * 实现群发
	 */
	public static void groupSend(List<String> uid, String message) {
		uid.forEach(a->send(a,message));
	}

}
