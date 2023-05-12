package com.zhidian.login.controller;

import com.zhidian.login.entity.User;
import com.zhidian.login.service.UserService;
import com.zhidian.login.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Pattern;
import java.util.Map;

/**
 * @description user
 * @author gzy
 * @date 2022-11-09
 */
@RestController
@RequestMapping(value = "/user")
@Api
public class UserController {

	@Resource
	private UserService userService;


	@ApiOperation(value ="注册用简化接口，至少提供用户名、密码、邮箱。无返回数据，200即注册成功")
	@GetMapping("/register")
	@ResponseBody
	public Result<String> register(
			@ApiParam(value = "姓名") @Pattern (regexp = "[\\w\\d 一-龟]{2,20}") @RequestParam("name") String name,
			@ApiParam(value = "邮箱") @Pattern (regexp = "(\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*)|^$") @RequestParam("mail") String mail,
			@ApiParam(value = "密码") @Pattern (regexp = "[\\w\\d]{6,20}") @RequestParam("password") String password) {

		/*TODO:验证码*/
		if(userService.contains(mail)) throw new IllegalArgumentException("用户已存在");
		userService.register(name, mail, password);
		return Result.success();
	}

	/**
	 * 新增
	 * @author gzy
	 * @date 2022/11/09
	 **/
	@RequestMapping("/insert")
	@ApiOperation(value ="注册用完全接口，提供完整的用户信息（也可用作注册之后，完善信息的接口）")
	public Object insert(User user){
		return userService.insert(user);
	}

	/**
	 * 刪除
	 * @author gzy
	 * @date 2022/11/09
	 **/
	@RequestMapping("/delete")
	public Result<String> delete(int id){
		return (Result<String>) userService.delete(id);
	}

	/**
	 * 更新
	 * @author gzy
	 * @date 2022/11/09
	 **/
	@RequestMapping("/update")
	public Result<String> update(User user){
		return (Result<String>) userService.update(user);
	}

	/**
	 * 查询 根据主键 id 查询
	 * @author gzy
	 * @date 2022/11/09
	 **/
	@RequestMapping("/load")
	public Result<User> load(String id){
		User user=userService.load(id);
		return Result.<User>success().data(user);
	}

	/**
	 * 查询 分页查询
	 * @author gzy
	 * @date 2022/11/09
	 **/
	@RequestMapping("/pageList")
	public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
	                                    @RequestParam(required = false, defaultValue = "10") int pagesize) {
		return userService.pageList(offset, pagesize);
	}

}