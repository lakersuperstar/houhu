package com.houhucun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.houhucun.domain.ProductType;
import com.houhucun.domain.ProductTypeQueryVO;
import com.houhucun.mapper.ProductTypeMapper;
import com.houhucun.service.ProductTypeService;

@Service("productTypeService")
public class ProductTypeServiceImpl implements ProductTypeService {

	@Resource(name = "productTypeMapper")
	private ProductTypeMapper productTypeMapper;

	@Override
	public boolean addProductType(ProductType productType) {
		return productTypeMapper.insert(productType) == 1 ? true : false;
	}

	@Override
	public List<ProductType> selectByParam(ProductTypeQueryVO queryVO) {
		return productTypeMapper.selectByParam(queryVO);
	}

	@Override
	public int countUserByParam(ProductTypeQueryVO queryVO) {
		return productTypeMapper.countUserByParam(queryVO);
	}

	@Override
	public List<ProductType> selectAll() {
		return productTypeMapper.selectAll();
	}

}
