/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: JunitParent.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.service 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月13日 下午3:50:58 
 * @version: V1.0   
 */
package com.bobo.cms.service.impl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
 * @ClassName: JunitParent 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月13日 下午3:50:58  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class JunitParent {

}
