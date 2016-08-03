package com.houhucun.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.houhucun.domain.ArticleCount;
import com.houhucun.mapper.ArticleCountMapper;
import com.houhucun.service.ArticleCountService;

@Service("articleCountService")
public class ArticleCountServiceImpl implements ArticleCountService {
	
	
	private static Logger LOGGER = LoggerFactory.getLogger(ArticleCountServiceImpl.class);
	@Resource(name = "articleCountMapper")
	private ArticleCountMapper articleCountMapper;
	@Resource(name = "threadPoolTaskExecutor")
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	@Override
	public void insertAndUpdate(int cid) {
		try {
			ArticleCount ac = new ArticleCount();
			ac.setArticleId(cid);
			ac.setCountNum(1l);
			threadPoolTaskExecutor.execute(new ExecuteInsert(ac, articleCountMapper));
		} catch (Exception e) {
			LOGGER.error("执行统计报错!id=" + cid, e);
		}
	}
}

class ExecuteInsert implements Runnable {
	
	
	ArticleCount ac;
	ArticleCountMapper articleCountMapper;
	
	public ExecuteInsert(ArticleCount ac, ArticleCountMapper articleCountMapper) {
		this.ac = ac;
		this.articleCountMapper = articleCountMapper;
	}
	
	public void run() {
		articleCountMapper.insertAndUpdate(ac);
	}
}
