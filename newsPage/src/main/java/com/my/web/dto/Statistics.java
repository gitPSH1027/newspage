package com.my.web.dto;

import java.util.Date;

public class Statistics {
	private String email;
	private String gender;
	private int age;
	private String findvalue;
	private Date regdate;
	
	public Statistics() {
		super();
	}

	public Statistics(String email, String gender, int age, String findvalue, Date regdate) {
		super();
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.findvalue = findvalue;
		this.regdate = regdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFindvalue() {
		return findvalue;
	}

	public void setFindvalue(String findvalue) {
		this.findvalue = findvalue;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "Statistics [email=" + email + ", gender=" + gender + ", age=" + age + ", findvalue=" + findvalue
				+ ", regdate=" + regdate + "]";
	}

	
}
