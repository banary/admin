package com.banary.admin.controller;

import com.banary.admin.entity.User;
import com.banary.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 获取角色列表
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getUserList() {
		List<User> userList = userService.loadAllUsers();
		return new ModelAndView("user-list", "userList", userList);
	}
	/**
	 * 跳转到添加角色页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView addSystemUser(Model model){
		model.addAttribute(new User());
		return new ModelAndView("user-add");
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/addSingleUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addSingleUser(@ModelAttribute(value="user")User user){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			userService.save(user);
			//TODO 可以封装成工具类
			result.put("flag", true);
			result.put("msg", "保存成功");
		}catch (Exception e){
			result.put("flag", false);
			result.put("msg", "系统错误，请联系管理员！");
		}
		return result;
	}

}
