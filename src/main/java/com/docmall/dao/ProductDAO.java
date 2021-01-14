package com.docmall.dao;

import java.util.List;
import java.util.Map;


import com.docmall.domain.CategoryVO;
import com.docmall.domain.ProductVO;

public interface ProductDAO {
	
	public List<CategoryVO> mainCGList() throws Exception;
	
	//2차 카테고리 가져오는거
	public List<CategoryVO> subCGList(String cg_code) throws Exception;
	
	public int getAmount(int pdt_num);
	

	public String getCGName(String cg_code) throws Exception;
	
	// 해당 카테고리에 해당하는 상품리스트(페이지에 맞춰서)
	public List<ProductVO> productListCG(Map map) throws Exception;
	
	// 해당 카테고리의 상품 개수
	public int productCount(String cg_code) throws Exception;
	
	public ProductVO readProduct(int pdt_num) throws Exception;

}
