package com.houhucun.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.houhucun.controller.vo.Page;
import com.houhucun.domain.ArticleList;
import com.houhucun.domain.ArticleListQueryVO;
import com.houhucun.service.ArticleListService;

@Controller
@RequestMapping("/show")
public class ArticleShowController {

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
		map.put("lastPage", page.getPageNo() == 1 ? 1 : (page.getPageNo() - 1));
		map.put("nextPage", page.getTotalPage() == 1 ? 1
				: (page.getPageNo() + 1));
		map.put("articleListQueryVO", queryVO);
		return "/show/articlelist";
	}

	@RequestMapping("/article/{cid}")
	public String showArticle(ModelMap map, @PathVariable(value = "cid") int cid) {
		ArticleList al = articleListService.findArticleList(cid);
		map.put("article", al);
		return "/show/article1";
	}

}
