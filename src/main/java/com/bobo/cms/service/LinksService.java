/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: LinksService.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.service 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月22日 上午10:13:25 
 * @version: V1.0   
 */
package com.bobo.cms.service;

import com.bobo.cms.domain.Links;
import com.github.pagehelper.PageInfo;

/** 
 * @ClassName: LinksService 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月22日 上午10:13:25  
 */
public interface LinksService {

	
	/**
	 * 
	 * @Title: insert 
	 * @Description: TODO
	 * @param links
	 * @return: void
	 */
	 boolean  insert (Links links);
	 
	 /**
	  * 
	  * @Title: selects 
	  * @Description: 查询
	  * @return
	  * @return: List<Links>
	  */
	 PageInfo<Links> selects(Integer page,Integer pageSize); 
}
