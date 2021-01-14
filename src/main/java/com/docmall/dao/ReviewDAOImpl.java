package com.docmall.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.docmall.domain.ReviewVO;



@Repository
public class ReviewDAOImpl implements ReviewDAO {

	@Autowired
	SqlSession session;
	
	public final static String NS="com.docmall.mappers.ReviewMapper";
	
	@Override
	public int countReview(int pdt_num) {
	
		return session.selectOne(NS+".countReview", pdt_num);
	}

	@Override
	public List<ReviewVO> listReview(Map<String, Object> map) {
	
		System.out.println("map==========" + map);
		return session.selectList(NS+".listReview", map);
	}

	@Override
	public void writeReview(ReviewVO vo, String mem_id) {
		
		session.insert(NS+".writeReview", vo);
		
	}

	@Override
	public void modifyReview(ReviewVO vo) {
		session.update(NS+".modifyReview", vo);
		
	}

	@Override
	public void deleteReview(ReviewVO vo) {
		session.delete(NS+".deleteReview", vo);
		
		
	}

}
