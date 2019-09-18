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
package com.bobo.cms.dao;

import java.util.List;
import java.util.Map;

import com.bobo.cms.domain.Vote;

/** 
 * @ClassName: VoteMapper 
 * @Description: TODO
 * @author: charles
 * @date: 2019年8月26日 下午12:49:59  
 */
public interface VoteMapper {
	//根据文章ID查询投票结果. 
	List<Map> select(Integer articleId );
	//投票
	int insert(Vote vote);
	
	

}
