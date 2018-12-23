package com.boot.SpringBoot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.SpringBoot.domain.Result;
import com.boot.SpringBoot.domain.User;
import com.boot.SpringBoot.domain.enumer.Gender;
import com.boot.SpringBoot.service.UserService;
//在@controller注解中，返回的是字符串，或者是字符串匹配的模板名称，即直接渲染视图，与html页面配合使用的，
//在这种情况下，前后端的配合要求比较高，java后端的代码要结合html的情况进行渲染,
//使用model对象（或者modelandview）的数据将填充user视图中的相关属性，然后展示到浏览器，这个过程也可以称为渲染；
import com.boot.SpringBoot.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//而RestController提供rest风格接口，返回Json,@Controller返回ModelAndView
@Api(value = "test the swagger API", tags = { "测试接口" })
@RestController
@RequestMapping("restful")
public class RestFulController
{
	private static Logger logger = LoggerFactory.getLogger(RestFulController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping("/heartBeat")
	public String heartBeat()
	{
		return "Hello,application is alive.";
	}

	@RequestMapping(value = "/swagger")
	public String index()
	{
		logger.debug("swagger-ui.html");
		return "redirect:swagger-ui.html";
	}

	@RequestMapping("/getUserById")
	@ApiOperation(value = "根据ID查询某个用户", notes = "查询用户")
	public Result<User> getUserById(@ApiParam(name = "ID", value = "用户ID", required = true) Integer id)
	{
		Result<User> result = new Result<User>();
		result.setCode(Constants.SUCCESS);
		result.setDesc(Constants.MSG_SUCCESS);
		result.setData(userService.getUserById(id));
		return result;
	}

	@RequestMapping("/getUserByName")
	@ApiOperation(value = "查询名称某个用户", notes = "查询用户")
	public Result<User> getUserByName(@ApiParam(name = "name", value = "用户名称", required = true) String name)
	{
		Result<User> result = new Result<User>();
		result.setCode(Constants.SUCCESS);
		result.setDesc(Constants.MSG_SUCCESS);
		result.setData(userService.getUserByName(name));
		return result;
	}

	@RequestMapping(value = "/getuser", method = RequestMethod.GET)
	public String getUser(Model model)
	{
		model.addAttribute("name", "attributeValue");
		model.addAttribute("sex", "attributeValue");
		return "modelandview";// user是模板名
	}

	@RequestMapping("/batchSaveUser")
	public void batchSaveUser()
	{
		userService.batchSaveUser();
	}
	
	@RequestMapping("/jdbcSaveUser")
	public void jdbcSaveUser()
	{
		userService.jdbcSaveUser();
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public User saveUser(@RequestBody User user)
	{
		logger.debug(user.toString());
		return userService.saveUser(user);
	}

	@RequestMapping(value = "/{category}/{brand}/{id}", method = RequestMethod.GET)
	public User getById(@PathVariable("category") String category, @PathVariable("brand") String brand,
			@PathVariable("id") Integer id)
	{
		User user = new User();
		user.setUserName(category);
		user.setUid(id);
		user.setPassword(brand);
		user.setGender(Gender.NEUTER.getValue());
		return user;
	}

}
