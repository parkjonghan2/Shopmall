package com.docmall.dao;

import java.util.List;
import java.util.Map;

import com.docmall.domain.CategoryVO;
import com.docmall.domain.ProductVO;
import com.docmall.util.SearchCriteria;

public interface AdProductDAO {
	
	//1차 카테고리
	public List<CategoryVO> mainCGList() throws Exception;

	//1차 카테고리에 따른 2차 카테고리
	public List<CategoryVO> subCGList(String cg_code) throws Exception;
	
	//상품등록 
	public void insertProduct(ProductVO vo) throws Exception;
	
	//상품리스트
	public List<ProductVO> searchListProduct(SearchCriteria cri) throws Exception;
	
	//검색 조건에 맞는 상품개수
	public int searchListCount(SearchCriteria cri) throws Exception;
	
	//상품 정보
	public ProductVO readProduct(int pdt_num);
	
	//상품 수정
	public void editProduct(ProductVO vo) throws Exception;
	
	//상품 삭제
	public void deleteProduct(int pdt_num);
	
	//선택된 상품 수정
	public void editChecked(Map<String, Object> map) throws Exception;
	
	public List<ProductVO> stock(ProductVO vo);
}
