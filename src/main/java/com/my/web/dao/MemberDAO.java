package com.my.web.dao;

import java.util.List;

import com.my.web.dto.Member;

public interface MemberDAO {
	int insert(Member member); //추가
	int update(Member memmer); //수정
	int delete(String email); //삭제
	Member seleteOne(String email); //한건조회
	List<Member> selectList(); //전체조회
	//이메일 인증 업데이트
	int UpdateAuthEmail(String email);
}
