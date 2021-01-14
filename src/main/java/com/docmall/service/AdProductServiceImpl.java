package com.docmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docmall.dao.AdProductDAO;
import com.docmall.domain.CategoryVO;
import com.docmall.domain.ProductVO;
import com.docmall.util.SearchCriteria;
@Service
public class AdProductServiceImpl implements AdProductService {
	
	@Autowired
	AdProductDAO dao;

	@Override
	public List<CategoryVO> mainCGList() throws Exception {
		return dao.mainCGList();
		
	}

	@Override
	public List<CategoryVO> subCGList(String cg_code) throws Exception {
		return dao.subCGList(cg_code);
	}

	@Override
	public void insertProudct(ProductVO vo) throws Exception {
		 dao.insertProduct(vo);
		
	}

	@Override
	public List<ProductVO> searchListProduct(SearchCriteria cri) throws Exception {
		
		return dao.searchListProduct(cri);
	}

	@Override
	public int searchListCount(SearchCriteria cri) throws Exception {
	
		return dao.searchListCount(cri);
	}

	@Override
	public ProductVO readProduct(int pdt_num) {
		
		System.out.println("serviceimpl에서 나온 pdt_num" + pdt_num);
		
		return dao.readProduct(pdt_num);
	}

	@Override
	public void editProduct(ProductVO vo) throws Exception {
	
		dao.editProduct(vo);
	}

	@Override
	public void deleteProduct(int pdt_num) {
		
		dao.deleteProduct(pdt_num);
		
		
	}

	@Override
	public void editChecked(Map<String, Object> map) throws Exception {
		dao.editChecked(map);
	}

	@Override
	public List<ProductVO> stock(ProductVO vo) {
		return dao.stock(vo);
		
	}

	
}