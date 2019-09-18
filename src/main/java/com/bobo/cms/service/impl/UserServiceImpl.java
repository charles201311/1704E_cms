/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: UserServiceImpl.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.service.impl 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月13日 下午3:48:28 
 * @version: V1.0   
 */
package com.bobo.cms.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bobo.cms.dao.UserMapper;
import com.bobo.cms.domain.User;
import com.bobo.cms.service.UserService;
import com.bobo.cms.util.CMSRuntimeException;
import com.bobo.cms.util.Md5Util;
import com.bobo.cms.vo.UserVO;
import com.bobo.common.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/** 
 * @ClassName: UserServiceImpl 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月13日 下午3:48:28  
 */
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;

	/* (non Javadoc) 
	 * @Title: selects
	 * @Description: TODO
	 * @param username
	 * @return 
	 * @see com.bobo.cms.service.UserService#selects(java.lang.String) 
	 */
	@Override
	public  PageInfo<User> selects(Integer page, Integer pageSize,String username) {
		PageHelper.startPage(page, pageSize);
		 List<User> list = userMapper.selects(username);
		 PageInfo<User> info = new PageInfo<>(list);
		
		return info;
	}

	/* (non Javadoc) 
	 * @Title: insertSelective
	 * @Description: TODO
	 * @param record
	 * @return 
	 * @see com.bobo.cms.service.UserService#insertSelective(com.bobo.cms.domain.User) 
	 */
	@Override
	public boolean  insertSelective(UserVO userVO) {
		//校验用户名是否重复
		User user = userMapper.selectByName(userVO.getUsername());
		//用户不为空
		 if(null!=user) 
		 throw new CMSRuntimeException("该用户已存在!");
		
		
		//校验两次密码是否一致
		if(!userVO.getPassword().equals(userVO.getRepassword()))
			 throw new CMSRuntimeException("两次密码不一致!");
		
		try {
			//对密码进行加密处理
			userVO.setPassword(Md5Util.md5Encoding(userVO.getPassword()));
			
			
			userMapper.insertSelective(userVO);
			return  true;
		} catch (Exception e) {
			e.printStackTrace();
			 throw new CMSRuntimeException("注册用户失败!");
		}
		
	}

	/* (non Javadoc) 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.bobo.cms.service.UserService#selectByPrimaryKey(java.lang.Integer) 
	 */
	@Override
	public User selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	/* (non Javadoc) 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: TODO
	 * @param record
	 * @return 
	 * @see com.bobo.cms.service.UserService#updateByPrimaryKeySelective(com.bobo.cms.domain.User) 
	 */
	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(record);
	}

	/* (non Javadoc) 
	 * @Title: login
	 * @Description: TODO
	 * @param userVO
	 * @return 
	 * @see com.bobo.cms.service.UserService#login(com.bobo.cms.vo.UserVO) 
	 */
	@Override
	public User login(UserVO userVO) {
		
		//校验用户名和密码都非空
	   if(!StringUtil.hasText(userVO.getUsername()))
		   throw new CMSRuntimeException("用户名不能为空!");
	   if(!StringUtil.hasText(userVO.getPassword()))
		   throw new CMSRuntimeException("密码不能为空!");
	   //根据用户名查询用户是否存在
	   
	   User user = userMapper.selectByName(userVO.getUsername());
	   if(null==user)
		   throw new CMSRuntimeException("无效用户!");	   
	   if(!Md5Util.md5Encoding(userVO.getPassword()).equals(user.getPassword()))
		   throw new CMSRuntimeException("密码不不正确!!");
	   
		
		return user;
	}

}
