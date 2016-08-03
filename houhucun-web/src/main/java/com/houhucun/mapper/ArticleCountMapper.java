package com.houhucun.mapper;

import com.houhucun.domain.ArticleCount;

public interface ArticleCountMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleCount record);

    int insertSelective(ArticleCount record);

    ArticleCount selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(ArticleCount record);

    int updateByPrimaryKey(ArticleCount record);
}