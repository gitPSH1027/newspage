package com.my.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.my.web.dto.Page;
import com.my.web.dto.Scrap;

@Repository
public class ScrapDAOImpl implements ScrapDAO{
	@Autowired
	private SqlSession sqlSession;
	
	//스크랩추가
	@Transactional
	@Override
	public int insert(Scrap scrap) {
		return sqlSession.insert("com.my.web.ScrapMapper.insert",scrap);
	}

	//스크랩리스트
	@Transactional
	@Override
	public List<Scrap> selectList(Page page) {
		return sqlSession.selectList("com.my.web.ScrapMapper.selectList",page);
	}

	//스크랩삭제
	@Transactional
	@Override
	public int delete(int snum) {
		return sqlSession.delete("com.my.web.ScrapMapper.delete",snum);
	}

	//전체게시물수
	@Transactional
	@Override
	public int totcnt(String email) {
		return sqlSession.selectOne("com.my.web.ScrapMapper.totCnt",email);
	}

}
