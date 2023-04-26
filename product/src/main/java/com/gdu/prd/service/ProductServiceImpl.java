package com.gdu.prd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.prd.domain.ProductDTO;
import com.gdu.prd.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public void loadProductList(Model model) {
		model.addAttribute("productList", productMapper.getProductList());
		model.addAttribute("productCount", productMapper.getProductCount());
	}
	
	@Override
	public int addProduct(ProductDTO productDTO) {
		int addResult = productMapper.addProduct(productDTO);
		return addResult;
	}
	
	@Override
	public void loadProduct(int prodNo, Model model) {
		ProductDTO productDTO = productMapper.getProductByNo(prodNo);
		model.addAttribute("productDTO", productDTO);
	}
	
	@Override
	public int modifyProduct(ProductDTO productDTO) {
		int modifyResult = productMapper.modifyProduct(productDTO);
		return modifyResult;
	}
	
	@Override
	public int deleteProduct(int prodNo) {
		int deleteResult = productMapper.deleteProduct(prodNo);
		return deleteResult;
	}
	
}
