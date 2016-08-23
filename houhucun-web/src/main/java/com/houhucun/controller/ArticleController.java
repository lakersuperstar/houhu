package com.houhucun.controller;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.fit.cssbox.demo.ImageRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.houhucun.controller.vo.ImageCodeVO;
import com.houhucun.controller.vo.Page;
import com.houhucun.domain.ArticleList;
import com.houhucun.domain.ArticleListQueryVO;
import com.houhucun.service.ArticleListService;
import com.houhucun.service.CookieService;
import com.houhucun.util.ZxingUtils;

@Controller
@RequestMapping("/article")
public class ArticleController {

	
	private static Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

	@Resource(name = "articleListService")
	private ArticleListService articleListService;

	@Resource(name = "cookieService")
	private CookieService cookieService;

	@RequestMapping("list")
	public String getArticleList(ModelMap map, ArticleListQueryVO queryVO,
			HttpServletRequest request) {
		String usr = cookieService.getCookieUser(request);
		String role = cookieService.getUserRole(request);
		
		if (Integer.valueOf(role) != 1) {
			queryVO.setCreateAccount(usr);
		}
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
	public Object add(ArticleList article, HttpServletRequest request) {
		String usr = cookieService.getCookieUser(request);
		article.setCreateAccount(usr);
		return articleListService.addArticle(article);
	}

	@RequestMapping("/edit")
	public String editArticle(ModelMap map) {
		return "/article/edit";
	}

	@RequestMapping("preupdate")
	public String preupdate(ModelMap map, @RequestParam(value = "cid") int cid) {
		ArticleList al = articleListService.findArticleList(cid);
		map.put("al", al);
		return "/article/edit";
	}

	@RequestMapping("update")
	@ResponseBody
	public Object update(ArticleList article) {
		boolean flag = articleListService.updateArticleList(article);
		if (flag) {
			return true;
		}
		return false;
	}

	@RequestMapping("updateYn")
	@ResponseBody
	public Object updateYn(ArticleList article) {
		boolean flag = articleListService.updateYnById(article);
		if (flag) {
			return true;
		}
		return false;
	}

}
