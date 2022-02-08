package com.my.web.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.my.web.dto.Member;
import com.my.web.service.LoginServie;
import com.my.web.service.MemberService;

@Controller
@RequestMapping("log")
public class LoginController {
	@Autowired
	private LoginServie ls;
	@Autowired
	private MemberService ms;

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 로그인정보 다삭제
		return "home";
	}

	@PostMapping("login")
	public String login(@RequestParam Map<String, String> logMap, RedirectAttributes rrar, HttpSession session) {
		Map<String, Object> loginResult = ls.loginChk(logMap);
		String email = logMap.get("email");
		String msg = (String) loginResult.get("msg");
		boolean check = (boolean) loginResult.get("check");

		rrar.addFlashAttribute("msg", msg);
		// 성공여부
		if (!check) {
			return "redirect:/member/login";
		}

		session.setMaxInactiveInterval(60 * 60 * 2);
		session.setAttribute("email", email);

		// 간편가입 일반가입 회원정보 분류
		Member dbmember = ms.selectOne(email);
		String simplejoin = dbmember.getSimplejoin();
		session.setMaxInactiveInterval(60 * 60 * 2);
		session.setAttribute("simplejoin", simplejoin);

		// 프로필사진표현
		String profilename = dbmember.getProfilename();
		session.setMaxInactiveInterval(60 * 60 * 2);
		session.setAttribute("profilename", profilename);

		return "redirect:/";
	}

}
