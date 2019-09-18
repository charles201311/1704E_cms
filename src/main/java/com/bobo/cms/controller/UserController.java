/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: UserController.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.controller 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月13日 下午6:53:39 
 * @version: V1.0   
 */
package com.bobo.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bobo.cms.domain.User;
import com.bobo.cms.service.UserService;
import com.bobo.cms.util.PageUtil;
import com.github.pagehelper.PageInfo;

/** 
 * @ClassName: UserController 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月13日 下午6:53:39  
 */
@RequestMapping("user")
@Controller
public class UserController {
	@Resource
	private UserService userService;
	
/**
 * 
 * @Title: selects 
 * @Description: 用户列表
 * @param username
 * @param model
 * @return
 * @return: String
 */
	@RequestMapping("selects")
	public String selects(@RequestParam(defaultValue = "1")Integer  page,@RequestParam(defaultValue = "3") Integer pageSize,  @RequestParam(defaultValue = "")String username,Model model) {
		
		 PageInfo<User> info = userService.selects(page, pageSize, username);
		 
		 //调用分页工具类
		 String pages = PageUtil.page(page, info.getPages(), "/user/selects?username="+username, pageSize);
		 
		model.addAttribute("users", info.getList());
		model.addAttribute("username", username);
		model.addAttribute("pages", pages);
		return "admin/users";
		
	}

/**
 * 
 * @Title: update 
 * @Description: TODO
 * @param user
 * @return
 * @return: boolean
 */
	@ResponseBody
//	@RequestMapping(value = "update",method = RequestMethod.POST)
	@PostMapping("update")
	public boolean update (User user) {
		 return userService.updateByPrimaryKeySelective(user)>0;
		
	}
}
