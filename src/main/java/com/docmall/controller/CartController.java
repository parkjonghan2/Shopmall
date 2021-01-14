package com.docmall.controller;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.docmall.domain.CartVO;
import com.docmall.dto.MemberDTO;
import com.docmall.service.CartService;

@Controller
@RequestMapping(value = "/cart/*")
public class CartController {
	
	@Inject
	private CartService service;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(CartController.class); 
	
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ResponseEntity<String> addCart(int pdt_num, HttpSession session) {
		
		logger.info("=====add execute ...");
		logger.info("pdt_num" + pdt_num);
		
		ResponseEntity<String> entity = null;
		CartVO vo = new CartVO();
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		vo.setMem_id(dto.getMem_id());
		vo.setPdt_num(pdt_num);
		vo.setCart_amount(1);
		logger.info("vo======" + vo.toString());
		
		try {
			service.addCart(vo);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity =new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		
		
		return entity;
	}
	
	//장바구니 목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listCartGet(Model model, HttpSession session) throws Exception {
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		model.addAttribute("cartProductList", service.getCart(dto.getMem_id()));
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	
	public String deleteCart(int cart_code) throws Exception {
		logger.info("delete execetue");
		
		service.deleteCart(cart_code);
		return "/cart/list/";
		
		
	}
	
	
	

}
