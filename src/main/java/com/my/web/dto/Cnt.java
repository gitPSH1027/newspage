package com.my.web.dto;

public class Cnt {
	private String findvalue;
	private int cnt;
	
	public Cnt() {
		super();
	}

	public Cnt(String findvalue, int cnt) {
		super();
		this.findvalue = findvalue;
		this.cnt = cnt;
	}

	public String getFindvalue() {
		return findvalue;
	}

	public void setFindvalue(String findvalue) {
		this.findvalue = findvalue;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "Cnt [findvalue=" + findvalue + ", cnt=" + cnt + "]";
	}
	
	
}
