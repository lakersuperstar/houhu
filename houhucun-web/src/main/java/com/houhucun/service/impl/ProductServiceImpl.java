package com.houhucun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.houhucun.domain.Product;
import com.houhucun.domain.ProductQueryVO;
import com.houhucun.mapper.ProductMapper;
import com.houhucun.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Resource(name = "productMapper")
	private ProductMapper productMapper;

	@Override
	public boolean addProduct(Product product) {
		return productMapper.insert(product) == 1 ? true : false;
	}

	@Override
	public List<Product> selectByParam(ProductQueryVO queryVO) {
		return productMapper.selectByParam(queryVO);
	}

	@Override
	public int countProductByParam(ProductQueryVO queryVO) {
		return productMapper.countProductByParam(queryVO);
	}

}
