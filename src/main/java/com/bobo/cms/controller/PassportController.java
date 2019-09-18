/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: PassportController.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.controller 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月14日 上午11:07:29 
 * @version: V1.0   
 */
package com.bobo.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bobo.cms.domain.User;
import com.bobo.cms.service.UserService;
import com.bobo.cms.util.CMSContant;
import com.bobo.cms.util.CMSRuntimeException;
import com.bobo.cms.vo.UserVO;

/**
 * @ClassName: PassportController
 * @Description: 登录或注册
 * @author: charles
 * @date: 2019年8月14日 上午11:07:29
 */
@RequestMapping("passport")
@Controller
public class PassportController {
	@Resource
	private UserService userService;

	/**
	 * 去注册
	 * 
	 * @Title: reg
	 * @Description: TODO
	 * @return
	 * @return: String
	 */

	@GetMapping(value = "reg")
	public String reg(UserVO userVO) {

		return "passport/reg";

	}

	/**
	 * 
	 * @Title: reg
	 * @Description: 注册
	 * @param user
	 * @return
	 * @return: String
	 */
	@PostMapping(value = "reg")
	public String reg(@Valid UserVO userVO, BindingResult result, RedirectAttributes redirectAttributes, Model model) {

		// 如果有错误消息则回到错误消息
		if (result.hasErrors()) {

			return "passport/reg";

		}
		// 注册成功

		try {
			userService.insertSelective(userVO);
			// 重定向携带参数
			redirectAttributes.addFlashAttribute(userVO);
			// 跳转到登录页面
			return "redirect:login";

		} catch (CMSRuntimeException cms) {
			// 出现自定义异常
			model.addAttribute("error", cms.getMessage());
		} catch (Exception e) {
			model.addAttribute("error", "系统异常,请与管理员联系");
		}
		return "passport/reg";

	}

	/**
	 * 
	 * @Title: login
	 * @Description: 去登录
	 * @return
	 * @return: String
	 */
	@GetMapping(value = "login")
	public String login(UserVO userVO) {

		return "passport/login";
	}

	@PostMapping(value = "login")
	public String login(UserVO userVO,HttpServletRequest request) {
		try {
			//登录成功
			User user = userService.login(userVO);
			
			HttpSession session = request.getSession(true);
			
			//根据角色进入相应的页面
			if(CMSContant.ROLE_GENERAL.equals(user.getRole())) {//普通用户
				session.setAttribute(CMSContant.SESSION_GENERAL, user);
				
				return "redirect:/my";
				
			}
			//存入session
			session.setAttribute(CMSContant.SESSION_ADMIN, user);
			return "redirect:/admin";	//进入管理员		
			
		} catch (CMSRuntimeException e) {
			request.setAttribute("error", e.getMessage());
		}catch (Exception e) {
			request.setAttribute("error","系统异常,请与管理员联系!");
		}
		
		

		return "passport/login";//登录失败,回到登录页面
	}
	
	@GetMapping(value = "logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(null!=session) {
			session.invalidate();
		}
		return "redirect:login";
	}
}
