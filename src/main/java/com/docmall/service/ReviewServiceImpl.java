package com.docmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.docmall.dao.ReviewDAO;
import com.docmall.domain.ReviewVO;
import com.docmall.util.Criteria;

@Repository
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDAO dao;
		
	
	@Override
	public int countReview(int pdt_num) {
		
		return dao.countReview(pdt_num);
	}


	@Override
	public List<ReviewVO> listReview(int pdt_num, Criteria cri) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pdt_num", pdt_num);
		map.put("cri", cri);
		
		
		return dao.listReview(map);
	}


	@Override
	public void writeReview(ReviewVO vo, String mem_id) {
		vo.setMem_id(mem_id);
		dao.writeReview(vo, mem_id);
	}


	@Override
	public void modifyReview(ReviewVO vo) {
		dao.modifyReview(vo);
		
	}


	@Override
	public void deleteReview(ReviewVO vo) {
		dao.deleteReview(vo);
		
	}

}
