/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: MyController.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.controller 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月15日 上午10:20:59 
 * @version: V1.0   
 */
package com.bobo.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @ClassName: MyController 
 * @Description: 个人中心
 * @author: charles
 * @date: 2019年8月15日 上午10:20:59  
 */
@RequestMapping("my")
@Controller
public class MyController {
	/**
	 * 进入个人中心
	 * @Title: index 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@GetMapping(value = {"","/","index"})
	public String index() {
		
		return "my/index";
		
	}
	
	

}
