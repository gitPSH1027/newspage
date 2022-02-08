package com.my.web.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.web.service.MemberService;

@Controller
@RequestMapping("search")
public class SearchController {
	@Autowired
	private MemberService ms;
	
	@ResponseBody
	@GetMapping(value="dupCheck",produces = "application/text; charset=utf8")
	String dupCheck(@RequestParam String email)
	{
		String joinChk = ms.emailCheck(email);
		return joinChk;
	}
	
	//주소팝업
	@RequestMapping("jusoPopup")
	public String jusoPopup() {
		return "member/jusoPopup";
	}
}
