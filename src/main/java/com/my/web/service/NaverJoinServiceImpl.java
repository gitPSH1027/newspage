package com.my.web.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.web.dao.MemberDAO;
import com.my.web.dto.Member;

@Service
public class NaverJoinServiceImpl implements NaverJoinService{
	@Autowired
	private MemberDAO mdao;
	
	
	//네이버 로그인버튼의 url생성
	@Override
	public Map<String, String> getApiURl() throws Exception {
		String clientId = "pCVhGY6wiG_Tg89tlZGE";//애플리케이션 클라이언트 아이디값";
	    String redirectURI = URLEncoder.encode("http://localhost:8081/web/naver_callback", "UTF-8");
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&state=" + state;
	    
	    Map<String, String> result = new HashMap<String, String>();
	    result.put("apiURL", apiURL);
	    result.put("state",state);
		return result;
	}

	
	//토큰얻기
	public String getToken(Map<String, String> map) throws Exception {
		String clientId = "pCVhGY6wiG_Tg89tlZGE";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "ij1oIzCiF0";//애플리케이션 클라이언트 시크릿값";
	    String code = map.get("code");
	    String state = map.get("state");
	    String redirectURI = URLEncoder.encode("http://localhost:8081/web/naver_callback", "UTF-8");
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    String access_token = "";
	    String refresh_token = "";
	    System.out.println("apiURL="+apiURL);
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.print("responseCode="+responseCode);
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode==200) {
	        System.out.println(res.toString());
	        //JSON파싱
	        JSONObject jb = (JSONObject) new JSONParser().parse(res.toString());
	        access_token = (String) jb.get("access_token");
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }
		return access_token;
	}
	
	//개인정보얻기
	public Map<String, Object> getUserInfo(String access_token) throws Exception
	{
		 //String token = access_token; // 네이버 로그인 접근 토큰;
	     String header = "Bearer " + access_token; // Bearer 다음에 공백 추가


	     String apiURL = "https://openapi.naver.com/v1/nid/me";


	     Map<String, String> requestHeaders = new HashMap<>();
	     requestHeaders.put("Authorization", header);
	     System.out.println("sys: "+requestHeaders);
	     String responseBody = get(apiURL,requestHeaders);
	     //JSON파싱
	     JSONObject jb = (JSONObject) new JSONParser().parse(responseBody);
	     jb = (JSONObject) jb.get("response");
	     String email = (String) jb.get("email");
	     String gender = (String) jb.get("gender");
	     int birthyear = Integer.parseInt((String) jb.get("birthyear")) ;
	     
	     Map<String, Object> result = new HashMap<String, Object>();
	     result.put("email", email);
	     result.put("gender", gender);
	     result.put("birthyear", birthyear);
	     
	     //System.out.println(responseBody);
		return result;
	}
	
	private String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
	
	//토큰얻고, 개인정보얻기
	@Override
	public Map<String, Object> getTokenUserInfo(Map<String, String> map) throws Exception {
		//토큰을 얻고 + 개인정보 얻기 
		String access_token = getToken(map);
		Map<String, Object> result = getUserInfo(access_token);
		return result;
	}

	@Transactional
	//네이버가입
	@Override
	public Map<String, Object> insert(Map<String, Object> result) {
		LocalDate now = LocalDate.now(); //현재년도구하기
		int year = now.getYear();//현재년도

		String email = (String) result.get("email");
		String gender = (String) result.get("gender");
		int birthyear = (int) result.get("birthyear");
		//네이버 간편가입완료 0
		//이미 간편가입된 회원 1
		//일반가입회원 2
		Map<String, Object> insertMap = new HashMap<String, Object>();
		
		//회원조회
		Member dbmember = mdao.seleteOne(email);
		
		//회원가입된 회원
		if(dbmember != null)
		{
			//일반회원
			if(dbmember.getSimplejoin().equals("0"))
			{
				insertMap.put("code", 2);
				insertMap.put("msg", "일반가입회원입니다.");
			}
			//간편가입회원
			else
			{
				insertMap.put("code", 1);
				insertMap.put("msg", "네이버로그인성공");
			}
			return insertMap;
		}
		
		//미가입 이메일경우
		Member member = new Member();
		member.setEmail(email);
		member.setPasswd("");
		member.setAddr("");
		member.setAddrdetail("");
		member.setZipcode("");
		member.setBirthyear(birthyear);
		member.setAge(year-birthyear);
		member.setGender(gender);
		member.setProfilename("");
		member.setSimplejoin("1");
		System.out.println("member: "+member);
		mdao.insert(member);
		insertMap.put("code", 0);
		insertMap.put("msg", "네이버간편가입완료");
		return insertMap;
	}
	
	
	

	
	 
}
