package com.my.web.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Scrap {
	private int snum;
	private String email;
	private String title;
	private String description;
	private String link;
	private Date regdate;
	
	public Scrap() {
		super();
	}

	public Scrap(int snum, String email, String title, String description, String link, Date regdate) {
		super();
		this.snum = snum;
		this.email = email;
		this.title = title;
		this.description = description;
		this.link = link;
		this.regdate = regdate;
	}

	public int getSnum() {
		return snum;
	}

	public void setSnum(int snum) {
		this.snum = snum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "Scrap [snum=" + snum + ", email=" + email + ", title=" + title + ", description=" + description
				+ ", link=" + link + ", regdate=" + regdate + "]";
	}
	
	
	
	
}
