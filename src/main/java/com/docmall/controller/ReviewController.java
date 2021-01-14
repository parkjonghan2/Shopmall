package com.docmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.ReviewVO;
import com.docmall.dto.MemberDTO;
import com.docmall.service.ReviewService;
import com.docmall.util.Criteria;
import com.docmall.util.PageMaker;


@Controller
@RequestMapping("/review/*")
public class ReviewController {
	
	@Inject
	ReviewService service;
	
	MemberDTO dto;
	
	
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	
	@RequestMapping(value = "{pdt_num}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listReview(
												@PathVariable("pdt_num") Integer pdt_num,
												@PathVariable("page") Integer page) {
		
		logger.info("====listReview() execute....");
		
		ResponseEntity<Map<String, Object>> entity =null;
		
		Criteria cri = new Criteria();
		 cri.setPage(page);
		 
		 PageMaker pageMaker = new PageMaker();
		 pageMaker.setCri(cri);
		 
		 Map<String, Object> map = new HashMap<String, Object>();
		 List<ReviewVO> list = service.listReview(pdt_num, cri);
		 
		 //후기 목록 페이지 포함 추가 
		 map.put("list", list);
		 System.out.println("map====두번째" + map);
		 
		 int replyCount = service.countReview(pdt_num);
		 
		 pageMaker.setTotalCount(replyCount);
		 
		 map.put("pageMaker", pageMaker);
		 
		 System.out.println("map====세번쨰" + map);
		 
		 try {
			 
			 
			 entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 entity = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
			 
		 }
		 return entity;
	}
	@ResponseBody
	@RequestMapping(value = "write" , method = RequestMethod.POST)
	public void write(ReviewVO vo, HttpSession session) {
		
		logger.info("======writePost execute...");
		logger.info("====vo" + vo.toString());
		
		 dto = (MemberDTO) session.getAttribute("user");
		 
		 System.out.println("dto=======" + dto);
		 
		 service.writeReview(vo, dto.getMem_id());
	}
	//상품후기 수정
	
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public ResponseEntity<String> modify(ReviewVO vo) {
		logger.info("modify --------.");
		logger.info("modify====vo" + vo.toString());
		
		ResponseEntity<String> entity = null;
		
		try{
			service.modifyReview(vo);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e) {
		
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		System.out.println("entity========" +entity);
		return entity;
	}
	//상품후기 삭제
	@RequestMapping(value ="{rv_num}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(ReviewVO vo) {
		logger.info("dele=========");
		
		ResponseEntity<String> entity =null;
				try {
					service.deleteReview(vo);
				entity = new ResponseEntity<String>(HttpStatus.OK);
					
					
				}catch(Exception e) {
					
					entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
					e.printStackTrace();
				}
		
		return entity;
	}
}
