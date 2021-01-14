package com.docmall.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.CategoryVO;
import com.docmall.domain.ProductVO;
import com.docmall.service.AdProductService;
import com.docmall.util.FileUtils;
import com.docmall.util.PageMaker;
import com.docmall.util.SearchCriteria;

@Controller
@RequestMapping("/admin/product/*")
public class AdProductController {
	
	@Autowired
	private AdProductService service;
	
	//웹 프로젝트 영역 외부에 파일을 저장할 때 사용할 경로
	@Resource(name="uploadPath")
	private String uploadPath;
	
	private final static Logger logger = LoggerFactory.getLogger(AdProductController.class); 
	
	
	
		/* 
		 * 1차 카테고리에 따른 2차 카테고리 출력
		 * 
		 * @Params
		 * 	@PathVariable("cg_code")
		 * 	: path로 들어온 1차카테고리
		 * 
		 * @return ResponseEntity<List<CategoryVO>>
		 * 	: REST 방식에 따른 HttpStatus를 같이 보내주기 위함
		 */
	    // ARC :Advanced Rest Client 프로그램 테스트 먼저 확인
		//  "/admin/product/subCGList/" + mainCGCode
		@ResponseBody
		@RequestMapping(value = "subCGList/{cg_code}", method=RequestMethod.GET)
		public ResponseEntity<List<CategoryVO>> subCGListPOST(@PathVariable("cg_code") String cg_code) {
			logger.info("==============subcglistget exceute");
			
			//2차 카테고리 리스트
			ResponseEntity<List<CategoryVO>> entity = null;
			try {
				entity = new ResponseEntity<List<CategoryVO>>(service.subCGList(cg_code), HttpStatus.OK);
				
				
			}catch(Exception e) {
				entity = new ResponseEntity<List<CategoryVO>>(HttpStatus.BAD_REQUEST);
			}
			return entity;
			
			
		}
		

		public void deleteFile(String fileName) {
			logger.info("delte FileName :" + fileName);
			FileUtils.deleteFile(uploadPath, fileName);
			
		}
		
		
		//상품등록 겟
		@RequestMapping(value = "insert", method = RequestMethod.GET)
		public void productInsertGET(Model model) throws Exception  {
			model.addAttribute("cateList",service.mainCGList());
		}
		
		//상품등록 포스트
		@RequestMapping(value = "insert", method = RequestMethod.POST)
		public String productInsertPOST(ProductVO vo, RedirectAttributes rttr ) throws Exception {
			logger.info("===============inserPOST execute");
			logger.info(vo.toString());
			
			//pdt_img 를 업로드 된 이미지 파일로 설정
			vo.setPdt_img(FileUtils.uploadFile(uploadPath, vo.getFile1().getOriginalFilename(),
					vo.getFile1().getBytes()));
			service.insertProudct(vo);
			rttr.addFlashAttribute("msg","INSERT_SUCCESS");
			//상품리스트로 이동
			return "redirect:/admin/product/list";
			}
		
		@RequestMapping(value = "list", method = RequestMethod.GET)
		public void productList(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
				logger.info("=========productList() execute...");
				logger.info("========ccri:" + cri.toString());
				
				model.addAttribute("productList", service.searchListProduct(cri));
				
				//PageMaker 생성
				PageMaker pm = new PageMaker();
				pm.setCri(cri);
				int count = service.searchListCount(cri);
				logger.info("===========일치하는 상품개수:" + count);
				pm.setTotalCount(count);
				model.addAttribute("pm",pm);
			
		}
		/*상품상세 ckeditor파일업로드
		 * 웹 프로젝트 영역상의 폴더에 업로드
		 * 
		 * MultipartFile upload: 이미 지정된 이름 
		 * */
		@RequestMapping(value = "displayFile", method = RequestMethod.GET )
		public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
			
			System.out.println("uploadPath"+ uploadPath + "fileName" +fileName);
			return FileUtils.getFile(uploadPath, fileName);
		}
		
		@RequestMapping(value = "imgUpload" , method = RequestMethod.POST)
		public void imgUpload(HttpServletRequest req, HttpServletResponse res, MultipartFile upload) {
			
			logger.info("====imgupload execute...");
			
			OutputStream out = null;
			PrintWriter printWriter = null;
			
			//설정
			
		
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html;charset=UTF-8");
			
			try {
				//전송할 파일 정보를 가져옴
				String fileName = upload.getOriginalFilename();
				byte[] bytes= upload.getBytes();
				//폴더 경로 설정
				String uploadPath = req.getSession().getServletContext().getRealPath("/");
				uploadPath = uploadPath + "resources\\upload\\" + fileName;
				
				logger.info("==============upload Path :"+ uploadPath);
				
				out = new FileOutputStream(new File(uploadPath));
				out.write(bytes);
				
				printWriter = res.getWriter();
				String fileUrl = "/upload/" + fileName;
				
			//ckeditor 4 에서 제공하는 형식
				printWriter.println("{\"filename\":\"" + fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}");
				printWriter.flush(); //전송 return과 같은역할 클ㄹ이언트로 보냄 
				
				//전송할 파일 정보를 가져옴
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(out != null) {
						try {out.close();}catch(Exception e) {e.printStackTrace();}
				}
			if(printWriter != null) {
				try {printWriter.close();} catch(Exception e) {e.printStackTrace();}
			}
				
			
		}
		
	}
		@RequestMapping(value = "read", method = RequestMethod.GET)
		public void productReadGET(@ModelAttribute("cri") SearchCriteria cri,
									@RequestParam("pdt_num") int pdt_num,Model model) {
			
			logger.info("productReadGET ....");
			//선택한 상품의 날짜 변환
			ProductVO vo = service.readProduct(pdt_num);
			System.out.println("productReadGEt에서 나온 pdt_num"+pdt_num);
		
			logger.info("=========product Detail"  + vo.toString());
			//썸네일 파일 이름 수정
			vo.setPdt_img(vo.getPdt_img().substring(vo.getPdt_img().lastIndexOf("_")+1));
			System.out.println("????===" +  vo.getPdt_img().substring(vo.getPdt_img().lastIndexOf("_")+1)) ;
		
			logger.info("===changed product detail" + vo.toString());
			model.addAttribute("vo" , vo);
			//PageMaker생성  - 상품목록으로 돌아가기 클릭시 이동하기 위해서
			PageMaker pm = new PageMaker();
			pm.setCri(cri);
			System.out.println("cri의 정보는" + cri);
			
			model.addAttribute("pm" , pm);
			System.out.println("pm의 정보는 ?" + pm);
			
			
			
			
		}
	/*상품수정 (get)
	#jsp로 전덜
	1. 선택한 상품정보 2. 1차카테고리 리스트 3.현재 선택된 2차 카테고리 리스트 4. pageMaaker5.
	
	*/
		@RequestMapping(value = "edit", method = RequestMethod.GET)
		public void productEditGET(@ModelAttribute("cri") SearchCriteria cri,
									@RequestParam("pdt_num") int pdt_num, Model model) throws Exception {
			
			logger.info("====productEditGET() ..");
			//선택한 정보의 날짜 변환
			ProductVO vo =service.readProduct(pdt_num);
			logger.info("=====product detail : " + vo.toString());
			
			String originFile = vo.getPdt_img().substring(vo.getPdt_img().lastIndexOf("_")+1);
			
			model.addAttribute("vo", vo);
			System.out.println("vo======" + vo);
			model.addAttribute("originFile", originFile);
			System.out.println("originFile ========="+originFile);
			model.addAttribute("cateList",service.mainCGList());
			System.out.println("cateList====="+ service.mainCGList());
			model.addAttribute("subCateList",service.subCGList(vo.getCg_code_prt()));
			System.out.println("subCateList=========" + service.subCGList(vo.getCg_code_prt()));
			
			PageMaker pm = new PageMaker();
			pm.setCri(cri);;
			
			model.addAttribute("pm", pm);
			
		}
		@RequestMapping(value = "edit", method = RequestMethod.POST)
		public String productEditPOST(ProductVO vo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
			logger.info("========productEditPost...");
			logger.info("======editted info :" + vo.toString());
			logger.info("====cri.info" + cri.toString());
			//파일 사이즈로 새로운 파일 등록 여부 확인
			//파일을 새로 등록하지 않은 경우 null 이아닌 비어있는 쓰레기 파일이 넘어옴
			if(vo.getFile1().getSize() > 0) {
				//파일이 변경된 경우 , pdt_img를 업로드 돈 파일 정보로 설정
				logger.info("=====file not zero size");
				vo.setPdt_img(FileUtils.uploadFile(uploadPath, vo.getFile1().getOriginalFilename(), vo.getFile1().getBytes()));
				
			}
			logger.info("====changed info:" +vo.toString());
			service.editProduct(vo);
			rttr.addFlashAttribute("cri",cri);
			rttr.addFlashAttribute("msg","EDIT_SUCCESS");
			
			return "redirect:/admin/product/list";
		}
		
		@RequestMapping(value = "delete", method = RequestMethod.POST)
		public String productDeletePOST(SearchCriteria cri, @RequestParam("pdt_num") int pdt_num , 
															@RequestParam ("pdt_img") String pdt_img,
															RedirectAttributes rttr) {
			logger.info("productdeletePost......");
			
			//이미지 삭제
			deleteFile(pdt_img);
			System.out.println("pdt_img ====" + pdt_img);
			
			//상품삭제.
			service.deleteProduct(pdt_num);
			System.out.println("int pdt_num=======" + pdt_num);
			
			rttr.addFlashAttribute("cri", cri);
			rttr.addFlashAttribute("msg","DELETE_SUCCESS");
			
			
			return "redirect:/admin/product/list";
		}
		
		@RequestMapping(value = "editChecked" , method = RequestMethod.POST)
		public ResponseEntity<String> editchecked(@RequestParam("checkArr[]") List<Integer> checkArr,
								  @RequestParam("amountArr[]") List<Integer> amountArr,
								  @RequestParam("buyArr[]") List <String> buyArr) {
			logger.info("==== editchecked() execute....");
			
			ResponseEntity<String> entity =null;
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i=0; i<checkArr.size(); i++) {
					map.put("pdt_num", checkArr.get(i));
					System.out.println("checkARrr" + checkArr.get(i));
					
					map.put("pdt_amount", amountArr.get(i));
					map.put("pdt_buy", buyArr.get(i));
					
					service.editChecked(map);
				}
				entity = new ResponseEntity<String>(HttpStatus.OK);
				
				
			}catch (Exception e) {
				entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
				
			}
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
			
			
			return entity;
		}
		
		@RequestMapping(value = "stock", method = RequestMethod.GET)
	public void stock(ProductVO vo, Model model) {
		
		logger.info("stock===" );
		List<ProductVO> list = service.stock(vo);
		
		
		
		model.addAttribute("ProductVO",list);
		
		
		
	}
		
}
		
