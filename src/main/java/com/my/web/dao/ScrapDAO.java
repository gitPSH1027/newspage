package com.my.web.dao;

import java.util.List;

import com.my.web.dto.Page;
import com.my.web.dto.Scrap;

public interface ScrapDAO {
	int insert(Scrap scrap); //스크랩추가

	List<Scrap> selectList(Page page); //스크랩리스트

	int delete(int snum); //스크랩삭제

	int totcnt(String email); //전체 게시물수
}
