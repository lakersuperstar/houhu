package com.houhucun.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.houhucun.controller.vo.Page;
import com.houhucun.domain.ArticleList;
import com.houhucun.domain.ArticleListQueryVO;
import com.houhucun.service.ArticleListService;
import com.houhucun.util.ZxingUtils;

@Controller
@RequestMapping("/article")
public class ArticleController {

	private static Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

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
		List<ArticleList> productTypes = articleListService.getArticleList(queryVO);
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

	@RequestMapping("getIcon")
	public void getImage(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "cid") int cid) {
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.setContentType("image/png");
			int endIndex = request.getRequestURL().length() - request.getRequestURI().length() + 1;
			String url1 = request.getRequestURL().substring(0, endIndex);
			String url = url1 + "show/article/" + cid;
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			Map hints = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, 400, 400, hints);
			ZxingUtils.writeToStream(bitMatrix, "jpg", os);
			os.flush();
		} catch (Exception e) {
			LOGGER.error("返回二维码图片流异常，e:", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					LOGGER.error("关闭二维码图片流异常，e:", e);
				}
			}

		}
		// return "/article/twocodeimage";
	}

}
