package com.docmall.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;


import com.docmall.dao.ProductDAO;
import com.docmall.domain.CategoryVO;
import com.docmall.domain.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductDAO dao;
	 
	@Override
	public List<CategoryVO> mainCGList() throws Exception {
		
		return dao.mainCGList();
	}

	@Override
	public List<CategoryVO> subCGList(String cg_code) throws Exception {
		
		return dao.subCGList(cg_code);
	}

	@Override
	public String getCGName(String cg_code) throws Exception {
		return dao.getCGName(cg_code);
	}
	
	// 해당 카테고리에 해당하는 상품리스트(페이지에 맞춰서)
	@Override
	public List<ProductVO> productListCG(Map map) throws Exception {
		return dao.productListCG(map);
	}

	// 카테고리에 해당하는 상품 개수
	@Override
	public int productCount(String cg_code) throws Exception {
		return dao.productCount(cg_code);
	}

	@Override
	public ProductVO readProduct(int pdt_num) throws Exception {
	
		return dao.readProduct(pdt_num);
	}
	

}
