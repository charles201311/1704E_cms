/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ChannelService.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.service 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月15日 下午3:29:47 
 * @version: V1.0   
 */
package com.bobo.cms.service;

import java.util.List;

import com.bobo.cms.domain.Category;
import com.bobo.cms.domain.Channel;

/** 
 * @ClassName: ChannelService 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月15日 下午3:29:47  
 */
public interface ChannelService {
	
	/**
	 * 
	 * @Title: selectsByCid 
	 * @Description: 查询栏目下所有分类
	 * @param cid
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectsByCid(Integer cid);

	/**
	 * 
	 * @Title: selects 
	 * @Description: 所有的栏目
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selects();
	
}
