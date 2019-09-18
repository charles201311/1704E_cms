package com.bobo.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bobo.cms.domain.User;

public interface UserMapper {
	/**
	 * 
	 * @Title: selectByName 
	 * @Description: 按照用户名查询返回单个对象
	 * @param username
	 * @return
	 * @return: User
	 */
	User selectByName(String username);
	
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 用户列表查询
	 * @param username
	 * @return
	 * @return: List<User>
	 */
	
	List<User> selects(@Param("username")String username);
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}