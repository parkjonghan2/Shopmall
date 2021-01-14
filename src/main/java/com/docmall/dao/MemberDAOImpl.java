package com.docmall.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.docmall.domain.MemberVO;
import com.docmall.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession session;
	
	public final static String NS = "com.docmall.mappers.MemberMapper";
	
	

	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		return session.selectOne(NS+ ".login", dto);
	}
	
	@Override 
	public void loginUpdate(String mem_id) throws Exception {
		session.update(NS+ ".loginUpdate", mem_id);
	}

	@Override
	public void join(MemberVO vo) throws Exception {
		session.insert(NS+".join",vo);
		
	}
//
//	@Override
//	public int checkIdDuplicate(String mem_id) throws Exception {
//		return session.selectOne(NS+".checkIdDuplicate" , mem_id);
//	}
	@Override
	public int checkIdDuplicate(String mem_id) throws Exception {
		return session.selectOne(NS+ ".checkIdDuplicate", mem_id);
	}

	@Override
	public MemberVO readUserInfo(String mem_id) throws Exception {
		
		return session.selectOne(NS+ ".readUserInfo", mem_id);
	}

}
