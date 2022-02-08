package com.my.web.service;

import java.util.Random;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSendServiceImpl implements MailSendService {
	@Autowired
	private JavaMailSender ms;

	// 인증키 생성코드
	private String getAuthCode() {
		StringBuffer sb = new StringBuffer();
		Random rd = new Random();
		for (int i = 0; i < 6; i++) {
			sb.append(rd.nextInt(10)); // 0~9
		}
		return sb.toString();
	}

	@Override
	public String sendAuthMail(String email) throws Exception {
		String authCode = getAuthCode();

		// 보낼이메일내용
		StringBuffer content = new StringBuffer();
		content.append("가입 감사합니다. 아래 링크를 클릭해주세요. <br>");

		// window 콜백주소
		// content.append("<a
		// href='http://localhost:8081/web/member/joinForm?authCode="+authCode+"&email="+email+"'>이메일인증확인</a>");
		// 서버 콜백주소
		content.append("<a href='http://49.50.174.40:8080/newsPage/member/joinForm?authCode=" + authCode + "&email="
				+ email + "'>이메일인증확인</a>");

		// 메일객체생성
		MimeMessage mmsg = ms.createMimeMessage();
		mmsg.setSubject("회원가입 이메일 인증", "utf-8");
		mmsg.setText(content.toString(), "utf-8", "html");
		mmsg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

		// 메일보내기
		ms.send(mmsg);
		return authCode;
	}

}
