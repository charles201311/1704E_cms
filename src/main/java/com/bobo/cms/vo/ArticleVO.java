/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleVO.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.vo 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月23日 上午11:13:59 
 * @version: V1.0   
 */
package com.bobo.cms.vo;

import com.bobo.cms.domain.Article;

/** 
 * @ClassName: ArticleVO 
 * @Description: 发布图片集
 * @author: charles
 * @date: 2019年8月23日 上午11:13:59  
 */
public class ArticleVO extends Article {

	
	private String descr;//图片描述
	private String url;//图片地址
	/**
	 * @return the descr
	 */
	public String getDescr() {
		return descr;
	}
	/**
	 * @param descr the descr to set
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
	
	
}
