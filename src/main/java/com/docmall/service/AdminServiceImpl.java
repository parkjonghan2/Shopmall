package com.docmall.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.docmall.dao.AdminDAO;
import com.docmall.domain.AdminVO;
import com.docmall.dto.AdminDTO;
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO dao;
	
	@Inject
	private BCryptPasswordEncoder crptPassEnc;

	@Override
	public AdminVO login(AdminDTO dto) throws Exception {
		
		AdminVO vo = dao.login(dto);
		
			if(vo!= null) {
				if(dto.getAdmin_pw().equals(vo.getAdmin_pw())) {
					//id로 이따가한번 바꿔봅시다
					dao.loginUpdate(dto.getAdmin_id());
					
				}else {
					vo = null;
				}
			
			
		}
		return vo;
	}
	
	
	

}
