/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleService.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.service 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月15日 下午3:54:11 
 * @version: V1.0   
 */
package com.bobo.cms.service;

import java.util.List;
import java.util.Map;

import com.bobo.cms.domain.Article;
import com.github.pagehelper.PageInfo;

/** 
 * @ClassName: ArticleService 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月15日 下午3:54:11  
 */
public interface ArticleService {
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 文章查询
	 * @param article
	 * @return
	 * @return: 	PageInfo<Map>
	 */
	PageInfo<Article> selects(Article article,Integer page,Integer pageSize);
	/**
	 * 
	 * @Title: insertSelective 
	 * @Description: 发布文章
	 * @param record
	 * @return
	 * @return: int
	 */
	   int insertSelective(Article record);
	   
	   Article selectByPrimaryKey(Integer id);
	   /**
	    * 更新
	    * @Title: updateByPrimaryKeySelective 
	    * @Description: TODO
	    * @param record
	    * @return
	    * @return: int
	    */
	   int updateByPrimaryKeySelective(Article record);
}
