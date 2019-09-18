/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: AdminController.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.controller 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月13日 下午4:55:48 
 * @version: V1.0   
 */
package com.bobo.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @ClassName: AdminController 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月13日 下午4:55:48  
 */
@RequestMapping("admin")
@Controller
public class AdminController {
	
	@RequestMapping(value = {"","/","index"})
	public String index() {
		
		return "admin/index";
		
	}

}
