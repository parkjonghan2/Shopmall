package com.docmall.dao;

import com.docmall.domain.MemberVO;
import com.docmall.dto.MemberDTO;

public interface MemberDAO {
	//로그인
	public MemberDTO login(MemberDTO dto) throws Exception; 
	//로그인
	public void loginUpdate(String mem_id) throws Exception;
	//회원가입
	public void join(MemberVO vo) throws Exception;
	//아이디 중복 체크
//	public int checkIdDuplicate(String mem_id) throws Exception;
	public int checkIdDuplicate(String mem_id) throws Exception;
	
	public MemberVO readUserInfo(String mem_id) throws Exception;
			

}
