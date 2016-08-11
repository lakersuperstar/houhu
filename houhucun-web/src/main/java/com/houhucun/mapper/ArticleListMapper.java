package com.houhucun.mapper;

import java.util.List;

import com.houhucun.domain.ArticleList;
import com.houhucun.domain.ArticleListMobile;
import com.houhucun.domain.ArticleListQueryForMobile;
import com.houhucun.domain.ArticleListQueryVO;

public interface ArticleListMapper {
	
	
	int insert(ArticleList record);
	
	List<ArticleList> selectByParam(ArticleListQueryVO queryVO);
	
	int countArticleListByParam(ArticleListQueryVO queryVO);
	
	ArticleList selectInfo(int id);
	
	int updateByPrimaryKey(ArticleList record);
	
	int updateYnById(ArticleList record);
	
	int del(int id);
	
	List<ArticleListMobile> selectByParamForMobile(ArticleListQueryForMobile queryVO);
}