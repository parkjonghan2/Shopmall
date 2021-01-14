package com.docmall.service;

import com.docmall.domain.MemberVO;
import com.docmall.dto.MemberDTO;

public interface MemberService {
	//로그인
	public MemberDTO login(MemberDTO dto) throws Exception; 
	//회원가입
	public void join(MemberVO vo) throws Exception;
	//로그인 중복 체크
//	public int checkIdDuplicate(String mem_id) throws Exception;
	public int checkIdDuplicate(String mem_id) throws Exception;
	
	public MemberVO readUserInfo(String mem_id) throws Exception;
	
}
