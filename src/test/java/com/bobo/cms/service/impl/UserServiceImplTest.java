/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: UserServiceImplTest.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.service.impl 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月13日 下午3:49:36 
 * @version: V1.0   
 */
package com.bobo.cms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.bobo.cms.domain.User;
import com.bobo.cms.service.UserService;

/** 
 * @ClassName: UserServiceImplTest 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月13日 下午3:49:36  
 */

public class UserServiceImplTest extends JunitParent{

	@Resource
	UserService userService;
	
	@Test//测试查询用户
	public void testSelects() {
		
		//List<User> list = userService.selects(null);
		//System.out.println(list);
	}

	@Test//测试增加用户
	public void testInsertSelective() {
		User user =new User();
		user.setUsername("jack");
		user.setGender(1);
		user.setUpdateTime(new Date());
		user.setRole("0");
		//userService.insertSelective(user);
		
	}

	/**
	 * Test method for {@link com.bobo.cms.service.impl.UserServiceImpl#selectByPrimaryKey(java.lang.Integer)}.
	 */
	@Test
	public void testSelectByPrimaryKey() {
	}

	/**
	 * Test method for {@link com.bobo.cms.service.impl.UserServiceImpl#updateByPrimaryKeySelective(com.bobo.cms.domain.User)}.
	 */
	@Test
	public void testUpdateByPrimaryKeySelective() {
	}

}
