/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ChannelController.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.controller 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月15日 下午3:32:21 
 * @version: V1.0   
 */
package com.bobo.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bobo.cms.domain.Category;
import com.bobo.cms.domain.Channel;
import com.bobo.cms.service.ChannelService;

/** 
 * @ClassName: ChannelController 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月15日 下午3:32:21  
 */
@RequestMapping("channel")
@Controller
public class ChannelController {

	@Resource
	private ChannelService channelService;
	
	/**
	 * 返回所有的栏目
	 * @Title: selects 
	 * @Description: TODO
	 * @return
	 * @return: List<Channel>
	 */
	@ResponseBody
	@GetMapping(value = "channels")
	public List<Channel> selects(){
		return channelService.selects();
	}
	
	/**
	 * 返回单个栏目下所有的分类
	 * @Title: selects 
	 * @Description: TODO
	 * @param cid
	 * @return
	 * @return: List<Category>
	 */
	@ResponseBody
	@GetMapping(value = "categorys")
	public List<Category> selects(Integer cid){
		return channelService.selectsByCid(cid);
	}
}
