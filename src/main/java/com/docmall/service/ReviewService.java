package com.docmall.service;

import java.util.List;

import com.docmall.domain.ReviewVO;
import com.docmall.util.Criteria;

public interface ReviewService {
	
	//리뷰 총 수
	public int countReview(int pdt_num);
	
	//리뷰 리스트 가져오는거
	public List<ReviewVO> listReview(int pdt_num, Criteria cri);
	
	//리뷰쓰기
	public void writeReview(ReviewVO vo, String mem_id);
	
	//리뷰 수정
	public void modifyReview(ReviewVO vo);
	
	//리뷰삭제
	public void deleteReview(ReviewVO vo);
	

}
