/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: test1.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.service.impl 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月23日 下午2:39:57 
 * @version: V1.0   
 */
package com.bobo.cms.service.impl;

import org.junit.Test;

/** 
 * @ClassName: test1 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月23日 下午2:39:57  
 */
public class test1 {
	public static void main(String[] args) {
		String test12 = new test1().test12();
		System.out.println(test12);
	}
	
	public String test12() {
		
		try {
			return "aaadfdf";
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			System.out.println("aaa");
		}
		return "bbbb";
		
	}

}
