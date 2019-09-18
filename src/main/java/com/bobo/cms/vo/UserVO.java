/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: UserVO.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.vo 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月14日 下午2:07:39 
 * @version: V1.0   
 */
package com.bobo.cms.vo;

import org.hibernate.validator.constraints.Length;

import com.bobo.cms.domain.User;

/** 
 * @ClassName: UserVO 
 * @Description: 用户的视图对象
 * @author: charles
 * @date: 2019年8月14日 下午2:07:39  
 */

public class UserVO extends User {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	@Length(min = 6,max = 10,message = "长度必须6-10之间" )
	private  String repassword;//再次输入密码
	/**
	 * @return the repassword
	 */
	public String getRepassword() {
		return repassword;
	}
	/**
	 * @param repassword the repassword to set
	 */
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	

}
