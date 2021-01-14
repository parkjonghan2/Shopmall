package com.docmall.dao;

import com.docmall.domain.AdminVO;
import com.docmall.dto.AdminDTO;

public interface AdminDAO {
	
	//로그인
	public AdminVO login(AdminDTO dto) throws Exception;
	
	//시간 업데이트
	public void loginUpdate(String admin_id) throws Exception;

}
