package com.docmall.dao;

import java.util.List;
import java.util.Map;

import com.docmall.domain.ReviewVO;


public interface ReviewDAO {
	
	//상품후기 총개수 가져옴
	public int countReview(int pdt_num);
	
	//상품 리스트 가져오는거 (페이지 포함 ? )
	public List<ReviewVO> listReview(Map<String, Object> map);
	
	public void writeReview(ReviewVO vo , String mem_id);

	//리뷰 수정
	public void modifyReview(ReviewVO vo);
	
	//리뷰삭제
	public void deleteReview(ReviewVO vo);
}
