package com.houhucun.mapper;

import com.houhucun.domain.ContentImg;

public interface ContentImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContentImg record);

    int insertSelective(ContentImg record);

    ContentImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContentImg record);

    int updateByPrimaryKey(ContentImg record);
}