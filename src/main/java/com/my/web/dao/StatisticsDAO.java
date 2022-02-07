package com.my.web.dao;

import java.util.List;
import java.util.Map;

import com.my.web.dto.Cnt;
import com.my.web.dto.Statistics;

public interface StatisticsDAO {

	// 검색키워드 저장
	int insert(Statistics st);

	// 중복검색체크
	Statistics selectOne(Map<String, String> map);

	// 키워드별 갯수
	List<Cnt> selectList();

	// 총 데이터 갯수
	int totCnt();

	// 남성검색키워드
	List<Cnt> selectListM();

	// 여성검색키워드
	List<Cnt> selectListW();

	// 나이별키워드
	List<Cnt> selectListAge(String findkey);
}
