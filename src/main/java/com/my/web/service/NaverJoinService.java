package com.my.web.service;

import java.util.Map;

public interface NaverJoinService {

	//네이버가입ApiURL
	Map<String, String> getApiURl() throws Exception;
	

	//토근+개인정보
	Map<String, Object> getTokenUserInfo(Map<String, String> map) throws Exception;
	
	//네이버가입
	Map<String, Object> insert(Map<String, Object> result);
}
