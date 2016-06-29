package com.houhucun.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houhucun.controller.vo.Page;
import com.houhucun.domain.ArticleList;
import com.houhucun.domain.ArticleListQueryVO;
import com.houhucun.service.ArticleListService;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Resource(name = "articleListService")
	private ArticleListService articleListService;

	@RequestMapping("list")
	public String getArticleList(ModelMap map, ArticleListQueryVO queryVO) {
		int counts = articleListService.countArticleListByParam(queryVO);
		Page page = new Page();
		if (queryVO.getPageNo() == 0) {
			page.setPageNo(1);
		} else {
			page.setPageNo(queryVO.getPageNo());
		}
		queryVO.setPageSize(page.getSize());
		queryVO.setNowPage();
		page.setTotalRecord(counts);
		List<ArticleList> productTypes = articleListService
				.getArticleList(queryVO);
		map.put("articles", productTypes);
		map.put("pageBut", page.sizeToString(queryVO.getFunctionName()));
		map.put("articleListQueryVO", queryVO);
		return "/article/articlelist";
	}

	@RequestMapping("/get")
	public String getArticle(ModelMap map, @RequestParam(value = "cid") int cid) {
		return null;
	}

	@RequestMapping("/add")
	@ResponseBody
	public Object add(ArticleList article) {
		return articleListService.addArticle(article);
	}

	@RequestMapping("/edit")
	public String editArticle(ModelMap map) {
		return "/article/edit";
	}
}
