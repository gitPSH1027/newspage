package com.my.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.my.web.dao.MemberDAO;
import com.my.web.dto.Member;

@Service
public class LoginServieImpl implements LoginServie{
	@Autowired
	private MemberDAO mdao;
	@Autowired
	private BCryptPasswordEncoder bC;
	
	
	
	@Override
	public Map<String, Object> loginChk(Map<String, String> logMap) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String email = (String) logMap.get("email");
		String passwd = (String) logMap.get("passwd");
		
		Member dbmember = mdao.seleteOne(email);
		//회원일경우
		if(dbmember == null)
		{
			result.put("msg", "등록 회원이 아닙니다.");
			result.put("check", false);
			return result;
		}
		
		//간편가입일경우
		if(!dbmember.getSimplejoin().equals("0"))
		{
			if(dbmember.getSimplejoin().equals("1"))//네이버간편가입
			{
				result.put("msg", "네이버 간편회원입니다.");
				result.put("check", false);
				return result;
			}
		}
		
		//비밀번호체크
		boolean match= bC.matches(passwd, dbmember.getPasswd());
		if (!match) 
		{
			result.put("msg", "비밀번호가 일치하지 않습니다.");
			result.put("check", false);
			return result;
		}
		
		//이메일인증체크
		if(!dbmember.getEmailauth().equals("1"))
		{
			result.put("msg", "이메일인증을 해주세요.");
			result.put("check", false);
			return result;
		}
		
		//로그인성공
		result.put("msg", "로그인성공");
		result.put("check", true);
		return result;
	}

}
