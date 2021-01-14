package com.docmall.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.dto.EmailDTO;
import com.docmall.service.EmailService;

@Controller
@RequestMapping("/email/*")
public class EmailController {
	@Inject
	private EmailService emailService;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	@ResponseBody
	@RequestMapping("send")
	public ResponseEntity<String> send(@ModelAttribute EmailDTO dto, Model model, HttpSession session) {
		logger.info("=====email send execute..");
		logger.info("emailDTO:" + dto.toString());
			
		
		ResponseEntity<String> entity =null;
		
		//6자리인증코드생성
		String authcode="";
		for(int i=0;i<6; i++) {
			authcode+=String.valueOf((int)(Math.random()*10));
			
		}
		//인증코드를 세션에 저장
		session.setAttribute("authcode", authcode);
		logger.info("=====authcode" + authcode);
		try {
			emailService.sendMail(dto, authcode);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			logger.info("entity ====" + e);
		}
		
		
		return entity;
		
	}
	

}
