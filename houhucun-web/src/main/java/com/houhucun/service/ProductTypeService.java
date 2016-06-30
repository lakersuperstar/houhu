package com.houhucun.service;

import java.util.List;

import com.houhucun.domain.ProductType;
import com.houhucun.domain.ProductTypeQueryVO;

public interface ProductTypeService {

	boolean addProductType(ProductType productType);

	List<ProductType> selectByParam(ProductTypeQueryVO queryVO);

	int countUserByParam(ProductTypeQueryVO queryVO);
	
	List<ProductType> selectAll();
	
	boolean del(int id);

}
