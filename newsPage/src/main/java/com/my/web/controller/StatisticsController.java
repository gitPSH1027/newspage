package com.my.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.web.dto.Cnt;
import com.my.web.service.StatisticsService;

@Controller
@RequestMapping("statistics")
public class StatisticsController {
	@Autowired
	private StatisticsService ss;

	// 통계그래프.jsp
	@GetMapping("graph")
	public void graph(Model model) {
		List<Cnt> resultCnt = ss.CntOfFindvalue();
		model.addAttribute("resultCnt", resultCnt);
	}

	// 통계그래프.jsp
	@ResponseBody
	@GetMapping("graphForm")
	public List<Cnt> graphForm() {
		List<Cnt> resultCnt = ss.CntOfFindvalue();
		return resultCnt;
	}

	// 남성별통계그래프
	@ResponseBody
	@GetMapping("graphForm/m")
	public List<Cnt> graphFormM() {
		List<Cnt> resultCnt = ss.CntOfFindvalueM();
		return resultCnt;
	}

	// 여성별통계그래프
	@ResponseBody
	@GetMapping("graphForm/w")
	public List<Cnt> graphFormW() {
		List<Cnt> resultCnt = ss.CntOfFindvalueW();
		System.out.println(resultCnt);
		return resultCnt;
	}

	// 나이별통계그래프
	@ResponseBody
	@GetMapping("graphForm/age")
	public List<Cnt> graphFormAge(@RequestParam String findkey) {
		List<Cnt> resultCnt = ss.CntOfFindvalueAge(findkey);
		// System.out.println(resultCnt);
		return resultCnt;
	}
}
