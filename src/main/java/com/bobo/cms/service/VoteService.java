/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: VoteMapper.java 
 * @Prject: bobo-cms
 * @Package: com.bobo.cms.dao 
 * @Description: TODO
 * @author: charles   
 * @date: 2019年8月26日 下午12:49:59 
 * @version: V1.0   
 */
package com.bobo.cms.service;

import java.util.List;
import java.util.Map;

import com.bobo.cms.domain.Vote;

/** 
 * @ClassName: VoteService 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月26日 下午12:49:59  
 */
public interface VoteService {
	//查询
	 List<Map> select(Integer articleId);
	//投票
	int insert(Vote vote);

}
