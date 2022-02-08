package com.my.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.web.dao.ScrapDAO;
import com.my.web.dto.Page;
import com.my.web.dto.Scrap;

@Service
public class ScrapServiceImpl implements ScrapService{
	@Autowired
	private ScrapDAO sdao;
	
	//스크랩추가
	@Transactional
	@Override
	public String insert(String email, Map<String, String> result) {
		Scrap scrap = new Scrap();
		String title = result.get("title"); //제목
		String description = result.get("description"); //내용
		String link = result.get("link"); //링크
		scrap.setDescription(description);
		scrap.setEmail(email);
		scrap.setLink(link);
		scrap.setTitle(title);
		
		String msg = null;
		int cnt = sdao.insert(scrap);
		if(cnt > 0)
		{
			msg = cnt + "건 스크랩 완료";
		}
		else
		{
			msg = cnt + "건 스크랩 실패";
		}
		return msg;
	}

	//스크랩리스트
	@Transactional
	@Override
	public List<Scrap> selectList(Page page) {
		String email = page.getEmail();
		//페이지처리값
		int perpage = page.getPerpage();//한페이지 게시물수
		int curpage = page.getCurpage();//현재페이지
		int perblock = page.getPerblock();//페이지블럭
				
		int totcnt = sdao.totcnt(email);//전체게시물수
		int totpage = totcnt/perpage;//전체페이지수
		if(totcnt%perpage > 0) totcnt++;//나머지 +1
		page.setTotpage(totpage); 
				
		int startnum = (curpage-1)*perpage;//시작번호 mysql 0부터 시작
		page.setStartnum(startnum);
				
		int endnum = startnum+perpage-1;//끝번호
		page.setEndnum(endnum);
				
		int startpage = curpage-((curpage-1)%perblock);//시작페이지
		page.setStartpage(startpage);
				
		int endpage = startpage+(perblock-1);//끝페이지
		if(endpage>totpage) endpage = totpage;//끝페이지는 전체페이지를 넘을 수 없다.
		page.setEndpage(endpage);
				
		return sdao.selectList(page);
	}

	//스크랩삭제
	@Transactional
	@Override
	public String delete(List<Integer> delSnum) {
		String msg = "";
		int cnt = 0;
		for(int i = 0; i<delSnum.size(); i++)
		{
			cnt += sdao.delete(delSnum.get(i));
		}
		
		if(cnt>0)
		{
			msg = cnt + "건 삭제완료";
		}
		else
		{
			msg = "삭제 실패";
		}
		return msg;
	}

}
