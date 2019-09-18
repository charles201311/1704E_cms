/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleEnum.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.util 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月23日 下午3:26:50 
 * @version: V1.0   
 */
package com.bobo.cms.util;

/**
 * @ClassName: ArticleEnum
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月23日 下午3:26:50
 */
public enum ArticleEnum {

	// 文章类型
	HTML(1, "HTML"), IMAGE(2, "IMAGE"), JSON(3, "json");

	private Integer code;
	private String name;

	ArticleEnum(int code, String name) {
		this.code=code;
		this.name=name;

	}

	ArticleEnum(int code) {
		this.code=code;
	}
	ArticleEnum() {
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	public static  ArticleEnum  get(Integer code) {
		for (ArticleEnum a : ArticleEnum.values()) {
			if(a.getCode() ==code)
				return a;
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(ArticleEnum.HTML.getCode());
		System.out.println(ArticleEnum.HTML.getName());
	}

}
