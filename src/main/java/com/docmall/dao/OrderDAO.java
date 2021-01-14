package com.docmall.dao;

import java.util.List;

import com.docmall.domain.OrderDetailVO;
import com.docmall.domain.OrderListVO;
import com.docmall.domain.OrderReadDetailVO;
import com.docmall.domain.OrderVO;



public interface OrderDAO {

	// 주문코드 시퀀스 가져오기
	public int readOrderCode() throws Exception;
	
	//
	public int sibal(int odr_code);
	
	public int getPdtnum(int odr_code);
	
	// 주문내역 추가
	public void addOrderDetail(OrderDetailVO vo) throws Exception;
	
	// 주문정보 추가
	public void addOrder(OrderVO vo) throws Exception;
	
	// 주문목록
	public List<OrderListVO>  orderList(String mem_id) throws Exception;
	
	// 주문 상세 정보
	public List<OrderReadDetailVO> readOrder(int odr_code) throws Exception;
	
	// 주문자 정보
	public OrderVO getOrder(int odr_code) throws Exception;
	
	public void deleteOrderDetail(OrderDetailVO vo);
	
}
