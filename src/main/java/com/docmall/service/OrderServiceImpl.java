package com.docmall.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.docmall.dao.OrderDAO;
import com.docmall.dao.ProductDAO;
import com.docmall.domain.OrderDetailVO;
import com.docmall.domain.OrderDetailVOList;
import com.docmall.domain.OrderListVO;
import com.docmall.domain.OrderReadDetailVO;
import com.docmall.domain.OrderVO;
import com.docmall.domain.ProductVO;



@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO dao;
	
	@Autowired
	private ProductDAO dbo;
	
	@Autowired
	private CartService cartService;

	/* 
	 * 상품구매(상품상세/ 바로구매)
	 * 주문내역과 주문정보 추가 
	 */

    // 사용자 1명이 여러건의 상품을 주문했을 때 
	// 메서드에서 2가지 이상의 기능이 사용될때. insert, update, delete 경우에 해당.
	@Transactional  
	@Override
	public void addOrder(HttpServletResponse response,ProductVO vo ,OrderVO order, OrderDetailVOList orderDetailList) throws Exception {
		
		PrintWriter out = response.getWriter();
		// 시퀀스(주문 코드) 가져오기
		int odr_code = dao.readOrderCode(); //45 
		order.setOdr_code(odr_code);
		dao.addOrder(order);  // 주문테이블에 데이타 삽입하기
		
		// 주문 정보 추가
	
		
		// 주문 내역 추가.  주문상세테이블에 데이타 삽입하기.
		List<OrderDetailVO> list = orderDetailList.getOrderDetailList();
		for(int i=0; i<list.size(); i++) {

				OrderDetailVO orderDetail = list.get(i);
				orderDetail.setOdr_code(odr_code); //18 
				
				int pdt_num = orderDetail.getPdt_num();
				int pdt_amount = dbo.getAmount(pdt_num);
				dao.addOrderDetail(orderDetail);
				
				int sibal = dao.sibal(odr_code);
				//사려는 수량이 잇는 수량보다 크거나 같을경우 .
				if(sibal>= pdt_amount) {
					dao.deleteOrderDetail(orderDetail);	
					
					System.out.println("절대안돼지~");
					 out.println("<script>alert('재고가없어염'); history.go(-1);</script>");

					 out.flush(); 
				
					
				} else {
					System.out.println("잘 사셨음");
				}
				
			
			
		}
		
		
	}

	/* 
	 * 상품구매(장바구니)
	 * 주문내역과 주문정보 추가 
	 * 장바구니에서 넘어온 경우, 장바구니 테이블에서 해당 상품들 삭제
	 */
	@Transactional
	@Override
	public void addOrderCart(OrderVO order, OrderDetailVOList orderDetailList, String mem_id) throws Exception {
		
		// 시퀀스(주문 코드) 가져오기
		int odr_code = dao.readOrderCode();
		
		// 주문 정보 추가
		order.setOdr_code(odr_code);
		dao.addOrder(order);
		
		// 주문 내역 추가
		List<OrderDetailVO> list = orderDetailList.getOrderDetailList();
		for(int i=0; i<list.size(); i++) {
			
			OrderDetailVO orderDetail = list.get(i);
			orderDetail.setOdr_code(odr_code);
			dao.addOrderDetail(orderDetail);
			
			// 장바구니 테이블에서 해당 상품들 삭제
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mem_id", mem_id);
			map.put("pdt_num", orderDetail.getPdt_num());
			cartService.deleteCartOrder(map);
			
		}
		
	}
	
	// 주문 목록
	@Override
	public List<OrderListVO> orderList(String mem_id) throws Exception {
		return dao.orderList(mem_id);
	}

	// 주문 상세 정보
	@Override
	public List<OrderReadDetailVO> readOrder(int odr_code) throws Exception {
		return dao.readOrder(odr_code);
	}

	// 주문자 정보
	@Override
	public OrderVO getOrder(int odr_code) throws Exception {
		return dao.getOrder(odr_code);
	}
	
}
