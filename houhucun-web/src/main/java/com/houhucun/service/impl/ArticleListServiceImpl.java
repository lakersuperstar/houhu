package com.houhucun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.houhucun.domain.ArticleList;
import com.houhucun.domain.ArticleListQueryVO;
import com.houhucun.mapper.ArticleListMapper;
import com.houhucun.service.ArticleListService;

@Service("articleListService")
public class ArticleListServiceImpl implements ArticleListService {

	@Resource(name = "articleListMapper")
	private ArticleListMapper articleListMapper;

	@Override
	public boolean addArticle(ArticleList articleList) {
		return articleListMapper.insert(articleList) == 1 ? true : false;
	}

	@Override
	public List<ArticleList> getArticleList(ArticleListQueryVO queryVO) {
		return articleListMapper.selectByParam(queryVO);
	}

	@Override
	public int countArticleListByParam(ArticleListQueryVO queryVO) {
		return articleListMapper.countArticleListByParam(queryVO);
	}

	@Override
	public ArticleList findArticleList(int id) {
		return articleListMapper.selectInfo(id);
	}

	@Override
	public boolean updateArticleList(ArticleList articleList) {
		return articleListMapper.updateByPrimaryKey(articleList) == 1 ? true
				: false;
	}

	@Override
	public boolean delArticleById(int id) {
		return articleListMapper.del(id) == 1 ? true : false;
	}

	@Override
	public boolean updateYnById(ArticleList articleList) {
		return articleListMapper.updateYnById(articleList) == 1 ? true : false;
	}

}
