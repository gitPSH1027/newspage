package com.my.web.service;

import java.util.List;
import java.util.Map;

import com.my.web.dto.Cnt;

public interface StatisticsService {
	// 성별, 나이별 키워드 통계 추가
	Map<String, Object> statistics_insert(String email, String findvalue);

	// 총통계리스트
	List<Cnt> CntOfFindvalue();

	// 남성통계
	List<Cnt> CntOfFindvalueM();

	// 여성통계
	List<Cnt> CntOfFindvalueW();

	// 나이별
	List<Cnt> CntOfFindvalueAge(String findkey);

}
