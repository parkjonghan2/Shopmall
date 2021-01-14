package com.docmall.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.docmall.domain.OrderDetailVOList;
import com.docmall.domain.OrderListVO;
import com.docmall.domain.OrderReadDetailVO;
import com.docmall.domain.OrderVO;
import com.docmall.domain.ProductVO;


public interface OrderService {

	// 주문정보 추가(상품 상세/ 바로구매)
	public void addOrder(HttpServletResponse response, ProductVO vo ,OrderVO order, OrderDetailVOList orderDetailList) throws Exception;
	
	// 주문정보 추가(장바구니)
	public void addOrderCart(OrderVO order, OrderDetailVOList orderDetailList, String mem_id) throws Exception;
	
	// 주문목록
	public List<OrderListVO>  orderList(String mem_id) throws Exception;
	
	// 주문 상세 정보
	public List<OrderReadDetailVO> readOrder(int odr_code) throws Exception;
		
	// 주문자 정보
	public OrderVO getOrder(int odr_code) throws Exception;
}
