package com.my.web.dto;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Member {
	private String email; //이메일
	private String passwd; //패스워드
	private String emailauth="0"; //이메일인증여부 '1':인증, '0':미인증
	private String simplejoin="0"; //간편가입여부 '1':간편가입, '0':간편미가입
	private String zipcode; //우편번호
	private String addr; //도로명주소
	private String addrdetail; //상세주소
	private String gender; //성별
	private int birthyear; //태어난년도
	private int age; //나이
	private String profilename; //프로필사진이름
	private Date regdate; //가입일자
	private MultipartFile profile; //업로드파일
	
	public Member() {
		super();
	}

	public Member(String email, String passwd, String emailauth, String simplejoin, String zipcode, String addr,
			String addrdetail, String gender, int birthyear, int age, String profilename, Date regdate,
			MultipartFile profile) {
		super();
		this.email = email;
		this.passwd = passwd;
		this.emailauth = emailauth;
		this.simplejoin = simplejoin;
		this.zipcode = zipcode;
		this.addr = addr;
		this.addrdetail = addrdetail;
		this.gender = gender;
		this.birthyear = birthyear;
		this.age = age;
		this.profilename = profilename;
		this.regdate = regdate;
		this.profile = profile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmailauth() {
		return emailauth;
	}

	public void setEmailauth(String emailauth) {
		this.emailauth = emailauth;
	}

	public String getSimplejoin() {
		return simplejoin;
	}

	public void setSimplejoin(String simplejoin) {
		this.simplejoin = simplejoin;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddrdetail() {
		return addrdetail;
	}

	public void setAddrdetail(String addrdetail) {
		this.addrdetail = addrdetail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(int birthyear) {
		this.birthyear = birthyear;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getProfilename() {
		return profilename;
	}

	public void setProfilename(String profilename) {
		this.profilename = profilename;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public MultipartFile getProfile() {
		return profile;
	}

	public void setProfile(MultipartFile profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "Member [email=" + email + ", passwd=" + passwd + ", emailauth=" + emailauth + ", simplejoin="
				+ simplejoin + ", zipcode=" + zipcode + ", addr=" + addr + ", addrdetail=" + addrdetail + ", gender="
				+ gender + ", birthyear=" + birthyear + ", age=" + age + ", profilename=" + profilename + ", regdate="
				+ regdate + ", profile=" + profile + "]";
	}
	
	

	
}
