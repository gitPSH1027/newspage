package com.my.web;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import com.my.web.dto.NewsPage;
import com.my.web.service.NewsPageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/**/servlet-context.xml"})
public class NewsPageServiceTest {
	@Autowired
	private NewsPageService nps;
	
	@Test
	public void testInsert() throws Exception {
//		Map<String, Object> result = nps.insert("주식");
//		String msg = (String) result.get("msg");
//		System.out.println(msg);
	}
	
	@Test
	public void testSelectOne()
	{
//		NewsPage np = nps.selectOne(1);
//		assertNotEquals(np, null);
//		System.out.println(np);
	}

}
