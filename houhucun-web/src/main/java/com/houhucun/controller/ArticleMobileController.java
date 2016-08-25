package com.houhucun.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houhucun.controller.vo.ArticleMobileListVO;
import com.houhucun.controller.vo.ArticleMobileRequestVO;
import com.houhucun.controller.vo.ArticleMobileVO;
import com.houhucun.domain.ArticleListMobile;
import com.houhucun.domain.ArticleListQueryForMobile;
import com.houhucun.domain.ArticleListQueryVO;
import com.houhucun.service.ArticleCountService;
import com.houhucun.service.ArticleListService;

@Controller
@RequestMapping("/m")
public class ArticleMobileController {
	
	
	private static Logger LOGGER = LoggerFactory.getLogger(ArticleMobileController.class);
	@Resource(name = "articleListService")
	private ArticleListService articleListService;
	@Resource(name = "articleCountService")
	private ArticleCountService articleCountService;
	private String httpArticle = "http://houhucun.com/show/article/";
	
	@RequestMapping("list")
	@ResponseBody
	public Object getArticleList(ModelMap map, ArticleMobileRequestVO queryVO) throws UnsupportedEncodingException {
		ArticleMobileListVO amlv = new ArticleMobileListVO();
		try {
			List<ArticleMobileVO> list = new ArrayList<ArticleMobileVO>();
			amlv.setCompleted(false);
			ArticleListQueryVO aqvo = new ArticleListQueryVO();
			aqvo.setTitle(queryVO.getSearch());
			int counts = articleListService.countArticleListByParam(aqvo);
			ArticleListQueryForMobile alqfm = new ArticleListQueryForMobile();
			alqfm.setSize(queryVO.getSize());
			alqfm.setStart(queryVO.getStart());
			if (queryVO.getSearch() != null) {
				alqfm.setTitle(URLDecoder.decode(queryVO.getSearch(), "UTF-8"));
			}
			if (queryVO.getOrder() != null && queryVO.getOrder().equals("PV")) {
				alqfm.setSortParam("1");
			} else {
				alqfm.setSortParam("0");
			}
			List<ArticleListMobile> als = articleListService.getArticleListForMobile(alqfm);
			if (queryVO.getStart() + als.size() >= counts) {
				amlv.setCompleted(true);
			}
			if (als != null) {
				for (ArticleListMobile al : als) {
					ArticleMobileVO amv = new ArticleMobileVO();
					amv.setCount(al.getCountNum());
					amv.setDesc(al.getSummary());
					amv.setHeadImg(al.getFaceImg());
					amv.setLink(httpArticle + al.getId());
					amv.setTitle(al.getTitle());
					list.add(amv);
				}
			}
			amlv.setCode(200);
			amlv.setList(list);
			amlv.setMsg("ok");
		} catch (Exception e) {
			amlv.setCode(400);
			amlv.setMsg("error");
			LOGGER.error("手机端查询列表失败!", e);
		}
		return amlv;
	}
}
