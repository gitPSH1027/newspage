package com.my.web.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.my.web.dto.Page;
import com.my.web.dto.Scrap;
import com.my.web.service.ScrapService;

@Controller
@RequestMapping("scrap")
public class ScrapController {
	@Autowired
	private ScrapService ss;

	//스크랩하기
	@ResponseBody
	@GetMapping(value="insert",produces = "application/text; charset=utf8")
	public String insert(@RequestParam Map<String, String> result, HttpSession session)
	{
		//스크랩한 유저 이메일
		String email = (String) session.getAttribute("email");
		//scrap 저장
		String msg =  ss.insert(email, result);
		return msg;
	}
	
	//스크랩리스트이동
	@GetMapping("list")
	public void list(@ModelAttribute Page page, HttpSession session,Model model) {
		String email = (String) session.getAttribute("email");
		page.setEmail(email);
		if(page.getFindkey()==null) page.setFindkey("title");
		if(page.getFindvalue()==null) page.setFindvalue("");
		List<Scrap> slist = ss.selectList(page);
		//인증이 안되었을경우
		if(session.getAttribute("cert")==null)
		{
			//1:본인확인성공
			session.setMaxInactiveInterval(60*60*2);
			session.setAttribute("cert", "1");
		}
		model.addAttribute("slist", slist);
		model.addAttribute("page", page);
	}
	
	//스크랩선택삭제
	@PostMapping("del")
	public String del(@RequestParam List<Integer> delSnum, RedirectAttributes rrar)
	{
		String msg = ss.delete(delSnum);
		rrar.addFlashAttribute("msg", msg);
		return "redirect:list";
	}
}
