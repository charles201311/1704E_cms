/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: LinksMapper.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.dao 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月22日 上午10:10:23 
 * @version: V1.0   
 */
package com.bobo.cms.dao;

import java.util.List;

import com.bobo.cms.domain.Links;

/** 
 * @ClassName: LinksMapper 
 * @Description: 友情链接
 * @author: charles
 * @date: 2019年8月22日 上午10:10:23  
 */
public interface LinksMapper {
	
	/**
	 * 
	 * @Title: insert 
	 * @Description: TODO
	 * @param links
	 * @return: void
	 */
	 int  insert (Links links);
	 
	 /**
	  * 
	  * @Title: selects 
	  * @Description: 查询
	  * @return
	  * @return: List<Links>
	  */
	List<Links> selects(); 
	

}
