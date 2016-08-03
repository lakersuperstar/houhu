package com.houhucun.controller;

import java.awt.Dimension;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.houhucun.controller.vo.ImageCodeVO;
import com.houhucun.util.ZxingUtils;

@Controller
@RequestMapping("/code")
public class CodeController {
	
	
	private static Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
	
	@RequestMapping("getIcon")
	public void getImage(HttpServletResponse response, HttpServletRequest request, @RequestParam(value = "cid") int cid) {
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
			// 设置二维码的纠错级别为h
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
			BitMatrix bitMatrix = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, 400, 400, hints);
			ZxingUtils.writeToStream(ZxingUtils.updateBit(bitMatrix, 0), "jpg", os);
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
	}
	
	@RequestMapping("toPrintCode")
	public String toPrintCode(HttpServletResponse response, HttpServletRequest request, ModelMap map, @RequestParam(value = "cid") int cid) {
		map.addAttribute("cid", cid);
		return "/code/printcode";
	}
	
	@RequestMapping("fastsave")
	public void fastsave(ImageCodeVO icvo, HttpServletResponse response, HttpServletRequest request) throws Exception {
		String url = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().indexOf(request.getRequestURI()));
		String requestUrl = url + "/code/getHtml?cid=" + icvo.getCid() + "&title=" + icvo.getTitle() + "&desc=" + icvo.getDesc() + "&radio=" + icvo.getInlineRadioOptions();
		ImageRenderer render = new ImageRenderer();
//		render.setMediaType("print");
		Dimension size = this.getDimension(icvo.getInlineRadioOptions());
		render.setWindowSize(size, false);
		response.reset();
		String fileName = RandomStringUtils.randomAlphanumeric(20) + ".png";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename='" + fileName + "'");
		response.setContentType("image/png");
		response.addHeader("Connection", "keep-alive");
		render.renderURL(requestUrl, response.getOutputStream(), ImageRenderer.Type.PNG);
	}
	
	@RequestMapping("getHtml")
	public Object getHtml(@RequestParam(value = "title") String title, @RequestParam(value = "desc") String desc, @RequestParam(value = "cid") String cid, @RequestParam(value = "radio") String radio,
			ModelMap map, HttpServletResponse response, HttpServletRequest request) {
		map.put("title", title);
		map.put("desc", desc);
		map.put("cid", cid);
		return "code/" + radio;
	}
	
	private Dimension getDimension(int radio) {
		if (radio == 1) { return new Dimension(320, 400); }
		if (radio == 2) { return new Dimension(400, 320); }
		return new Dimension(500, 500);
	}
}
