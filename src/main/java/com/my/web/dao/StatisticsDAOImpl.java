package com.my.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.web.dto.Cnt;
import com.my.web.dto.Statistics;

@Repository
public class StatisticsDAOImpl implements StatisticsDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(Statistics st) {
		return sqlSession.insert("com.my.web.StatisticsMapper.insert", st);
	}

	@Override
	public Statistics selectOne(Map<String, String> map) {
		return sqlSession.selectOne("com.my.web.StatisticsMapper.selectOne", map);
	}

	@Override
	public List<Cnt> selectList() {
		return sqlSession.selectList("com.my.web.StatisticsMapper.CntOfFindvalue");
	}

	@Override
	public int totCnt() {
		return sqlSession.selectOne("com.my.web.StatisticsMapper.totCnt");
	}

	@Override
	public List<Cnt> selectListM() {
		return sqlSession.selectList("com.my.web.StatisticsMapper.CntOfFindvalueM");
	}

	@Override
	public List<Cnt> selectListW() {
		return sqlSession.selectList("com.my.web.StatisticsMapper.CntOfFindvalueW");
	}

	@Override
	public List<Cnt> selectListAge(String findkey) {
		return sqlSession.selectList("com.my.web.StatisticsMapper.CntOfFindvalueAge", findkey);
	}

}
