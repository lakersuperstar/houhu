package com.houhucun.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houhucun.controller.vo.Page;
import com.houhucun.domain.ProductType;
import com.houhucun.domain.ProductTypeQueryVO;
import com.houhucun.service.ProductTypeService;
import com.houhucun.util.JsonSerializeUtil;

@Controller
@RequestMapping("/dicController")
public class DicController {

	@Resource(name = "productTypeService")
	private ProductTypeService productTypeService;

	@RequestMapping("list")
	public String getProductType(ModelMap map, ProductTypeQueryVO queryVO) {
		int counts = productTypeService.countUserByParam(queryVO);
		Page page = new Page();
		if (queryVO.getPageNo() == 0) {
			page.setPageNo(1);
		} else {
			page.setPageNo(queryVO.getPageNo());
		}
		queryVO.setPageSize(page.getSize());
		queryVO.setNowPage();
		page.setTotalRecord(counts);
		List<ProductType> productTypes = productTypeService
				.selectByParam(queryVO);
		map.put("productTypes", productTypes);
		map.put("pageBut", page.sizeToString(queryVO.getFunctionName()));
		map.put("productTypeQueryVO", queryVO);
		return "/dic/productTypeList";
	}

	@RequestMapping("all")
	@ResponseBody
	public Object getProductAllType() {
		List<ProductType> types = productTypeService.selectAll();
		return types;
	}

	@RequestMapping("del")
	@ResponseBody
	public Object delProductType(@RequestParam(value="id") int id) {
		return productTypeService.del(id);
	}
	
	@RequestMapping("addProductType")
	@ResponseBody
	public Object add(ProductType productType) {
		try{
			return productTypeService.addProductType(productType);
		}catch(Exception e){
			return false;
		}
		
	}

}
