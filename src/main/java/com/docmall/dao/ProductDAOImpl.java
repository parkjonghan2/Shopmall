package com.docmall.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


import com.docmall.domain.CategoryVO;
import com.docmall.domain.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Inject
	private SqlSession session;
	public final static String NS = "com.docmall.mappers.ProductMapper";
	
	
	@Override
	public List<CategoryVO> mainCGList() throws Exception {
		
		return session.selectList(NS+".mainCGList");
	}


	@Override
	public List<CategoryVO> subCGList(String cg_code) throws Exception {
		
		return session.selectList(NS+".subCGList", cg_code);
	}
	@Override
	public String getCGName(String cg_code) throws Exception {
		return session.selectOne(NS+ ".getCGName", cg_code);
	}
	
	// 해당 카테고리에 해당하는 상품리스트(페이지에 맞춰서)
	@Override
	public List<ProductVO> productListCG(Map map) throws Exception {
		return session.selectList(NS+ ".productListCG", map);
	}

	// 카테고리에 해당하는 상품개수
	@Override
	public int productCount(String cg_code) throws Exception {
		return session.selectOne(NS+".productCount", cg_code);
	}

	@Override
	public ProductVO readProduct(int pdt_num) throws Exception {
		return session.selectOne(NS+ ".readProduct", pdt_num);
	}


	@Override
	public int getAmount(int pdt_num) {
		
		return session.selectOne(NS+".getAmount", pdt_num);
	}

	
	

}
