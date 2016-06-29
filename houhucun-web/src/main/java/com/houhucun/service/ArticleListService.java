package com.houhucun.service;

import java.util.List;

import com.houhucun.domain.ArticleList;
import com.houhucun.domain.ArticleListQueryVO;

public interface ArticleListService {

	boolean addArticle(ArticleList articleList);

	List<ArticleList> getArticleList(ArticleListQueryVO queryVO);

	int countArticleListByParam(ArticleListQueryVO queryVO);

	ArticleList findArticleList(int id);

	boolean updateArticleList(ArticleList articleList);
	
	boolean delArticleById(int id);
}
