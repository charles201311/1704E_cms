/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: UserService.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.service 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月13日 下午3:46:38 
 * @version: V1.0   
 */
package com.bobo.cms.service;

import com.bobo.cms.domain.User;
import com.bobo.cms.vo.UserVO;
import com.github.pagehelper.PageInfo;

/** 
 * @ClassName: UserService 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月13日 下午3:46:38  
 */
public interface UserService {
	
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 登录
	 * @param userVO
	 * @return
	 * @return: User
	 */
	User login(UserVO userVO);
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 用户列表查询
	 * @param username
	 * @return
	 * @return:  PageInfo<User>
	 */
	
	 PageInfo<User> selects(Integer page, Integer pageSize,String username);
	

     /**
      * 注册
      * @Title: insertSelective 
      * @Description: TODO
      * @param userVO
      * @return
      * @return: boolean
      */
	 boolean insertSelective(UserVO userVO);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

}
