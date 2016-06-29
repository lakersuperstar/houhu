package com.houhucun.service;

import java.util.List;

import com.houhucun.domain.Product;
import com.houhucun.domain.ProductQueryVO;

public interface ProductService {

	boolean addProduct(Product product);

	List<Product> selectByParam(ProductQueryVO queryVO);

	int countProductByParam(ProductQueryVO queryVO);

}
