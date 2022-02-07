<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../include/jusoincludefile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<script type="text/x-handlebars-template" id="template">
		<table border="1">
		<tr>
			<th>순번</th>
			<th>제목</th>
		</tr>
		{{#each .}}
		<tr>
			<th>{{snum}}</th>
			<th><a href="{{link}}">{{title}}</a></th>
		</tr>
		{{/each}}
	</table>
</script>
<script type="text/javascript">
	$(function () {
		//수정
		$('#modibt1').on('click',function(){
			var newpasswd = $('#newpasswd').val();
			var Renewpasswd = $('#Renewpasswd').val();
			var birthyear = $('#birthyear').val();
			
			//변경패스워드, 패스워드재확인 일치여부
			if(newpasswd != '' && Renewpasswd != '')
			{
				//불일치
				if(newpasswd != Renewpasswd)
				{
					alert('입력하신 비밀번호가 일치하지 않습니다.');
					$('#newpasswd').val('');
					$('#Renewpasswd').val('');
					$('#newpasswd').focus();
					return;
				}
			} 
			
			if(birthyear == '')
			{
				alert('태어난년도를 입력해주세요.');
				$('#birthyear').focus();
			}
			else
			{
				$('#frmModi').attr('action','${path}/member/modify?email=${email}').submit();	
			}
		});
		
		//탈퇴
		$('#delbt1').on('click',function(){
			var result = confirm('정말 탈퇴하시겠습니까?');
			
			if(result)
			{
				location.href='${path}/member/delete?email=${email}';
			}
		});
		//스크랩정보가져오기
		$('#showScrapBt1').on('click',function(){
			console.log('연결');
			$.ajax({
				url : '${path}/scrap/list',
				type : 'get',
				contentType: "application/json; charset=utf-8",
				dataType : 'json',
				success : function (data) {
					//소스불러오기
					var source = $('#template').html();
					//컴파일
					var template = Handlebars.compile(source);
					//데이터적용
					var html = template(data);
					//div 표시
					$('#showScrapList').html(html);
				},
				error : function () {
					console.log('error');
				}
			});
		});
	});
</script>
</head>
<body>
	<form class="detailForm" name="frmJoinAndModi" id="frmModi" action="" method="post" enctype="multipart/form-data">
		<h2>회원정보</h2>
		<div class="textForm" style="text-align: left; font-weight:bold;">
			이메일 - ${member.email}
		</div>
		<c:if test="${simplejoin == '0'}">
			<div class="textForm" >
				<input type="password" class="id" name="newpasswd" id="newpasswd" placeholder="변경패스워드">
			</div>
			<div class="textForm">
				<input type="password" class="id" name="Renewwpasswd" id="Renewpasswd" placeholder="변경패스워드재확인">
			</div>
			<div class="textForm">
				<input type="text" class="id" name="zipcode" size="2" readonly id="zipcode" value="${member.zipcode }">
				<button type="button" onclick="goPopup()" id="zipSearch">우편검색</button>
			</div>
			<div class="textForm">
				<input type="text" class="id" name="addr" size="25" readonly id="addr" value="${member.addr }">
			</div>
			<div class="textForm">
				<input type="text" class="id" name="addrdetail" readonly id="addrdetail" value="${member.addrdetail }">
			</div>
		</c:if>
			<div class="textForm">
				<input type="number" class="id" name="birthyear" id="birthyear" value="${member.birthyear}">
			</div>
			<label class="test_obj">
				<input type="radio" name="gender" value="M" <c:out value="${member.gender == 'M'? 'checked':'' }"/>> 
				<span>남</span>
			</label>
			<label class="test_obj">
				<input type="radio" name="gender" value="W" <c:out value="${member.gender == 'W'? 'checked':'' }"/>>
				<span>여</span>
			</label>
		<c:if test="${simplejoin == '0'}">
			<div class="filebox">
				<img alt="profile" src="${path}/saveImges/${member.profilename }" class="img-circle" width="40" height="40">
				<hr>
				<label for="file">프로필사진</label> 
    			<input class="upload-name" value="${member.profilename }" placeholder="프로필사진" readonly size="30">
    			<input type="file" id="file" name="profile">
			</div>
		</c:if>
			<div class="textForm" style="text-align: left; font-weight:bold;">
				나이 - ${member.age} 세
			</div>
			<div class="textForm" style="text-align: left; font-weight:bold;">
				본인확인 - 본인인증완료
				<c:if test="${simplejoin == '0'}">
						<c:out value="${member.emailauth == '1'? ', 이메일인증완료':'이메일미인증'}"/> 
				</c:if>
			</div>
			<div class="textForm" style="text-align: left; font-weight:bold;">
				가입일자 - <fmt:formatDate value="${member.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/> 
			</div>
		<c:if test="${simplejoin == '0'}">
			<button type="button" id="modibt1" class="btn btn-default">정보수정</button>
		</c:if>
			<button type="button" id="delbt1" class="btn btn-default">회원탈퇴</button>
	</form>
	<div id="showScrapList"></div>
</body>
</html>