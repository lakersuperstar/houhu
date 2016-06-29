package com.houhucun.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houhucun.controller.vo.Page;
import com.houhucun.domain.Product;
import com.houhucun.domain.ProductQueryVO;
import com.houhucun.service.ProductService;

@Controller
@RequestMapping("/productController")
public class ProductController {

	@Resource(name = "productService")
	private ProductService productService;

	@RequestMapping("list")
	public String getProduct(ModelMap map, ProductQueryVO queryVO) {
		int counts = productService.countProductByParam(queryVO);
		Page page = new Page();
		if (queryVO.getPageNo() == 0) {
			page.setPageNo(1);
		} else {
			page.setPageNo(queryVO.getPageNo());
		}
		queryVO.setPageSize(page.getSize());
		queryVO.setNowPage();
		page.setTotalRecord(counts);
		List<Product> products = productService
				.selectByParam(queryVO);
		map.put("products", products);
		map.put("pageBut", page.sizeToString(queryVO.getFunctionName()));
		map.put("productQueryVO", queryVO);
		return "/product/productlist";
	}

	@RequestMapping("addProduct")
	@ResponseBody
	public Object add(Product product) {
		return productService.addProduct(product);
	}

}
