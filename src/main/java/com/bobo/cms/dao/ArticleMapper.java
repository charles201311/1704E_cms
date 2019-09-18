package com.bobo.cms.dao;

import java.util.List;
import java.util.Map;

import com.bobo.cms.domain.Article;

public interface ArticleMapper {
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 文章查询
	 * @param article
	 * @return
	 * @return: List<Map>
	 */
	List<Article> selects(Article article);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
}