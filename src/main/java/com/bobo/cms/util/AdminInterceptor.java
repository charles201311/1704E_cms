/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: AdminInterceptor.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.util 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月21日 上午9:55:49 
 * @version: V1.0   
 */
package com.bobo.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bobo.cms.vo.UserVO;

/** 
 * @ClassName: AdminInterceptor 
 * @Description: 管理员后台拦截器
 * @author: charles
 * @date: 2019年8月21日 上午9:55:49  
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {

	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler)
			throws Exception {
		
		//如果用户没有登录或者用户登录不是管理员账户,则把用户的请求转发到登录页面
		//true:如果没有session就创建.false:没有不创建
		HttpSession session = request.getSession(false);
		if(null==session || null ==session.getAttribute(CMSContant.SESSION_ADMIN)) {
			request.setAttribute("userVO", new UserVO());
			request.setAttribute("error", "没有权限");
	     	request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);
			return false;//拦截
		}
		
		return true;//放行
	}
}
