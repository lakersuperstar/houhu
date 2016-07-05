package com.houhucun.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.houhucun.domain.ImageUpload;
import com.houhucun.util.FileUtils;

@Controller
@RequestMapping("img")
public class ImageController {

	@Value("#{configProperties['imgSavePath']}")
	private String imgSavePath;

	private static Logger LOGGER = LoggerFactory
			.getLogger(ImageController.class);

	@RequestMapping("/show")
	public String index() {
		return "/image/upload";
	}

	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Object upload(@RequestParam("iocn") MultipartFile iocn,
			HttpServletRequest request, Model model, ModelMap modelMap) {
		ImageUpload imageResult = new ImageUpload();
		try {
			InputStream is = iocn.getInputStream();
			String originalName = iocn.getOriginalFilename();
			int indexExt = originalName.lastIndexOf(".");
			String extName = originalName.substring(indexExt);
			String uuidname = UUID.randomUUID().toString();
			String fileName = System.currentTimeMillis() + "" + uuidname
					+ extName;
			FileUtils.saveFileFromInputStream(is, imgSavePath, fileName);
			imageResult.setOriginal(fileName);
			imageResult.setState("SUCCESS");
			imageResult.setTitle(fileName);
			imageResult.setUrl( "assets/uploadimg/" + fileName);
		} catch (IOException e) {
			LOGGER.error("上传图片异常", e);
			imageResult.setState("ERROR");
			return imageResult;
		}
		return imageResult;
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadFirst", method = RequestMethod.POST)
	public Object uploadFirst(@RequestParam("addFirstNameForm") MultipartFile iocn,
			HttpServletRequest request, Model model, ModelMap modelMap) {
		ImageUpload imageResult = new ImageUpload();
		try {
			InputStream is = iocn.getInputStream();
			String originalName = iocn.getOriginalFilename();
			int indexExt = originalName.lastIndexOf(".");
			String extName = originalName.substring(indexExt);
			String uuidname = UUID.randomUUID().toString();
			String fileName = System.currentTimeMillis() + "" + uuidname
					+ extName;
			FileUtils.saveFileFromInputStream(is, imgSavePath, fileName);
			imageResult.setOriginal(fileName);
			imageResult.setState("SUCCESS");
			imageResult.setTitle(fileName);
			imageResult.setUrl( "assets/uploadimg/" + fileName);
		} catch (IOException e) {
			LOGGER.error("上传图片异常", e);
			imageResult.setState("ERROR");
			return false;
		}
		return true;
	}

}
