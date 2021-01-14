package com.docmall.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.docmall.dao.MemberDAO;
import com.docmall.domain.MemberVO;
import com.docmall.dto.MemberDTO;

@Service
public class MemberServiceimpl implements MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	
	@Inject  // 주입 이노테이션.  이 클래스의 bean객체 생성 : spring-security.xml
	private BCryptPasswordEncoder crptPassEnc;
	
	
	//로그인
	
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		MemberDTO memDTO = dao.login(dto);  // 아이디 파라미터만 사용함.(비밀번호 파라미터사용안함)
		
		// 회원가입시 비밀번호를 암호화하여 저장됨.

		if(memDTO != null) {
			// 비밀번호가 암호화 된 비밀번호와 일치하는지 확인.  crptPassEnc.matches(일반비밀번호, 암호화된비밀번호)
			if(crptPassEnc.matches(dto.getMem_pw(), memDTO.getMem_pw())) { // true 면, 로그인 정보 존재한다.
				dao.loginUpdate(memDTO.getMem_id()); // 로그인 시간저장.
			} else { 
			// 비밀번호가 일치하지 않으면, null 반환
				memDTO = null;
			} 
		}
		return memDTO;
	}


	@Override
	public void join(MemberVO vo) throws Exception {
		dao.join(vo);
		
	}

//
//	@Override
//	public int checkIdDuplicate(String mem_id) throws Exception {
//	
//		return dao.checkIdDuplicate(mem_id);
//	}

	// 아이디 중복 체크
	@Override
	public int checkIdDuplicate(String mem_id) throws Exception {
		return dao.checkIdDuplicate(mem_id);
	}


	@Override
	public MemberVO readUserInfo(String mem_id) throws Exception {
		
		return dao.readUserInfo(mem_id);
	}


}
 