package com.docmall.controller;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.AdminVO;
import com.docmall.dto.AdminDTO;
import com.docmall.service.AdminService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	@Autowired
	private AdminService service;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value = "/main" , method = RequestMethod.GET)
	public String adminMain() {
		return "admin/main";
	}

	//관리자 로그인 Post
	
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	public String loginPOST(AdminDTO dto,RedirectAttributes rttr, HttpSession session) throws Exception {
		
		logger.info("============loginPOST");
		logger.info("=========admindto :" + dto.toString());
		
		AdminVO vo = null;
		vo = service.login(dto);
		String msg ="";
		
		if (vo!=null) {
			session.setAttribute("admin", vo);
			msg = "LOGIN_SUCCESS";
		} else {
			msg = "LOGIN_FAIL";
		}
		rttr.addFlashAttribute("msg",msg);
		//애드플래시 위에 msg두개로 나누고 이렇게하는 방법도있군 .
		return "redirect:/admin/main";
		
		
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes rttr) {
		logger.info("================logout get exceute");
		session.invalidate();
		rttr.addFlashAttribute("msg", "LOGOUT_SUCCESS");
		
		
		return "redirect:/admin/main";
	}
}
