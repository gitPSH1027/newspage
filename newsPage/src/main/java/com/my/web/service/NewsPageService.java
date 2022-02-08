package com.my.web.service;

import java.util.List;
import java.util.Map;


public interface NewsPageService {
	//검색리스트
	List<Map<String, Object>> selectList(String findvalue) throws Exception;
}
