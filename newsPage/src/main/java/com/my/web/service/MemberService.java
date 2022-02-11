package com.my.web.service;

import java.util.Map;

import com.my.web.dto.Member;

public interface MemberService {
	// 아이디 중복체크
	String emailCheck(String email);

	Map<String, Object> insert(Member member) throws Exception; // 추가

	Map<String, Object> update(Member member, String newpasswd, String email) throws Exception; // 수정

	Map<String, Object> delete(String email); // 삭제

	Member selectOne(String email); // 한건조회

	// 이메일인증완료후 authemail변경
	String UpdateAuthEmail(String email);

	// 본인확인 패스워드 일치여부
	Map<String, Object> pwChk(String email, String passwd);

}
