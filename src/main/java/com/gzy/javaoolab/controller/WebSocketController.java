package com.gzy.javaoolab.controller;


import com.gzy.javaoolab.utils.WebSocketServer;
import com.gzy.javaoolab.utils.WebSocketUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("test")
public class WebSocketController {

	@RequestMapping(value = "messagePage/{userID}")
	public ModelAndView messagePage(@PathVariable String userID, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("userID", userID);
		mav.addObject("userID", userID);
		mav.setViewName("web_socket");
		return mav;
	}


	@GetMapping("/sendMsg/{content}")
	@ResponseBody
	public void sendMsg(@PathVariable("content") String content){
		List<WebSocketServer> list = WebSocketUtil.getAllWS();
		list.forEach(a ->{
			try {
				a.sendMessage(content);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

}