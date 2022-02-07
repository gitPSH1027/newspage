package com.my.web.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.my.web.dto.Member;
import com.my.web.service.MemberService;
import com.my.web.service.NaverJoinService;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	private MemberService ms;
	@Autowired
	private NaverJoinService nj;
	
	//join.jsp
	@RequestMapping("join")
	public void join() {}
	
	//pwchk.jsp 본인확인
	@GetMapping("pwchk") 
	public void pwchk(Model model, @RequestParam String route) {
		//스크랩정보 회원정보 루트체크
		//회원정보 이동 root:0
		//스크랩정보 이동 root:1
		model.addAttribute("route", route);
	}
	
	//회원가입 폼
	@PostMapping("joinForm")
	public String joinForm(@ModelAttribute Member member, RedirectAttributes rrar, HttpSession session) throws Exception
	{
		Map<String, Object> result = ms.insert(member);
		String msg = (String) result.get("msg"); //결과메세지
		Boolean check = (Boolean) result.get("check"); //성공여부
		String authCode = (String) result.get("authCode"); //이메일인증코드
		
		//가입성공일겨우
		if(check)
		{
			rrar.addFlashAttribute("msg", msg);
			session.setMaxInactiveInterval(60*60*2);//유효시간
			session.setAttribute("authCode", authCode);
			return "redirect:/";
		}
		//실패일경우
		else
		{
			return "redirect:/";
		}
	}
	
	//이메일인증 콜백주소
	@GetMapping("joinForm")
	public String joinForm_callback(@RequestParam Map<String, String> map, HttpSession session, RedirectAttributes rrar) 
	{
		String msg;
		String authCode = map.get("authCode");
		String email = map.get("email");
		
		//보낸 코드, 받은 코드 비교
		String sendAuthCode = (String) session.getAttribute("authCode"); //회원가입시 보낸 인증코드
		if(sendAuthCode.equals(authCode))
		{
			session.invalidate();
			msg = ms.UpdateAuthEmail(email); // 미인증:"0"->인증:"1"변경
			rrar.addFlashAttribute("msg", msg);
			return "redirect:/";
		}
		else
		{
			msg = ms.UpdateAuthEmail(email);
			rrar.addFlashAttribute("msg", msg);
			return "redirect:/";
		}
	}
	//login.jsp
	@GetMapping("login")
	public void login(Model model) throws Exception{
		Map<String, String> result = nj.getApiURl();
		String apiURL = result.get("apiURL");
		model.addAttribute("apiURL", apiURL);
	}
	
	//detail.jsp
	@GetMapping("detail")
	public void detail(HttpSession session, Model model) {
		String email = (String) session.getAttribute("email");
		Member member = ms.selectOne(email);
		//인증이 안되었을경우
		if(session.getAttribute("cert")==null)
		{
			//1:본인확인성공
			session.setMaxInactiveInterval(60*60*2);
			session.setAttribute("cert", "1");
		}
		model.addAttribute("member", member);
	}

	//회원정보수정
	@PostMapping("modify")
	public String modify(@ModelAttribute Member member, @RequestParam String newpasswd, @RequestParam String email
						,RedirectAttributes rrar) throws Exception {
		Map<String, Object> result = ms.update(member, newpasswd, email);
		boolean check = (boolean) result.get("check");
		String msg = (String) result.get("msg");
		rrar.addFlashAttribute("msg", msg);
		
		//성공여부
		if(!check)
		{
			return "member/detail";
		}
		return "redirect:/";
	}
	
	//회원탈퇴
	@GetMapping("delete")
	public String delete(@RequestParam String email, RedirectAttributes rrar, HttpSession session)
	{
		Map<String, Object> result = ms.delete(email);
		boolean check = (boolean) result.get("check");
		String msg = (String) result.get("msg");
		
		rrar.addFlashAttribute("msg", msg);
		
		//성공여부
		if(!check)
		{
			return "member/detail";
		}
		session.invalidate();//모든 세션 삭제
		return "redirect:/";
	}
	
}
