/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CookieUtil.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.controller 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月26日 下午6:24:59 
 * @version: V1.0   
 */
package com.bobo.cms.util;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName : CookieUtil
 * @Description : TODO
 * @author : charles
 * @date : 2019年8月26日 下午6:24:59
 */
public class CookieUtil {
/**
 * 获取cookie
 * @Title: getCookie 
 * @Description: TODO
 * @param request
 * @param cookieName
 * @return
 * @return: Cookie
 */
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {

		Cookie cookie = null;
		// 获取所有的cookie对象
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie ck : cookies) {
				String name = ck.getName();
				if (cookieName.equals(name)) {
					cookie = ck;
					break;
				}
			}
		}

		if (cookie == null) {
			cookie = new Cookie(cookieName, null);
		}

		return cookie;
	}
	
	//检查是否存在该cookie
	public static boolean isCheck(Cookie cookie,String newValue,HttpServletResponse response) {
		if (cookie.getValue() != null) {
			String[] split = cookie.getValue().split("-");
			if (split != null && split.length > 0) {
				List<String> asList = Arrays.asList(split);
				if (asList.contains(newValue )) {
					System.out.println("已经投票过了");
					return false;
				} else {
					//设置新的cookie值
					cookie.setValue(newValue);
					//回写到浏览器  
					response.addCookie(cookie);
					
					
					System.out.println("正常投票");
					return true;
				}
			}
		}else {
			//设置新的cookie值
			cookie.setValue(newValue);
			//回写到浏览器  
			response.addCookie(cookie);
		}
		return true;
	}
}