package com.houhucun.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houhucun.controller.vo.WeixinConfigStatic;
import com.houhucun.controller.vo.WeixinConfigVO;
import com.houhucun.data.JsticketStaticData;
import com.houhucun.data.WeixinStatic;
import com.houhucun.domain.ArticleList;
import com.houhucun.service.ArticleListService;
import com.houhucun.util.HttpclientUtils;
import com.houhucun.util.WeixinUtil;

@Controller
@RequestMapping("/weixinConfig")
public class WeixinConfigController {
	
	
	private static Logger LOGGER = LoggerFactory.getLogger(WeixinConfigController.class);
	@Resource(name = "articleListService")
	private ArticleListService articleListService;
	private String nonceStr = "houhucunsongkunfirst";
	private String httpArticle = "http://houhucun.com/show/article/";
	
	@RequestMapping("/config")
	@ResponseBody
	public Object getConfig(HttpServletRequest request, @RequestParam(value = "id") int id) {
		if (id < 0) { return false; }
		WeixinConfigVO configVO = new WeixinConfigVO();
		try {
			ArticleList al = articleListService.findArticleList(id);
			if (al.getSummary() == null || "".equals(al.getSummary().trim())) {
				configVO.setDesc("点击可查看更多信息！");
			} else {
				configVO.setDesc(al.getSummary());
			}
			configVO.setImgUrl(al.getFaceImg());
			configVO.setLink(httpArticle + id);
			configVO.setTitle(al.getTitle());
			long timestamp = System.currentTimeMillis() / 1000;
			configVO.setTimestamp(timestamp);
			configVO.setAppId(HttpclientUtils.appid);
			configVO.setNonceStr(nonceStr);
			WeixinConfigStatic configStatic = this.getWeixinConfigStatic();
			if (configStatic == null) { return false; }
			try {
				configVO.setSignature(WeixinUtil.getSignature(configStatic.getTicket(), timestamp + "", nonceStr, httpArticle + id + "?from=singlemessage&isappinstalled=0"));
			} catch (IOException e) {
				LOGGER.error("生成签名出错|id|" + id, e);
				return false;
			}
		} catch (Exception e2) {
			LOGGER.error("获取微信配置出错|id|" + id, e2);
			return false;
		}
		return configVO;
	}
	
	private WeixinConfigStatic getWeixinConfigStatic() {
		WeixinConfigStatic resultWCS = WeixinStatic.wcsStatic;
		if (resultWCS.getTicket() == null) { return null; }
		return resultWCS;
	}
}
