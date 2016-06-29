package com.houhucun.mapper;

import java.util.List;

import com.houhucun.domain.Product;
import com.houhucun.domain.ProductQueryVO;

public interface ProductMapper {
	int deleteByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Product record);

	int updateByPrimaryKey(Product record);

	int insert(Product product);

	List<Product> selectByParam(ProductQueryVO queryVO);

	int countProductByParam(ProductQueryVO queryVO);

}