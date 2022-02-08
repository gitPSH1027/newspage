package com.my.web.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.my.web.dto.Member;
import com.my.web.service.MemberService;
import com.my.web.service.NaverJoinService;


@Controller
public class HomeController {
	@Autowired
	private NaverJoinService njs;
	@Autowired
	private MemberService ms;
	
	@GetMapping("/")
	public String home() {
		return "/home";
	}
	
	//naver_callback
	@GetMapping("naver_callback")
	public String naver_callback(@RequestParam Map<String, String> map, RedirectAttributes rrar, HttpSession session) throws Exception
	{
		Map<String, Object> naverResult = njs.getTokenUserInfo(map);
		String email = (String) naverResult.get("email");
		//db회원등록
		Map<String, Object> insertMap = njs.insert(naverResult);
		int code = (int) insertMap.get("code");
		String msg = (String) insertMap.get("msg");
		//네이버 간편가입완료 0
		//이미 간편가입된 회원 1
		//일반가입회원 2
		rrar.addFlashAttribute("msg", msg);
		if(code>1)//일반회원일경우
		{
			return "redirect:member/login";
		}
		//네이버회원
		session.setMaxInactiveInterval(60*60*2);
		session.setAttribute("email", email);
		
		//간편가입 일반가입 회원정보 분류
		Member dbmember = ms.selectOne(email);
		String simplejoin = dbmember.getSimplejoin();
		session.setMaxInactiveInterval(60*60*2);
		session.setAttribute("simplejoin", simplejoin);
		
		return "redirect:/";
	}
	
	//place.jsp
	@GetMapping("place")
	public void place() {}
	
	//본인확인폼
	@ResponseBody
	@PostMapping("pwchk")
	public Map<String, Object> pwchk(String passwd, HttpSession session) {
		String email = (String) session.getAttribute("email");
		Map<String, Object> result = ms.pwChk(email, passwd); //패스워드 일치여부
		return result;
	}
}
