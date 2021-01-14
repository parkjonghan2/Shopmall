package com.docmall.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.MemberVO;
import com.docmall.dto.MemberDTO;
import com.docmall.service.MemberService;

import oracle.jdbc.proxy.annotation.Methods;

@Controller 
@RequestMapping("/member/*")
public class MemberController {
	
	
	@Inject
	MemberService service;
	
	@Inject
	private BCryptPasswordEncoder crptPassEnce;
	
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
//회원가입
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String joinGET() {
		logger.info("join , success....");
		
		return "/member/join";
	
	}
	//회원가입 포스트
	@RequestMapping(value = "join" , method = RequestMethod.POST)
	public String joinPOST(MemberVO vo, RedirectAttributes rttr) throws Exception {
		//비밀번호 암호화
		vo.setMem_pw(crptPassEnce.encode(vo.getMem_pw()));
		
		service.join(vo);
		rttr.addFlashAttribute("msg","register_success");
		
		return "redirect:/";
	}
	/* 아이디 중복체크(ajax요청)   /member/checkIdDuplicate  */
	@ResponseBody
	@RequestMapping(value = "checkIdDuplicate", method=RequestMethod.POST)
	public ResponseEntity<String> checkIdDuplicate(@RequestParam("mem_id") String mem_id) throws Exception {
		
		logger.info("=====checkIdDuplicate execute()...");
		ResponseEntity<String> entity = null;
		try {
			int count = service.checkIdDuplicate(mem_id);
			// count 가 0이면 아이디 사용가능, 1d 이면 사용 불가능.

			if(count != 0) {
				// 아이디가 존재해서 사용이 불가능.
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
			} else {
				// 사용가능한 아이디
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST); // 요청이 문제가 있다.
		}
		
		return entity;
	}
	//로그인 겟
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void loginGET() {
		
		logger.info("loginpage ,successs....");
		
	
}// 로그인 포스트
	@RequestMapping(value = "loginPost", method = RequestMethod.POST)
	public String loginPOST(MemberDTO dto, RedirectAttributes rttr , HttpSession session,
							Model model, HttpServletResponse response) throws Exception {
	
		logger.info("loginPost sucesss...");
		
		MemberDTO memDTO = service.login(dto);
		
		if(memDTO != null) {
			logger.info("로그인성공");
			
			session.setAttribute("user", memDTO);
			
		
		
		rttr.addFlashAttribute("msg", "LOGIN_SUCCESS");
		return "redirect:/";
		} else {
			logger.info("로그인실패");
			rttr.addFlashAttribute("msg", "LOGIN_FAIL");
			return "redirect:/member/login";
		}
	}
	//로그아웃
	@RequestMapping(value = "logout" , method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes rttr) {
		
		session.invalidate();
		rttr.addFlashAttribute("msg", "LOGOUT_SUCCESS");
		
		
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value = "checkAuthcode" , method = RequestMethod.POST)
	public ResponseEntity<String> checkAuthcode(@RequestParam("code") String code, HttpSession session){
		
		ResponseEntity<String> entity = null;
		try {
			if(code.equals(session.getAttribute("authcode"))) {
				logger.info("authcode success");
				entity =new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
				
			} else {
				
				logger.info("authcode fail");
				entity =new ResponseEntity<String>("FAIL" , HttpStatus.OK);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
		
	}
	//비밀번호 확인하는 페이지 - 회원정보 수정을 위해서.
	@RequestMapping(value = "checkPw" , method = RequestMethod.GET)
	public void checkPw(@ModelAttribute("url") String url) {
		
	}
	
	
}
