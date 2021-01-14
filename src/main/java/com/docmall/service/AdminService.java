package com.docmall.service;

import com.docmall.domain.AdminVO;
import com.docmall.dto.AdminDTO;

public interface AdminService {
	
	//로그인
	public AdminVO login(AdminDTO dto)throws Exception;

}
 