package com.gzy.javaoolab.controller;

import com.gzy.javaoolab.entity.User;
import com.gzy.javaoolab.service.UserService;
import com.gzy.javaoolab.utils.JwtUtils;
import com.gzy.javaoolab.vo.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

/**
 * @description login_state
 * @author gzy
 * @date 2022-11-17
 */
@RestController
@RequestMapping(value = "/user")
public class LoginController {


	@Resource
	private UserService userService;

	@Resource
	private JwtUtils jwtUtils;

	@PostMapping("/login")
	@ApiResponses({
			@ApiResponse(code = 200,message = "生成的token"),
			@ApiResponse(code = 401,message = "密码与邮箱不对应")
	})
	@ApiOperation(value = "登陆接口",notes="login返回一个token，访问需要带着该token，一小时失效，最后10%有效期访问会刷新token")
	public Result<String> login(
			@RequestBody Map<String,String> map){
		String mail=map.get("name"),password=map.get("password");

		/*TODO:校验合法*/
		/*TODO:验证码*/
		User user=userService.loadByName(mail);


		if(user==null||!user.getPassword().equals(password)){
			return Result.error(HttpServletResponse.SC_UNAUTHORIZED,"登陆验证未通过");
		}else{
			Map<String,Object> data=new HashMap<>();
			//data.put("userID", user.getUserId());
			data.put("mail",user.getMail());
			String token= jwtUtils.createJwt(user.getUserId(),user.getUsername(),data);
			return Result.<String>success("登陆成功").data(token);
		}
	}

	@PostMapping("/test/*")
	public String testMethod(){
		return "hello world";
	}


	/**
	 * logout功能需要存token到数据库，暂时忽略，
	 * 现在是login，一小时失效，最后10%有效期登陆会刷新token
	 * 下面几个和loginState原本想的是token存数据库，但是现在用jwt，不太需要了
	 */

	/**/

}