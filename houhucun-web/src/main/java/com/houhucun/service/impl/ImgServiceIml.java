package com.houhucun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.houhucun.domain.ContentImg;
import com.houhucun.mapper.ContentImgMapper;
import com.houhucun.service.ImgService;

@Service("imgService")
public class ImgServiceIml implements ImgService {

	@Resource(name = "contentImgMapper")
	private ContentImgMapper contentImgMapper;

	@Override
	public boolean addImg(ContentImg img) {
		return contentImgMapper.insert(img) == 1 ? true : false;
	}

}
