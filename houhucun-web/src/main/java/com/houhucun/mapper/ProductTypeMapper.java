package com.houhucun.mapper;

import java.util.List;

import com.houhucun.domain.ProductType;
import com.houhucun.domain.ProductTypeQueryVO;

public interface ProductTypeMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ProductType record);

	int insertSelective(ProductType record);

	List<ProductType> selectByParam(ProductTypeQueryVO queryVO);

	int countUserByParam(ProductTypeQueryVO queryVO);

	int updateByPrimaryKeySelective(ProductType record);

	int updateByPrimaryKey(ProductType record);
	
	List<ProductType> selectAll();
}