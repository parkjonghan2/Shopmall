package com.docmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.CategoryVO;
import com.docmall.domain.ProductVO;
import com.docmall.service.ProductService;
import com.docmall.service.ReviewService;
import com.docmall.util.Criteria;
import com.docmall.util.FileUtils;
import com.docmall.util.PageMaker;



@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	@Inject
	ProductService service;
	
	@Inject
	ReviewService reviewService;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@ResponseBody
	@RequestMapping(value="subCGList/{cg_code}", method=RequestMethod.GET)
	public ResponseEntity<List<CategoryVO>> subCGListPOST(@PathVariable("cg_code") String cg_code){
		
		logger.info("===== subCGListGET() execute.....");
		System.out.println("cg_code====" + cg_code);
		
		// 2차 카테고리 리스트
		ResponseEntity<List<CategoryVO>> entity = null;
		try {
			logger.info("====="+ service.subCGList(cg_code));
			entity = new ResponseEntity<List<CategoryVO>>(service.subCGList(cg_code), HttpStatus.OK);
		} catch(Exception e){
			entity = new ResponseEntity<List<CategoryVO>>(HttpStatus.BAD_REQUEST);
		}
		
		System.out.println("시발거===" + entity);
		return entity; 
	}
	@RequestMapping(value = "list" , method = RequestMethod.GET)
	public void list(@ModelAttribute("cri") Criteria cri,
					 @ModelAttribute("cg_code") String cg_code,
					 Model model) throws Exception {
		
		logger.info("list실행됫당" );
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cg_code", cg_code);
		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
		
		List<ProductVO> list = service.productListCG(map);
		model.addAttribute("productList", list);
		model.addAttribute("cg_name", service.getCGName(cg_code));
		System.out.println("cg_name===================" + service.getCGName(cg_code));
		
		//pagemaker 생성
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int count =service.productCount(cg_code);
		pm.setTotalCount(count);
		
		model.addAttribute("pm", pm);

	}

	@ResponseBody
	@RequestMapping(value = "displayFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		System.out.println("이게 무슨파일일까?" +  FileUtils.getFile(uploadPath, fileName));
		return FileUtils.getFile(uploadPath, fileName);
	}
	
	
	@RequestMapping(value="read", method=RequestMethod.GET)
	public void productReadGET(@ModelAttribute("cri") Criteria cri, 
								@RequestParam("pdt_num") int pdt_num, Model model) throws Exception{
		
		logger.info("=====productReadGET() execute...");
		
		// 선택한 상품 정보의 이미지를 썸네일에서 원본으로 변경
		ProductVO vo = service.readProduct(pdt_num);
		vo.setPdt_img(FileUtils.thumbToOriginName(vo.getPdt_img()));
		
		//logger.info("=====dateFormat: " + DateFormatUtils.kstToDate(vo.getPdt_date_sub()).toString());
		logger.info("=====Product Detail: "+ vo.toString());
		model.addAttribute("vo", vo);
		
		// PageMaker 생성 - 상품목록으로 돌아가기 클릭 시 이동하기 위해서
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		model.addAttribute("pm", pm);
		
		// 해당 상품에 달린 상품 후기 개수를 함께 보냄
		model.addAttribute("totalReview", reviewService.countReview(vo.getPdt_num()));
	}
}