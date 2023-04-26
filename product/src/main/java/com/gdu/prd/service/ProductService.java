package com.gdu.prd.service;

import org.springframework.ui.Model;

import com.gdu.prd.domain.ProductDTO;

public interface ProductService {
	public void loadProductList(Model model);
	public int addProduct(ProductDTO productDTO);
	public void loadProduct(int prodNo, Model model);
	public int modifyProduct(ProductDTO productDTO);
	public int deleteProduct(int prodNo);
}
