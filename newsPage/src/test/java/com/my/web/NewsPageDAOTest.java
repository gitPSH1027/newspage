package com.my.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import com.my.web.dao.NewsPageDAO;
//import com.my.web.dto.NewsPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/**/servlet-context.xml"})
public class NewsPageDAOTest {

	
	@Test
	public void testInsert() {
//		NewsPage np = new NewsPage();
//		np.setDescription("내용");
//		np.setLink("링크");
//		np.setPubDate("제공날짜");
//		np.setTitle("제목");
//		
//		int cnt = npdao.insert(np);
//		assertEquals(cnt, 1);
//		System.out.println(cnt +"건 추가");
	}

}
