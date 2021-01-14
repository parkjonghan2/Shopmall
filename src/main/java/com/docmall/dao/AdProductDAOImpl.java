package com.docmall.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.docmall.domain.CategoryVO;
import com.docmall.domain.ProductVO;
import com.docmall.util.SearchCriteria;

@Repository
public class AdProductDAOImpl implements AdProductDAO {
	@Autowired
	SqlSession session;
	public final static String NS="com.docmall.mappers.AdProductMapper";

	
	@Override
	public List<CategoryVO> mainCGList() throws Exception {
	
		return session.selectList(NS+".mainCGList");
	}

	@Override
	public List<CategoryVO> subCGList(String cg_code) throws Exception {
		
		return session.selectList(NS+".subCGList" , cg_code);
	}

	@Override
	public void insertProduct(ProductVO vo) throws Exception {
		 session.insert(NS+".insertProduct" , vo);
		
	}

	@Override
	public List<ProductVO> searchListProduct(SearchCriteria cri) throws Exception {
		
		return session.selectList(NS+".searchListProduct", cri);
	}

	@Override
	public int searchListCount(SearchCriteria cri) throws Exception {
	
		return session.selectOne(NS+".searchListCount", cri);
	}

	@Override
	public ProductVO readProduct(int pdt_num) {
		
		return session.selectOne(NS+".readProduct", pdt_num);
	}

	@Override
	public void editProduct(ProductVO vo) throws Exception {
	
		 session.update(NS+".editProduct",vo);
		
	}

	@Override
	public void deleteProduct(int pdt_num) {
		
		System.out.println("===dletePdofsdfsfsdf==");
		session.delete(NS+".deleteProduct",pdt_num);
		
	}

	@Override
	public void editChecked(Map<String, Object> map) throws Exception {
	
		session.update(NS +".editChecked", map);
		System.out.println("map==============" + map);
		
	}

	@Override
	public List<ProductVO> stock(ProductVO vo) {
		return session.selectList(NS+".stock", vo);
		
	}

}
