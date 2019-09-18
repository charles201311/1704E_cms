package com.bobo.cms.dao;

import java.util.List;

import com.bobo.cms.domain.Category;

public interface CategoryMapper {
	/**
	 * 
	 * @Title: selectsByCid 
	 * @Description: 查询栏目下所有分类
	 * @param cid
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectsByCid(Integer cid);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}