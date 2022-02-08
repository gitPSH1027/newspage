package com.my.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.web.dao.MemberDAO;
import com.my.web.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private FileService fs; // 파일업로드
	@Autowired
	private BCryptPasswordEncoder bCpasswd; // 패스워드 암호화
	@Autowired
	private MemberDAO mdao; // Member dao
	@Autowired
	private MailSendService ms; // 메일 전송

	// 아이디 중복체크 //ajax 비동기 방식으로 구현
	@Transactional
	@Override
	public String emailCheck(String email) {
		String joinChk = null;
		// db비교
		Member dbmember = mdao.seleteOne(email);
		// "1":가입회원, "0":미가입회원
		// 회원이 아니라면
		if (dbmember == null)
			joinChk = "0";
		else
			joinChk = "1";
		return joinChk;
	}

	@Transactional
	@Override
	public Map<String, Object> insert(Member member) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = null; // 결과 메세지
		Boolean check = false; // 성공 실패여부
		String authCode = null; // 이메일 인증코드

		// pk체크
		Member dbmember = mdao.seleteOne(member.getEmail());
		// 회원이 없으면 추가
		if (dbmember == null) {
			// 파일업로드
			String profilename = fs.MemberfileUpload(member.getProfile());
			member.setProfilename(profilename);
			// 비밀번호 암호화
			String passwd = bCpasswd.encode(member.getPasswd());
			member.setPasswd(passwd);

			// db저장
			int cnt = mdao.insert(member);
			if (cnt > 0) {
				msg = member.getEmail() + " 님 회원가입완료";
				check = true;
				// 메일전송
				authCode = ms.sendAuthMail(member.getEmail());
			} else {
				msg = "중복회원입니다.";
				check = false;
			}
		}
		result.put("msg", msg);
		result.put("check", check);
		result.put("authCode", authCode);
		return result;
	}

	@Transactional
	@Override
	public Map<String, Object> delete(String email) {
		Map<String, Object> result = new HashMap<String, Object>();
		String msg;
		boolean check;
		int cnt = mdao.delete(email);

		if (cnt > 0) {
			msg = "회원탈퇴가 완료되었습니다.";
			check = true;
		} else {
			msg = "회원탈퇴 오류입니다.";
			check = false;
		}
		result.put("msg", msg);
		result.put("check", check);
		return result;
	}

	@Override
	public Member selectOne(String email) {
		return mdao.seleteOne(email);
	}

	@Transactional
	// 이메일인증 업데이트
	@Override
	public String UpdateAuthEmail(String email) {
		String msg = null;
		int cnt = mdao.UpdateAuthEmail(email);
		if (cnt > 0) {
			msg = "이메일 인증완료";
		} else {
			msg = "이메일 인증실패..";
		}
		return msg;
	}

	@Transactional
	// 본인환인 패스워드 일치여부 판단
	@Override
	public Map<String, Object> pwChk(String email, String passwd) {
		Map<String, Object> result = new HashMap<String, Object>();
		Member dbmember = mdao.seleteOne(email);
		boolean match = bCpasswd.matches(passwd, dbmember.getPasswd());

		// 패스워드 불일치
		if (!match) {
			result.put("msg", "본인확인에 실패했습니다.");
			result.put("check", false);
			return result;
		}

		// 일치
		result.put("msg", "본인확인 성공");
		result.put("check", true);

		return result;
	}

	@Transactional
	// 회원정보수정
	@Override
	public Map<String, Object> update(Member member, String newpasswd, String email) throws Exception {
		Member dbmember = mdao.seleteOne(email);

		// 변경비밀번호가 공백이 아닐경우
		if (!newpasswd.equals("")) {
			// 패스워드 암호화 저장
			String passwd = bCpasswd.encode(newpasswd);
			member.setPasswd(passwd);
		}
		// 공백일경우 기존비밀번호유지
		else {
			member.setPasswd(dbmember.getPasswd());
		}

		// 파일처리
		String profilename = fs.MemberfileUpload(member.getProfile());

		if (!profilename.equals(""))// 파일변경할경우
		{
			member.setProfilename(profilename);
		} else {
			member.setProfilename(dbmember.getProfilename());
		}

		Map<String, Object> result = new HashMap<String, Object>();

		int cnt = mdao.update(member);
		if (cnt > 0) {
			result.put("msg", "회원정보수정완료");
			result.put("check", true);
		} else {
			result.put("msg", "회원정보수정오류");
			result.put("check", false);
		}
		return result;
	}

}
