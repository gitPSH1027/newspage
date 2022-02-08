package com.my.web.service;

import java.util.List;
import java.util.Map;

import com.my.web.dto.Page;
import com.my.web.dto.Scrap;


public interface ScrapService {
	String insert(String email, Map<String, String> result); //스크랩추가

	List<Scrap> selectList(Page page); //스크랩리스트

	String delete(List<Integer> delSnum); //스크랩선택삭제
}
