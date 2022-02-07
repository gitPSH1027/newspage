package com.my.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.web.service.NewsPageService;
import com.my.web.service.StatisticsService;

@Controller
@RequestMapping("newspage")
public class NewsPageController {
	@Autowired
	private NewsPageService nps;
	@Autowired
	private StatisticsService ss;

	// search.jsp
	@GetMapping("/")
	public String search() {
		return "newspage/search";
	}

	// 검색리스트
	@ResponseBody
	@GetMapping("searchlist")
	public List<Map<String, Object>> searchlist(@RequestParam String findvalue, HttpSession session) throws Exception {
		List<Map<String, Object>> result = nps.selectList(findvalue);

		// 성별/나이별 테이블에 데이터 저장 서비스 생성
		String email = (String) session.getAttribute("email");
		Map<String, Object> stResult = ss.statistics_insert(email, findvalue);
		boolean check = (boolean) stResult.get("check");

		if (check) {
			return result;
		}
		return null;
	}

	// 뉴스간략내용 및 전체기사 링크
	@PostMapping("detail")
	public void detail(@RequestParam Map<String, String> result, Model model) {
		model.addAttribute("result", result);
	}

}
