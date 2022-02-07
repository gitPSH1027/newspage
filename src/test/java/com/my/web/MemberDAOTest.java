package com.my.web;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.web.dao.MemberDAO;
import com.my.web.dto.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/**/servlet-context.xml"})
public class MemberDAOTest {
	@Autowired
	private MemberDAO memberDAO;
	
	@Test
	public void InsertTest() {
		Member member = new Member();
		member.setEmail("java@email");
		member.setPasswd("1111");
		member.setEmailauth("1");
		member.setSimplejoin("0");
		member.setZipcode("00000");
		member.setAddr("서울");
		member.setAddrdetail("래미안아파트");
		member.setGender("남");
		member.setBirthyear(1995);
		//member.setProfile("c.png");
		int cnt = memberDAO.insert(member);
		assertEquals(cnt, 1);
		System.out.println(cnt + "건 추가");
	}
	
	@Test
	public void UpdateTest() {
		Member member = new Member();
		member.setPasswd("2222");
		member.setZipcode("1111");
		member.setAddr("경기도");
		member.setAddrdetail("빌라");
		member.setGender("여");
		member.setBirthyear(1998);
		//member.setProfile("d.png");
		member.setEmail("c@email");
		int cnt = memberDAO.update(member);
		assertEquals(cnt, 1);
		System.out.println(cnt + "건 수정");
	}
	
	@Test
	public void DeleteTest() {
		int cnt = memberDAO.delete("java@email");
		assertEquals(cnt, 1);
		System.out.println(cnt + "건 삭제");
	}
	
	@Test
	public void SelectOneTest() {
		Member member = memberDAO.seleteOne("psungho0880@naver.com");
		assertNotEquals(null, member);
		System.out.println(member);
	}

	@Test
	public void SelectListTest() {
		List<Member> mlist = memberDAO.selectList();
		assertNotEquals(null, mlist);
		System.out.println(mlist);
	}
}
