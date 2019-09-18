/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ChannelService.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.service.impl 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月15日 下午3:30:24 
 * @version: V1.0   
 */
package com.bobo.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bobo.cms.dao.CategoryMapper;
import com.bobo.cms.dao.ChannelMapper;
import com.bobo.cms.domain.Category;
import com.bobo.cms.domain.Channel;
import com.bobo.cms.service.ChannelService;

/** 
 * @ClassName: ChannelService 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月15日 下午3:30:24  
 */
@Service
public class ChannelServiceImpl implements ChannelService {

	@Resource
	private ChannelMapper channelMapper;
	
	@Resource
	private CategoryMapper categoryMapper;
	
	
	/* (non Javadoc) 
	 * @Title: selectsByCid
	 * @Description: TODO
	 * @param cid
	 * @return 
	 * @see com.bobo.cms.service.ChannelService#selectsByCid(java.lang.Integer) 
	 */
	@Override
	public List<Category> selectsByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryMapper.selectsByCid(cid);
	}

	/* (non Javadoc) 
	 * @Title: selects
	 * @Description: TODO
	 * @return 
	 * @see com.bobo.cms.service.ChannelService#selects() 
	 */
	@Override
	public List<Channel> selects() {
		// TODO Auto-generated method stub
		return channelMapper.selects();
	}

}
