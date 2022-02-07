package com.my.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.web.dto.Member;



@Repository
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(Member member) {
		return sqlSession.insert("com.my.web.MemberMapper.insert",member);
	}

	@Override
	public int update(Member member) {
		return sqlSession.update("com.my.web.MemberMapper.update",member);
	}

	@Override
	public int delete(String email) {
		return sqlSession.delete("com.my.web.MemberMapper.delete",email);
	}

	@Override
	public Member seleteOne(String email) {
		return sqlSession.selectOne("com.my.web.MemberMapper.selectOne",email);
	}

	@Override
	public List<Member> selectList() {
		return sqlSession.selectList("com.my.web.MemberMapper.selectList");
	}

	@Override
	public int UpdateAuthEmail(String email) {
		return sqlSession.update("com.my.web.MemberMapper.emailAuthUpdate",email);
	}
	
}
