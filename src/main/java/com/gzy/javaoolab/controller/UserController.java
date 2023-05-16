package com.gzy.javaoolab.controller;

import com.gzy.javaoolab.entity.User;
import com.gzy.javaoolab.service.UserService;
import com.gzy.javaoolab.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

	Logger logger= LoggerFactory.getLogger(UserController.class);
	@Resource
	private UserService userService;


	@ApiOperation(value ="注册用简化接口，至少提供用户名、密码。无返回数据，200即注册成功")
	@PostMapping("/register")
	@ResponseBody
	public Result<String> register(
			@ApiParam(value = "用户名") @Pattern(regexp = "\\w{6,20}") @RequestParam("name") String name,
			@ApiParam(value = "密码") @Pattern(regexp = "\\w{6,20}") @RequestParam("password") String password) {
		/*TODO:验证码*/
		logger.info("注册，用户名{}，密码{}",name,password);
		if(userService.contains(name)) throw new IllegalArgumentException("用户已存在");
		userService.register(name, password);
		return Result.success();
	}

	@GetMapping("/register")
	@ResponseBody
	public Result<List<User>> listAllUSer() {
		return Result.<List<User>>success("成功获取所有用户信息，但建议不要用这个接口").data(userService.loadAll());
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
		User user=userService.load(Integer.valueOf(id));
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