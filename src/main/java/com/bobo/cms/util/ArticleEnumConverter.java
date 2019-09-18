/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleEnumConverter.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.util 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月29日 上午9:28:59 
 * @version: V1.0   
 */
package com.bobo.cms.util;

import org.springframework.core.convert.converter.Converter;

/** 
 * @ClassName: ArticleEnumConverter 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月29日 上午9:28:59  
 */
public class ArticleEnumConverter implements Converter<Integer, ArticleEnum> {

	
	@Override
	public ArticleEnum convert(Integer code) {
		return 	ArticleEnum.get(code);
	}

}
