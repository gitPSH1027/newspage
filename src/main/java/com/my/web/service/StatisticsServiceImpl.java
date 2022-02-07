package com.my.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.web.dao.MemberDAO;
import com.my.web.dao.StatisticsDAO;
import com.my.web.dto.Cnt;
import com.my.web.dto.Member;
import com.my.web.dto.Statistics;

@Service
public class StatisticsServiceImpl implements StatisticsService {
	@Autowired
	private MemberDAO mdao;
	@Autowired
	private StatisticsDAO sdao;

	// 통계테이블 추가
	@Transactional
	@Override
	public Map<String, Object> statistics_insert(String email, String findvalue) {
		Map<String, String> map = new HashMap<String, String>(2); // 검색중복여부확인 객체
		Map<String, Object> result = new HashMap<String, Object>(2); // 리턴값 객체
		map.put("email", email);
		map.put("findvalue", findvalue);
		Statistics st = new Statistics();
		boolean check; // 성공여부

		// db정보 불러오기
		Member dbmember = mdao.seleteOne(email);

		// 데이터존재여부
		// 회원일경우만 추가
		if (dbmember != null) {
			// 중복키워드 검색여부확인
			Statistics dbst = sdao.selectOne(map);
			System.out.println(dbst);
			// 입력키워드가 db키워드랑 다를경우에 추가
			if (dbst == null) {
				String gender = dbmember.getGender();
				int age = dbmember.getAge();
				st.setEmail(email);
				st.setAge(age);
				st.setFindvalue(findvalue);
				st.setGender(gender);

				int cnt = sdao.insert(st);

				if (cnt > 0) {
					// 검색완료
					result.put("msg", "검색완료");
					result.put("check", true);
				} else {
					// 검색실패
					result.put("msg", "검색실패");
					result.put("check", false);
				}
			} else {
				// 중복검색일경우
				result.put("msg", "중복검색완료");
				result.put("check", true);
			}

			return result;
		}

		// 회원이 아닌사람이 접근시
		result.put("msg", "잘못된접근입니다. 로그인후 검색하세요.");
		result.put("check", false);
		return result;
	}

	// 키워드별 갯수
	@Override
	public List<Cnt> CntOfFindvalue() {
		List<Cnt> resultCnt = sdao.selectList();
		return resultCnt;
	}

	// 남성키워드
	@Override
	public List<Cnt> CntOfFindvalueM() {
		List<Cnt> resultCnt = sdao.selectListM();
		return resultCnt;
	}

	// 여성키워드
	@Override
	public List<Cnt> CntOfFindvalueW() {
		List<Cnt> resultCnt = sdao.selectListW();
		return resultCnt;
	}

	@Override
	public List<Cnt> CntOfFindvalueAge(String findkey) {
		List<Cnt> resultCnt = sdao.selectListAge(findkey);
		return resultCnt;
	}
}
